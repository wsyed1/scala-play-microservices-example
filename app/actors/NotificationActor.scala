package actors

import akka.actor._
import models.{FavouriteNotification, FavouriteStudio, Friend}

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

   // Lookup friends
    val fFriends = Friend.findAllFriends(favourite.userId)

    for (
      friends <- fFriends;
      friend <- fFriends;
      notification = FavouriteNotification(friend, favourite)
    ) {
      notification.send()
    }
  }
}

