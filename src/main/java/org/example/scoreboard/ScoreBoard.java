package org.example.scoreboard;

import java.util.Collection;

public interface ScoreBoard {

    void startGame(String homeTeam, String awayTeam);
    void updateGame(String homeTeam, String awayTeam, int homeTeamPoints, int awayTeamPoints);
    void finishGame(String homeTeam, String awayTeam);
    Collection<Game> getActiveGames();
}
