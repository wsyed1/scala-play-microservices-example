package models

import play.api.libs.json._
import play.api.libs.functional.syntax._
import models.dao.FavouriteStudiosDAO

/**
  * Created by 901124 on 10/14/16.
  */
object FavouriteStudio {

  implicit val favouriteStudioWrites: Writes[FavouriteStudio] = (
    (JsPath \ "userId").write[Int] and
      (JsPath \ "studioId").write[Int]
    )(unlift(FavouriteStudio.unapply))

  def addFavourite(userId: Int, studioId: Int): FavouriteStudio = {
    val favourite = FavouriteStudio(userId, studioId)
    FavouriteStudiosDAO.add(favourite)

    favourite
  }

  def delete(userId: Int, studioId: Int) =
    FavouriteStudiosDAO.delete(FavouriteStudio(userId, studioId))

  def findAllByUser(userId: Int) : List[FavouriteStudio] =
    FavouriteStudiosDAO.index(userId)

  def find(userId: Int, studioId: Int): Option[FavouriteStudio] = {
    val favourite = FavouriteStudio(userId, studioId)
    if(FavouriteStudiosDAO.exists(favourite))
      Some(favourite)
    else
      None
  }
}

case class FavouriteStudio(userId: Int, studioId: Int)
