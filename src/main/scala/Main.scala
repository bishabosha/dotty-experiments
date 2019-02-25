import example._
import Safety._
import Maybe._
import implied Equatable._
import implied Equatable1._
import MonadSyntax._
import MonadOps._
import FunctorSyntax._

object Main {

  def main(args: Array[String]): Unit = {
    println(Applicative.pure(msg))
    println(1 equal 1)
    println(Maybe(1) equal zero)
    println(minList(Maybe(3), zero, Maybe(2)))
    println(minList(5, 3, 1))

    val sum = for {
      x <- Maybe.point(2)
      y <- Maybe.point(3)
    } yield (x + y)

    println(sum)

    for {
      x <- Maybe.point(10)
      y <- Maybe.point(1)
    } println(x + y)

    val sumIt = liftM2(Integer.sum)

    println(sumIt(Maybe(12), Maybe(13)))
  }

  def msg = "I was compiled by dotty!"
}
