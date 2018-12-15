package com.gabrielsson.adventofcode;

import com.gabrielsson.adventofcode.day15.*;
import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class Day15 {

    public Object part1(List<String> playArea) {

        Maze maze = new Maze(playArea);

        List<Asset> assets = Arrays.stream(maze.getMaze())
                .flatMap(a -> Arrays.stream(a))
                .filter(s -> s.gameClass == Constants.ELF || s.gameClass == Constants.GOBLIN)
                .sorted(this::assetSort)
                .collect(Collectors.toList());


        for(Asset a: assets) {
            maze.reset();
            Pair<Asset, List<Coordinate>> path = getPathToClosestOpponent(a, maze, assets);
            int minSize = Integer.MAX_VALUE;
            Pair<Asset, List<Coordinate>> smallest = null;

            if(path != null && path.getValue().size() < minSize && path.getValue().size() > 0) {
                minSize = path.getValue().size();
                smallest = path;
            }


            if(smallest != null) {
                printMaze(maze, smallest, a);
            }

        }

        return "";
    }

    private void printMaze(Maze maze, Pair<Asset, List<Coordinate>> smallest, Asset a) {
        maze.setStart(a.coordinate);
        maze.setEnd(smallest.getKey().coordinate);
        if(smallest != null && smallest.getValue() != null && smallest.getValue().size() > 0) {

            maze.printPath(smallest.getValue());
            maze.reset();

        }
    }

    private int assetSort(Asset asset, Asset asset1) {
        if (asset.coordinate.y == asset1.coordinate.y) {
            return Integer.compare(asset.coordinate.x, asset1.coordinate.x);
        } else {
            return Integer.compare(asset.coordinate.y, asset1.coordinate.y);
        }
    }

    public Pair<Asset, List<Coordinate>> getPathToClosestOpponent(Asset player, Maze maze, List<Asset> opponents) {

        List<Pair<Asset, List<Coordinate>>> list = new ArrayList<>();

        for(Asset a: opponents.stream().filter(c -> c.gameClass != player.gameClass).collect(Collectors.toList())) {
            List<Coordinate> path = findPath(player, a, maze);
            if(path.size() > 0)
                list.add(new Pair(a, path));
        }

        return list.stream().sorted(this::compare).findFirst().orElse(null);

    }

    private int compare(Pair<Asset, List<Coordinate>> pair, Pair<Asset, List<Coordinate>> pair1) {
        return Integer.compare(pair.getValue().size(), pair1.getValue().size());
    }

    private List<Coordinate> findPath(Asset player, Asset opponent, Maze maze) {
        BFSMazeSolver bfs = new BFSMazeSolver();

        return bfs.solve(maze, player, opponent);

    }


}
