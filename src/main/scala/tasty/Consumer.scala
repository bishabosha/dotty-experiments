package tasty

abstract class Consumer extends TASTYFlagSets {
  import tastyFlags._
  import given tastyFlags._

  lazy val flagset: TASTYFlagSet = Inline | Opaque | Exported

  lazy val str = flagset.show
}
