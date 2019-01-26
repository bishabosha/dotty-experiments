package example

trait Applicative[F[_]: Functor] {
  def (f: Applicative.type) pure[A](a: A): F[A]
  def (fa: F[A]) <*>[A, B](fab: F[A => B]): F[B]
}

object Applicative {
  import Functor._

  implicit val MaybeApplicative: Applicative[Maybe] = new {
    import Maybe._

    override def (f: Applicative.type) pure[A](a: A): Maybe[A] =
      Just(a)

    override def (ma: Maybe[A]) <*>[A, B](mf: Maybe[A => B]) =
      mf match {
        case Just(f) => ma fmap f
        case Nothing => Nothing
      }
  }
}