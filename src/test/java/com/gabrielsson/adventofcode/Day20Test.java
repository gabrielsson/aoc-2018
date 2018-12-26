package com.gabrielsson.adventofcode;//package com.gabrielsson.adventofcode;

import org.junit.Assert;
import org.junit.Test;

public class Day20Test {

    @Test
    public void part1example() {
        PuzzleInput input = new PuzzleInput("day20example.txt");

        Day20 d = new Day20();
        Assert.assertEquals(2, d.part1(input.getInputScanner().next()));

    }

    @Test
    public void part1() {
        PuzzleInput input = new PuzzleInput("day20.txt");

        Day20 d = new Day20();
        Assert.assertEquals(2, d.part1(input.getInputScanner().next()));


    }

    @Test
    public void part2() {
        PuzzleInput input = new PuzzleInput("day20.txt");

        Day d = new Day();
        Assert.assertEquals(8, d.part2(input.getListOfRows()));

    }
}