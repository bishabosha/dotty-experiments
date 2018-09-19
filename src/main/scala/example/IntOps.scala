package example

import Ordering._
import Equatable._

object IntOps {
  implicit object EquatableInt extends Equatable[Int] {
    override def equal(i1: Int, i2: Int): Boolean = i1 == i2
  }

  implicit object Ordered extends Ordered[Int]() {
    override def compare(value1: Int, value2: Int) =
      if (equal(value1, value2)) EQ else if (value1 < value2) LT else GT
  }
}