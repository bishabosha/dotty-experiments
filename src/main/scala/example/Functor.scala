package example

object Functor {
  def map[F[_], A, B](fa: F[A])(f: A => B): implicit Functor[F] => F[B] =
    implicitly[Functor[F]].map(fa)(f)
}

trait Functor[F[_]] {
  def map[A, B](fa: F[A])(f: A => B): F[B]
}