package com.gabrielsson.adventofcode;//package com.gabrielsson.adventofcode;

import org.junit.Assert;
import org.junit.Test;

public class Day16Test {

    @Test
    public void part1example() {
        PuzzleInput input = new PuzzleInput("day16example.txt");

        Day16 d = new Day16();
        Assert.assertEquals(1, d.part1(input.getListOfRows()));

    }

    @Test
    public void part1() {
        PuzzleInput input = new PuzzleInput("day16.txt");

        Day16 d = new Day16();
        Assert.assertEquals(646, d.part1(input.getListOfRows()));

    }

    @Test
    public void part2() {
        PuzzleInput input = new PuzzleInput("day16part2");

        Day16 d = new Day16();
        Assert.assertEquals("681", d.part2(input.getListOfRows()));

    }

}