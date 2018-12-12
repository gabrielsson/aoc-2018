package com.gabrielsson.adventofcode;//package com.gabrielsson.adventofcode;

import org.junit.Assert;
import org.junit.Test;

public class Day12Test {

    @Test
    public void part1example() {
        PuzzleInput input = new PuzzleInput("day12example.txt");

        Day12 d = new Day12();
        Assert.assertEquals(325, d.part1(input.getListOfRows(), "#..#.#..##......###...###"));

    }

    @Test
    public void part1() {
        PuzzleInput input = new PuzzleInput("day12.txt");

        Day12 d = new Day12();
        Assert.assertEquals(325, d.part1(input.getListOfRows(), "##..#..##....#..#..#..##.#.###.######..#..###.#.#..##.###.#.##..###..#.#..#.##.##..###.#.#...#.##.."));

    }
}