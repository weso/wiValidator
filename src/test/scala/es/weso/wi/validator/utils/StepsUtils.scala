package es.weso.wi.validator.utils

import es.weso.wi.entities.Indicator
import scala.collection.mutable.{ Map => MutableMap }

object StepsUtils {
  
  val vars = MutableMap[String, String]()
  var indicator : Indicator = null
  
  val INDICATOR = "indicator"
  val REGION = "region"
  val YEAR = "year"
  val VALUE = "value"
  val PATH = "files/" 
  val BEGIN_COL = "beginCol"
  val END_COL = "endcol"
  val CELL = "cell"
  

}