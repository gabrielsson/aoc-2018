package com.gabrielsson.adventofcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class Day5Test {


    @Test
    public void part1Test() {
        PuzzleInput input = new PuzzleInput("day5.txt");


        Assert.assertEquals(9288, Day5.part1(input.getInputScanner().next()));

    }
    @Test
    public void part2Test() {
        PuzzleInput input = new PuzzleInput("day5.txt");
        Assert.assertEquals(5844, Day5.part2(input.getInputScanner().next()));
    }

    @Test
    public void part2Example() {
        PuzzleInput input = new PuzzleInput("day5example.txt");
        Assert.assertEquals(4, Day5.part2(input.getInputScanner().next()));
    }

    @Test
    public void part1Example() {
        PuzzleInput input = new PuzzleInput("day5example.txt");


        Assert.assertEquals(10, Day5.part1(input.getInputScanner().next()));

    }
}
