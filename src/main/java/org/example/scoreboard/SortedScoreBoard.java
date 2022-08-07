package org.example.scoreboard;

import com.google.common.collect.Sets;

import java.util.Set;

public class SortedScoreBoard implements ScoreBoard{

    private final Set<Game> activeGames;

    public SortedScoreBoard() {
        activeGames = Sets.newHashSet();
    }

    @Override
    public Set<Game> getActiveGames() {
        return activeGames;
    }

    @Override
    public void startGame(String homeTeam, String awayTeam) {
        activeGames.add(Game.ofTeams(homeTeam, awayTeam));
    }

    @Override
    public void updateGame(String homeTeam, String awayTeam, int homeTeamPoints, int awayTeamPoints) {
        activeGames.stream()
                .filter(game -> game.equals(Game.ofTeams(homeTeam, awayTeam)))
                .findAny()
                .orElseThrow()
                .setPoints(homeTeamPoints, awayTeamPoints);
    }

    @Override
    public void finishGame(String homeTeam, String awayTeam) {
        activeGames.remove(Game.ofTeams(homeTeam, awayTeam));
    }
}
