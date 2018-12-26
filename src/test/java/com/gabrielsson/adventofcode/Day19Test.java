package com.gabrielsson.adventofcode;//package com.gabrielsson.adventofcode;

import com.gabrielsson.adventofcode.day16.OP;
import com.gabrielsson.adventofcode.day16.OPRR;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class Day19Test {

    @Test
    public void part1example() {
        PuzzleInput input = new PuzzleInput("day19example.txt");

        Day19 d = new Day19();
        Assert.assertEquals(6, d.part1(input.getListOfRows()));

    }

    @Test
    public void part1() {
        PuzzleInput input = new PuzzleInput("day19.txt");

        Day19 d = new Day19();
        Assert.assertEquals(646, d.part1(input.getListOfRows()));

    }

    @Test
    public void part2() {
        PuzzleInput input = new PuzzleInput("day16part2");


    }

    @Test
    public void testAddr() {



        OP op = new OPRR("addr", (a,b) -> a+b);

        List<Long> result = op.doOperation(Arrays.asList(0l,1l,5l,1l,0l,955l), "addr 4 2 2" );
        List<Long> result2 = op.doOperation(Arrays.asList(0l,1l,5l,1l,0l,955l), "addr 4 2 2" );

        Assert.assertEquals("","");
    }
}