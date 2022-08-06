package org.example.scoreboard;

import com.google.common.collect.Lists;

import java.util.List;

public class ScoreBoard {

    private final List activeGames;

    public ScoreBoard() {
        activeGames = Lists.newArrayList();
    }
}
