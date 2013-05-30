package es.weso.wi.validator.poi.worldBank

import cucumber.api.scala.{ScalaDsl, EN}
import org.scalatest.matchers.ShouldMatchers
import scala.collection.mutable.{ Map => MutableMap }
import cucumber.runtime.PendingException
import scala.io.Source
import es.weso.wi.entities.Indicator

class ValidateWebindexSteps extends ScalaDsl with EN with ShouldMatchers {

  val vars = MutableMap[String, String]()
  var indicator : Indicator = null

  val INDICATOR = "indicator"
  val REGION = "region"
  val YEAR = "year"
  val VALUE = "value"
  val PATH = "src/test/scala/resources/files/"    
    
  Given("""^I want to check the indicator "([^"]*)" for "([^"]*)" in "([^"]*)"$""") { (indicator: String, region: String, year: String) =>
    vars.clear
    vars += INDICATOR -> indicator
    vars += REGION -> region
    vars += YEAR -> year
  }
  
  When("""^I check the original WorlBank source$"""){ () =>
    val extractor = new WorldBankExtractor()
    indicator = extractor.loadDataSource("files/"+vars(INDICATOR)+".xls",true)
    
  }

  Then("""^the value should be "([^"]*)"$""") { (value: Double) =>
    val record = indicator.getRecord(vars(REGION), vars(YEAR).toInt)
    value should be(record.value.toDouble plusOrMinus 0.0000001f)
  }
  
  Then("""^it should raise an Exception$"""){ () =>
    intercept[Exception]{
      indicator.getRecord(vars(REGION), vars(YEAR).toInt)
    }
  }
}