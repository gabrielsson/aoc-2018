package com.gabrielsson.adventofcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class Day1Test {

    @Test
    public void puzzle() {
        PuzzleInput input = new PuzzleInput("day1.txt");

        Day1 d = new Day1();

        System.out.println(d.puzzle(input.getListOfIntegers()));
    }
    @Test
    public void puzzle2() {
        PuzzleInput input = new PuzzleInput("day1.txt");

        Day1 d = new Day1();

        System.out.println(d.puzzle2(input.getListOfIntegers()));
    }

    @Test
    public void puzzle2_e() {

        Day1 d = new Day1();

        List<Integer> inputs = Arrays.asList(+3, +3, +4, -2, -4);

        //System.out.println(d.puzzle2(inputs));
    }


    @Test
    public void puzzle2_c() {

        Day1 d = new Day1();

        List<Integer> inputs = Arrays.asList(  +7, +7, -2, -7, -4
        );

        //System.out.println(d.puzzle2(inputs));
    }


}