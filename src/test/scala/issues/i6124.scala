package issues

import org.junit.{ Test => test }
import org.junit.Assert._

class i6124 {
  @test def badTest = BadTest.test
  @test def okTest  = OkTest.test
  @test def okTest2 = OkTest2.test
}