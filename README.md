# ScoreBoard

## Coding Exercise
### Instructions
Please provide the implementation of the Football World Cup Score Board as a simple library. 
### Guidelines
- Keep it simple. Stick to the requirements and try to implement the simplest solution you can possibly think of that works and don't forget about edge cases.
- Use an in-memory store solution (for example just use collections to store the information you might require).
- We are NOT looking for a REST API, a Web Service or Microservice. Just a simple implementation.
- Focus on Quality. Use Test-Driven Development (TDD), pay attention to OO design, Clean Code and adherence to SOLID principles.
- Approach. Code the solution according to your standards. Please share your solution with a link to a source control repository (e.g. GitHub, GitLab, BitBucket) as we would like you to see your progress (your commit history is important)
- Add a README.md file where you can make notes of any assumption or things you would like to mention to us about your solution.

### Football World Cup Score Board
You are working on a sports data company, and we would like you to develop a new Live Football World Cup Score Board that shows matches and scores.
#### The board supports the following operations:
1. Start a game. When a game starts, it should capture (being initial score 0 – 0):
   a. Home team
   b. Away team
2. Finish game. It will remove a match from the scoreboard.
3. Update score. Receiving the pair score; home team score and away team score
   updates a game score.
4. Get a summary of games by total score. Those games with the same total score will
   be returned ordered by the most recently added to our system.
#### As an example, being the current data in the system:
a. Mexico - Canada: 0 - 5
b. Spain - Brazil: 10 – 2
c. Germany - France: 2 – 2
d. Uruguay - Italy: 6 – 6
e. Argentina - Australia: 3 - 1

#### The summary would provide with the following information:
1. Uruguay 6 - Italy 6
2. Spain 10 - Brazil 2
3. Mexico 0 - Canada 5
4. Argentina 3 - Australia 1
5. Germany 2 - France 2


## Solution
### Overview
1. Used language: Java 17
2. Additional frameworks/libraries: Gradle, Spock, Guava
3. TDD approach
### Description
The core class is *SortedScoreBoard* which implements ScoreBoardWithSummary. The interface requires to implement in total 5 methods as below:
```java
    boolean startGame(String homeTeam, String awayTeam);
    void updateGame(String homeTeam, String awayTeam, int homeTeamPoints, int awayTeamPoints);
    Game finishGame(String homeTeam, String awayTeam);
    Collection<Game> getActiveGames();
    String summary();
```
The class *SortedScoreBoard* uses as a collection *LinkedHashSet* which helps us with two things:
- Keeps insertion order - needed to proper sorting
- Does not allow duplicate games in score board - we do want to have unique team pairs. The equality check only takes teams to consideration. Score fields for game are ignored.

The collection contains Game instances where every Game object keeps information about team and score.

All methods' implementations are simple operations in Set collection.

### Possible improvements:
- Care about concurrent updates. Race condition is possible.
- Add more complex object for storing summary. For now, it is a string.
- Add fields to *Game* to save information about starting time
- Add method to return game length