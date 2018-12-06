package com.gabrielsson.adventofcode;

import java.util.ArrayList;
import java.util.List;

public class Day6 {

    public static Object part1(List<String> rows) {

        //highest x,y

        int x = rows.stream().mapToInt(s -> Integer.valueOf(s.split(",")[0].trim())).max().getAsInt();
        int y = rows.stream().mapToInt(s -> Integer.valueOf(s.split(",")[1].trim())).max().getAsInt();

        List<Coordintate> areaCenters = new ArrayList<>();
        for (int i = 0; i < rows.size(); i++) {
            areaCenters.add(new Coordintate(i, rows.get(i)));
        }


        CountMap<Coordintate> countMap = new CountMap<>();
        for (Coordintate coordinate : areaCenters) {
            for (int i = 0; i < x; i++) {
                for (int j = 0; j < y; j++) {

                    Integer score = countMap.get(new Coordintate(0, i, j));
                    int manhattansDistance = getManhattanDistance(coordinate, i, j);
                    if (score == null || manhattansDistance < score) {
                        countMap.put(new Coordintate(coordinate.owner, i, j), getManhattanDistance(coordinate, i, j));
                    }

                }
            }
        }

        //filter away the areas with side conneted
        int sum = 0;
        for (Coordintate a : areaCenters) {
            int localsum = countMap.entrySet().stream().filter(e -> e.getKey().owner == a.owner).mapToInt(e -> e.getValue()).sum();



        }


    ;


        return"";
}

    private static Integer getManhattanDistance(Coordintate coordinate, int x, int y) {
        int distance = 0;
        distance += Math.abs(coordinate.x - x);
        distance += Math.abs(coordinate.y - y);
        return distance;
    }

    public static Object part2(List<String> rows) {


        return "";
    }


static class Coordintate {
    int x;
    int y;
    int owner;

    public Coordintate(int id, String row) {
        x = Integer.valueOf(row.split(",")[0].trim());
        y = Integer.valueOf(row.split(",")[0].trim());
        owner = id;
    }

    public Coordintate(int id, int x, int y) {
        this.x = x;
        this.y = y;
        owner = id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordintate that = (Coordintate) o;
        return x == that.x &&
                y == that.y;
    }
}
}
