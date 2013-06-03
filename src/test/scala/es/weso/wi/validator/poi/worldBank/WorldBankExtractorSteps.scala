package es.weso.wi.validator.poi.worldBank

import cucumber.api.scala.{ScalaDsl, EN}
import org.scalatest.matchers.ShouldMatchers
import scala.collection.mutable.{ Map => MutableMap }
import cucumber.runtime.PendingException
import scala.io.Source
import es.weso.wi.entities.Indicator
import es.weso.wi.validator.utils.StepsUtils

class ValidateWebindexSteps extends ScalaDsl with EN with ShouldMatchers {
  
    
  Given("""^I want to check the indicator "([^"]*)" for "([^"]*)" in "([^"]*)"$""") { (indicator: String, region: String, year: String) =>
    StepsUtils.vars.clear
    StepsUtils.vars += StepsUtils.INDICATOR -> indicator
    StepsUtils.vars += StepsUtils.REGION -> region
    StepsUtils.vars += StepsUtils.YEAR -> year
  }
}