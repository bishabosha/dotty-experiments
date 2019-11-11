package tasty

object Consumer {
  import TASTYFlags.Live.{_, given}

  val flagset: TASTYFlagSet = Inline | Opaque | Exported

  val str = flagset.show
}
