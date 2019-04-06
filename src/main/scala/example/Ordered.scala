package example

trait Ordered[A] extends Equatable[A] {
  def (a1: A) `rel` (a2: A): Int
  inline def (a1: A) <  (a2: A)  = (a1 `rel` a2) <  0
  inline def (a1: A) <= (a2: A)  = (a1 `rel` a2) <= 0
  inline def (a1: A) >  (a2: A)  = (a1 `rel` a2) >  0
  inline def (a1: A) >= (a2: A)  = (a1 `rel` a2) >= 0
  inline def (a1: A) <> (a2: A)  = (a1 `rel` a2) == 0
}

object Ordered {
  import Safety._
  import Equatable._

  implied for Ordered[Int], EquatableInt {
    def (value1: Int) `rel` (value2: Int) = value2 - value1
  }

  implied [A] given Ordered[A] for Ordered[Maybe[A]], EquatableMaybe[A] {
    import Maybe._
    def (m1: Maybe[A]) `rel` (m2: Maybe[A]): Int =
      m1.fold(m2.fold(0)(_ => -1))(x => m2.fold(1)(x `rel` _))
  }
}