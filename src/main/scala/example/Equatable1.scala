package example

object Equatable1 {
  def apply[F[_]: Equatable1] = implicitly
  def equal1[F[_]: Equatable1, A: Equatable](fa1: F[A], fa2: F[A]): Boolean = Equatable1[F] equal1 (fa1, fa2)
}

trait Equatable1[F[_]] {
  def equal1[A: Equatable](fa1: F[A], fa2: F[A]): Boolean
}

import Maybe._
import Equatable._
object Equatable1s {
  implicit object Equatable1Maybe extends Equatable1[Maybe] {
    override def equal1[A: Equatable](m1: Maybe[A], m2: Maybe[A]) =
      m1 match {
        case Just(a) => m2 match {
          case Just(b) => equal(a, b)
          case Nothing => false
        }
        case Nothing => Nothing == m2
      }
  }
}