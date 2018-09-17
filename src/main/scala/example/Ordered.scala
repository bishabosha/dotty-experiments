package example

object Ordered {
  def compare[A](a1: A, a2: A): implicit (Equatable[A], Ordered[A]) => Ordering =
    implicitly[Ordered[A]].compare(a1, a2)
}

trait Ordered[A] {
  def compare(a1: A, a2: A): implicit Equatable[A] => Ordering
}

enum Ordering {
  case EQ, LT, GT
}