package issues

object PostConditions {
  opaque type WrappedResult[T] = T

  def result[T] given (r: WrappedResult[T]): T = r

  def (x: T) ensuring[T](condition: given WrappedResult[T] => Boolean): T = {
    assert(condition given x)
    x
  }
}