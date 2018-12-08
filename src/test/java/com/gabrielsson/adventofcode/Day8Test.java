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
        Assert.assertEquals(3722, d.part1(input.getListOfIntegers()));
//1 2 1 6 9 1 9 1 1 2 1 2 1 1 2 3

    }
    @Test
    public void part2() {
        PuzzleInput input = new PuzzleInput("day8.txt");

        Day8 d = new Day8();
        Assert.assertEquals(3722, d.part2(input.getListOfIntegers()));

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