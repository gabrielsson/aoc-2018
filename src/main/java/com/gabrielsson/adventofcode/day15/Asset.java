package com.gabrielsson.adventofcode.day15;

import com.gabrielsson.adventofcode.Day15;

public class Asset {

    public int gameClass;
    public int hp = 200;
    public int attack = 3;
    public Coordinate coordinate;

    public Asset(int gameClass, Coordinate coordinate) {
        this.gameClass = gameClass;
        this.coordinate = coordinate;
    }
}


