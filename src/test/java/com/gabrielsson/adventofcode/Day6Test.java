package com.gabrielsson.adventofcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class Day6Test {

    @Test
    public void part1() {
        PuzzleInput input = new PuzzleInput("day6.txt");

        Assert.assertEquals("", Day6.part1(input.getListOfRows()));

        System.out.println(Day6.part1(input.getListOfRows()) + "");
    }
    @Test
    public void part2() {
        PuzzleInput input = new PuzzleInput("day6.txt");

        Assert.assertEquals("", Day6.part2(input.getListOfRows()));

        System.out.println(Day6.part1(input.getListOfRows()) + "");
    }

    @Test
    public void part1Example() {

        PuzzleInput input = new PuzzleInput("day6example.txt");

        Assert.assertEquals(17, Day6.part1(input.getListOfRows()));

        System.out.println(Day6.part1(input.getListOfRows()) + "");
    }


    @Test
    public void part2Example() {

        PuzzleInput input = new PuzzleInput("day6example.txt");

        Assert.assertEquals("", Day6.part2(input.getListOfRows()));

        System.out.println(Day6.part2(input.getListOfRows()) + "");
    }


}