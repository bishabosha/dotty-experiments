package tasty

import scala.collection.immutable.ArraySeq
import scala.collection.mutable
import scala.reflect.ClassTag

/**Implements bitfield of up to size 32
 */
trait IntField {
  private[this] var start = true
  private[this] var end   = false
  private[this] var size  = 0

  protected def enterZero(): Int = {
    require(start, "Already entered zero.")
    start = false
    0
  }

  protected def enterFlag(): Int = {
    require(!end && !start, if end then "Already entered final flag." else "Not yet entered zero.")
    val flag = 1 << size
    size += 1
    flag
  } ensuring (size < 32, s"maximum number of flags is 32")

  protected def enterLast(): Int = {
    require(!start && !end, if end then "Already entered final flag." else "Not yet entered zero.")
    end = true
    1 << size
  }

  protected def mapOverMask[A](f: Int => A)(mask: Int): Iterable[A] = {
    require(end, "Uninitialised IntSet")
    val buf = Iterable.newBuilder[A]
    var i = 0
    while (i <= size) {
      val flag = 1 << i
      if ((flag & mask) != 0) {
        buf += f(flag)
      }
      i += 1
    }
    buf.result
  }
}
