package example

object IntOps {
  implicit object EqInt extends Equatable[Int] {
    override def equal(value1: Int, value2: Int): Boolean = value1 == value2
  }

  import Ordering._;
  implicit object OrdInt extends Ordered[Int] {
    override def compare(value1: Int, value2: Int) =
      if (EqInt.equal(value1, value2)) EQ else if (value1 < value2) LT else GT
  }
}