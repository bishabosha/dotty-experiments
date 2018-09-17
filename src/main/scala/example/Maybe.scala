package example

import Equatable._
import Ordered._
import Maybe._;
import Ordering._;

enum Maybe[+A] {
  case Just(value: A)
  case Nothing
}

object MaybeOps {

  implicit object Equatable1 extends Equatable1[Maybe] {
    override def liftEqual[A](m1: Maybe[A], m2: Maybe[A]) = (m1, m2) match {
      case (Just(a), Just(b)) => equal(a, b) 
      case (Nothing, Nothing) => true
      case _                  => false
    }
  }

  implicit object Ordered1 extends Ordered1[Maybe] {
    override def liftCompare[A](m1: Maybe[A], m2: Maybe[A]) = (m1, m2) match {
      case (Just(a), Just(b)) => compare(a, b)
      case (Nothing, Nothing) => EQ
      case (Just(_), Nothing) => GT
      case _                  => LT
    }
  }
}