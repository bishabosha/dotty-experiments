/**Look in https://github.com/lampepfl/dotty/issues/5494
 */
package issues

class A()
class B()

object Demo {

  def safe_test(foo: Any): String = {
    foo match {
      case ab: (A | B) =>
        "got A | B"
      case _ =>
        "got Any"
    }
  }

  type AorB = A | B

  def unsafe_test(foo: Any): String = {
    foo match {
      // TODO: Warning is generated that AorB cannot be checked at runtime
      case ab: AorB =>
        "got AorB"
      case _ =>
        "got Any"
    }
  }
}