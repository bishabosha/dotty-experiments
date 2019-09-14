package tasty

/**Flags from TASTy with no equivalent in scalac
 */
trait TASTYFlags { self =>
  type TASTYFlagSet
  type SetOf[_ <: TASTYFlagSet]

  val EmptyTASTYFlagSet: TASTYFlagSet
  val Erased: TASTYFlagSet
  val Internal: TASTYFlagSet
  val Inline: TASTYFlagSet
  val InlineProxy: TASTYFlagSet
  val Opaque: TASTYFlagSet
  val Scala2x: TASTYFlagSet
  val Extension: TASTYFlagSet
  val Given: TASTYFlagSet
  val Exported: TASTYFlagSet

  given (flagset: TASTYFlagSet) {
    final def project: SetOf[TASTYFlagSet]         = self.toSetOfSingletonOrEmpty(flagset)
    final def |(other: TASTYFlagSet): TASTYFlagSet = self.union(flagset, other)
    final def is(mask: TASTYFlagSet): Boolean      = self.is(flagset, mask)
    final def equal(set: TASTYFlagSet): Boolean    = self.equal(flagset, set)
    final def not(mask: TASTYFlagSet): Boolean     = self.not(flagset, mask)
    final def &~(mask: TASTYFlagSet): TASTYFlagSet = self.remove(flagset, mask)
    final def isEmpty: Boolean                     = self.isEmpty(flagset)
    final def nonEmpty: Boolean                    = !self.isEmpty(flagset)
    final def show: String                         = self.show(flagset)

    final def is(mask: TASTYFlagSet, butNot: TASTYFlagSet): Boolean = self.is(flagset, mask, butNot)
  }

  given (flagsets: SetOf[TASTYFlagSet]) {
    final def map[A](f: TASTYFlagSet => A): Iterable[A] = self.map(flagsets, f)
  }

  private final def show(set: TASTYFlagSet): String = set match {
    case _ if equal(set, Erased)      => "Erased"
    case _ if equal(set, Internal)    => "Internal"
    case _ if equal(set, Inline)      => "Inline"
    case _ if equal(set, InlineProxy) => "InlineProxy"
    case _ if equal(set, Opaque)      => "Opaque"
    case _ if equal(set, Scala2x)     => "Scala2x"
    case _ if equal(set, Extension)   => "Extension"
    case _ if equal(set, Given)       => "Given"
    case _ if equal(set, Exported)    => "Exported"
    case _ if isEmpty(set)            => "EmptyTASTYFlagSet"
    case sets                         => map(toSetOfSingletonOrEmpty(sets), show).mkString(" | ")
  }

  protected def toSetOfSingletonOrEmpty(multiset: TASTYFlagSet): SetOf[TASTYFlagSet]
  protected def map[A](set: SetOf[TASTYFlagSet], f: TASTYFlagSet => A): Iterable[A]
  protected def union(as: TASTYFlagSet, bs: TASTYFlagSet): TASTYFlagSet
  protected def is(set: TASTYFlagSet, mask: TASTYFlagSet, butNot: TASTYFlagSet): Boolean
  protected def is(set: TASTYFlagSet, mask: TASTYFlagSet): Boolean
  protected def equal(set: TASTYFlagSet, other: TASTYFlagSet): Boolean
  protected def not(set: TASTYFlagSet, mask: TASTYFlagSet): Boolean
  protected def isEmpty(set: TASTYFlagSet): Boolean
  protected def remove(set: TASTYFlagSet, mask: TASTYFlagSet): TASTYFlagSet
}

object TASTYFlags {
  val Live: TASTYFlags = new TASTYFlags {

    type TASTYFlagSet = Int
    type SetOf[X <: TASTYFlagSet] = X

    val EmptyTASTYFlagSet = 0
    val Erased            = 1 << 0
    val Internal          = 1 << 1
    val Inline            = 1 << 2
    val InlineProxy       = 1 << 3
    val Opaque            = 1 << 4
    val Scala2x           = 1 << 5
    val Extension         = 1 << 6
    val Given             = 1 << 7
    val Exported          = 1 << 8

    final def union(a: TASTYFlagSet, b: TASTYFlagSet)       = a | b
    final def is(set: TASTYFlagSet, mask: TASTYFlagSet)     = (set & mask) != 0
    final def equal(set: TASTYFlagSet, other: TASTYFlagSet) = set == other
    final def not(set: TASTYFlagSet, mask: TASTYFlagSet)    = (set & mask) == 0
    final def remove(set: TASTYFlagSet, mask: TASTYFlagSet) = set & ~mask
    final def isEmpty(set: TASTYFlagSet)                    = set == 0

    final def is(set: TASTYFlagSet, mask: TASTYFlagSet, butNot: TASTYFlagSet): Boolean =
      ((set & mask) != 0) && ((set & butNot) == 0)

    final def toSetOfSingletonOrEmpty(set: TASTYFlagSet): SetOf[TASTYFlagSet] = set

    final def map[A](set: SetOf[TASTYFlagSet], f: TASTYFlagSet => A) = {
      val buf = Iterable.newBuilder[A]
      var i = 0
      while (i <= 8) {
        val flag = 1 << i
        if ((flag & set) != 0) {
          buf += f(flag)
        }
        i += 1
      }
      buf.result
    }
  }
}
