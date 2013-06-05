package es.weso.wi.validator.selenium

import es.weso.wi.entities.Indicator
import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import com.sun.java.util.jar.pack.Driver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.remote.RemoteWebDriver
import es.weso.wi.validator.Extractor

trait SeleniumExtractor extends Extractor{

  var driver: WebDriver = null

  def loadDriver() {
    driver = new FirefoxDriver
  }

  def closeDriver() {
    driver.close()
  }

  def loadDataSource(path: String, relativePath: Boolean = false): Indicator = {
    loadDriver()
    driver.get(path)
    val indicator = loadValues()
    closeDriver()
    indicator
  }

}