package es.weso.wi.validator.rest

import es.weso.wi.validator.Extractor
import org.apache.http.impl.client.DefaultHttpClient
import org.apache.http.client.methods.HttpGet
import es.weso.wi.entities.Horse
import scala.collection.mutable.HashMap
import scala.util.parsing.json.JSON
import scala.io.Source

trait RestExtractor extends Extractor {

  /**
   * Returns the text content from a REST URL. Returns a blank String if there
   * is a problem. (Probably should use Option/Some/None; I didn't know about it
   * back then.)
   */
  def getRestContent(url: String): String = {
    val httpClient = new DefaultHttpClient()
    val httpResponse = httpClient.execute(new HttpGet(url))
    val entity = httpResponse.getEntity()
    var content = ""
    if (entity != null) {
      val inputStream = entity.getContent()
      content = io.Source.fromInputStream(inputStream).getLines.mkString
      inputStream.close
    }
    httpClient.getConnectionManager().shutdown()
    return content
  }
}