package es.weso

import es.weso.wi.entities.Record
import es.weso.wi.entities.Indicator
import es.weso.wi.validator.poi.worldBank.WorldBankExtractor
import es.weso.wi.validator.poi.freedomHouse.FreedomHouseExtractor
import es.weso.wi.validator.poi.itu.ITUExtractor

object Main {

  def main(args: Array[String]): Unit = {
    
    val extractor = new WorldBankExtractor
    extractor.loadDataSource("files/AG.LND.IRIG.AG.ZS.xls", true)
          
  }
}