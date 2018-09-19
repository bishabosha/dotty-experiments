package example

object Ordered1 {
  def apply[F[_]: Ordered1] =
    implicitly

  def compare1[F[_]: Ordered1, A: Equatable: Ordered](fa1: F[A], fa2: F[A]): Ordering =
    Ordered1[F] compare1 (fa1, fa2)
}

trait Ordered1[F[_]: Equatable1] {
  def compare1[A: Equatable: Ordered](fa1: F[A], fa2: F[A]): Ordering
}