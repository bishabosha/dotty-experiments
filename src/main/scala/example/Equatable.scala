package example

trait Equatable[A] {
  def (a1: A) equal (a2: A): Boolean
}

object Equatable {
  implicit val EquatableInt: Equatable[Int] = new {
    override def (i1: Int) equal (i2: Int): Boolean = i1 == i2
  }
}