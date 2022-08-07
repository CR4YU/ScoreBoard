package org.example.scoreboard;

import com.google.common.collect.Sets;

import java.util.Set;

public class ScoreBoard {

    private final Set<Game> activeGames;

    public ScoreBoard() {
        activeGames = Sets.newHashSet();
    }

    public Set<Game> getActiveGames() {
        return activeGames;
    }

    public void startGame(String homeTeam, String awayTeam) {
        activeGames.add(Game.ofTeams(homeTeam, awayTeam));
    }

    public void updateGame(String homeTeam, String awayTeam, int homeTeamPoints, int awayTeamPoints) {
        activeGames.stream()
                .filter(game -> game.equals(Game.ofTeams(homeTeam, awayTeam)))
                .findAny()
                .orElseThrow()
                .setPoints(homeTeamPoints, awayTeamPoints);
    }
}
