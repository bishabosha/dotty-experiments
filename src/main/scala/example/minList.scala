package example

object minList {
  def apply[A](vals: A*): Ordered.Derive[A, A] =
    vals.reduce(min(_, _))

  def apply[F[_], A](vals: F[A]*): Ordered1.Derive[F, A, F[A]] =
    vals.reduce(min(_, _))
}