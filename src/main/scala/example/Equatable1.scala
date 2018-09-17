package example

object Equatable1 {
  def equal1[F[_], A](fa1: F[A], fa2: F[A]): implicit (Equatable1[F], Equatable[A]) => Boolean =
    implicitly[Equatable1[F]].equal1(fa1, fa2)
}

trait Equatable1[F[_]] {
  def equal1[A](fa1: F[A], fa2: F[A]): implicit Equatable[A] => Boolean
}