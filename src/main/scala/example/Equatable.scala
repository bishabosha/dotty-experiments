package example

trait Equatable[A] {
  def (a1: A) equal (a2: A): Boolean
}

object Equatable {
  implied for Equatable[Int] {
    def (i1: Int) equal (i2: Int): Boolean = i1 == i2
  }
}