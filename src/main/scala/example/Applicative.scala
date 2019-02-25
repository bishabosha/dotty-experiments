package example

trait Applicative[F[_]: Functor] {
  def (f: Applicative.type) pure[A](a: A): F[A]
  def (fa: F[A]) <*>[A, B](fab: F[A => B]): F[B]
}

object Applicative {

  import implied Functor._
  import Safety._

  implied for Applicative[Maybe] {

    import Maybe._

    def (f: Applicative.type) pure[A](a: A) = Maybe(a)

    def (ma: Maybe[A]) <*>[A, B](mf: Maybe[A => B]) =
      mf.fold(zero)(f => ma fmap f)
  }
}