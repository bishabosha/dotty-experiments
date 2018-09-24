package example

object Ordered {
  def apply[A: Ordered] =
    implicitly

  def compare[A: Equatable: Ordered](a1: A, a2: A): Ordering =
    Ordered[A] compare (a1, a2)
}

trait Ordered[A: Equatable] {
  def compare(a1: A, a2: A): Ordering
}

enum Ordering {
  case EQ, LT, GT
}

import Equatable._
import Equatables._
import Ordering._
object Ordereds {
  implicit object OrderedInt extends Ordered[Int]() {
    override def compare(value1: Int, value2: Int) =
      if (equal(value1, value2)) EQ else if (value1 < value2) LT else GT
  }
}