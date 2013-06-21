package es.weso.wi.validator

import sun.reflect.generics.reflectiveObjects.NotImplementedException
import es.weso.wi.entities.Indicator

trait Extractor {

  def loadDataSource(path: String, relativePath: Boolean = false): Map[String, Indicator]

  def loadValues():  Map[String, Indicator]

  def checkValue(indicator: String, region: String, value: String) = {
    throw new NotImplementedException
  }

  def checkValue(indicator: String, region: String, value: String, year: String) = {
    throw new NotImplementedException
  }

}