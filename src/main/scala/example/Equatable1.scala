package example

object Equatable1 {
  type Derive[F[_], A, O] = implicit (Equatable1[F], Equatable[A]) => O
  def liftEqual[F[_], A]: (F[A], F[A]) => Derive[F, A, Boolean] = implicitly[Equatable1[F]].liftEqual(_, _)
}

trait Equatable1[F[_]] {
  def liftEqual[A](value1: F[A], value2: F[A]): implicit Equatable[A] => Boolean
}