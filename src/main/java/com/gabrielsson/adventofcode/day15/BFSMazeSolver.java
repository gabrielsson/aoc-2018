package com.gabrielsson.adventofcode.day15;


import java.util.*;

public class BFSMazeSolver {
    private static final int[][] DIRECTIONS = {  { 1, 0 },{ -1, 0 },{ 0, 1 },{ 0, -1 } };

    public List<Coordinate> solve(Maze maze, Asset player, Asset end) {
        LinkedList<Coordinate> nextToVisit = new LinkedList<>();
        maze.setStart(player.coordinate);
        maze.setEnd(end.coordinate);


        Coordinate start = player.coordinate;
        nextToVisit.add(start);

        while (!nextToVisit.isEmpty()) {
            Coordinate cur = nextToVisit.remove();

            if (!maze.isValidLocation(cur.getY(), cur.getX()) || maze.isExplored(cur.getY(), cur.getX())) {
                continue;
            }

            if (maze.isWall(cur.getY(), cur.getX()) && !maze.isStart(cur.getX(), cur.getY()) && !maze.isExit(cur.getX(), cur.getY())) {
                maze.setVisited(cur.getY(), cur.getX(), true);
                continue;
            }

            if (maze.isExit(cur.getX(), cur.getY())) {

                Coordinate last = new Coordinate(cur.getX(), cur.getY(), cur);
                return backtrackPath(last);
            }

            for (int[] direction : DIRECTIONS) {
                Coordinate coordinate = new Coordinate(cur.getX() + direction[0], cur.getY() + direction[1], cur);
                nextToVisit.add(coordinate);
                maze.setVisited(cur.getY(), cur.getX(), true);
            }
        }


        return Collections.emptyList();
    }

    private List<Coordinate> backtrackPath(Coordinate cur) {
        List<Coordinate> path = new ArrayList<>();
        Coordinate iter = cur;

        while (iter != null) {
            path.add(iter);
            iter = iter.parent;
        }

        return path;
    }
}

