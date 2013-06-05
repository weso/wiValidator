package es.weso.wi.entities

/**
 *
 * @author cesarla
 */
class Template {
  var prefixes: Set[String] = Set.empty
  val path: String = ""
  val properties: Map[String, Value] = Map.empty
}

/**
 *
 * @author cesarla
 */
abstract class Value {
  var right: Value = null
  var value: String = null
  var left: Value = null

  def prepped(value: Value): Value = {
    head.prepped(value)
  }

  def append(value: Value): Value = {
    tail.append(value)
  }

  protected def head(): Value = {
    var current = this.left;
    left match {
      case null => this
      case _ => current.head
    }
  }

  protected def tail(): Value = {
    var current = this.right;
    right match {
      case null => this
      case _ => current.tail
    }
  }
}

/**
 *
 * @author cesarla
 */
class Literal(value: String) extends Value {
  override def toString(): String = {
    value + right.toString()
  }
}

/**
 *
 * @author cesarla
 */
class Match(value: String) extends Value {
  override def toString(): String = {
    "{" + value + "}" + right.toString()
  }
}

object Test {
  def main(args: Array[String]): Unit = {
    val v = new Match("{String}")
    v.append(new Literal("@en"))
    println(v.toString)
  }
}
