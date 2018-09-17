package example

object minList {
  def apply[A](vals: A*): implicit (Equatable[A], Ordered[A]) => A =
    vals.reduce(min(_, _))

  def apply[F[_], A](vals: F[A]*): implicit (Equatable[A], Ordered[A], Ordered1[F]) => F[A] =
    vals.reduce(min(_, _))
}