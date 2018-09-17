package example

object Ordered1 {
  type Derive =
    [F[_], A, B] => implicit (Equatable[A], Ordered[A], Ordered1[F]) => B

  implicit def liftCompare[F[_], A]: (F[A], F[A]) => Derive[F, A, Ordering] =
    implicitly[Ordered1[F]].liftCompare(_, _)
}

trait Ordered1[F[_]] {
  def liftCompare[A](ord1: F[A], ord2: F[A]): implicit (Ordered[A], Equatable[A]) => Ordering
}