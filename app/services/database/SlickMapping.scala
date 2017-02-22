package services.database

import models._
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

    override def * = (userID.?, userToken) <> (User.tupled, User.unapply)
  }

  val GroupTable = TableQuery[GroupTable]

  class GroupTable(tag: Tag) extends Table[Group](tag, "Groups") {

    def groupID = column[Int]("groupID", O.PrimaryKey, O.AutoInc)
    def groupToken = column[String]("groupToken")
    def websiteID = column[Int]("websiteID")

    def websiteID_FK = foreignKey("websiteID_FK", websiteID, WebsiteTable)(_.websiteID, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Cascade)

    override def * = (groupID.?, groupToken, websiteID) <> (Group.tupled, Group.unapply)
  }

  val UserGroupRelationTable = TableQuery[UserGroupRelationTable]

  class UserGroupRelationTable(tag: Tag) extends Table[UserGroupRelation](tag, "User_Group_Relation") {

    def rowID = column[Int]("ID", O.PrimaryKey, O.AutoInc)
    def userID = column[Int]("userID")
    def groupID = column[Int]("groupID")


    override def * = (rowID.?, userID, groupID) <> (UserGroupRelation.tupled, UserGroupRelation.unapply)
  }
  val WebsiteTable = TableQuery[WebsiteTable]

  class WebsiteTable(tag: Tag) extends Table[Website](tag, "Websites") {

    def websiteID = column[Int]("websiteID", O.PrimaryKey, O.AutoInc)
    def link = column[String]("link")
    def parseFormat = column[String]("parseFormat")

    override def * = (websiteID.?, link, parseFormat) <> (Website.tupled, Website.unapply)
  }
}