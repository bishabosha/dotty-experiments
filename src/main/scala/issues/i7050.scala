package issues

import scala.deriving._

case class CaseClass(x: Int, y: String)

inline def locally[T](body: => T): T = body

def weird = {
  val m = the[Mirror.ProductOf[CaseClass]]
  val mRes = the[Mirror.ProductOf[(Int, String)]]
  type R = m.MirroredElemTypes
  locally {
    given mt as Mirror.ProductOf[R] = mRes
  }
}