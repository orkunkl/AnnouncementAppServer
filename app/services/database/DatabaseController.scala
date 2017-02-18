package services.database

import javax.inject._

import akka.actor.ActorSystem
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile
import play.api.libs.concurrent.Execution.Implicits.defaultContext


/**
  * Created by orkun on 18/02/17.
  */
@Singleton
class DatabaseController @Inject()(protected val dbConfigProvider: DatabaseConfigProvider, val system: ActorSystem) extends  HasDatabaseConfigProvider[JdbcProfile]
   with SlickMapping {

}
