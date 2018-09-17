package example

object Ordered {
  type Derive =
    [A, B] => implicit (Equatable[A], Ordered[A]) => B

  implicit def compare[A]: (A, A) => Derive[A, Ordering] =
    implicitly[Ordered[A]].compare(_, _)
}

trait Ordered[A] {
  def compare(ord1: A, ord2: A): implicit Equatable[A] => Ordering
}

enum Ordering {
  case EQ, LT, GT
}