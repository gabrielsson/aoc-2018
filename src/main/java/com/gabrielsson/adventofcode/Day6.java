package com.gabrielsson.adventofcode;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Day6 {

    public static Object part1(List<String> rows) {

        //highest maxx,maxy

        int maxx = rows.stream().mapToInt(s -> Integer.valueOf(s.split(",")[0].trim())).max().getAsInt();
        int maxy = rows.stream().mapToInt(s -> Integer.valueOf(s.split(",")[1].trim())).max().getAsInt();

        List<OriginPoint> areaCenters = new ArrayList<>();
        for (int i = 0; i < rows.size(); i++) {
            areaCenters.add(new OriginPoint(i + "", rows.get(i)));
        }


        List<List<Score>> grid = new ArrayList<>();


        for (int i = 0; i < maxx; i++) {
            ArrayList<Score> ys = new ArrayList<>();
            for (int j = 0; j < maxy; j++) {
                ys.add(j, new Score("-", Integer.MAX_VALUE));
            }
            grid.add(i, ys);
        }

<<<<<<< HEAD
        for (OriginPoint point : areaCenters) {
            for (int x = 0; x < maxx; x++) {
=======
        //filter away the areas with side conneted
        List<Integer> idsWithInfite = countMap.entrySet().stream().filter(e -> e.getKey().x == x || e.getKey().y == y).map(e -> e.getKey().owner).collect(Collectors.toList());


        for(Integer id : idsWithInfite) {
            areaCenters.remove(id);
        }
        for (Coordintate a : areaCenters) {
            int localsum = countMap.entrySet().stream().filter(e -> e.getKey().owner == a.owner).mapToInt(e -> e.getValue()).sum();

>>>>>>> Day7

                for (int y = 0; y < maxy; y++) {
                    Score pointScore = grid.get(x).get(y);
                    int manhattanDistance = getManhattanDistance(point.p.x,point.p.y, x, y);
                    if (pointScore.score > manhattanDistance) {
                        grid.get(x).set(y, new Score(point.owner, manhattanDistance));
                    } else if (pointScore.score == manhattanDistance) {
                        grid.get(x).set(y, new Score(".", manhattanDistance));
                    }
                }

<<<<<<< HEAD
            }
=======

>>>>>>> Day7
        }


        CountMap<String> countMap = new CountMap<>();
        Set<String> removeIds = new HashSet<>();
        for (int x = 0; x < maxx; x++) {

            for (int y = 0; y < maxy; y++) {
                if (x == 0 || x == maxx || y == 0 || y == maxy) {
                    removeIds.add(grid.get(x).get(y).id);
                }
                countMap.increment(grid.get(x).get(y).id);
            }
        }

        for (String id : removeIds) {
            countMap.remove(id);
        }

        String key = countMap.getMaxKey();
        return countMap.get(key);
    }

    public static Integer getManhattanDistance(int ox, int oy, int x, int y) {
        int distance = 0;
        distance += Math.abs(ox - x);
        distance += Math.abs(oy - y);
        return distance;
    }

    public static Object part2(List<String> rows, int limit) {

        int maxx = rows.stream().mapToInt(s -> Integer.valueOf(s.split(",")[0].trim())).max().getAsInt();
        int maxy = rows.stream().mapToInt(s -> Integer.valueOf(s.split(",")[1].trim())).max().getAsInt();
        List<OriginPoint> areaCenters = new ArrayList<>();
        for (int i = 0; i < rows.size(); i++) {
            areaCenters.add(new OriginPoint(i + "", rows.get(i)));
        }

        int biggestArea = 0;

        for (int x = 0; x < maxx; x++) {

            for (int y = 0; y < maxy; y++) {

                int manhattanDistance = 0;

                for (OriginPoint point : areaCenters) {
                    manhattanDistance += getManhattanDistance(x, y, point.p.x, point.p.y);
                }
                if(manhattanDistance < limit) {
                    biggestArea++;
                }
            }


        }


        return biggestArea;
    }

    static class Score {
        String id = ".";
        int score = 0;

        public Score(String owner, int manhattanDistance) {
            score = manhattanDistance;
            id = owner;
        }
    }

    static class OriginPoint {
        Point p;
        String owner;

        public OriginPoint(String id, String row) {
            p = new Point(Integer.valueOf(row.split(",")[0].trim()), Integer.valueOf(row.split(",")[1].trim()));
            owner = id;
        }

        public OriginPoint(String id, int x, int y) {
            p = new Point(x, y);
            owner = id;
        }

    }
}
