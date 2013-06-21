package es.weso.wi.validator.selenium.freedomPress

import org.scalatest.BeforeAndAfter
import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import es.weso.exceptions.ExtractorException

@RunWith(classOf[JUnitRunner])
class FreedomPressExtractorSuite  extends FunSuite with BeforeAndAfter with ShouldMatchers{

  var freedomPressExtractor : FreedomPressExtractor = null
  
  before {
    freedomPressExtractor = new FreedomPressExtractor
    freedomPressExtractor.loadDataSource("http://en.rsf.org/press-freedom-index-2013,1054.html")
  }
  
  test("Test loadValues") {
    freedomPressExtractor.loadValues.map(_._2.size).foldLeft(0)(_+_) should be (2030)
  }
  
  test("Test navigateTo correct index '2'") {
   freedomPressExtractor.navigateTo(2)
  }
  
  test("Test navigateTo random web 'http://www.example.com'") {
    freedomPressExtractor.driver.get("http://www.example.com")
    intercept[ExtractorException]{
    	freedomPressExtractor.navigateTo(2)
    }
  }
  
  test(s"Test navigateTo bad index '${Int.MaxValue}'") {
        intercept[IllegalArgumentException]{
        	freedomPressExtractor.navigateTo(Int.MaxValue)
        }
  }
}