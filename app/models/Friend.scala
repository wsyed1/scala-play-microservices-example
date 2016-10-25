package models


import javax.inject.Inject

import models.dao._
import play.api.{Configuration, Play}

import scala.concurrent.{ExecutionContext, Future}



/**
  * Created by 901124 on 10/19/16.
  */

class Friend @Inject() (friendDAO: FriendDAO) {

//  val friendDAO = new FriendDAO(ws = WS)

  def findAllFriends(userId: Int) (implicit ec: ExecutionContext): Future[Set[Friend]] ={

    friendDAO.index(userId).map(_.toSet)
}

}

//case class Friend(userId: Int)