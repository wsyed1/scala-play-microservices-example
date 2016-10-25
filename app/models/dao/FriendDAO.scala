package models.dao

import com.google.inject.{Inject, Singleton}
import com.typesafe.config.ConfigFactory
import scala.concurrent._
import models.Friend
import play.api.libs.ws._


/**
  * Created by 901124 on 10/19/16.
  */

class FriendDAO @Inject()  (ws: WSClient, friend: Friend) {

  private final val backend = ConfigFactory.load getConfig "backend"
  private final val friendServiceUrl = backend getString("service.backend.url") + "/friends"


  def index(userId: Int)(implicit ec: ExecutionContext): Future[List[Friend]] = {
    val holder: WSRequest = ws.url(friendServiceUrl).withQueryString("userId" -> userId.toString)
    val fResponse = holder.get()
    fResponse.map { response =>

      val friendsIds: List[Int] = (response.json \ "result" \\ "friendId").map(_.as[Int]).toList

      friendsIds.map(friendsIds => friend)
    }
  }


}