package es.weso

import es.weso.wi.entities.Record
import es.weso.wi.entities.Indicator
import es.weso.wi.validator.poi.worldBank.WorldBankExtractor
import es.weso.wi.validator.poi.freedomHouse.FreedomHouseExtractor

object Main {

  def main(args: Array[String]): Unit = {
      val extractor = new FreedomHouseExtractor("PR")
      val indicator : Indicator = extractor.loadDataSource("C:/Nacho/Web_Foundation_Index/wiValidator/src/test/resources/files/CL.xls"
          , false)   
      println(indicator.regions)     
          
  }
}