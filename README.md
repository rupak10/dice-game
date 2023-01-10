# dice-game
This is a console based dice game

#Application-Run
1. Clone the project 
2. Import the project as existing maven application
3. I have used JDK 8 to develop the application
4. Run the project as spring boot app
4. The application will be running on the default port - 8080

#Basic-Functionality
1. You can add player to the game (minimum 2 players and maximum 4 players)
2. Winning score is configurable
3. Current scores can be fetched while the game is running

#Game-Flow
1. Add player : http://localhost:8080/api/v1/players/add
 Sample Request :
 {
    "name" : "Rupak",
    "age" : 29
}
2. Start game : http://localhost:8080/api/v1/game/start
3. You can't start another game round while one round is running
4. Follow the console to watch the game
5. Fetch current scores : http://localhost:8080/api/v1/game/current-scores

#Documentation - 
This application is integrated with swagger. Swagger documentation can be found in the location below : 
1. http://localhost:8080/swagger-ui/index.html
2. http://localhost:8080/v2/api-docs
