package controllers

import akka.actor.ActorSystem
import javax.inject._

import net.ruippeixotog.scalascraper.browser.JsoupBrowser
import net.ruippeixotog.scalascraper.browser.JsoupBrowser.JsoupDocument
import net.ruippeixotog.scalascraper.dsl.DSL._
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL.Parse._
import org.jsoup.nodes.Document
import play.api._
import play.api.libs.ws.WSClient
import play.api.mvc._
import services.database.DatabaseController

import scala.concurrent.{ExecutionContext, Future, Promise}
import scala.concurrent.duration._

/**
 * This controller creates an `Action` that demonstrates how to write
 * simple asynchronous code in a controller. It uses a timer to
 * asynchronously delay sending a response for 1 second.
 *
 * @param actorSystem We need the `ActorSystem`'s `Scheduler` to
 * run code after a delay.
 * @param exec We need an `ExecutionContext` to execute our
 * asynchronous code.
 */
@Singleton
class ApiController @Inject()(databaseController: DatabaseController, actorSystem: ActorSystem, WSClient: WSClient)(implicit exec: ExecutionContext) extends Controller {

  def message = Action.async {
    WSClient.url("http://bilmuh.ege.edu.tr/").get().map(
      response => {
        JsoupDocument(Document.createShell(response.body))
        Ok("")
      }
    )
  }

  private def getFutureMessage(delayTime: FiniteDuration): Future[String] = {
    val promise: Promise[String] = Promise[String]()
    actorSystem.scheduler.scheduleOnce(delayTime) { promise.success("Hi!") }
    promise.future
  }

}
