package example

object minList {
  def apply[A: Equatable: Ordered](vals: A*): A =
    vals.reduce(min(_, _))

  def apply[F[_]: Ordered1, A: Equatable: Ordered](vals: F[A]*): F[A] =
    vals.reduce(min(_, _))
}