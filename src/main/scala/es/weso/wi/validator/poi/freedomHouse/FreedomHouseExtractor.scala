package es.weso.wi.validator.poi.freedomHouse

import es.weso.wi.validator.poi.PoiExtractor
import es.weso.wi.entities.Indicator
import org.apache.poi.hssf.util.CellReference
import org.apache.poi.ss.usermodel.Row
import es.weso.wi.entities.Record
import org.apache.poi.ss.usermodel.Cell
import es.weso.exceptions.ExtractorException
import scala.collection.immutable.Range.Inclusive

class FreedomHouseExtractor(var indicator: String) extends PoiExtractor{
  
  val startCell: String = "C5"
  val prInitialCol = 2
  val prFinalCol = 12
  val clIntialCol = 14
  val clFinalCol = 24
    
  def getIndicator(): Indicator = {
    indicator match {
      case "PR" => new Indicator("FHA")
      case "CL" => new Indicator("FHB")
      case _ => throw new ExtractorException(indicator + " isn't a freedom " +
      		"house indicator")
    }
  }
  
  def getYear(col : Int) : Int = {
    val cell = workbook.getSheet("Countries").getRow(4).getCell(col)
    if(cell == null) 
      throw new IllegalArgumentException("Number of column incorrect")
    obtainNumericCellValue(cell).toInt
  }
  
  def getRegion(row : Int) : String = {
    val cellRow = workbook.getSheet("Countries").getRow(row)
    if(cellRow == null)
      throw new IllegalArgumentException("Number of row incorrect")
    cellRow.getCell(0).getStringCellValue()
  }
  
  def loadValues() : Indicator = {
    val initialCell : CellReference = new CellReference(startCell); 
    val indicator = getIndicator()
    val sheet = workbook.getSheet("Countries")
    val rowStart = initialCell.getRow()
    
    var rowNum = null; var row = null; var colNum = null;
    for{
      rowNum <- rowStart  to sheet.getLastRowNum();
      row = sheet.getRow(rowNum);
      colNum <- getRange
    } {
      val cell = row.getCell(colNum, Row.RETURN_BLANK_AS_NULL)
      if(cell != null){
        def createExtractor() = {
	          try {
	        	  val record = new Record(indicator.name, getRegion(cell.getRowIndex()), 
    			  getYear(cell.getColumnIndex()), obtainNumericCellValue(cell))
	        	  indicator.addRecord(record)
	          } catch {
	          	case e: ExtractorException => Console.err.println(
	              "There is an empty value")
	          }
        }
        createExtractor()
      }        
    }
    indicator
  }
  
  def getRange() : Inclusive = {
    indicator match {
      case "PR" => prInitialCol to prFinalCol
      case "CL" => clIntialCol to clFinalCol
      case _ => throw new ExtractorException(indicator + " isn't a freedom " +
      		"house indicator")
    }
  }

}