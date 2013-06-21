package es.weso.wi.validator.indaba

import cucumber.api.scala.ScalaDsl
import cucumber.api.scala.EN
import org.scalatest.matchers.ShouldMatchers
import es.weso.wi.validator.utils.StepsUtils

class IndabaExtractorSteps extends ScalaDsl with EN with ShouldMatchers{    
  Given("""^I want to check the Indaba indicator "([^"]*)" for "([^"]*)" in "([^"]*)"$""") { (indicator: String, region: String, year: String) =>
    StepsUtils.vars.clear
    StepsUtils.vars += StepsUtils.INDICATOR -> indicator
    StepsUtils.vars += StepsUtils.REGION -> region
    StepsUtils.vars += StepsUtils.YEAR -> year
  }  
}