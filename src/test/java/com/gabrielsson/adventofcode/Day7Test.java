package com.gabrielsson.adventofcode;

import org.junit.Assert;
import org.junit.Test;

public class Day7Test {

    @Test
    public void part1() {
        PuzzleInput input = new PuzzleInput("day7.txt");

        Assert.assertEquals("OCPUEFIXHRGWDZABTQJYMNKVSL", Day7.part1(input.getListOfRows()));

        System.out.println(Day7.part1(input.getListOfRows()) + "");

        //OCPUEFIXHRGWDZABTQJYMNKVSL
    }
    @Test
    public void part2() {
        PuzzleInput input = new PuzzleInput("day7.txt");

        Assert.assertEquals("", Day7.part2(input.getListOfRows(), 5, 60));

        System.out.println(Day7.part1(input.getListOfRows()) + "");
    }

    @Test
    public void part1Example() {

        PuzzleInput input = new PuzzleInput("day7example.txt");

        Assert.assertEquals("CABDFE", Day7.part1(input.getListOfRows()));

        System.out.println(Day7.part1(input.getListOfRows()) + "");
    }


    @Test
    public void part2Example() {

        PuzzleInput input = new PuzzleInput("day7example.txt");

        Assert.assertEquals(15, Day7.part2(input.getListOfRows(), 2, 0));

        //System.out.println(Day7.part2(input.getListOfRows()) + "");
    }


}