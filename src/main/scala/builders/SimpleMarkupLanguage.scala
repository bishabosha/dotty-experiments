package builders

enum SimpleMarkupLanguage:
  case Node(tag: String, inner: List[SimpleMarkupLanguage])
  case Singleton(tag: String)
  case Text(msg: String)

object SimpleMarkupLanguage:
  def show(sml: SimpleMarkupLanguage): String = sml match
    case Singleton(tag)   => s"<$tag/>"
    case Text(msg)        => msg
    case Node(tag, elems) => s"<$tag>${elems.map(show).mkString}</$tag>"

trait SMLCallback:
  def startNode(tag: String): Unit
  def endNode: Unit
  def text(msg: String): Unit
  def singleton(tag: String): Unit

enum SMLStackElement:
  case StartNode(tag: String)
  case StackSave(sml: SimpleMarkupLanguage)

import SimpleMarkupLanguage._
import SMLStackElement._

final class SMLElementBuilderCallback extends SMLCallback:

  private val elements = List.newBuilder[SimpleMarkupLanguage]
  private var stack = List.empty[SMLStackElement]

  def pushElem(sml: SimpleMarkupLanguage) = if stack.isEmpty then elements += sml else stack ::= StackSave(sml)

  def startNode(tag: String): Unit = stack ::= StartNode(tag)

  def endNode: Unit =
    val inner = List.newBuilder[SimpleMarkupLanguage]
    while !stack.head.isInstanceOf[StartNode] do // if endNode comes before a startNode then an invariant is broken
      (stack.head: @unchecked) match
        case StackSave(elem) => inner += elem
      stack = stack.tail
    end while
    val node = Node(stack.head.asInstanceOf[StartNode].tag, inner.result.reverse)
    stack = stack.tail
    pushElem(node)
  end endNode

  def text(msg: String): Unit      = pushElem(Text(msg))
  def singleton(tag: String): Unit = pushElem(Singleton(tag))

  def elems: List[SimpleMarkupLanguage] = elements.result

trait Writer[-T]:
  def (t: T).write(using SMLCallback): Unit

given as Writer[Int]:
  def (t: Int).write(using cb: SMLCallback): Unit =
    cb.startNode("Int")
    cb.text(t.toString)
    cb.endNode

given as Writer[String]:
  def (t: String).write(using cb: SMLCallback): Unit =
    cb.startNode("String")
    cb.text(t)
    cb.endNode

given as Writer[Unit]:
  def (t: Unit).write(using cb: SMLCallback): Unit = cb.singleton("Unit")

given [T: Writer, U: Writer, V: Writer] as Writer[(T, U, V)]:
  def (t: (T, U, V)).write(using cb: SMLCallback): Unit =
    cb.startNode("scala.Tuple3")
    t._1.write
    t._2.write
    t._3.write
    cb.endNode

def inspectTripleAsSML[T: Writer, U: Writer, V: Writer](t: (T, U, V)): Seq[SimpleMarkupLanguage] =
  given cb as SMLElementBuilderCallback = SMLElementBuilderCallback()
  t.write
  cb.elems

@main def SMLTriple =
  val elems = inspectTripleAsSML((23, "abc", ()))
  val sml   = elems.map(SimpleMarkupLanguage.show).mkString
  assert(sml == "<scala.Tuple3><Int>23</Int><String>abc</String><Unit/></scala.Tuple3>")
