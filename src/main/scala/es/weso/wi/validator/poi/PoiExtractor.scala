package es.weso.wi.validator.poi

import es.weso.wi.validator.Extractor
import sun.reflect.generics.reflectiveObjects.NotImplementedException

trait PoiExtractor extends Extractor{
  
	def loadDataSource(indicator:String) = {
	   throw new NotImplementedException
	}
	
}