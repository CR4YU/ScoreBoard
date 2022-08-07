package org.example.scoreboard;

import java.util.Collection;

public interface ScoreBoard {

    boolean startGame(String homeTeam, String awayTeam);
    void updateGame(String homeTeam, String awayTeam, int homeTeamPoints, int awayTeamPoints);
    Game finishGame(String homeTeam, String awayTeam);
    Collection<Game> getActiveGames();
}
