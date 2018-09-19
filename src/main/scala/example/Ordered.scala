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