package example

object Safety {

  opaque type Maybe[+A] = A | Null

  object Maybe {
    inline def apply[A](a: A): Maybe[A] = a
    inline def zero[A]: Maybe[A] = null
    def point[A](a: A): Maybe[A] = Maybe(a)

    inline private[this] def recover[A](ma: Maybe[A]): A = ma.asInstanceOf[A]

    inline def (ma: Maybe[A]) fold[A, U](ifZero: => U)(f: A => U): U =
      if ma == null then ifZero else f(recover(ma))

    inline def (ma: Maybe[A]) ??[A] (ifZero: => A): A =
      if ma == null then ifZero else recover(ma)
  }
}