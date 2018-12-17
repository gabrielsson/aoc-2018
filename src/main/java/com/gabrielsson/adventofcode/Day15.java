package com.gabrielsson.adventofcode;

import com.gabrielsson.adventofcode.day15.*;
import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class Day15 {

    public Object part1(List<String> playArea) {

        Maze maze = new Maze(playArea);
        System.out.println(maze.toString(maze.getMaze()));

        int turn = 0;
        boolean stillOpponents = true;
        while (stillOpponents) {
            turn++;
            List<Asset> assets = Arrays.stream(maze.getMaze())
                    .flatMap(a -> Arrays.stream(a))
                    .filter(s -> s.gameClass == Constants.ELF || s.gameClass == Constants.GOBLIN)
                    .sorted(this::assetSort)
                    .collect(Collectors.toList());
            for (Asset a : assets) {
                if (maze.getMaze()[a.coordinate.y][a.coordinate.x].gameClass != a.gameClass) continue;
                Optional<Asset> adjecentOpponent = assets.stream()
                        .filter(opponent -> opponent.gameClass != a.gameClass)
                        .filter(opponent -> isAdjecent(a, opponent))
                        .sorted(this::combatSort).findFirst();

                if (!adjecentOpponent.isPresent()) {

                    Pair<Asset, List<Coordinate>> path = getPathToClosestOpponent(a, maze, assets);
                    int minSize = Integer.MAX_VALUE;
                    Pair<Asset, List<Coordinate>> smallest = null;

                    if (path != null && path.getValue().size() < minSize && path.getValue().size() > 0) {
                        minSize = path.getValue().size();
                        smallest = path;
                    }
                    if (smallest != null) {

                        List<Coordinate> list = smallest.getValue();

                        Coordinate newCoord = smallest.getValue().get(smallest.getValue().size() - 2);
                        maze.move(a.coordinate.x, a.coordinate.y, newCoord.x, newCoord.y);
                        //printMaze(maze, smallest, a);

                    }
                }

            }


            assets.stream().sorted(this::assetSort).forEach(a -> {
                List<Asset> adjecentOpponents = assets.stream()
                        .filter(opponent -> opponent.gameClass != a.gameClass)
                        .filter(opponent -> isAdjecent(a, opponent))
                        .collect(Collectors.toList());
                if(adjecentOpponents.size()>0) {

                    Asset adjecentOpponent = adjecentOpponents.stream()
                            .sorted(this::combatSort)
                            .findFirst().get();

                    maze.hitAssetAt(adjecentOpponent.coordinate.x, adjecentOpponent.coordinate.y, a.attack);
                }

            });


            System.out.println("After " + turn + " rounds:" + assets.stream().sorted(this::assetSort).map(a -> a.gameClass + ":" + a.hp).collect(Collectors.joining(",")));

            System.out.println(maze.toString(maze.getMaze()));

            List<Asset> elfs = Arrays.stream(maze.getMaze())
                    .flatMap(a -> Arrays.stream(a))
                    .filter(s -> s.gameClass == Constants.ELF)
                    .sorted(this::assetSort)
                    .collect(Collectors.toList());
            List<Asset> goblins = Arrays.stream(maze.getMaze())
                    .flatMap(a -> Arrays.stream(a))
                    .filter(s -> s.gameClass == Constants.GOBLIN)
                    .sorted(this::assetSort)
                    .collect(Collectors.toList());
            System.out.println("Score round " + turn + ":" + Arrays.stream(maze.getMaze())
                    .flatMap(a -> Arrays.stream(a))
                    .filter(s -> s.gameClass == Constants.ELF || s.gameClass == Constants.GOBLIN)
                    .sorted(this::assetSort).mapToInt(a -> a.hp).sum());
            stillOpponents = elfs.size() > 0 && goblins.size() > 0;
        }

        List<Asset> finalAssets = Arrays.stream(maze.getMaze())
                .flatMap(a -> Arrays.stream(a))
                .filter(s -> s.gameClass == Constants.ELF || s.gameClass == Constants.GOBLIN)
                .sorted(this::assetSort)
                .collect(Collectors.toList());

        int score = finalAssets.stream().mapToInt(a -> a.hp < 0?0:a.hp).sum();
        return turn * score;
    }

    private int combatSort(Asset asset, Asset asset1) {
        if(asset.hp == asset1.hp) {
            return assetSort(asset, asset1);
        }
        return Integer.compare(asset.hp, asset1.hp);
    }

    private boolean isAdjecent(Asset player, Asset opponent) {

        return (Math.abs(player.coordinate.x - opponent.coordinate.x) == 1 && player.coordinate.y == opponent.coordinate.y) ||
                (Math.abs(player.coordinate.y - opponent.coordinate.y) == 1 && player.coordinate.x == opponent.coordinate.x);


    }

    private void printMaze(Maze maze, Pair<Asset, List<Coordinate>> smallest, Asset a) {
        maze.setStart(a.coordinate);
        maze.setEnd(smallest.getKey().coordinate);
        if (smallest != null && smallest.getValue() != null && smallest.getValue().size() > 0) {

            maze.printPath(smallest.getValue());
            maze.reset();

        }
    }

    private int assetSort(Asset asset, Asset asset1) {
        if (asset.coordinate.y == asset1.coordinate.y) {
            return Integer.compare(asset1.coordinate.x, asset.coordinate.x);
        } else {
            return Integer.compare(asset.coordinate.y, asset1.coordinate.y);
        }
    }

    public Pair<Asset, List<Coordinate>> getPathToClosestOpponent(Asset player, Maze maze, List<Asset> opponents) {

        List<Pair<Asset, List<Coordinate>>> list = new ArrayList<>();

        for (Asset a : opponents.stream().filter(c -> c.gameClass != player.gameClass).collect(Collectors.toList())) {
            maze.reset();
            List<Coordinate> path = findPath(player, a, maze);
            if (path.size() > 0)
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
