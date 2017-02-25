package services.database

import javax.inject._

import akka.actor.ActorSystem
import models.User
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import slick.driver.PostgresDriver.api._

import scala.concurrent.Future


/**
  * Created by orkun on 18/02/17.
  */
@Singleton
class DatabaseController @Inject()(protected val dbConfigProvider: DatabaseConfigProvider, val system: ActorSystem) extends  HasDatabaseConfigProvider[JdbcProfile] with SlickMapping {

  def insertUser(user: User): Future[Int] = db.run(UserTable += user)

  def updateUser(prevUserToken: String, newToken: String): Future[Int] = db.run(UserTable.filter(_.userToken === prevUserToken).map(user => user.userToken).update(newToken))

  def websites = db.run(WebsiteTable.result)
}
