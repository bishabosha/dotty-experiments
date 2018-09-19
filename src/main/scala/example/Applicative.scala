package example

object Applicative {
  def apply[F[_]: Applicative] =
    implicitly

  def pure[F[_]: Applicative, A](a: A): F[A] =
    Applicative[F] pure a

  def <*>[F[_]: Applicative, A, B](fa: F[A])(fab: F[A => B]): F[B] =
    Applicative[F].<*>(fa)(fab)
}

trait Applicative[F[_]: Functor] {
  def pure[A](a: A): F[A]
  def <*>[A, B](fa: F[A])(fab: F[A => B]): F[B]
}

import Applicative._
object ApplicativeSyntax {
  implicit class Apply[F[_]: Applicative, A](`this`: F[A]){
    def <*>[B](fab: F[A => B]): F[B] = (Applicative[F] <*> `this`)(fab)
  }
}