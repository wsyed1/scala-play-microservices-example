package actors

import akka.actor._
import models.{FavouriteNotification, FavouriteStudio, Friend, FriendTest}

/**
  * Created by 901124 on 10/19/16.
  */
object NotificationActor {

  def props: Props = Props(new NotificationActor)
}

class NotificationActor extends Actor {

  def receive = {
    case favourite: FavouriteStudio =>
      notifyFriends(favourite)
}
  private def notifyFriends(favourite: FavouriteStudio): Unit ={

    val fFriends = FriendTest.findAllFriends(favourite.userId)

    for (
      friends <- fFriends;
      friend <- fFriends;
      notification = FavouriteNotification(friend, favourite)
    ) {
      notification.send()
    }
  }
}

