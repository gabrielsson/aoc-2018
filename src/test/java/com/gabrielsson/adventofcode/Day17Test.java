package com.gabrielsson.adventofcode;//package com.gabrielsson.adventofcode;

import org.junit.Assert;
import org.junit.Test;

public class Day17Test {

    @Test
    public void part1example() {
        PuzzleInput input = new PuzzleInput("day17example.txt");

        Day17 d = new Day17();
        Assert.assertEquals(57l, d.part1(input.getListOfRows()));

    }

    @Test
    public void part1() {
        PuzzleInput input = new PuzzleInput("day17.txt");

        Day17 d = new Day17();
        Assert.assertEquals(646, d.part1(input.getListOfRows()));

    }


}