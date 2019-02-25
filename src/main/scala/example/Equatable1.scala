package example

trait Equatable1[F[_]] {
  def (fa1: F[A]) equal[A: Equatable](fa2: F[A]): Boolean
}

object Equatable1 {
  import Safety._
  import Maybe._
  import Equatable._

  implied for Equatable1[Maybe] {
    def (m1: Maybe[A]) equal[A: Equatable](m2: Maybe[A]) =
      m1.fold(m2.fold(true)(_ => false))(x => m2.fold(false)(x equal _))
  }
}