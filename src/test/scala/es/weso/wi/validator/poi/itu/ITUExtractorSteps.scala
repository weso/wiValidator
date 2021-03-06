package es.weso.wi.validator.poi.itu

import cucumber.api.scala.ScalaDsl
import cucumber.api.scala.EN
import org.scalatest.matchers.ShouldMatchers
import scala.collection.mutable.{ Map => MutableMap }
import es.weso.wi.entities.Indicator
import cucumber.api.PendingException
import es.weso.wi.validator.utils.StepsUtils

class ITUExtractorSteps extends ScalaDsl with EN with ShouldMatchers{
    
  Given("""^I want to check the ITU indicator "([^"]*)" which name is in cell "([^"]*)" and goes from column "([^"]*)" to "([^"]*)", for "([^"]*)" in "([^"]*)"$"""){ (indicator:String, cell:String, beginCol:String, endCol:String, region:String, year:String) =>
	StepsUtils.vars.clear
	StepsUtils.vars += StepsUtils.INDICATOR -> indicator
	StepsUtils.vars += StepsUtils.CELL -> cell
	StepsUtils.vars += StepsUtils.BEGIN_COL -> beginCol
	StepsUtils.vars += StepsUtils.END_COL -> endCol
	StepsUtils.vars += StepsUtils.REGION -> region
	StepsUtils.vars += StepsUtils.YEAR -> year
  }  

}