package es.weso.wi.validator.poi.itu

import cucumber.api.scala.ScalaDsl
import cucumber.api.scala.EN
import org.scalatest.matchers.ShouldMatchers
import scala.collection.mutable.{ Map => MutableMap }
import es.weso.wi.entities.Indicator
import cucumber.api.PendingException

class ITUExtractorSteps extends ScalaDsl with EN with ShouldMatchers{
  
  val vars = MutableMap[String, String]()
  var indicator : Indicator = null

  val INDICATOR = "indicator"
  val REGION = "region"
  val YEAR = "year"
  val VALUE = "value"
  val PATH = "src/test/scala/resources/files/"
  val CELL = "cell"
  val BEGINCOL = "beginCol"
  val ENDCOL = "endCol"
    
  Given("""^I want to check the ITU indicator "([^"]*)" which name is in cell "([^"]*)" and goes from column "([^"]*)" to "([^"]*)", for "([^"]*)" in "([^"]*)"$"""){ (indicator:String, cell:String, beginCol:String, endCol:String, region:String, year:String) =>
	vars.clear
	vars += INDICATOR -> indicator
	vars += CELL -> cell
	vars += BEGINCOL -> beginCol
	vars += ENDCOL -> endCol
	vars += REGION -> region
	vars += YEAR -> year
  }  
  
  When("""^I check the original ITU source$"""){ () =>
    val extractor = new ITUExtractor(vars(BEGINCOL).toInt, vars(ENDCOL).toInt, 
        vars(CELL))    
    indicator = extractor.loadDataSource(PATH + vars(INDICATOR), false)
  }
  
  Then("""^the original ITU value should be "([^"]*)"$"""){ (value:Double) =>
	var record = indicator.getRecord(vars(REGION), vars(YEAR).toInt)
	value should be (record.value.toDouble plusOrMinus 0.0000001f)
  }

}