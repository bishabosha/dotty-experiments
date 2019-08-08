package issues

import org.junit.{ Test => test }
import org.junit.Assert._

class i5494 {
  import Demo._

  @test def safeTest: Unit = {
    val a = new A()
    val b = new B()
    val unit = ()
    
    assertEquals(safe_test(a),    "got A | B")
    assertEquals(safe_test(b),    "got A | B")
    assertEquals(safe_test(unit), "got Any")
  }

  @test def unsafeTest: Unit = {
    val a = new A()
    val b = new B()
    val unit = ()
    
    assertEquals(unsafe_test(a),    "got AorB")
    assertEquals(unsafe_test(b),    "got AorB")
    assertEquals(unsafe_test(unit), "got Any")
  }
}