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
import javax.management.BadAttributeValueExpException
import org.openqa.selenium.internal.seleniumemulation.GetLocation

class WorldBankExtractor extends PoiExtractor {

  val indicatorCell = "A2"
  val startCell = "C2"

  def getIndicator(): Indicator = {
    val cr: CellReference = new CellReference(indicatorCell)
    val cell = workbook.getSheetAt(1).getRow(cr.getRow()).getCell(cr.getCol())
    new Indicator(cell.getStringCellValue())
  }

  def getYear(col: Int): Int = {
    val cell = workbook.getSheetAt(0).getRow(0).getCell(col)
    if(cell==null)
      throw new IllegalArgumentException
    obtainNumericCellValue(cell).toInt
  }

  def getRegion(row: Int): String = {
    val cellRow = workbook.getSheetAt(0).getRow(row)
    if(cellRow==null)
      throw new IllegalArgumentException
    cellRow.getCell(1).getStringCellValue()
  }

  def loadValues(): Indicator = {
    val indicator = getIndicator()
    val sheet: Sheet = workbook.getSheetAt(0);

    val c2: CellReference = new CellReference(startCell)
    val rowStart = Math.min(c2.getRow(), sheet.getFirstRowNum());

    var rowNum = null; var row = null; var collBum = null;
    for {
      rowNum <- c2.getRow to sheet.getLastRowNum();
      row = sheet.getRow(rowNum);
      collBum <- c2.getCol to row.getLastCellNum()
    } {
      val cell = row.getCell(collBum, Row.RETURN_BLANK_AS_NULL)
      if (cell != null) {
        val record = new Record(indicator.name, getRegion(cell.getRowIndex()),
          getYear(cell.getColumnIndex()), obtainNumericCellValue(cell))
        indicator.addRecord(record)
      }
    }

    indicator
  }

}