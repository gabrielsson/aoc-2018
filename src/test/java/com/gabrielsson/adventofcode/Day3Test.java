package com.gabrielsson.adventofcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class Day3Test {

    @Test
    public void part1() {
        PuzzleInput input = new PuzzleInput("day3.txt");
        System.out.println(Day3.part1(input.getListOfRows()));


    }

    @Test
    public void part2() {
        PuzzleInput input = new PuzzleInput("day3.txt");
        System.out.println(Day3.part2(input.getListOfRows()));


    }

    @Test
    public void example() {

        List<String> inputs = Arrays.asList(
                "#1 @ 1,3: 4x4",
                "#2 @ 3,1: 4x4",
                "#3 @ 5,5: 2x2"
        );

        System.out.println((Day3.part1(inputs)));
    }
}
