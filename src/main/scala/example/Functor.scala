package example

object Functor {
  def apply[F[_]: Functor] =
    implicitly

  def map[F[_]: Functor, A, B](fa: F[A])(f: A => B): F[B] =
    (Functor[F] map fa) (f)
}

trait Functor[F[_]] {
  def map[A, B](fa: F[A])(f: A => B): F[B]
}

object FunctorSyntax {
  implicit class Map[F[_]: Functor, A](`this`: F[A]){
    def map[B](f: A => B): F[B] = (Functor map `this`)(f)
  }

  implicit class ForEach[F[_]: Functor, A](`this`: F[A]){
    def foreach(f: A => Unit): Unit = (Functor map `this`)(f)
  }
}