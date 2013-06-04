package es.weso.wi.entities

class Indicator (val name:String){
  
	var years : Map[Int, Map[String, Record]] = Map.empty
	var regions : Map[String, Map[Int, Record]] = Map.empty

	def getRecordsByRegion(region : String) : Map[Int, Record] = {
	  regions.getOrElse(region, Map.empty)
	}
	
	def getRecordsByYear(year : Int) : Map[String, Record] = {
	  years.getOrElse(year, Map.empty)
	}
	
	def getRecord(region :String, year:Int):Record = {
	 	val reg = regions.getOrElse(region, null)
	 	val value = reg.getOrElse(year, null)
	 	if(value == null)
	 	  throw new Exception
	 	value
	}
	
	def addRecord(record:Record){
	  addRecordByRegion(record)
	  addRecordByYear(record)
	}
	
	def removeRecord(record:Record){
	  removeRecordByRegion(record)
	  removeRecordByYear(record)
	}
	
	def size() : Int = {
	  years.map(a=>a._2.size).foldLeft(0)(_ + _)
	}
	
	
	
	private def addRecordByYear(record:Record){
	  val map = years.getOrElse(record.year, Map.empty[String, Record])
	  years.+=(record.year -> map.+(record.region->record))
	}

	private def addRecordByRegion(record:Record){
	  val map = regions.getOrElse(record.region, Map.empty[Int, Record])
	  regions.+=(record.region -> map.+(record.year->record))
	}
	
	private def removeRecordByYear(record:Record){
	  var map = years.getOrElse(record.year, Map.empty[String, Record])
	  map.-=(record.region)
	  if(map.isEmpty)
		 years=years.filterNot(_._1==record.year)
	  else
		  years.+=(record.year -> map)
	}

	private def removeRecordByRegion(record:Record){
	  var map = regions.getOrElse(record.region, Map.empty[Int, Record])
	  map.-=(record.year)
	  if(map.isEmpty)
		 regions=regions.filterNot(_._1==record.region)
	  else
		  regions.+=(record.region -> map)
	}
}