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

    def "Finish game and remove from score board"() {
        setup:
        def scoreBoard = ScoreBoards.newSortedScoreBoard()
        def team1 = "Poland"
        def team2 = "Brazil"
        scoreBoard.startGame(team1, team2)

        when:
        scoreBoard.finishGame(team1, team2)

        then:
        scoreBoard.activeGames.isEmpty()
    }
}
