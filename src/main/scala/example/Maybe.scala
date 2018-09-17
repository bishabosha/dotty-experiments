package example

enum Maybe[+A] {
  case Just(value: A)
  case Nothing
}

object MaybeOps {
  import Maybe._;

  import Equatable._
  implicit object Equatable1 extends Equatable1[Maybe] {
    override def equal1[A](m1: Maybe[A], m2: Maybe[A]) =
      m1 match {
        case Just(a) => m2 match {
          case Just(b) => equal(a, b)
          case Nothing => false
        }
        case Nothing => Nothing == m2
      }
  }

  import Ordered._
  import Ordering._;
  implicit object Ordered1 extends Ordered1[Maybe] {
    override def compare1[A](m1: Maybe[A], m2: Maybe[A]) =
      m1 match {
        case Just(a) => m2 match {
          case Just(b) => compare(a, b)
          case Nothing => GT
        }
        case Nothing => m2 match {
          case Nothing => EQ
          case Just(_) => LT
        }
      }
  }

  implicit object Functor extends Functor[Maybe] {
    override def map[A, B](fa: Maybe[A])(f: A => B) =
      fa match {
        case Just(a) => Just(f(a))
        case Nothing => Nothing
      }
  }

  import Functor._
  implicit object Applicative extends Applicative[Maybe] {
    override def pure[A](a: A): Maybe[A] =
      Just(a)

    override def <*>[A, B](fa: Maybe[A], fab: Maybe[A => B]) =
      fab match {
        case Just(f) => map(fa)(f)
        case Nothing => Nothing
      }
  }
}