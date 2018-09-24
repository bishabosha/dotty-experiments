package example

object Functor {
  def apply[F[_]: Functor] =
    implicitly

  def fmap[F[_]: Functor, A, B](fa: F[A])(f: A => B): F[B] =
    Functor[F].fmap(fa)(f)
}

trait Functor[F[_]] {
  def fmap[A, B](fa: F[A])(f: A => B): F[B]
}

import Functor._
object FunctorSyntax {
  implicit class Map[F[_]: Functor, A](`this`: F[A]){
    def map[B](f: A => B): F[B] = fmap(`this`)(f)
  }

  implicit class ForEach[F[_]: Functor, A](`this`: F[A]){
    def foreach(f: A => Unit): Unit = fmap(`this`)(f)
  }
}

import Maybe._
object Functors {
  implicit object FunctorMaybe extends Functor[Maybe] {
    override def fmap[A, B](fa: Maybe[A])(f: A => B) =
      fa match {
        case Just(a) => Just(f(a))
        case Nothing => Nothing
      }
  }
}