package es.weso.wi.validator.poi.worldBank

import org.scalatest.BeforeAndAfter
import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers

@RunWith(classOf[JUnitRunner])
class WorldBankExtractorSuite extends FunSuite with BeforeAndAfter with ShouldMatchers{

   var worldBankExtractor : WorldBankExtractor = null
  
  before {
    worldBankExtractor = WorldBankExtractor()
    worldBankExtractor.loadDataSource("files/AG.LND.IRIG.AG.ZS.xls", true)
  }
  
  test("Test getYear given a correct number of column, '6'") {
	 worldBankExtractor.getYear(6) should be (1964)
  }

  test(s"Test getYear given a negative number of column, '${Int.MinValue}'") {
    intercept[IllegalArgumentException]{
	  val year = worldBankExtractor.getYear(Int.MinValue)
    }
  }

  test(s"Test getYear given a empty column, '${Int.MaxValue}'") {
    intercept[IllegalArgumentException]{
	  val year = worldBankExtractor.getYear(Int.MaxValue)
    }
  }

  test("Test getRegion given a correct number of row, '48'") {
	  worldBankExtractor.getRegion(48) should be ("BGD")
  }

  test(s"Test getRegion given a negative number of row, '${Int.MinValue}'") {
    intercept[IllegalArgumentException]{
    	val region = worldBankExtractor.getRegion(Int.MinValue)
    }
  }

  test(s"Test getRegion given a empty row, '${Int.MaxValue}'") {
    intercept[IllegalArgumentException]{
    	val region = worldBankExtractor.getRegion(Int.MaxValue)
    }
  }

  test("Test loadValues, '539'") {
    worldBankExtractor.loadValues.map(_._2.size).foldLeft(0)(_+_) should be (539)
  }
}