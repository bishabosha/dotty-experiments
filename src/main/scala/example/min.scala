package example

import example.Ordered._
import Ordered1._
import Ordering._

object min {

  def (value1: A) min[A: Equatable : Ordered](value2: A): A =
    impl(value1, value2) { _ compare _ }

  def (value1: F[A]) min[F[_]: Ordered1, A: Equatable: Ordered](value2: F[A]): F[A] =
    impl(value1, value2) { _ compare _ }

  private inline def impl[T](value1: T, value2: T)(f: (T, T) => Ordering) = f(value1, value2) match {
    case LT | EQ => value1
    case _ => value2
  }
}