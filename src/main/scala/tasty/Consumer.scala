package tasty

object Consumer {
  import TASTYFlags.Live._
  import given TASTYFlags.Live._

  val flagset: TASTYFlagSet = Inline | Opaque | Exported

  val str = flagset.show
}
