package services.schedulers

import javax.inject.{Inject, Named, Singleton}
import play.api.libs.concurrent.Execution.Implicits.defaultContext

import akka.actor.{ActorRef, ActorSystem}

/**
  * Created by orkun on 25/02/17.
  */
@Singleton
class WebsiteUpdateScheduler @Inject() (val system: ActorSystem, @Named("scheduler-actor") val schedulerActor: ActorRef){


}
