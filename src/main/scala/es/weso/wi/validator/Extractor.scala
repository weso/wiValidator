package es.weso.wi.validator

import sun.reflect.generics.reflectiveObjects.NotImplementedException

trait Extractor {	
  
	def checkValue(indicator:String, region:String, value: String) = {
	   throw new NotImplementedException
	}
	
	def checkValue(indicator:String, region:String, value: String, year: String) = {
	   throw new NotImplementedException
	}
	
}