package com.gabrielsson.adventofcode;//package com.gabrielsson.adventofcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class Day8Test {

    @Test
    public void part1() {
        PuzzleInput input = new PuzzleInput("day8.txt");

        Day8 d = new Day8();
        Assert.assertEquals(42768, d.part1(input.getListOfIntegers()));

    }
    @Test
    public void part2() {
        PuzzleInput input = new PuzzleInput("day8.txt");

        Day8 d = new Day8();
        Assert.assertEquals(34348, d.part2(input.getListOfIntegers()));

    }

    @Test
    public void part1Example() {

        PuzzleInput input = new PuzzleInput("day8example.txt");

        Day8 d = new Day8();
        Assert.assertEquals(138, d.part1(input.getListOfIntegers()));

    }


    @Test
    public void part2Example() {


        PuzzleInput input = new PuzzleInput("day8example.txt");

        Day8 d = new Day8();
        Assert.assertEquals(66, d.part2(input.getListOfIntegers()));


    }



}