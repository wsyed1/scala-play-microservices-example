# scala-play-microservices-example
APIs and Microservices using Scala and Play - Sample Favourite Studio Application 

##Initialize a skeleton service in one command:

activator new favourites-service play-scala

##You can run it immediately:

activator run

##Api End Points

###Favourite a studio
POST http://localhost:9000/users/:userId/favouriteStudios/:studioId

###Unfavourite a studio
DELETE http://localhost:9000/users/:userId/favouriteStudios/:studioId

###Check if a studio has been favourited
GET http://localhost:9000/users/:userId/favouriteStudios/:studioId

###Get a list (index) of all studios a user has favourited
GET http://localhost:9000/users/:userId/favouriteStudios

###How to run it on command prompt
curl -X GET http://localhost:9000/users/3/favouriteStudios/10




