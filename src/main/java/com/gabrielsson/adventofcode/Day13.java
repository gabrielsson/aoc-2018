package com.gabrielsson.adventofcode;//package com.gabrielsson.adventofcode;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day13 {
    List<Cart> carts = new ArrayList<>();

    public Object part1(List<String> playArea) {

        int y = 0;
        for (String row : playArea) {
            int x = 0;
            for (String s : row.split("")) {
                if (s.equals("<") ||
                        s.equals(">") ||
                        s.equals("^") ||
                        s.equals("v")) {

                    carts.add(new Cart(x, y, s));
                }

                x++;
            }
            y++;

        }

       int byxis = 2000000;
        while(true) {
            carts.sort(this::sort);
            Set<Cart> reduced = new HashSet<>();
            for(int i =0; i <carts.size(); i++) {
                carts.get(i).move(getField(carts.get(i).pos.x, carts.get(i).pos.y, playArea));

                reduced = checkCollition(carts);


            }
            carts.removeAll(reduced);


            if(carts.size() == 1) {
                stop(carts.get(0));
            }




            if(byxis < 0) break;
            byxis--;


        }


        return "7,3";
    }

    private Set<Cart> checkCollition(List<Cart> carts) {

        final Set<Cart> setToReturn = new HashSet<>();
        final Set<Cart> set1 = new HashSet<>();

        for (Cart cart : carts) {
            if (!set1.add(cart)) {
                setToReturn.add(cart);
            }
        }


        return setToReturn;


    }

    private String getField(int x, int y, List<String> playArea) {
        return playArea.get(y).substring(x, x+1);
    }

    private int sort(Cart c1, Cart c2) {

        if (c1.pos.y == c2.pos.y) {
            return Integer.compare(c1.pos.x, c2.pos.x);
        } else {
            return Integer.compare(c1.pos.y, c2.pos.y);
        }

    }


    class Cart {
        String id;
        Point pos;
        Turn nextTurn = Turn.LEFT;

        Direction direction;


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Cart cart = (Cart) o;
            return Objects.equals(pos, cart.pos);
        }

        @Override
        public int hashCode() {
            return Objects.hash(pos);
        }

        public Cart(int x, int y, String s) {
            init(x, y, s);
        }

        private void init(int x, int y, String s) {
            pos = new Point(x, y);
            id = "Initialpos: " + x + "," +y;
            switch (s) {
                case "<":
                    direction = Direction.WEST;
                    break;
                case ">":
                    direction = Direction.EAST;
                    break;
                case "v":
                    direction = Direction.SOUTH;
                    break;
                case "^":
                    direction = Direction.NORTH;
                    break;
            }
        }

        public void move(String field) {
            switch (field) {
                case "|":
                    this.move();
                    break;
                case "-":
                    this.move();
                    break;
                case "/":
                    this.turn("/");
                    this.move();
                    break;
                case "\\":
                    this.turn("\\");
                    this.move();
                    break;
                case "+":
                    this.chooseTurn();
                    this.move();
                    break;
                case "<":
                    this.move();
                    break;
                case ">":
                    this.move();

                    break;
                case "v":
                    this.move();

                    break;
                case "^":
                    this.move();
                    break;
                case " ":
                    System.out.println(":::Outside track:::");
                    System.out.println("Position: " + pos.toString());
                    break;
                default:
                    System.out.println(":::Outside track:::");
                    System.out.println("Position: " + pos.toString());
                    break;


            }
        }

        private void chooseTurn() {
            switch (direction) {
                case NORTH:
                    switch (nextTurn) {
                        case LEFT:
                            direction = Direction.WEST;
                            nextTurn = Turn.STRAIGHT;
                            break;
                        case STRAIGHT:
                            nextTurn = Turn.RIGHT;
                            break;
                        case RIGHT:
                            direction = Direction.EAST;
                            nextTurn = Turn.LEFT;
                            break;
                    }
                    break;
                case EAST:
                    switch (nextTurn) {
                        case LEFT:
                            direction = Direction.NORTH;
                            nextTurn = Turn.STRAIGHT;
                            break;
                        case STRAIGHT:
                            nextTurn = Turn.RIGHT;
                            break;
                        case RIGHT:
                            direction = Direction.SOUTH;
                            nextTurn = Turn.LEFT;
                            break;
                    }

                    break;
                case WEST:
                    switch (nextTurn) {
                        case LEFT:
                            direction = Direction.SOUTH;
                            nextTurn = Turn.STRAIGHT;
                            break;
                        case STRAIGHT:
                            nextTurn = Turn.RIGHT;
                            break;
                        case RIGHT:
                            direction = Direction.NORTH;
                            nextTurn = Turn.LEFT;
                            break;
                    }
                    break;
                case SOUTH:
                    switch (nextTurn) {
                        case LEFT:
                            direction = Direction.EAST;
                            nextTurn = Turn.STRAIGHT;
                            break;
                        case STRAIGHT:
                            nextTurn = Turn.RIGHT;
                            break;
                        case RIGHT:
                            direction = Direction.WEST;
                            nextTurn = Turn.LEFT;
                            break;
                    }
                    break;
            }
        }

        private void turn(String s) {
            if ("/".equals(s)) {
                switch (direction) {
                    case NORTH:
                        direction = Direction.EAST;
                        break;
                    case EAST:
                        direction = Direction.NORTH;
                        break;
                    case WEST:
                        direction = Direction.SOUTH;
                        break;
                    case SOUTH:
                        direction = Direction.WEST;
                        break;
                }

            } else {  // \
                switch (direction) {
                    case NORTH:
                        direction = Direction.WEST;
                        break;
                    case EAST:
                        direction = Direction.SOUTH;
                        break;
                    case WEST:
                        direction = Direction.NORTH;
                        break;
                    case SOUTH:
                        direction = Direction.EAST;
                        break;
                }
            }

        }


        private void move() {
            switch (direction) {
                case NORTH:
                    pos.y -= 1;
                    break;
                case EAST:
                    pos.x += 1;
                    break;
                case WEST:
                    pos.x -= 1;
                    break;
                case SOUTH:
                    pos.y += 1;
                    break;
                default:
                    break;
            }
        }


    }

    private void stop(Cart c) {
        System.out.println(":::First colltion:::");
        System.out.println("Position: " + c.pos.toString());
        System.exit(0);
    }

    enum Turn {
        LEFT, STRAIGHT, RIGHT
    }

    enum Direction {
        NORTH, SOUTH, EAST, WEST
    }


}
