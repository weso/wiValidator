package es.weso.wi.validator.poi.itu

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.BeforeAndAfter
import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ITUExtractorSuite extends FunSuite with BeforeAndAfter with ShouldMatchers{

  var extractor : ITUExtractor = null
  
  before {
    extractor = ITUExtractor(1, 12, "B1")
    extractor.loadDataSource("src/test/resources/files/ITUH.xls")
  }
  
  test("Test getIndicator for test file \"ITUH.xls\" with correct indicator cell") {
    val indicator = extractor.getIndicator
    indicator.name should be ("Percentage of Individuals using the Internet")
  }
  
  test("Test getIndicator for test file \"ITUH.xls\" with invalid indicator cell") {
    val extractor = ITUExtractor(1, 12, "A1")
    extractor.loadDataSource("src/test/resources/files/ITUH.xls")
    val indicator = extractor.getIndicator
    indicator.name should not be ("Percentage of Individuals using the Internet")
    indicator.name should be ("")
  }
  
  test("Test getYear for correct number of column") {
    val year = extractor.getYear(4)
    year should be (2003)
  }
  
  test("Test getYear for negative number of column") {
    intercept[IllegalArgumentException] {
      extractor.getYear(-1)
    }
  }
  
  test("Test getYear for a great number of column") {
    intercept[IllegalArgumentException] {
      extractor.getYear(13)
    }
  }
  
  test("Test getRegion for a correct number of row") {
    val region = extractor.getRegion(15)
    region should be ("Austria")
  }
  
  test("Test getRegion for a negative number of row") {
    intercept[IllegalArgumentException] {
      extractor.getRegion(-1)
    }
  }
  
  test("Test getRegion for Int max value") {
    intercept[IllegalArgumentException] {
      extractor.getRegion(Int.MaxValue)
    }
  }
  
  test("Test load values") {
    val indicator = extractor.loadValues
    indicator.size should not be 0
  }
}