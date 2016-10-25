package models

import scala.concurrent.ExecutionContext

/**
  * Created by 901124 on 10/19/16.
  */
case class FavouriteNotification (recipient: FriendTest, favourite: FavouriteStudio) {
  def send(): Unit = {
    println("SENDING PUSH NOTIFICATION TO: " + recipient.friendId)
  }

}
