package example

import Functor._
import Applicative._
import Monad._
import FunctorSyntax._
import MonadSyntax._

object Monad {
  def apply[M[_]: Monad] =
    implicitly

  def >>=[M[_]: Monad, A, B](ma: M[A])(amb: A => M[B]): M[B] =
    Monad[M].>>=(ma)(amb)

  def >>[M[_]: Monad, A, B](ma: M[A])(mb: M[B]): M[B] =
    Monad[M].>>(ma)(mb)

  def <<[M[_]: Monad, A, B](ma: M[A])(mb: M[B]): M[A] =
    Monad[M].<<(ma)(mb)
}

trait Monad[M[_] : Applicative] {
  def >>=[A, B](ma: M[A])(amb: A => M[B]): M[B]
  def >>[A, B](ma: M[A])(mb: M[B]): M[B] = mb
  def <<[A, B](ma: M[A])(mb: M[B]): M[A] = ma
}

object MonadOps {
  def liftM[F[_]: Functor, A, B](f: A => B): F[A] => F[B] =
    map(_)(f)
    
  def liftM2[M[_]: Monad: Functor, A, B, C](f: (A, B) => C): (M[A], M[B]) => M[C] =
    for {
      a <- _
      b <- _
    } yield f(a, b)
}

object MonadSyntax {
  implicit class Bind[M[_]: Monad, A](`this`: M[A]){
    def >>=[B](f: A => M[B]): M[B] = (Monad[M] >>= `this`)(f)
  }

  implicit class FlatMap[M[_]: Monad, A](`this`: M[A]){
    def flatMap[B](f: A => M[B]): M[B] = (Monad[M] >>= `this`)(f)
  }

  implicit class RightShift[M[_]: Monad, A](`this`: M[A]){
    def >>[B](mb: M[B]): M[B] = (Monad[M] >> `this`)(mb)
  }

  implicit class LeftShift[M[_]: Monad, A](`this`: M[A]){
    def <<[B](mb: M[B]): M[A] = (Monad[M] << `this`)(mb)
  }
}