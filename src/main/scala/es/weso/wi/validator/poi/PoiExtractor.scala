package es.weso.wi.validator.poi

import es.weso.wi.entities.Indicator
import es.weso.wi.validator.Extractor
import sun.reflect.generics.reflectiveObjects.NotImplementedException
import java.io.InputStream
import org.apache.poi.ss.usermodel.Workbook
import java.io.FileInputStream
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import java.io.File

trait PoiExtractor extends Extractor {

  var file: InputStream = null
  var workbook: Workbook = null

  def loadWorkbook(path: String, absolutPath: Boolean) = {
    val currentFile = if (absolutPath)
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

  def getIndicator(): Indicator

  def getYear(col: Int): Int

  def getRegion(row: Int): String

  def loadValues(): Indicator

}