package controllers

import javax.inject.Inject
import actors.NotificationActor
import akka.actor.ActorSystem
import models.FavouriteStudio
import play.api.cache._
import play.api.libs.json.Json
import play.api.mvc._


/**
  * Created by 901124 on 10/14/16.
  */

class FavouriteStudios @Inject() (cached: Cached, cacheApi: CacheApi, system: ActorSystem) extends Controller {
  lazy val notificationActor = system.actorOf(NotificationActor.props)
  private def clearCaches(userId: Int, studioId: Int) =
    List(
      "find_"+userId+ "_" +studioId,
      "findAll_" +userId
    ).map { key =>
      cacheApi.remove(key)
    }

  def add(userId: Int, studioId: Int) = Action {
    val favourite = FavouriteStudio.addFavourite(userId, studioId)
    clearCaches(userId, studioId)

    // Send a message to the notification Actor describing the added favourite
    notificationActor ! favourite

    Ok(Json.obj("result" -> favourite))
  }

  def remove(userId: Int, studioId: Int) = Action {
    val favourite = FavouriteStudio.delete(userId, studioId)
    clearCaches(userId, studioId)

    Ok(Json.obj("result" -> favourite))
  }

  def find(userId: Int, studioId: Int) = cached("find_" +userId + "_" +studioId) {
    Action {
      val oFavourite = FavouriteStudio.find(userId, studioId)

      oFavourite match {
        case None => Ok(Json.obj("result" -> "NOT FOUND"))
        case Some(favourite) => Ok(Json.obj("result" -> favourite))
      }

    }
  }

  def findAll(userId: Int) = cached("findAll_"+userId) {
    Action {
      val allFavourites = FavouriteStudio.findAllByUser(userId)

      Ok(Json.obj("result" -> allFavourites))

    }
  }
}
