package org.example.scoreboard;

import com.google.common.base.Objects;

public class Game {

    private final String homeTeam;
    private final String awayTeam;
    private int homeTeamPoints;
    private int awayTeamPoints;

    private Game(String homeTeam, String awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    static Game ofTeams(String homeTeam, String awayTeam) {
        return new Game(homeTeam, awayTeam);
    }

    public int getHomeTeamPoints() {
        return homeTeamPoints;
    }

    public int getAwayTeamPoints() {
        return awayTeamPoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equal(homeTeam, game.homeTeam) && Objects.equal(awayTeam, game.awayTeam);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(homeTeam, awayTeam);
    }
}
