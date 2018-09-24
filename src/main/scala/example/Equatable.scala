package example

object Equatable {
  def apply[A: Equatable] = implicitly
  def equal[A: Equatable](a1: A, a2: A): Boolean = Equatable[A] equal (a1, a2)
}

trait Equatable[A] {
  def equal(a1: A, a2: A): Boolean
}

object Equatables {
  implicit object EquatableInt extends Equatable[Int] {
    override def equal(i1: Int, i2: Int): Boolean = i1 == i2
  }
}