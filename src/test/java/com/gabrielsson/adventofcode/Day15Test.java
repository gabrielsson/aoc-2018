package com.gabrielsson.adventofcode;//package com.gabrielsson.adventofcode;

import org.junit.Assert;
import org.junit.Test;

public class Day15Test {

    @Test
    public void part1example() {
        PuzzleInput input = new PuzzleInput("day15.txt");

        Day15 d = new Day15();
        Assert.assertEquals("7,3", d.part1(input.getListOfRows()));

    }

}