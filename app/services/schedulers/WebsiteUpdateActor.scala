package services.schedulers

import javax.inject.Inject

import akka.actor.{Actor, Props}
import net.ruippeixotog.scalascraper.browser.JsoupBrowser.JsoupDocument
import org.jsoup.nodes.Document
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.ws.WSClient
import services.WebsiteParser.HTMLParser
import services.schedulers.WebsiteUpdateActor.update

/**
  * Created by orkun on 25/02/17.
  */
class WebsiteUpdateActor @Inject()(WSClient: WSClient, HTMLParser: HTMLParser) extends Actor {
  override def receive: Receive = {
    case update(url: String, parseFormat: String) => {
      for{
        doc <- WSClient.url(url).get()
        elements <- HTMLParser.tryParse(JsoupDocument(Document.createShell(doc.body)), parseFormat)
      } yield {
        elements.
      }
    }
  }
}
object WebsiteUpdateActor {
  def props = Props[WebsiteUpdateActor]

  case class update(url: String, parseFormat: String)
}

