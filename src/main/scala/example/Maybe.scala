package example

import Equatable._
import Maybe._
import Functor._
import FunctorSyntax._
import Ordered._
import Ordering._

enum Maybe[+A] {
  case Just(value: A)
  case Nothing
}

object MaybeOps {
  implicit object Equatable1 extends Equatable1[Maybe] {
    override def equal1[A: Equatable](m1: Maybe[A], m2: Maybe[A]) =
      m1 match {
        case Just(a) => m2 match {
          case Just(b) => equal(a, b)
          case Nothing => false
        }
        case Nothing => Nothing == m2
      }
  }

  implicit object Ordered1 extends Ordered1[Maybe]() {
    override def compare1[A: Equatable: Ordered](m1: Maybe[A], m2: Maybe[A]) =
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

  implicit object Applicative extends Applicative[Maybe]() {
    override def pure[A](a: A): Maybe[A] =
      Just(a)

    override def <*>[A, B](ma: Maybe[A])(mf: Maybe[A => B]) =
      mf match {
        case Just(f) => ma map f
        case Nothing => Nothing
      }
  }

  implicit object Monad extends Monad[Maybe]() {
    override def >>=[A, B](ma: Maybe[A])(f: A => Maybe[B]) =
      ma match {
        case Just(a) => f(a)
        case Nothing => Nothing
      }
  }
}