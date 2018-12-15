package com.gabrielsson.adventofcode.day15;

import java.util.Arrays;
import java.util.List;

import static com.gabrielsson.adventofcode.day15.Constants.*;

public class Maze {


    private Asset[][] maze;
    private boolean[][] visited;


    private Coordinate start;
    private Coordinate end;

    public Maze(List<String> text) {


        String[] lines = new String[text.size()];
        for (int i = 0; i < text.size(); i++)
            lines[i] = text.get(i);
        initializeMaze(lines);
    }

    public Asset[][] getMaze() {
        return Arrays.stream(maze)
                .map(Asset[]::clone)
                .toArray(Asset[][]::new);
    }

    private void initializeMaze(String[] lines) {


        maze = new Asset[lines.length][lines[0].length()];
        visited = new boolean[lines.length][lines[0].length()];

        for (int row = 0; row < getHeight(); row++) {
            if (lines[row].length() != getWidth()) {
                throw new IllegalArgumentException("line " + (row + 1) + " wrong length (was " + lines[row].length() + " but should be " + getWidth() + ")");
            }

            for (int col = 0; col < getWidth(); col++) {
                if (lines[row].charAt(col) == '#')
                    maze[row][col] = new Asset(WALL, new Coordinate(col, row));
                else if (lines[row].charAt(col) == 'E') {
                    maze[row][col] = new Asset(ELF, new Coordinate(col, row));
                } else if (lines[row].charAt(col) == 'G') {
                    maze[row][col] = new Asset(GOBLIN, new Coordinate(col, row));
                } else {
                    maze[row][col] = new Asset(ROAD, new Coordinate(col, row));
                }
            }
        }
    }

    public void setStart(Coordinate start) {
        this.start = new Coordinate(start.x, start.y);
    }

    public void setEnd(Coordinate end) {
        this.end = new Coordinate(end.x, end.y);
    }

    public int getHeight() {
        return maze.length;
    }

    public int getWidth() {
        return maze[0].length;
    }

    public void setMaze(Asset[][] maze) {
        this.maze = maze;
    }

    public Coordinate getEntry() {
        return start;
    }

    public Coordinate getExit() {
        return end;
    }

    public boolean isExit(int x, int y) {
        return x == end.getX() && y == end.getY();
    }

    public boolean isStart(int x, int y) {
        return x == start.getX() && y == start.getY();
    }

    public boolean isExplored(int row, int col) {
        return visited[row][col];
    }

    public boolean isWall(int row, int col) {
        int gameClass = maze[row][col].gameClass;

        return gameClass > 0;
    }

    public void setVisited(int row, int col, boolean value) {
        visited[row][col] = value;
    }

    public boolean isValidLocation(int row, int col) {
        if (row < 0 || row >= getHeight() || col < 0 || col >= getWidth()) {
            return false;
        }
        return true;
    }

    public void printPath(List<Coordinate> path) {
        Asset[][] tempMaze = getMaze();
        for (Coordinate coordinate : path) {
            if (isStart(coordinate.getX(), coordinate.getY()) || isExit(coordinate.getX(), coordinate.getY())) {
                continue;
            } else {
                if(!isWall(coordinate.getY(),coordinate.getX())) {
                    tempMaze[coordinate.getY()][coordinate.getX()].gameClass = Constants.PATH;
                }
            }
        }
        System.out.println(toString(tempMaze));
    }

    public String toString(Asset[][] maze) {
        StringBuilder result = new StringBuilder(getWidth() * (getHeight() + 1));
        for (int row = 0; row < getHeight(); row++) {
            for (int col = 0; col < getWidth(); col++) {
                if(start.getX() == col && start.getY() == row) {
                    result.append('S');
                } else if(end.getX() == col && end.getY() == row) {
                    result.append('X');
                } else if (maze[row][col].gameClass == ROAD) {
                    result.append(' ');
                } else if (maze[row][col].gameClass == WALL) {
                    result.append('#');
                } else if (maze[row][col].gameClass == ELF) {
                    result.append('E');
                } else if (maze[row][col].gameClass == GOBLIN) {
                    result.append('G');
                } else if (maze[row][col].gameClass == PATH){
                    result.append('.');
                }
            }
            result.append('\n');
        }
        return result.toString();
    }

    public void reset() {
        for (int i = 0; i < visited.length; i++) {
            Arrays.fill(visited[i], false);
            Arrays.stream(maze).flatMap(as -> Arrays.stream(as)).filter(a -> a.gameClass == PATH).forEach(a -> a.gameClass = ROAD);
        }
    }


}