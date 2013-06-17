package es.weso.wi.entities

class CC[T] { def unapply(a: Any): Option[T] = Some(a.asInstanceOf[T]) }
object JsMap extends CC[Map[String, Any]]
object JsList extends CC[List[Any]]
object JsString extends CC[String]
object JsDouble extends CC[Double]
object JsBoolean extends CC[Boolean]