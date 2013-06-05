package es.weso.wi.validator.poi

import es.weso.wi.entities.Indicator
import es.weso.wi.validator.Extractor
import sun.reflect.generics.reflectiveObjects.NotImplementedException
import java.io.InputStream
import org.apache.poi.ss.usermodel.Workbook
import java.io.FileInputStream
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import java.io.File
import org.apache.poi.ss.usermodel.Cell
import es.weso.exceptions.ExtractorException

trait PoiExtractor extends Extractor {

  var file: InputStream = null
  var workbook: Workbook = null

  def loadWorkbook(path: String, relativePath: Boolean) = {
    val currentFile = if (relativePath)
      new File(getClass().getClassLoader().getResource(path).getPath())
    else
      new File(path)
    file = new FileInputStream(currentFile)
    workbook = new HSSFWorkbook(file)
  }
  
  def closeWorkbook() {
    file.close()
  }

  def loadDataSource(path: String, relativePath: Boolean = false): Indicator = {
    loadWorkbook(path, relativePath)
    val indicator = loadValues()
    closeWorkbook
    indicator
  }

  def getYear(col: Int): Int

  def getRegion(row: Int): String
  
  def obtainNumericCellValue(cell : Cell) : Double = {
    cell.getCellType() match {
      case Cell.CELL_TYPE_BLANK => throw new ExtractorException(
          "Value of the cell is not a number")
      case Cell.CELL_TYPE_NUMERIC => cell.getNumericCellValue
      case Cell.CELL_TYPE_STRING => {
    	  try {
    	    cell.getStringCellValue().toInt
    	  } catch {
    	    case e: Exception => throw new ExtractorException(
    	        "Value of the cell is not a number") 
    	  }
        }
      case _ => throw new ExtractorException("Value of the cell " +
      		"is not a number")
    }
  }

}