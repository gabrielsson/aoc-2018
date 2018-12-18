package com.gabrielsson.adventofcode;//package com.gabrielsson.adventofcode;

import org.junit.Assert;
import org.junit.Test;

public class Day18Test {

    @Test
    public void part1example() {
        PuzzleInput input = new PuzzleInput("day18example.txt");

        Day18 d = new Day18();
        Assert.assertEquals(1157, d.part1(input.getListOfRows()));


    }

    @Test
    public void part1() {
        PuzzleInput input = new PuzzleInput("day18.txt");

        Day18 d = new Day18();
        Assert.assertEquals(189720l, d.part1(input.getListOfRows()));

    }


}