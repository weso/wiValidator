package es.weso


import es.weso.wi._
import es.weso.wi.validator.selenium.freedomPress.FreedomPressExtractor

object Main {

  def main(args: Array[String]): Unit = {
      val foo = new FreedomPressExtractor()
      println(foo.loadDataSource("http://en.rsf.org/press-freedom-index-2013,1054.html").size)
  }
}