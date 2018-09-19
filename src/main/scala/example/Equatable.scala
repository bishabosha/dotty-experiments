package example

object Equatable {
  def apply[A: Equatable] = implicitly
  def equal[A: Equatable](a1: A, a2: A): Boolean = Equatable[A] equal (a1, a2)
}

trait Equatable[A] {
  def equal(a1: A, a2: A): Boolean
}