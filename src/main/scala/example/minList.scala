package example
import min._

object minList {
  def apply[A: Equatable: Ordered](vals: A*): A =
    vals.reduce(_ min _)

  def apply[F[_]: Ordered1, A: Equatable: Ordered](vals: F[A]*): F[A] =
    vals.reduce(_ min _)
}