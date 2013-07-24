package es.weso.wi.validator

import sun.reflect.generics.reflectiveObjects.NotImplementedException
import es.weso.wi.entities.Indicator
import es.weso.reconciliator.CountryReconciliator

trait Extractor {
  
  protected val reconciliator : CountryReconciliator = new CountryReconciliator(
      "files/countries.json", true)

  def loadDataSource(path: String, relativePath: Boolean = false): Indicator

  def loadValues(): Indicator

  def getIndicator(): Indicator

  def checkValue(indicator: String, region: String, value: String) = {
    throw new NotImplementedException
  }

  def checkValue(indicator: String, region: String, value: String, year: String) = {
    throw new NotImplementedException
  }

}