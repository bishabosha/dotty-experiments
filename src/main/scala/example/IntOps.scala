package example

object IntOps {
  implicit object EquatableInt extends Equatable[Int] {
    override def equal(i1: Int, i2: Int): Boolean = i1 == i2
  }

  import Ordering._;
  implicit object Ordered extends Ordered[Int] {
    override def compare(value1: Int, value2: Int) =
      if (value1 == value2) EQ else if (value1 < value2) LT else GT
  }
}