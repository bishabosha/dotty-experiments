/** look in https://github.com/lampepfl/dotty/issues/6124
 */
package issues

type Box[A] = A

trait Foo[A]

given [A]: Foo[A]

object OkTest {
  trait Ok[A: Foo] extends Foo[Box[A]] {}
  given [A](given Foo[A]): Ok[A]

  def test: Unit = {
    println(summon[Ok[Box[Unit]]])
  }
}

object OkTest2 {
  trait Ok[A](given f: Foo[A]) extends Foo[Box[A]] {}
  given [A](given Foo[A]): Ok[A]

  def test: Unit = {
    println(summon[Ok[Box[Unit]]])
  }
}

object BadTest {
  trait Bad[A](given Foo[A]) extends Foo[Box[A]] {}
  given [A](given Foo[A]): Bad[A]

  def test: Unit = {
    println(summon[Bad[Box[Unit]]])
  }
}
