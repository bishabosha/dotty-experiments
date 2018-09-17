package example

trait Applicative[F[_]] {
  def pure[A](a: A): F[A]
  def <*>[A, B](fa: F[A], fab: F[A => B]): implicit Functor[F] => F[B]
}