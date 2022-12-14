package org.example.scoreboard

import spock.lang.Specification

class SortedScoreBoardSpec extends Specification {

    def "Create empty ScoreBoard"() {
        when:
        def scoreBoard = ScoreBoards.newSortedScoreBoard()

        then:
        scoreBoard.activeGames.isEmpty()
    }

    def "Start game in ScoreBoard with initial score 0-0"() {
        setup:
        def scoreBoard = ScoreBoards.newSortedScoreBoard()
        def team1 = "Poland"
        def team2 = "Brazil"

        when:
        scoreBoard.startGame(team1, team2)

        then:
        scoreBoard.activeGames.size() == 1
        Game.ofTeams(team1, team2) in scoreBoard.activeGames
        scoreBoard.activeGames[0].homeTeamPoints == 0
        scoreBoard.activeGames[0].awayTeamPoints == 0
    }

    def "Score boards contains only unique games, should not be able to start non unique"() {
        setup:
        def scoreBoard = ScoreBoards.newSortedScoreBoard()
        def team1 = "Poland"
        def team2 = "Brazil"

        expect:
        scoreBoard.startGame(team1, team2)
        !scoreBoard.startGame(team1, team2)
        !scoreBoard.startGame(team2, team1)
    }

    def "Update game in score board"() {
        setup:
        def scoreBoard = ScoreBoards.newSortedScoreBoard()
        def team1 = "Poland"
        def team2 = "Brazil"
        scoreBoard.startGame(team1, team2)

        when:
        scoreBoard.updateGame(team1, team2, 1, 2)

        then:
        scoreBoard.activeGames[0].homeTeamPoints == 1
        scoreBoard.activeGames[0].awayTeamPoints == 2
    }

    def "Updating non existing game returns NoSuchElementException"() {
        setup:
        def scoreBoard = ScoreBoards.newSortedScoreBoard()

        when:
        scoreBoard.updateGame("Poland", "Brazil", 1, 1)

        then:
        thrown NoSuchElementException
    }

    def "Finish game and remove from score board, returns Game instance"() {
        setup:
        def scoreBoard = ScoreBoards.newSortedScoreBoard()
        def team1 = "Poland"
        def team2 = "Brazil"
        startGameWithScore(scoreBoard, team1, team2, 2, 3)

        when:
        def game = scoreBoard.finishGame(team1, team2)

        then:
        scoreBoard.activeGames.isEmpty()
        game == Game.ofTeams(team1, team2)
        game.homeTeamPoints == 2
        game.awayTeamPoints == 3
    }

    def "Finishing non existing game returns Exception" () {
        setup:
        def scoreBoard = ScoreBoards.newSortedScoreBoard()

        when:
        scoreBoard.finishGame("Poland", "Brazil")

        then:
        thrown NoSuchElementException
    }

    def "Active games are sorted by score descending"() {
        setup:
        def scoreBoard = ScoreBoards.newSortedScoreBoard()
        startGameWithScore(scoreBoard, "Mexico", "Canada", 0, 5)
        startGameWithScore(scoreBoard, "Spain", "Brazil", 10, 2)
        startGameWithScore(scoreBoard, "Germany", "France", 2, 2)
        startGameWithScore(scoreBoard, "Uruguay", "Italy", 6, 6)
        startGameWithScore(scoreBoard, "Argentina", "Australia", 3, 1)

        when:
        def activeGames = scoreBoard.activeGames

        then:
        activeGames as List == [
                Game.ofTeams("Uruguay", "Italy"),
                Game.ofTeams("Spain", "Brazil"),
                Game.ofTeams("Mexico", "Canada"),
                Game.ofTeams("Argentina", "Australia"),
                Game.ofTeams("Germany", "France")
        ]
    }

    def "Return empty summary for empty score board" () {
        when:
        def scoreBoard = ScoreBoards.newSortedScoreBoard()

        then:
        scoreBoard.summary().isEmpty()
    }

    def "Return sorted summary for multiple games" () {
        setup:
        def scoreBoard = ScoreBoards.newSortedScoreBoard()
        startGameWithScore(scoreBoard, "Mexico", "Canada", 0, 5)
        startGameWithScore(scoreBoard, "Spain", "Brazil", 10, 2)
        startGameWithScore(scoreBoard, "Germany", "France", 2, 2)
        startGameWithScore(scoreBoard, "Uruguay", "Italy", 6, 6)
        startGameWithScore(scoreBoard, "Argentina", "Australia", 3, 1)

        when:
        def summary = scoreBoard.summary()

        then:
        summary == """\
                        1. Uruguay 6 - Italy 6
                        2. Spain 10 - Brazil 2
                        3. Mexico 0 - Canada 5
                        4. Argentina 3 - Australia 1
                        5. Germany 2 - France 2
                        """.stripIndent()
    }

    void startGameWithScore(ScoreBoard scoreBoard, homeTeam, awayTeam, homeTeamPoints, awayTeamPoints) {
        scoreBoard.startGame(homeTeam, awayTeam)
        scoreBoard.updateGame(homeTeam, awayTeam, homeTeamPoints, awayTeamPoints)
    }
}
