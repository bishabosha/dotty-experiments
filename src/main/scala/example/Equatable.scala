package example

trait Equatable[A] {
  def (a1: A) equal (a2: A): Boolean
}

object Equatable {
  import Safety._

  trait EquatableInt extends Equatable[Int] {
    def (x: Int) `equal` (y: Int) = x == y
  }

  trait EquatableMaybe[A: Equatable] extends Equatable[Maybe[A]] {
    import Maybe._
    def (m1: Maybe[A]) `equal` (m2: Maybe[A]): Boolean =
      m1.fold(m2.fold(true)(_ => false))(x => m2.fold(false)(x `equal` _))
  }

  implied for Equatable[Int], EquatableInt
  implied [A] given Equatable[A] for Equatable[Maybe[A]], EquatableMaybe[A]
}