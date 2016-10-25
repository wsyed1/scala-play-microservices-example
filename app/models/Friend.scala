package models

import models.dao.FriendDAO

import scala.concurrent.Future

/**
  * Created by 901124 on 10/21/16.
  */
object Friend {


  def findAllFriends(userId: Int): List[Friend] ={

    FriendDAO.index(userId)
  }

}

case class Friend(friendId: Int)