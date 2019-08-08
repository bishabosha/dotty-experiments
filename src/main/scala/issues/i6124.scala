/** look in https://github.com/lampepfl/dotty/issues/6124
 */
package issues

type Box[A] = A

trait Foo[A]

given [A] as Foo[A]

object OkTest {
  trait Ok[A: Foo] extends Foo[Box[A]] {}
  given [A] as Ok[A] given Foo[A] 

  def test: Unit = {
    println(the[Ok[Box[Unit]]])
  }
}

object OkTest2 {
  trait Ok[A] given (f: Foo[A]) extends Foo[Box[A]] {}
  given [A] as Ok[A] given Foo[A]

  def test: Unit = {
    println(the[Ok[Box[Unit]]])
  }
}

object BadTest {
  trait Bad[A] given Foo[A] extends Foo[Box[A]] {}
  given [A] as Bad[A] given Foo[A]

  def test: Unit = {
    println(the[Bad[Box[Unit]]])
  }
}