package example

trait Ordered[A: Equatable] {
  def (a1: A) compare(a2: A): Ordering
}

enum Ordering {
  case EQ, LT, GT
}

object Ordered {
  import Equatable._
  import Ordering._

  implicit val OrderedInt: Ordered[Int] = new {
    override def (value1: Int) compare(value2: Int) =
      if value1 equal value2 then
        EQ
      else if value1 < value2 then
        LT
      else
        GT
  }
}