package com.gabrielsson.adventofcode;//package com.gabrielsson.adventofcode;

import org.junit.Assert;
import org.junit.Test;

public class Day13Test {

    @Test
    public void part1example() {
        PuzzleInput input = new PuzzleInput("day13example.txt");

        Day13 d = new Day13();
        Assert.assertEquals("7,3", d.part1(input.getListOfRows()));

    }

    @Test
    public void part1() {
        PuzzleInput input = new PuzzleInput("day13.txt");

        Day13 d = new Day13();
        Assert.assertEquals("7,3", d.part1(input.getListOfRows()));

    }
}