package org.example.scoreboard

import spock.lang.Specification

class GameSpec extends Specification {

    def "Create Game instance with initial score 0-0"() {
        setup:
        def team1 = "Poland"
        def team2 = "Brazil"

        when:
        def game = Game.ofTeams(team1, team2)

        then:
        with (game) {
            homeTeam == team1
            awayTeam == team2
            homeTeamPoints == 0
            awayTeamPoints == 0
        }
    }

    def "Creating game with null or empty team name throws exception"() {
        when:
        Game.ofTeams(team1, team2)

        then:
        def e = thrown(IllegalArgumentException)
        e.message == "Team name cannot be empty or null"

        where:
        team1 | team2
        "Pol" | ""
        ""    | "Pol"
        "Pol" | null
        null  | "Pol"
    }

    def "Two game instances are equal if teams are the same - eqauls() test"() {
        setup:
        def team1 = "Poland"
        def team2 = "Brazil"
        def game1 = Game.ofTeams(team1, team2)
        def game2 = Game.ofTeams(team1, team2)

        expect:
        game1 == game2
        !game1.is(game2)
    }

    def "Assign points to teams, pointsSum() returns sum"() {
        setup:
        def game = Game.ofTeams("Poland", "Brazil")

        when:
        game.setPoints(1, 2)

        then:
        game.homeTeamPoints == 1
        game.awayTeamPoints == 2
        game.pointsSum() == 3
    }
}
