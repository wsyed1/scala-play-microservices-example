package controllers

import models.Friend
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}


/**
  * Created by 901124 on 10/21/16.
  */
class Friends extends Controller{

  def findAll(userId: Int) = Action {
    val allFriends = Friend.findAllFriends(userId)

    Ok(Json.obj("result" -> allFriends.toString()))

  }
}