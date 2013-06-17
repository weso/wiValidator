package es.weso

import es.weso.wi.entities.Record
import es.weso.wi.entities.Indicator
import es.weso.wi.validator.poi.worldBank.WorldBankExtractor
import es.weso.wi.validator.poi.freedomHouse.FreedomHouseExtractor
import es.weso.wi.validator.selenium.freedomPress.FreedomPressExtractor
import es.weso.wi.validator.poi.itu.ITUExtractor
import es.weso.wi.validator.rest.indaba.IndabaExtractor

object Main {

  def main(args: Array[String]): Unit = {
        val indaba = new IndabaExtractor()
        val ws = indaba.loadDataSource("q1")

        println(ws)
  }
}