package services.FCM

import javax.inject.{Inject, Singleton}

import play.api.libs.ws.WSClient

/**
  * Created by orkun on 20/02/17.
  */
@Singleton
class FCMController @Inject()(configuration: play.api.Configuration, WSClient: WSClient){
  private final val authenticationKey = configuration.underlying.getString("FCMAuthenticationKey")
}
