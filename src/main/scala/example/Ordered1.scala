package example

trait Ordered1[F[_]: Equatable1] {
  def (fa1: F[A]) compare[A: Equatable: Ordered](fa2: F[A]): Ordering
}

object Ordered1 {

  import Ordering._
  import Safety._

  implied for Ordered1[Maybe] {
    import Maybe._

    def (m1: Maybe[A]) compare[A: Equatable: Ordered](m2: Maybe[A]) =
      m1.fold(m2.fold(EQ)(_ => LT))(x => m2.fold(GT)(x compare _))
  }
}
