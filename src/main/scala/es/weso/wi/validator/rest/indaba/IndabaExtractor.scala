package es.weso.wi.validator.rest.indaba

import es.weso.wi.validator.rest.RestExtractor
import scala.util.parsing.json.JSON
import es.weso.wi.entities.Indicator
import es.weso.wi.entities.Record
import es.weso.wi.entities._
import scala.collection.immutable.HashMap
import es.weso.wi.entities.JsMap

case class IndabaExtractor(val horsePath:String = "src/main/resources/files/Horseids.json")  extends RestExtractor {

  val indabaPath = "https://www.indabaplatform.com:443/ids"
  val categories: List[Int] = loadCategoryIds("src/main/resources/files/IndabaCategories.json")
  val horses : Map[Int,Horse] = loadHorseIds(horsePath)
  var indicators: Map[String, Indicator] = Map.empty
  var path :String = null
  
  def loadHorseIds(path: String) = {
    val source = scala.io.Source.fromFile(path)
    val lines = source.mkString
    source.close()
    val json = JSON.parseFull(lines)
    val list = for {
      Some(JsMap(map)) <- List(json)
      JsList(horses) = map("horseids")
      JsMap(horse) <- horses
      JsDouble(horseid) = horse("horseid")
      JsString(target) = horse("target")
      JsString(iso2) = horse("iso-2")
      JsString(iso3) = horse("iso-3")
    } yield {
      Horse(horseid.toInt, target, iso2, iso3)
    }
    list.map(horse => horse.id -> horse).toMap
  }

  def loadCategoryIds(path: String) = {
    val source = scala.io.Source.fromFile(path)
    val lines = source.mkString
    source.close()
    val json = JSON.parseFull(lines)
    for {
      Some(JsMap(map)) <- List(json)
      JsList(horses) = map("categories")
      JsMap(category) <- horses
      JsDouble(horseid) = category("questionSetId")
    } yield {
      horseid.toInt
    }
  }

  def getVerticalScorecardQuestionSetDetailService(horseId: Int, questionSetId: Int, version: Int = 1) = {

    val country = horses.getOrElse(horseId, null)

    if (country != null) {
      val query = s"${indabaPath}/vcardQuestionSet.do?horseId=${horseId}&questionSetId=${questionSetId}&version=${version}"
      val data = getRestContent(query)
      val json = JSON.parseFull(data)

      for {
        Some(JsMap(map)) <- List(json)
        JsMap(data) = map("data")
        JsList(questions) = data("questions")
        JsMap(question) <- questions
        JsDouble(score) = question("score")
        JsString(publicName) = question("publicName")
      } {
        addRecord(Record(s"q${publicName}", country.iso2, 2013, score))
      }
    } else throw new Exception(s"HorseId: ${horseId} undefined")
  }

  def addRecord(record: Record) {
    val indicator = indicators.getOrElse(record.name, Indicator(record.name))
    indicator.addRecord(record)
    indicators += record.name -> indicator
  }

  override def loadDataSource(path: String = "", relativePath: Boolean = false): Map[String, Indicator] = {
    this.path = path
    loadValues()
  }

  def loadIndabaData() = {
    for {
      category <- categories
      horse <- horses
    } {
      getVerticalScorecardQuestionSetDetailService(horse._1, category)
    }
  }

  def loadValues() : Map[String, Indicator] = {
    if (indicators isEmpty) {
      for {
        category <- categories
        horse <- horses
      } {
        getVerticalScorecardQuestionSetDetailService(horse._1, category)
      }
    }
    indicators
  }

  def getIndicator(): Indicator = {
    null
  }
}