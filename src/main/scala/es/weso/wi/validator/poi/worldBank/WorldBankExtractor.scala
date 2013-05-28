package es.weso.wi.validator.poi.worldBank

import es.weso.wi.validator.poi.PoiExtractor
import java.io.FileInputStream
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import java.io.File
import org.apache.poi.hssf.usermodel.HSSFSheet
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.hssf.util.CellReference
import es.weso.wi.entities.Indicator
import es.weso.wi.entities.Record
import java.util.Iterator
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet

class WorldBankExtractor extends PoiExtractor {

  def getIndicator(): Indicator = {
    val cr: CellReference = new CellReference("A2")
    val cell = workbook.getSheetAt(1).getRow(cr.getRow()).getCell(cr.getCol())
    new Indicator(cell.getStringCellValue())
  }

  def getYear(col: Int): Int = {
    val cell = workbook.getSheetAt(0).getRow(0).getCell(col)
    cell.getStringCellValue().toInt
  }

  def getCountry(row: Int): String = {
    val cell = workbook.getSheetAt(0).getRow(row).getCell(1)
    cell.getStringCellValue()
  }

  def loadValues(): Indicator = {
    val indicator = getIndicator()
    val sheet: Sheet = workbook.getSheetAt(0);

    val c2: CellReference = new CellReference("C2")
    val rowStart = Math.min(c2.getRow(), sheet.getFirstRowNum());

    var rowNum = null; var row = null; var collBum = null;
    for {
      rowNum <- c2.getRow to sheet.getLastRowNum();
      row = sheet.getRow(rowNum);
      collBum <- c2.getCol to row.getLastCellNum()
    } {
      val cell = row.getCell(collBum, Row.RETURN_BLANK_AS_NULL)
      if (cell != null) {
        val record = new Record(indicator.name, getCountry(cell.getRowIndex()),
          getYear(cell.getColumnIndex()), cell.getNumericCellValue())
        indicator.addRecord(record)
      }
    }

    indicator
  }

}