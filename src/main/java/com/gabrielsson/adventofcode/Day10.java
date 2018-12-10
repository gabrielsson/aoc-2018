package com.gabrielsson.adventofcode;//package com.gabrielsson.adventofcode;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class Day10 {
    List<Integer> rows;
    int index;

    public Object part1(List<String> input) {


        List<MovingPoint> list = input.stream().map(MovingPoint::new).collect(Collectors.toList());


        int localmaxx = list.stream().mapToInt(m -> m.point.x).max().getAsInt();
        int localmaxy = list.stream().mapToInt(m -> m.point.y).max().getAsInt();
        int localminx = list.stream().mapToInt(m -> m.point.x).min().getAsInt();
        int localminy = list.stream().mapToInt(m -> m.point.y).min().getAsInt();
        int dx = Integer.MAX_VALUE;
        int dy = Integer.MAX_VALUE;

        int seconds = -1;
        boolean isShrinking = true;
        while (isShrinking) {
            list.stream().forEach(MovingPoint::move);
            int olddx = dx;
            int olddy = dy;

            localmaxx = list.stream().mapToInt(m -> m.point.x).max().getAsInt();
            localmaxy = list.stream().mapToInt(m -> m.point.y).max().getAsInt();
            localminx = list.stream().mapToInt(m -> m.point.x).min().getAsInt();
            localminy = list.stream().mapToInt(m -> m.point.y).min().getAsInt();

            dx = localmaxx - localminx;
            dy = localmaxy - localminy;
            if (dy > olddy || dx > olddx) {
                isShrinking = false;

            }
            seconds++;
        }
        System.out.println(seconds + "   seconds"); //Part 2

        list.stream().forEach(MovingPoint::moveBack);
        printList(list, localmaxx, localmaxy, localminx, localminy); //Part 1


        return "";
    }

    private void printList(List<MovingPoint> list, int localmaxx, int localmaxy, int localminx, int localminy) {
        list.sort(this::sort);

        int currentPoint = 0;

        for (int y = localminy; y <= localmaxy; y++) {
            System.out.println(".");

            for (int x = localminx; x <= localmaxx; x++) {

                if (list.get(currentPoint).point.x == x && list.get(currentPoint).point.y == y) {
                    System.out.print("#");

                    while (list.get(currentPoint).point.x == x && list.get(currentPoint).point.y == y) {
                        currentPoint++;
                    }
                } else {
                    System.out.print(".");
                }
            }
        }
    }

    private int sort(MovingPoint m1, MovingPoint m2) {
        if (m1.point.y == m2.point.y) {
            return Integer.compare(m1.point.x, m2.point.x);
        } else {
            return Integer.compare(m1.point.y, m2.point.y);
        }
    }

    class MovingPoint {
        Point point;
        Point dpoint;

        public MovingPoint(String row) {
            int x = Integer.valueOf(row.substring(10, 16).trim());
            int y = Integer.valueOf(row.substring(17, 24).trim());
            int dx = Integer.valueOf(row.substring(36, 38).trim());
            int dy = Integer.valueOf(row.substring(39, 42).trim());
            point = new Point(x, y);
            dpoint = new Point(dx, dy);
        }

        private void move() {
            point.x += dpoint.x;
            point.y += dpoint.y;
        }

        public void moveBack() {
            point.x -= dpoint.x;
            point.y -= dpoint.y;
        }
    }

}
