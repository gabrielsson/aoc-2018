package com.gabrielsson.adventofcode;

import org.junit.Assert;
import org.junit.Test;

public class Day4Test {

    @Test
    public void part1() {
        PuzzleInput input = new PuzzleInput("day4.txt");

        Assert.assertEquals(77084, Day4.part1(input.getListOfRows()));
    }

    @Test
    public void part2() {
        PuzzleInput input = new PuzzleInput("day4.txt");

        Assert.assertEquals(23047, Day4.part2(input.getListOfRows()));
    }

    @Test
    public void examplePart1() {
        PuzzleInput input = new PuzzleInput("day4example.txt");

        Assert.assertEquals(240, Day4.part1(input.getListOfRows()));
    }

    @Test
    public void examplePart2() {
        PuzzleInput input = new PuzzleInput("day4example.txt");

        Assert.assertEquals(4455, Day4.part2(input.getListOfRows()));
    }
}