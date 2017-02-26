package services.WebsiteParser

import javax.inject.Inject

import net.ruippeixotog.scalascraper.browser.JsoupBrowser.JsoupDocument
import net.ruippeixotog.scalascraper.scraper._
import net.ruippeixotog.scalascraper.dsl.DSL._
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL.Parse._
import net.ruippeixotog.scalascraper.model.{Document, Element}
import play.api.libs.ws.WSClient

import scala.collection.JavaConverters
import scala.concurrent.Future
/**
  * Created by or
  * kun on 22/02/17.
  */
class HTMLParser @Inject()(){

  def tryParse(doc: JsoupDocument, parseFormat: String): Future[Option[Iterable[String]]] = Future { doc >?> extractor(parseFormat, texts) }

}
