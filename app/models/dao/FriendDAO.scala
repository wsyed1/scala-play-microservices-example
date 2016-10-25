package models.dao

import java.sql.{Connection, DriverManager}

import com.typesafe.config.ConfigFactory
import models.{FavouriteStudio, Friend}

import scala.collection.mutable.ListBuffer
import scala.concurrent.Future

/**
  * Created by 901124 on 10/21/16.
  */
object FriendDAO {

  private final val mysql = ConfigFactory.load getConfig "mysql"
  private final val url = mysql getString("url")
  private final val driver = mysql getString("driver")
  private final val username = mysql getString("username")
  private final val password = mysql getString("password")
  private var connection:Connection = _
  getConnection


  def index(userId: Int) : List[Friend] = {

    val conn: Connection = DriverManager.getConnection(url, username, password)
    val ps = conn.prepareStatement("Select userId, friends FROM `favourites-svc`.`users` WHERE userId =?")
    ps.setString(1, userId.toString)
    val rs = ps.executeQuery()

    var friends = new ListBuffer[Friend]()

    while (rs.next()) {
      friends.+=(new Friend(rs.getString("friends").toInt))
    }

    friends.toList

  }


  private def getConnection(): Unit ={
    if(connection == null) {
      try{
        Class.forName(driver)
        connection = DriverManager.getConnection(url, username, password)
      } catch {
        case e: Exception => e.printStackTrace
      }
    }
  }

  private def closeConnection(): Unit ={
    connection = null;
  }
}
