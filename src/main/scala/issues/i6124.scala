/** look in https://github.com/lampepfl/dotty/issues/6124
 */
package issues

type Box[A] = A

trait Foo[A]

implied [A] for Foo[A]

object OkTest {
  trait Ok[A: Foo] extends Foo[Box[A]] {}
  implied [A] given Foo[A] for Ok[A]

  def test: Unit = {
    println(the[Ok[Box[Unit]]])
  }
}

object OkTest2 {
  trait Ok[A] given (f: Foo[A]) extends Foo[Box[A]] {}
  implied [A] given Foo[A] for Ok[A]

  def test: Unit = {
    println(the[Ok[Box[Unit]]])
  }
}

object BadTest {
  trait Bad[A] given Foo[A] extends Foo[Box[A]] {}
  implied [A] given Foo[A] for Bad[A]

  def test: Unit = {
    println(the[Bad[Box[Unit]]])
  }
}