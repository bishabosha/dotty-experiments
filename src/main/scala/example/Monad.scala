package example

trait Monad[M[_]: Applicative] {
  def (ma: M[A])        >>= [A, B](amb: A => M[B]): M[B]
  inline def (ma: M[A]) >>  [A, B](mb: M[B]): M[B] = ma >>= (_ => mb)
  inline def (ma: M[A]) <<  [A, B](mb: M[B]): M[A] = mb >>= (_ => ma)
}

object Monad {

  import Safety._

  implied for Monad[Maybe] {
    import Maybe._

    def (ma: Maybe[A]) >>=[A, B](f: A => Maybe[B]) =
      ma.fold(zero)(f)
  }
}

object MonadOps {

  import MonadSyntax._
  import FunctorSyntax._
  
  def liftM[F[_]: Functor, A, B](f: A => B): F[A] => F[B] =
    _ fmap f
    
  def liftM2[M[_]: Monad: Functor, A, B, C]
    (f: (A, B) => C): (M[A], M[B]) => M[C] =
      for {
        a <- _
        b <- _
      } yield f(a, b)
}

object MonadSyntax {
  inline def (ma: M[A]) flatMap[M[_]: Monad, A, B] (f: A => M[B]): M[B] = ma >>= f
}