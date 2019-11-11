package issues

import scala.deriving._

case class CaseClass(x: Int, y: String)

inline def locally[T](body: => T): T = body

def weird = {
  val m = summon[Mirror.ProductOf[CaseClass]]
  val mRes = summon[Mirror.ProductOf[(Int, String)]]
  type R = m.MirroredElemTypes
  locally {
    given mt: Mirror.ProductOf[R] = mRes
  }
}
