package es.weso.wi.entities

case class Record (val name:String, val region:String, val year:Int, val value:BigDecimal){
	override def toString():String = {
	  "[("+name+", "+region+", "+year+"), "+value+"]"
	}
}