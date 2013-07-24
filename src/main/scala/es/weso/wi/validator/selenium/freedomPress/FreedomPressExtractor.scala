package es.weso.wi.validator.selenium.freedomPress

import es.weso.wi.validator.Extractor
import es.weso.wi.entities.Indicator
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By.ByXPath
import scala.collection.JavaConversions._
import es.weso.wi.entities.Record
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.By.ById
import org.openqa.selenium.support.ui.Select
import scala.math.BigDecimal.double2bigDecimal
import es.weso.wi.validator.selenium.SeleniumExtractor
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.DesiredCapabilities
import es.weso.exceptions.ExtractorException
import java.util.NoSuchElementException

class FreedomPressExtractor extends SeleniumExtractor {

  def getIndicator(): Indicator = {
    new Indicator("FREEDOM-INDEX")
  }
  
  def getValue(tds: List[WebElement]): Double = {
    tds.get(2).getText().replace(',', '.').toDouble
  }

  def getRegion(tds: List[WebElement]): String = {
    val result = reconciliator.searchCountryResult(tds.get(1).getText())
    if(result.iso3Code == null)
      throw new IllegalArgumentException("There is no country in Web Index " +
      		"with name " + tds.get(1).getText())
    result.iso3Code
    //reconciliator.searchCountryResult(tds.get(1).getText()).iso3Code
  }
  
  def navigateTo(index: Int) {
    val elem = driver.findElement(new ById("annnes"))

    if (elem == null)
      throw new ExtractorException("annnes select not found in : " + driver.getCurrentUrl())

    val select = new Select(elem)
    
    try{
    	select.selectByIndex(index)
    }catch {
      case e:NoSuchElementException => throw new IllegalArgumentException(s"Bad Index ${index}")
    }
  }

  def retrieveTRs(): List[WebElement] = {
    val trs = driver.findElements(new ByXPath("//*[@id=\"Col2inner\"]/div/div[2]/table/tbody/tr[position() > 1]"))

    if (trs == null)
      throw new ExtractorException("index table not found in : " + driver.getCurrentUrl())

    trs.toList
  }
  
  def loadValues(): Indicator = {

    val indicator = getIndicator

    val years = List[(Int, Int)]((2013, 1), (2012, 2), (2011, 2), (2010, 3), (2009 -> 4),
      (2008 -> 5), (2007 -> 6), (2006 -> 7), (2005 -> 8), (2004 -> 9), (2003 -> 10), (2002 -> 11))

    for (year: (Int, Int) <- years) {

      navigateTo(year._2)

      for (tr <- retrieveTRs) {
        val tds = tr.findElements(new ByXPath("./*"));
        indicator.addRecord(new Record(indicator.name, getRegion(tds.toList),
          year._1, getValue(tds.toList)))
      }
    }
    indicator
  }
}