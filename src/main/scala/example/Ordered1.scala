package example

trait Ordered1[F[_]: Equatable1] {
  def (fa1: F[A]) compare[A: Equatable: Ordered](fa2: F[A]): Ordering
}

object Ordered1 {
  import Ordering._

  implicit val Ordered1Maybe: Ordered1[Maybe] = new {
    import Maybe._

    override def (m1: Maybe[A]) compare[A: Equatable: Ordered](m2: Maybe[A]) =
      m1 match {
        case Just(a) => m2 match {
          case Just(b) => a compare b
          case Nothing => GT
        }
        case Nothing => m2 match {
          case Just(_) => LT
          case Nothing => EQ
        }
      }
  }
}
