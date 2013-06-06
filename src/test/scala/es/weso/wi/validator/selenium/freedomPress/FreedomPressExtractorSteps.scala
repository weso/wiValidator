package es.weso.wi.validator.selenium.freedomPress

import cucumber.api.scala.ScalaDsl
import cucumber.api.scala.EN
import org.scalatest.matchers.ShouldMatchers
import scala.collection.mutable.{ Map => MutableMap }
import es.weso.wi.entities.Indicator
import cucumber.api.PendingException
import es.weso.wi.validator.utils.StepsUtils

class FreedomPressExtractorSteps extends ScalaDsl with EN with ShouldMatchers{
    
 Given("""^I want to check the FreedomPress indicator "([^"]*)" for "([^"]*)" in "([^"]*)"$"""){ (indicator:String, region:String, year:String) =>
	StepsUtils.vars.clear
    StepsUtils.vars += StepsUtils.INDICATOR -> indicator
    StepsUtils.vars += StepsUtils.REGION -> region
    StepsUtils.vars += StepsUtils.YEAR -> year
  }  

}