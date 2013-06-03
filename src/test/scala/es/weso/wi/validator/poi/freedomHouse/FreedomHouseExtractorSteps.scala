package es.weso.wi.validator.poi.freedomHouse

import cucumber.api.scala.ScalaDsl
import org.scalatest.matchers.ShouldMatchers
import cucumber.api.scala.EN
import scala.collection.mutable.{ Map => MutableMap }
import es.weso.wi.entities.Indicator
import es.weso.wi.validator.utils.StepsUtils

class FreedomHouseExtractorSteps extends ScalaDsl with EN with ShouldMatchers{    
    
  Given("""^I want to check the FreedomHouse indicator "([^"]*)" for "([^"]*)" in "([^"]*)"$""") { (indicator: String, region: String, year: String) =>
    StepsUtils.vars.clear
    StepsUtils.vars += StepsUtils.INDICATOR -> indicator
    StepsUtils.vars += StepsUtils.REGION -> region
    StepsUtils.vars += StepsUtils.YEAR -> year
  }  

}