package example

trait Monad[M[_]: Applicative] {
  def (ma: M[A])    >>=[A, B] (amb: A => M[B]): M[B]
  def (ma: => M[A]) >>[A, B]  (mb: M[B]): M[B]    = mb
  def (ma: M[A])    <<[A, B]  (mb: => M[B]): M[A] = ma
}

object Monad {  
  
  implicit val MonadMaybe: Monad[Maybe] = new {
    import Maybe._

    override def >>=[A, B](ma: Maybe[A])(f: A => Maybe[B]) =
      ma match {
        case Just(a) => f(a)
        case Nothing => Nothing
      }
  }
}

object MonadOps {
  def liftM[F[_]: Functor, A, B](f: A => B): F[A] => F[B] =
    _ fmap f
    
  import MonadSyntax._
  import FunctorSyntax._
  def liftM2[M[_]: Monad: Functor, A, B, C](f: (A, B) => C): (M[A], M[B]) => M[C] =
    for {
      a <- _
      b <- _
    } yield f(a, b)
}

object MonadSyntax {
  def (ma: M[A]) flatMap[M[_]: Monad, A, B](f: A => M[B]): M[B] = ma >>= f
}