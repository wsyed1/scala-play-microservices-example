package models.dao

import java.sql.{Connection, DriverManager}
import com.typesafe.config.ConfigFactory
import models.FavouriteStudio
import scala.collection.mutable.ListBuffer

/**
  * Created by 901124 on 10/16/16.
  */
object FavouriteStudiosDAO {
  private final val mysql = ConfigFactory.load getConfig "mysql"
  private final val url = mysql getString("url")
  private final val driver = mysql getString("driver")
  private final val username = mysql getString("username")
  private final val password = mysql getString("password")
  private var connection:Connection = _
  getConnection

  def add(favourite: FavouriteStudio) = {
    val conn: Connection = DriverManager.getConnection(url, username, password)
    val ps = conn.prepareStatement("INSERT INTO favouriteStudio (userId,studioId) VALUES (?, ?)")
    ps.setString(1, favourite.userId.toString)
    ps.setString(2, favourite.studioId.toString)
    val rs = ps.executeUpdate()
    rs
  }

  def delete(favourite: FavouriteStudio) = {
    val conn: Connection = DriverManager.getConnection(url, username, password)
    val ps = conn.prepareStatement("DELETE FROM `favourites-svc`.`favouriteStudio` WHERE `userId`=? and`studioId`=?")
    ps.setString(1, favourite.userId.toString)
    ps.setString(2, favourite.studioId.toString)
    val rs = ps.executeUpdate()
    rs
  }
  def exists(favourite: FavouriteStudio) : Boolean ={
    val conn: Connection = DriverManager.getConnection(url, username, password)
    val ps = conn.prepareStatement("Select COUNT(*) AS 'numMatches' FROM `favourites-svc`.`favouriteStudio` WHERE userId = ? and studioId = ?")
    ps.setString(1, favourite.userId.toString)
    ps.setString(2, favourite.studioId.toString)
    val rs = ps.executeQuery()
    rs.next()

    rs.getInt(1) != 0

  }

  def index(userId: Int) : List[FavouriteStudio] = {

    val conn: Connection = DriverManager.getConnection(url, username, password)
    val ps = conn.prepareStatement("Select userId, studioId FROM `favourites-svc`.`favouriteStudio` WHERE userId =?")
    ps.setString(1, userId.toString)
    val rs = ps.executeQuery()

    var favourites = new ListBuffer[FavouriteStudio]()

    while (rs.next()) {
      favourites.+=(new FavouriteStudio(rs.getString("userId").toInt, rs.getString("studioId").toInt))
    }

    favourites.toList

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
