import example.Maybe._
import example.Functors._
import example.Monads._
import example.Equatables._
import example.Equatable1s._
import example.Ordereds._
import example.Ordered1s._
import example.Equatable._
import example.Equatable1._
import example.minList
import example.MonadSyntax._
import example.MonadOps._
import example.FunctorSyntax._

object Main {

  def main(args: Array[String]): Unit = {
    println(Just(msg))
    println(equal(1, 1))
    println(equal1(Just(1), Nothing))
    println(minList(Just(3), Nothing, Just(2)))
    println(minList(5, 3, 1))

    val sum = for {
      x <- Just(2)
      y <- Just(3)
    } yield (x + y)

    println(sum)

    for {
      x <- Just(10)
      y <- Just(1)
    } println(x + y)

    val sumIt = liftM2(Integer.sum)

    println(sumIt(Just(12), Just(13)))
  }

  def msg = "I was compiled by dotty :)"
}
