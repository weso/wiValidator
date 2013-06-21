package es.weso.wi.validator

import cucumber.api.scala.ScalaDsl
import cucumber.api.scala.EN
import org.scalatest.matchers.ShouldMatchers
import es.weso.wi.validator.poi.PoiExtractor
import es.weso.wi.validator.poi.worldBank.WorldBankExtractor
import es.weso.wi.validator.poi.freedomHouse.FreedomHouseExtractor
import es.weso.wi.validator.utils.StepsUtils
import es.weso.wi.validator.poi.itu.ITUExtractor
import es.weso.wi.validator.selenium.freedomPress.FreedomPressExtractor

class ExtractorSteps extends ScalaDsl with EN with ShouldMatchers {

  When("""^I check the original "([^"]*)" source$""") { (source: String) =>
    var extractor: Extractor = source match {
      case "WorldBank" => new WorldBankExtractor
      case "FreedomHouse" => new FreedomHouseExtractor(StepsUtils.vars(StepsUtils.INDICATOR))
      case "ITU" => new ITUExtractor(StepsUtils.vars(StepsUtils.BEGIN_COL).toInt,
        StepsUtils.vars(StepsUtils.END_COL).toInt, StepsUtils.vars(StepsUtils.CELL))
      case "FreedomPress" => new FreedomPressExtractor
      case _ => throw new IllegalArgumentException("Given source is unknown")
    }

    if (source equals "FreedomPress") {
    	StepsUtils.indicators = 
    	  extractor.loadDataSource("http://en.rsf.org/press-freedom-index-2013,1054.html")
    } else {
      StepsUtils.indicators = extractor.loadDataSource("files/" +
        StepsUtils.vars(StepsUtils.INDICATOR) + ".xls", true)
    }
  }

  Then("""^the value should be "([^"]*)"$""") { (value: Double) =>
    val indicator = StepsUtils.vars.getOrElse(StepsUtils.INDICATOR, null)
    val record = StepsUtils.indicators.getOrElse(indicator, null)
    	.getRecord(StepsUtils.vars(StepsUtils.REGION),
      StepsUtils.vars(StepsUtils.YEAR).toInt)
    value should be(record.value.toDouble plusOrMinus 0.0000001f)
  }

  Then("""^it should raise an Exception$""") { () =>
    intercept[Exception] {
      val indicator = StepsUtils.vars.getOrElse(StepsUtils.INDICATOR, null)
      StepsUtils.indicators.getOrElse(indicator, null)
      .getRecord(StepsUtils.vars(StepsUtils.REGION),
        StepsUtils.vars(StepsUtils.YEAR).toInt)
    }
  }

}