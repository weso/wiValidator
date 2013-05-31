package es.weso.wi.validator.poi.freedomHouse

import cucumber.api.scala.ScalaDsl
import org.scalatest.matchers.ShouldMatchers
import cucumber.api.scala.EN
import scala.collection.mutable.{ Map => MutableMap }
import es.weso.wi.entities.Indicator

class FreedomHouseExtractorSteps extends ScalaDsl with EN with ShouldMatchers{

  val vars = MutableMap[String, String]()
  var indicator : Indicator = null
  
  val INDICATOR = "indicator"
  val REGION = "region"
  val YEAR = "year"
  val VALUE = "value"
  val PATH = "src/test/scala/resources/files/"  
    
    
  Given("""^I want to check the FreedomHouse indicator "([^"]*)" for "([^"]*)" in "([^"]*)"$""") { (indicator: String, region: String, year: String) =>
    vars.clear
    vars += INDICATOR -> indicator
    vars += REGION -> region
    vars += YEAR -> year
  }  
  
  When("""^I check the original FreedomHouse source$"""){ () =>
    val extractor = new FreedomHouseExtractor(vars(INDICATOR))
    indicator = extractor.loadDataSource("resources/files/"+vars(INDICATOR)+
        ".xls",true)    
  }
  
  Then("""^the original value should be "([^"]*)"$""") { (value: Double) =>
    val record = indicator.getRecord(vars(REGION), vars(YEAR).toInt)
    value should be(record.value.toDouble plusOrMinus 0.0000001f)
  }
  
  Then("""^it should raise an Exception$"""){ () =>
    intercept[Exception]{
      indicator.getRecord(vars(REGION), vars(YEAR).toInt)
    }
  }
}