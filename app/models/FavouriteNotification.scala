package models

import scala.concurrent.ExecutionContext

/**
  * Created by 901124 on 10/19/16.
  */
case class FavouriteNotification (recipient: Friend, favourite: FavouriteStudio) {
  def send(): Unit = {
    //TODO: Send a notification to Amazon SNS
    println("SENDING PUSH NOTIFICATION TO: " + recipient.friendId)
  }

}
