package org.example.scoreboard

import spock.lang.Specification

class ScoreBoardSpec extends Specification {

    def "Create empty ScoreBoard"() {
        when:
        def scoreBoard = ScoreBoards.newScoreBoard()

        then:
        scoreBoard.activeGames.isEmpty()
    }

    def "Start game in ScoreBoard with initial score 0-0"() {
        setup:
        def scoreBoard = ScoreBoards.newScoreBoard()
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

    def "Update game in score board"() {
        setup:
        def scoreBoard = ScoreBoards.newScoreBoard()
        def team1 = "Poland"
        def team2 = "Brazil"
        scoreBoard.startGame(team1, team2)

        when:
        scoreBoard.updateGame(team1, team2, 1, 2)

        then:
        scoreBoard.activeGames[0].homeTeamPoints == 1
        scoreBoard.activeGames[0].awayTeamPoints == 2
    }
}
