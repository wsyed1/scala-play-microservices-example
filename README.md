# scala-play-microservices-example
APIs and Microservices using Scala and Play - Sample Favourite Studio Application 

##Initialize a skeleton service in one command:

activator new favourites-service play-scala

##Run
You can run it immediately:

1. Type "activator run" in Terminal to start the application.
2. Open another Terminal window and curl any of the below commands

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

##Debug:

1. Go to Run->Edit Config
2. Add a SBT Task. 
3. Enter run in the task and provide a name appropriately
4. Hit Apply and Ok
5. Go to the Terminal in IntelliJ or Mac and type "activator -jvm-debug 9999" and hit Enter.(Ensure the port in in use)
6. You should see "Listening for transport dt_socket at address: 9999"
7. Select to the Run->Debug in IntelliJ. You should see "Running the application, auto-reloading is enabled" and "p.c.s.NettyServer - Listening for HTTP on /0:0:0:0:0:0:0:0:9000"
8. Put a breakpoint in the code where you want to debug.
9. Now Open another Terminal in Mac or IntelliJ and curl the command you want to run.
  Ex:curl -X POST http://localhost:9000/users/100/favouriteStudios/786 -v
10. The run stops at the breakpoint to help you debug.
