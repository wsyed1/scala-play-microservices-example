package models

import models.dao.FriendDAOTest

import scala.concurrent.Future

/**
  * Created by 901124 on 10/21/16.
  */
object FriendTest {


  def findAllFriends(userId: Int): List[FriendTest] ={

    FriendDAOTest.index(userId)
  }

}

case class FriendTest(friendId: Int)