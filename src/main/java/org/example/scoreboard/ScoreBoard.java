package org.example.scoreboard;

import com.google.common.collect.Lists;

import java.util.List;

public class ScoreBoard {

    private final List<Game> activeGames;

    public ScoreBoard() {
        activeGames = Lists.newArrayList();
    }

    public List<Game> getActiveGames() {
        return activeGames;
    }
}
