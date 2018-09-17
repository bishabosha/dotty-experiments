package example

import example.Ordered._
import Ordered1._
import Ordering._

object min {
  def apply[A](value1: A, value2: A): implicit (Equatable[A], Ordered[A]) => A =
    impl(value1, value2) { compare(_, _) }

  def apply[F[_], A](value1: F[A], value2: F[A]): implicit (Equatable[A], Ordered[A], Ordered1[F]) => F[A] =
    impl(value1, value2) { compare1(_, _) }

  def impl[T](value1: T, value2: T)(f: (T, T) => Ordering) = f(value1, value2) match {
    case LT | EQ => value1
    case _ => value2
  }
}