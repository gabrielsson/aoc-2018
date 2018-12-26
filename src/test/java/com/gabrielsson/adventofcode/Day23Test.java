package com.gabrielsson.adventofcode;//package com.gabrielsson.adventofcode;

import org.junit.Assert;
import org.junit.Test;

public class Day23Test {

    @Test
    public void part1example() {
        PuzzleInput input = new PuzzleInput("day23example.txt");

        Day23 d = new Day23();
        Assert.assertEquals(7, d.part1(input.getListOfRows()));

    }

    @Test
    public void part1() {
        PuzzleInput input = new PuzzleInput("day23.txt");

        Day23 d = new Day23();
        Assert.assertEquals(2, d.part1(input.getListOfRows()));


    }

    @Test
    public void part2() {
        PuzzleInput input = new PuzzleInput("day23example2.txt");

        Day23 d = new Day23();
        Assert.assertEquals(36, d.part1(input.getListOfRows()));

    }
}