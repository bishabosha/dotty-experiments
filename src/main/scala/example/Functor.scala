package example

trait Functor[F[_]] {
  def (fa: F[A]) fmap[A, B](f: A => B): F[B]
}

object Functor {

  import Safety._

  implied for Functor[Maybe] {
    import Maybe._
    def (fa: Maybe[A]) fmap[A, B](f: A => B) =
      fa.fold(zero)(a => Maybe(f(a)))
  }
}

import Functor._
object FunctorSyntax {
  inline def (fa: F[A]) map[F[_]: Functor, A, B](f: A => B): F[B] = fa fmap f
  inline def (fa: F[A]) foreach[F[_]: Functor, A](f: A => Unit): Unit = fa fmap f
}