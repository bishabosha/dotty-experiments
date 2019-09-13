package issues

import org.junit.{ Test => test }
import org.junit.Assert._

import PostConditions.{ensuring, result}

class i7059 {
  @test def sum: Unit = List(1, 2, 3).sum.ensuring(result == 6)
}