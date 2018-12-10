package com.gabrielsson.adventofcode;//package com.gabrielsson.adventofcode;

import org.junit.Assert;
import org.junit.Test;

public class Day10Test {

    @Test
    public void part1() {
        PuzzleInput input = new PuzzleInput("day10.txt");

        Day10 d = new Day10();
        d.part1(input.getListOfRows());

    }
}