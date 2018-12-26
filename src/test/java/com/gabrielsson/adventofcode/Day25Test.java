package com.gabrielsson.adventofcode;//package com.gabrielsson.adventofcode;

import com.gabrielsson.adventofcode.day16.OP;
import com.gabrielsson.adventofcode.day16.OPRR;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class Day25Test {

    @Test
    public void part1example() {
        PuzzleInput input = new PuzzleInput("day25example.txt");

        Day25 d = new Day25();
        Assert.assertEquals(2, d.part1(input.getListOfRows()));

    }
    @Test
    public void part1example1() {
        PuzzleInput input = new PuzzleInput("day25example1.txt");

        Day25 d = new Day25();
        Assert.assertEquals(4, d.part1(input.getListOfRows()));

    }

    @Test
    public void part1example2() {
        PuzzleInput input = new PuzzleInput("day25example2.txt");

        Day25 d = new Day25();
        Assert.assertEquals(8, d.part1(input.getListOfRows()));

    }

    @Test
    public void part1example3() {
        PuzzleInput input = new PuzzleInput("day25example3.txt");

        Day25 d = new Day25();
        Assert.assertEquals(3, d.part1(input.getListOfRows()));

    }

    @Test
    public void part1exampleOwn() {
        PuzzleInput input = new PuzzleInput("day25exampleOwn.txt");

        Day25 d = new Day25();
        Assert.assertEquals(1, d.part1(input.getListOfRows()));

    }
    @Test
    public void part1() {
        PuzzleInput input = new PuzzleInput("day25.txt");

        Day25 d = new Day25();
        Assert.assertEquals(8, d.part1(input.getListOfRows()));

    }
}