package com.gabrielsson.adventofcode;

import java.awt.*;
import java.util.List;
import java.util.*;

public class Day17 {
    Set<Point> water = new HashSet<>();
    Set<Point> walls = null;
    Map<Point, Boolean> contained = new HashMap<>();

    int maxY, maxX, minY, minX;

    public Object part1(List<String> playArea) {

        walls = getWalls(playArea);

        maxY = walls.stream().mapToInt(p -> p.y).max().getAsInt();
        minY = walls.stream().mapToInt(p -> p.y).min().getAsInt();

        maxX = walls.stream().mapToInt(p -> p.x).max().getAsInt();
        minX = walls.stream().mapToInt(p -> p.x).min().getAsInt();
        char[][] playField = new char[maxY + 1][maxX - minX + 2];
        walls.stream().forEach(p -> playField[p.y][p.x -minX] = '#');

        System.out.println(toString(playField));


        drip(500, minY);
        water.stream().forEach(p -> playField[p.y][p.x - minX] = '|');
        water.stream().filter(p-> isSurrounded(p.x, p.y)).forEach(p -> playField[p.y][p.x - minX] = '~');
        System.out.println(toString(playField));

        long stillWaterCount = water.stream().filter(p-> isSurrounded(p.x, p.y)).count();
        long waterCount = water.stream().count();

        return waterCount;

    }

    private void drip(int x, int y) {
        if (y > maxY) {
            return;
        }

        water.add(new Point(x, y));

        //left
        if (!isWall(x, y +1) && !isWater(x, y +1)) {
            drip(x, y + 1);
        }

        if (isSurrounded(x, y + 1)) {
            //expand left
            if (!isWall(x - 1, y) && !isWater(x - 1, y)) {
                drip(x - 1, y);
            }
            //exapnd right
            if (!isWall(x + 1, y) && !isWater(x + 1, y)) {
                drip(x + 1, y);
            }

        }


    }

    private boolean isSurrounded(int x, int y) {
        if (y > maxY || x < minX || x > maxX) {
            return false;
        }

        Point p = new Point(x, y);


        Boolean cont = contained.get(p);
        if (cont != null) {
            return cont;
        }
        if (walls.contains(p)) {
            return true;
        }

        cont = isSurrounded(x, y + 1) && hasWallToRight(x + 1, y) && hasWallToLeft(x - 1, y);
        contained.put(p, cont);
        return cont;

    }

    private boolean hasWallToLeft(int x, int y) {
        if (isWall(x, y)) {
            return true;
        }
        return isSurrounded(x, y + 1) && hasWallToLeft(x - 1, y);
    }

    private boolean hasWallToRight(int x, int y) {
        if (isWall(x, y)) {
            return true;
        }
        return isSurrounded(x, y + 1) && hasWallToRight(x + 1, y);

    }

    private boolean isWater(int x, int y) {
        return water.contains(new Point(x, y));
    }

    private boolean isWall(int x, int y) {
        return walls.contains(new Point(x, y));
    }


    private Set<Point> getWalls(List<String> playArea) {
        Set<Point> walls = new HashSet<>();

        playArea.stream().forEach(inputRow -> {
            if (inputRow.startsWith("x")) {
                int x = Integer.valueOf(inputRow.substring(2, inputRow.indexOf(", ")));
                int y1 = Integer.valueOf(inputRow.substring(inputRow.indexOf("y=") + 2, inputRow.indexOf("..")));
                int y2 = Integer.valueOf(inputRow.substring(inputRow.indexOf("..") + 2));

                for (int y = y1; y <= y2; y++) {
                    walls.add(new Point(x, y));
                }
            } else {
                int y = Integer.valueOf(inputRow.substring(2, inputRow.indexOf(", ")));
                int x1 = Integer.valueOf(inputRow.substring(inputRow.indexOf("x=") + 2, inputRow.indexOf("..")));
                int x2 = Integer.valueOf(inputRow.substring(inputRow.indexOf("..") + 2));

                for (int x = x1; x <= x2; x++) {
                    walls.add(new Point(x, y));
                }
            }
        });

        return walls;
    }

    public String toString(char[][] playField) {
        StringBuilder result = new StringBuilder(playField.length * playField.length + 1);
        for (int row = 0; row < playField.length; row++) {
            for (int col = 0; col < playField[0].length; col++) {
                if (playField[row][col] == '#') {
                    result.append('#');
                } else if (playField[row][col] == '|') {
                    result.append('|');
                } else if (playField[row][col] == '~') {
                    result.append('~');
                } else {
                    result.append('.');
                }
            }
            result.append('\n');
        }
        return result.toString();
    }


}
