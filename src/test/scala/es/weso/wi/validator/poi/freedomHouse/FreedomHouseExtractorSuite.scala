package es.weso.wi.validator.poi.freedomHouse

import scala.collection.Seq
import org.scalatest.FunSuite
import org.scalatest.events.Formatter
import scala.collection.immutable.Set
import scala.reflect.Manifest
import scala.runtime.BoxedUnit
import java.lang.reflect.Method
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.BeforeAndAfter
import es.weso.exceptions.ExtractorException

@RunWith(classOf[JUnitRunner])
class FreedomHouseExtractorSuite extends FunSuite with BeforeAndAfter {
  
  var freedomExtractor : FreedomHouseExtractor = null
  
  /*before {
    freedomExtractor = new FreedomHouseExtractor("PR")
    freedomExtractor.loadDataSource("/resources/files/PR.xls")
  }*/
  
  test("Test getIndicator for given value \"PR\" ") {
    val extractor = new FreedomHouseExtractor("PR")
    val indicator = extractor.getIndicator
    assert(indicator.name == "FHA")
  }
  
  test("Test getIndicator for given value \"CL\"") {
    val extractor = new FreedomHouseExtractor("CL")
    val indicator = extractor.getIndicator
    assert(indicator.name == "FHB")
  }
  
  test("Test getIndicator for given value \"PL\"") {
    val extractor = new FreedomHouseExtractor("PL")
    intercept[ExtractorException] {
      val indicator = extractor.getIndicator
    }    
  }
  
  test("Test getRange for given indicator \"PR\"") {
    val extractor = new FreedomHouseExtractor("PR")
    val range = extractor.getRange
    assert(range.equals(2 to 12))
  }
  
  test("Test getRange for given indicator \"CL\"") {
    val extractor = new FreedomHouseExtractor("CL")
    val range = extractor.getRange
    assert(range.equals(14 to 24))
  }
  
  test("Test getRange for given indicator \"PL\"") {    
     val extractor = new FreedomHouseExtractor("PL")
     intercept[ExtractorException]{
       val range = extractor.getRange
     }
  }
  
  test("Test getYear given a correct number of column") {
    val year = freedomExtractor.getYear(8)
    //assert(year == 2009)
  }
  
  test("Test getYear given a negative number of column") {
    val year = freedomExtractor.getYear(-1)
    
  }
  
  test("Test getYear given a empty column") {
    val year = freedomExtractor.getYear(27)
  }
  
  test("Test getRegion given a correct number of row") {
    val region = freedomExtractor.getRegion(20)
    //assert(region == "Belarus")
  }
  
  test("Test getRegion given a negative number of row") {
    val region = freedomExtractor.getRegion(-1)
  }
  
  test("Test getRegion given a empty row") {
    val region = freedomExtractor.getRegion(230)
  }
  
  test("Test loadValues") {
    val indicator = freedomExtractor.loadValues
  }

}