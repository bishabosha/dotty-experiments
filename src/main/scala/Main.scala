import example.Maybe._
import example.IntOps._
import example.MaybeOps._
import example.Equatable._
import example.Equatable1._
import example.minList

object Main {

  def main(args: Array[String]): Unit = {
    println(Just(msg))
    println(equal(1, 1))
    println(equal1(Just(1), Nothing))
    println(minList(Just(3), Nothing, Just(2)))
    println(minList(5, 3, 1))
  }

  def msg = "I was compiled by dotty :)"
}
