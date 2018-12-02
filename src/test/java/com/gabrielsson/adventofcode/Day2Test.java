package com.gabrielsson.adventofcode;

import org.junit.Assert;
import org.junit.Test;

public class Day2Test {

    @Test
    public void puzzle() {
        PuzzleInput input = new PuzzleInput("day2.txt");
        System.out.println(Day2.part1(input.getListOfRows()));
        Assert.assertEquals(6972, Day2.part1(input.getListOfRows()));

    }

    @Test
    public void puzzle2() {
        PuzzleInput input = new PuzzleInput("day2.txt");
        System.out.println(Day2.part2(input.getListOfRows()));
        Assert.assertEquals("aixwcbzrmdvpsjfgllthdyoqe", Day2.part2(input.getListOfRows()));
    }
}