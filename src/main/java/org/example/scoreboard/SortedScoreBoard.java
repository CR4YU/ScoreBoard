package org.example.scoreboard;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.*;
import java.util.stream.Collectors;

public class SortedScoreBoard implements ScoreBoard{

    private final LinkedHashSet<Game> activeGames;

    public SortedScoreBoard() {
        activeGames = Sets.newLinkedHashSet();
    }

    @Override
    public Set<Game> getActiveGames() {
        return sortedGames();
    }

    @Override
    public boolean startGame(String homeTeam, String awayTeam) {
        return activeGames.add(Game.ofTeams(homeTeam, awayTeam));
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

    public String summary() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Game game : sortedGames()) {
            sb.append(String.format("%d. %s %d - %s %d\n",
                    i++, game.getHomeTeam(), game.getHomeTeamPoints(), game.getAwayTeam(), game.getAwayTeamPoints()));
        }
        return sb.toString();
    }

    private LinkedHashSet<Game> sortedGames() {
        List<Game> activeGamesList = Lists.newArrayList(activeGames);
        Collections.reverse(activeGamesList);
        return activeGamesList.stream()
                .sorted(Comparator.comparingInt(Game::pointsSum).reversed())
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
