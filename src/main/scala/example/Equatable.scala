package example

object Equatable {
  def equal[A](a1: A, a2: A): implicit Equatable[A] => Boolean =
    implicitly[Equatable[A]].equal(a1, a2)
}

trait Equatable[A] {
  def equal(a1: A, a2: A): Boolean
}