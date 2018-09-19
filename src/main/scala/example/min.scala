package example

import example.Ordered._
import Ordered1._
import Ordering._

object min {
  def apply[A: Equatable : Ordered](value1: A, value2: A): A =
    impl(value1, value2) { compare(_, _) }

  def apply[F[_]: Ordered1, A: Equatable: Ordered](value1: F[A], value2: F[A]): F[A] =
    impl(value1, value2) { compare1(_, _) }

  private def impl[T](value1: T, value2: T)(f: (T, T) => Ordering) = f(value1, value2) match {
    case LT | EQ => value1
    case _ => value2
  }
}