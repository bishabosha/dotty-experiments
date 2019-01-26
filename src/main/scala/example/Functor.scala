package example

trait Functor[F[_]] {
  def (fa: F[A]) fmap[A, B](f: A => B): F[B]
}

object Functor {

  implicit val MaybeFunctor: Functor[Maybe] = new {
    import Maybe._
    override def (fa: Maybe[A]) fmap[A, B](f: A => B) = fa match {
      case Just(a) => Just(f(a))
      case Nothing => Nothing
    }
  }
}

import Functor._
object FunctorSyntax {
  def (fa: F[A]) map[F[_]: Functor, A, B](f: A => B): F[B] = fa fmap f
  def (fa: F[A]) foreach[F[_]: Functor, A](f: A => Unit): Unit = fa fmap f
}