package tasty

/**Flags from TASTy with no equivalent in scalac
 */
trait TASTYFlags { self =>
  type TASTYFlagSet

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

  protected def map[A](f: TASTYFlagSet => A)(set: TASTYFlagSet): Iterable[A]
  protected def union(as: TASTYFlagSet, bs: TASTYFlagSet): TASTYFlagSet
  protected def is(set: TASTYFlagSet, mask: TASTYFlagSet, butNot: TASTYFlagSet): Boolean
  protected def is(set: TASTYFlagSet, mask: TASTYFlagSet): Boolean
  protected def not(set: TASTYFlagSet, mask: TASTYFlagSet): Boolean
  protected def remove(set: TASTYFlagSet, mask: TASTYFlagSet): TASTYFlagSet

  private final def show(set: TASTYFlagSet): String = set match {
    case EmptyTASTYFlagSet => "EmptyTASTYFlagSet"
    case Erased            => "Erased"
    case Internal          => "Internal"
    case Inline            => "Inline"
    case InlineProxy       => "InlineProxy"
    case Opaque            => "Opaque"
    case Scala2x           => "Scala2x"
    case Extension         => "Extension"
    case Given             => "Given"
    case Exported          => "Exported"
    case multi             => map(show)(multi).mkString(" | ")
  }

  given (flagset: TASTYFlagSet) {
    final def map[A](f: TASTYFlagSet => A): Iterable[A] = self.map(f)(flagset)
    final def |(other: TASTYFlagSet): TASTYFlagSet      = self.union(flagset, other)
    final def is(mask: TASTYFlagSet): Boolean           = self.is(flagset, mask)
    final def not(mask: TASTYFlagSet): Boolean          = self.not(flagset, mask)
    final def &~(mask: TASTYFlagSet): TASTYFlagSet      = self.remove(flagset, mask)
    final def isEmpty: Boolean                          = flagset == EmptyTASTYFlagSet
    final def nonEmpty: Boolean                         = flagset != EmptyTASTYFlagSet
    final def show: String                              = self.show(flagset)

    final def is(mask: TASTYFlagSet, butNot: TASTYFlagSet): Boolean = self.is(flagset, mask, butNot)
  }
}

object TASTYFlags {
  val Live: TASTYFlags = new TASTYFlags with IntField {

    type TASTYFlagSet = Int

    val EmptyTASTYFlagSet = enterZero()
    val Erased            = enterFlag()
    val Internal          = enterFlag()
    val Inline            = enterFlag()
    val InlineProxy       = enterFlag()
    val Opaque            = enterFlag()
    val Scala2x           = enterFlag()
    val Extension         = enterFlag()
    val Given             = enterFlag()
    val Exported          = enterLast()

    final def map[A](f: TASTYFlagSet => A)(set: TASTYFlagSet) = mapOverMask(f)(set)
    final def union(a: TASTYFlagSet, b: TASTYFlagSet)         = a | b
    final def is(set: TASTYFlagSet, mask: TASTYFlagSet)       = (set & mask) != 0
    final def not(set: TASTYFlagSet, mask: TASTYFlagSet)      = (set & mask) == 0
    final def remove(set: TASTYFlagSet, mask: TASTYFlagSet)   = set & ~mask

    final def is(set: TASTYFlagSet, mask: TASTYFlagSet, butNot: TASTYFlagSet): Boolean =
      ((set & mask) != 0) && ((set & butNot) == 0)
  }
}
