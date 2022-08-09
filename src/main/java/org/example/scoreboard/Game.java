package org.example.scoreboard;

import com.google.common.base.Objects;
import com.google.common.base.Strings;

public class Game {

    private final String homeTeam;
    private final String awayTeam;
    private int homeTeamPoints;
    private int awayTeamPoints;

    private Game(String homeTeam, String awayTeam) {
        if (Strings.isNullOrEmpty(homeTeam) || Strings.isNullOrEmpty(awayTeam)) {
            throw new IllegalArgumentException("Team name cannot be empty or null");
        }
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    static Game ofTeams(String homeTeam, String awayTeam) {
        return new Game(homeTeam, awayTeam);
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public int getHomeTeamPoints() {
        return homeTeamPoints;
    }

    public int getAwayTeamPoints() {
        return awayTeamPoints;
    }

    void setPoints(int homeTeamPoints, int awayTeamPoints) {
        if (homeTeamPoints < 0 || awayTeamPoints < 0) {
            throw new IllegalArgumentException("Points value negative");
        }
        this.homeTeamPoints = homeTeamPoints;
        this.awayTeamPoints = awayTeamPoints;
    }

    public int pointsSum() {
        return homeTeamPoints + awayTeamPoints;
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
