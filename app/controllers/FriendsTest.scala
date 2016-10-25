package controllers

import models.FriendTest
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}


/**
  * Created by 901124 on 10/21/16.
  */
class FriendsTest extends Controller{

  def findAll(userId: Int) = Action {
    val allFriends = FriendTest.findAllFriends(userId)

    Ok(Json.obj("result" -> allFriends.toString()))

  }
}