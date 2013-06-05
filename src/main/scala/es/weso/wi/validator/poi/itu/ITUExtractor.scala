package es.weso.wi.validator.poi.itu

import es.weso.wi.validator.poi.PoiExtractor
import es.weso.wi.entities.Indicator
import org.apache.poi.hssf.util.CellReference
import org.apache.poi.ss.usermodel.Row
import es.weso.exceptions.ExtractorException
import es.weso.wi.entities.Record

class ITUExtractor(initialColumn: Int, finalColumn: Int, indicatorCell: String)
  extends PoiExtractor {

  def getIndicator(): Indicator = {
    val cr: CellReference = new CellReference(indicatorCell)
    val cell = workbook.getSheetAt(0).getRow(cr.getRow()).getCell(cr.getCol())
    new Indicator(cell.getStringCellValue().trim())
  }

  def getYear(col: Int): Int = {
    if (col < initialColumn || col > finalColumn)
      throw new IllegalArgumentException("Invalid number of column")
    val cell = workbook.getSheetAt(0).getRow(1).getCell(col)
    obtainNumericCellValue(cell).toInt
  }

  def getRegion(row: Int): String = {
    val cellRow = workbook.getSheetAt(0).getRow(row)
    if (cellRow == null || row < 2)
      throw new IllegalArgumentException("Invalid number of row")
    val cell = cellRow.getCell(0)
    cell.getStringCellValue()
  }

  def loadValues(): Indicator = {
    val startCell: CellReference = new CellReference(2, initialColumn)
    val indicator = getIndicator
    val sheet = workbook.getSheetAt(0)
    val rowStart = startCell.getRow

    var rowNum = null; var row = null; var colNum = null
    for {
      rowNum <- rowStart to sheet.getLastRowNum;
      row = sheet.getRow(rowNum);
      colNum <- initialColumn to finalColumn
    } {
      val cell = row.getCell(colNum, Row.RETURN_BLANK_AS_NULL)
      if (cell != null) {
        def createRecord() = {
          try {
            val record = new Record(indicator.name, getRegion(cell.getRowIndex()),
              getYear(cell.getColumnIndex()), obtainNumericCellValue(cell))
            indicator.addRecord(record)
          } catch {
            case e: ExtractorException => Console.err.println(
              "There is an empty value")
          }
        }
        createRecord()
      }
    }
    indicator
  }

}