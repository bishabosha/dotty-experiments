package traits

trait T {
  case class X[A]()
}

object a extends T
object b extends T

object TraitTest {
  def main(args: Array[String]): Unit = {
    val ax = a.X()
    val bx = b.X()
    println(ax == bx)
  }
}
