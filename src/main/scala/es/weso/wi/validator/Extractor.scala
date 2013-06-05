package es.weso.wi.validator

import sun.reflect.generics.reflectiveObjects.NotImplementedException
import es.weso.wi.entities.Indicator

trait Extractor {

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