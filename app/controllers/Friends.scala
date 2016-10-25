package controllers

import javax.inject.Inject

import models.Friend
import play.api.libs.json.Json
import play.api.mvc._

import scala.concurrent.ExecutionContext


/**
  * Created by 901124 on 10/21/16.
  */
class Friends @Inject() (friend: Friend) extends Controller{

  def findAll(userId: Int)(implicit ec: ExecutionContext) = Action {
    val allFriends = friend.findAllFriends(userId)

    Ok(Json.obj("result" -> allFriends.toString))

  }
}
