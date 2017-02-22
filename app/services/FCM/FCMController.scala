package services.FCM

import javax.inject.Inject

import play.api.libs.json.Json
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.ws.WSClient

import scala.concurrent.Future

/**
  * Created by orkun on 20/02/17.
  */
@Singleton
class FCMConfig @Inject()(configuration: play.api.Configuration){
  final val authenticationKey = configuration.underlying.getString("FCMAuthenticationKey")
  final val FCMUrl = configuration.underlying.getString("FCM_URL")
  final val collapse_key = "1"
}

class FCMController @Inject()(WSClient: WSClient, fCMConfig: FCMConfig) extends FCMTrait {

  def checkUserExistence(userKey: String): Future[Option[Unit]] = {
    WSClient.url(fCMConfig.FCMUrl).withHeaders("Authorization:key" -> fCMConfig.authenticationKey).post(Json.obj("to" -> userKey)).map(response =>
      if((response.json \ "success").get.toString() == "1") Some(Unit) else None)
  }

  def sendData(topicKey: String, data: String): Future[Option[Unit]] = {
    WSClient.url(fCMConfig.FCMUrl).withHeaders("Authorization:key" -> fCMConfig.authenticationKey).
      post(Json.obj("to" -> topicKey, "data" -> data, "collapse_key" -> fCMConfig.collapse_key)).map(response =>
      if((response.json \ "success").get.toString() == "1") Some(Unit) else None)
  }
}
