package com.gabrielsson.adventofcode;//package com.gabrielsson.adventofcode;

import org.junit.Assert;
import org.junit.Test;

public class Day9Test {

    @Test
    public void part1() {

        Day9 d = new Day9();
        Assert.assertEquals(42768, d.part1(429, 70901));

    }

    @Test
    public void part2() {

        Day9 d = new Day9();
        Assert.assertEquals(42768, d.part1(429, 70901 * 100));

    }

    @Test
    public void part1Example() {


        Day9 d = new Day9();
        Assert.assertEquals(32, d.part1(9, 25));

    }

    @Test
    public void part1ExampleOwn() {


        Day9 d = new Day9();
        Assert.assertEquals(32, d.part1(9, 47));

    }

    @Test
    public void part1Example1() {


        Day9 d = new Day9();
        Assert.assertEquals(8317l, d.part1(10, 1610));


    }

    @Test
    public void part1Example2() {


        Day9 d = new Day9();
        Assert.assertEquals(146373, d.part1(13, 7999));

    }

    @Test
    public void part2Example() {


        PuzzleInput input = new PuzzleInput("day8example.txt");

        Day8 d = new Day8();
        Assert.assertEquals(66, d.part2(input.getListOfIntegers()));


    }



}