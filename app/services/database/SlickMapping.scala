package services.database

import models.User
import play.api.db.slick.HasDatabaseConfigProvider
import slick.driver.JdbcProfile
import slick.lifted.{TableQuery, Tag}
import slick.driver.PostgresDriver.api._

/**
  * Created by orkun on 18/02/17.
  */
trait SlickMapping { self: HasDatabaseConfigProvider[JdbcProfile] =>

  val UserTable = TableQuery[UserTable]

  class UserTable(tag: Tag) extends Table[User](tag, "Users") {

    def userID = column[Int]("userID", O.PrimaryKey, O.AutoInc)
    def userToken = column[String]("userToken")
    def groupID = column[Int]("groupID")

    def groupID_FK = foreignKey("roomId", roomID, RoomTable)(_.roomID, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Cascade)

    override def * = (roomID.?, password) <> (Room.tupled, Room.unapply)
  }

  val CharacterTable = TableQuery[CharacterTable]

  class CharacterTable(tag: Tag) extends Table[Character](tag, "characters") {

    def roomID = column[Int]("room_id")
    def roomID_FK = foreignKey("roomId", roomID, RoomTable)(_.roomID, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Cascade)
    def character = column[String]("character")

    override def * = (roomID, character) <> (Character.tupled, Character.unapply)
  }
}