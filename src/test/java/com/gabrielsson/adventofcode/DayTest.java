package com.gabrielsson.adventofcode;//package com.gabrielsson.adventofcode;

import org.junit.Assert;
import org.junit.Test;

public class DayTest {

    @Test
    public void part1example() {
        PuzzleInput input = new PuzzleInput("dayexample.txt");

        Day d = new Day();
        Assert.assertEquals(2, d.part1(input.getListOfRows()));

    }

    @Test
    public void part1() {
        PuzzleInput input = new PuzzleInput("day.txt");

        Day d = new Day();
        Assert.assertEquals(8, d.part1(input.getListOfRows()));

    }

    @Test
    public void part2() {
        PuzzleInput input = new PuzzleInput("day.txt");

        Day d = new Day();
        Assert.assertEquals(8, d.part1(input.getListOfRows()));

    }
}