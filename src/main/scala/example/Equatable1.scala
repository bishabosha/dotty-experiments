package example

trait Equatable1[F[_]] {
  def (fa1: F[A]) equal[A: Equatable](fa2: F[A]): Boolean
}

object Equatable1 {
  import Maybe._
  import Equatable._

  implicit val Equatable1Maybe: Equatable1[Maybe] = new {
    override def (m1: Maybe[A]) equal[A: Equatable](m2: Maybe[A]) =
      m1 match {
        case Just(a) => m2 match {
          case Just(b) => a equal b
          case Nothing => false
        }
        case Nothing => Nothing == m2
      }
  }
}