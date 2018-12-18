package com.gabrielsson.adventofcode;//package com.gabrielsson.adventofcode;

import org.junit.Assert;
import org.junit.Test;

public class Day15Test {

    @Test
    public void part1example() {
        PuzzleInput input = new PuzzleInput("day15example.txt");

        Day15 d = new Day15();
        Assert.assertEquals(27730, d.part1(input.getListOfRows()));

    }

    @Test
    public void part1() {
        PuzzleInput input = new PuzzleInput("day15.txt");

        Day15 d = new Day15();
        Assert.assertEquals(36334, d.part1(input.getListOfRows()));

    }

    @Test
    public void part1own() {
        PuzzleInput input = new PuzzleInput("day15own.txt");

        Day15 d = new Day15();
        Assert.assertEquals(18740, d.part1(input.getListOfRows()));

    }
    @Test
    public void part139514() {
        PuzzleInput input = new PuzzleInput("day15example39514.txt");

        Day15 d = new Day15();
        Assert.assertEquals(39514, d.part1(input.getListOfRows()));

    }

    @Test
    public void part1example1() {
        PuzzleInput input = new PuzzleInput("day15example1.txt");

        Day15 d = new Day15();
        Assert.assertEquals(27755, d.part1(input.getListOfRows()));

    }

}