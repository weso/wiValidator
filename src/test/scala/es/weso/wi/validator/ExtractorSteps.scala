package es.weso.wi.validator

import cucumber.api.scala.ScalaDsl
import cucumber.api.scala.EN
import org.scalatest.matchers.ShouldMatchers
import es.weso.wi.validator.poi.PoiExtractor
import es.weso.wi.validator.poi.worldBank.WorldBankExtractor
import es.weso.wi.validator.poi.freedomHouse.FreedomHouseExtractor
import es.weso.wi.validator.utils.StepsUtils
import es.weso.wi.validator.poi.itu.ITUExtractor

class ExtractorSteps extends ScalaDsl with EN with ShouldMatchers{
  
  When("""^I check the original "([^"]*)" source$"""){ (source: String) =>
    var extractor : Extractor = null
    source match {
      case "WorldBank" => extractor = new WorldBankExtractor
      case "FreedomHouse" => extractor = new FreedomHouseExtractor(StepsUtils.vars(StepsUtils.INDICATOR))
      case "ITU" => extractor = new ITUExtractor(StepsUtils.vars(StepsUtils.BEGIN_COL).toInt, 
    		  StepsUtils.vars(StepsUtils.END_COL).toInt, StepsUtils.vars(StepsUtils.CELL))
      case _ => throw new IllegalArgumentException("Given source is unknown")
    }
    StepsUtils.indicator = extractor.loadDataSource("files/" + 
        StepsUtils.vars(StepsUtils.INDICATOR) + ".xls",true)    
  }
  
  Then("""^the value should be "([^"]*)"$""") { (value: Double) =>
    val record = StepsUtils.indicator.getRecord(StepsUtils.vars(StepsUtils.REGION), 
        StepsUtils.vars(StepsUtils.YEAR).toInt)
    value should be(record.value.toDouble plusOrMinus 0.0000001f)
  }
  
  Then("""^it should raise an Exception$"""){ () =>
    intercept[Exception]{
      StepsUtils.indicator.getRecord(StepsUtils.vars(StepsUtils.REGION), 
          StepsUtils.vars(StepsUtils.YEAR).toInt)
    }
  }

}