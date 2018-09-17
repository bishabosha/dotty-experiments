package example

object Ordered1 {
  def compare1[F[_], A](fa1: F[A], fa2: F[A]): implicit (Equatable[A], Ordered[A], Ordered1[F]) => Ordering =
    implicitly[Ordered1[F]].compare1(fa1, fa2)
}

trait Ordered1[F[_]] {
  def compare1[A](fa1: F[A], fa2: F[A]): implicit (Ordered[A], Equatable[A]) => Ordering
}