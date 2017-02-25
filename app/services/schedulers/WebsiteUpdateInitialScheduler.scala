package services.schedulers

import javax.inject.{Inject, Named}

import akka.actor.{ActorRef, ActorSystem}
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.DatabaseController

/**
  * Created by orkun on 25/02/17.
  */
class WebsiteUpdateInitialScheduler @Inject()(databaseController: DatabaseController, system: ActorSystem, @Named("scheduler-actor") val schedulerActor: ActorRef){

}
