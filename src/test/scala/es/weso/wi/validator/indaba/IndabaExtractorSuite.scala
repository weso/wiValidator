package es.weso.wi.validator.indaba

import org.scalatest.BeforeAndAfter
import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import es.weso.wi.validator.rest.indaba.IndabaExtractor

@RunWith(classOf[JUnitRunner])
class IndabaHouseExtractorSuite extends FunSuite with BeforeAndAfter 
	with ShouldMatchers{

  var indabaExtractor : IndabaExtractor = null
  
  before {
    indabaExtractor = IndabaExtractor("src/test/resources/es/weso/wi/validator/indaba/Horseids.json")
  }
  
  test("Test Indicator, 'q6'") {
    val indicator = indabaExtractor.loadDataSource().getOrElse("q6", null)
	indicator should not be (null)
  }
    
  test("Test Bad Indicator, 'qpwle6'") {
    val indicator = indabaExtractor.loadDataSource().getOrElse("qpwle6", null)
	indicator should be (null)
  }

  test(s"Test get value given a existing country, 'Spain'") {
    val indicator = indabaExtractor.loadDataSource().getOrElse("q6", null)
	indicator.getRecordsByRegion("Spain").get(2013) should be (5.0)
  }

  test(s"Test get value given a non-existing country, 'Narnia'") {
    val indicator = indabaExtractor.loadDataSource().getOrElse("q6", null)
	  indicator.getRecordsByRegion("Narnia").getOrElse(2013, null) should be (null)
  }
  
}