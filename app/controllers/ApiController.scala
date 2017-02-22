package controllers

import akka.actor.ActorSystem
import javax.inject._

import models.User
import net.ruippeixotog.scalascraper.browser.JsoupBrowser
import net.ruippeixotog.scalascraper.browser.JsoupBrowser.JsoupDocument
import net.ruippeixotog.scalascraper.dsl.DSL._
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL.Parse._
import org.jsoup.nodes.Document
import play.api._
import play.api.libs.json.{JsError, JsResult, JsSuccess}
import play.api.libs.ws.WSClient
import play.api.mvc._
import services.FCM.FCMController
import services.database.DatabaseController

import scala.Unit
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
class ApiController @Inject()(FCMController: FCMController, databaseController: DatabaseController, actorSystem: ActorSystem, WSClient: WSClient)(implicit exec: ExecutionContext) extends Controller {

  def message = Action.async {
    WSClient.url("http://bilmuh.ege.edu.tr/").get().map(
      response => {
        JsoupDocument(Document.createShell(response.body))
        Ok("")
      }
    )
  }

  /**
    *
    *   User Related
    *
    * */
  def addUser = Action.async(parse.json) { request =>

    (request.body \ "userKey").validate[String] match {
      case s: JsSuccess[String] => {
        FCMController.checkUserExistence(s.get).flatMap(
          _ match {
            case Some(asd) => databaseController.insertUser(User(None, s.value)).map(_ => Ok(""))
            case None => Future(NotFound)
          }
        )
      }
      case e: JsError =>  Future(Ok(""))
    }
  }

  def updateUserToken = Action.async(parse.json) { request =>
    val validate = for {
      prevUserKey <- (request.body \ "userKey").validate[String]
      newUserKey <- (request.body \ "newUserKey").validate[String]
      userExists <- FCMController.checkUserExistence(prevUserKey)
    } yield {
      databaseController.updateUser(prevUserKey, newUserKey).map(_ => Ok(""))
    }
    validate.getOrElse(
      Future(NotFound)
    )
  }
}
