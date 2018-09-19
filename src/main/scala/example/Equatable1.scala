package example

object Equatable1 {
  def apply[F[_]: Equatable1] = implicitly
  def equal1[F[_]: Equatable1, A: Equatable](fa1: F[A], fa2: F[A]): Boolean = Equatable1[F] equal1 (fa1, fa2)
}

trait Equatable1[F[_]] {
  def equal1[A: Equatable](fa1: F[A], fa2: F[A]): Boolean
}