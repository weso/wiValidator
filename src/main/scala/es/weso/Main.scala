package es.weso

import es.weso.wi.entities.Record
import es.weso.wi.entities.Indicator
import es.weso.wi.validator.poi.worldBank.WorldBankExtractor

object Main {

  def main(args: Array[String]): Unit = {
    val indicator = new Indicator("ITUE")
    val record1 = new Record("ITUE","ESP", 1970, 20)
    val record2 = new Record("ITUE","JAP", 1979, 2.0)
    val record3 = new Record("ITUE","FRA", 1970, 20.67)
    val record4 = new Record("ITUE","USA", 1995, 10)
    indicator.addRecord(record1)
    indicator.addRecord(record2)
    indicator.addRecord(record3)
    indicator.addRecord(record4)
    indicator.removeRecord(record1)
    
    val extractor = new WorldBankExtractor
    extractor.loadDataSource("/home/cesarla/Descargas/AG.LND.IRIG.AG.ZS_Indicator_MetaData_en_EXCEL.xls");
  }

}