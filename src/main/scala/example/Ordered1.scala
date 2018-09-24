package example

object Ordered1 {
  def apply[F[_]: Ordered1] =
    implicitly

  def compare1[F[_]: Ordered1, A: Equatable: Ordered](fa1: F[A], fa2: F[A]): Ordering =
    Ordered1[F] compare1 (fa1, fa2)
}

trait Ordered1[F[_]: Equatable1] {
  def compare1[A: Equatable: Ordered](fa1: F[A], fa2: F[A]): Ordering
}

import Maybe._
import Ordered._
import Ordering._
import Equatable1s._
object Ordered1s {
  implicit object Ordered1Maybe extends Ordered1[Maybe]() {
    override def compare1[A: Equatable: Ordered](m1: Maybe[A], m2: Maybe[A]) =
      m1 match {
        case Just(a) => m2 match {
          case Just(b) => compare(a, b)
          case Nothing => GT
        }
        case Nothing => m2 match {
          case Nothing => EQ
          case Just(_) => LT
        }
      }
  }
}