package com.gabrielsson.adventofcode;//package com.gabrielsson.adventofcode;

import org.junit.Assert;
import org.junit.Test;

public class Day14Test {

    @Test
    public void part1example() {

        Day14 d = new Day14();
        Assert.assertEquals("5158916779", d.part1(864801));

    }

    @Test
    public void part2example() {

        Day14 d = new Day14();
        Assert.assertEquals(2018, d.part2("59414"));
        Assert.assertEquals(18, d.part2("92510"));
        Assert.assertEquals(5, d.part2("01245"));
        Assert.assertEquals(9, d.part2("51589"));

    }

    @Test
    public void part2() {

        Day14 d = new Day14();
        Assert.assertEquals(20279772, d.part2("864801")); //20279773

    }




}