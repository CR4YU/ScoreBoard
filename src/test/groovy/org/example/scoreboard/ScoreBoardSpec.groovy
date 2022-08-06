package org.example.scoreboard

import spock.lang.Specification

class ScoreBoardSpec extends Specification {

    def "Create empty ScoreBoard"() {
        when:
        def scoreBoard = ScoreBoards.newScoreBoard()

        then:
        scoreBoard.activeGames.isEmpty()
    }
}
