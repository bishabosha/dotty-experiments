package example

trait Applicative[F[_]] extends Functor[F] {
  def pure[A](a: A): F[A]
  def (fa: F[A]) <*>[A, B] (fab: F[A => B]): F[B]
}

object Applicative {

  import Functor._
  import Safety._

  def pure[A, F[_]](a: A) given Applicative[F]: F[A] =
    the[Applicative[F]].pure(a)

  trait ApplicativeMaybe extends Applicative[Maybe] with FunctorMaybe {
    import Maybe._

    def pure[A](a: A) = Maybe(a)

    def (ma: Maybe[A]) <*> [A, B] (mf: Maybe[A => B]) =
      mf.fold(zero)(f => ma `fmap` f)
  }

  implied for Applicative[Maybe], ApplicativeMaybe
}