package com.gabrielsson.adventofcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class Day5Test {


    @Test
    public void part1Test() {
        PuzzleInput input = new PuzzleInput("day5.txt");


        Assert.assertEquals(4, Day5.part1(input.getInputScanner().next()));

    }
}
