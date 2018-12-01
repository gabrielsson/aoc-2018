package com.gabrielsson.adventofcode;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day1 {


    public static Object puzzle(List<Integer> input) {

        int sum = 0;

        for(int i: input) {
            sum += i;
        }


        return sum;
    }


    public static Object puzzle2(List<Integer> input) {

        List<Integer> freqs = new ArrayList<>();



        int current = 0;
        while(true) {
            for (int i = 0; i < input.size(); i++) {
                int freqChange = current + input.get(i);
                if (freqs.contains(freqChange)) {
                    return freqChange;
                } else {
                    freqs.add(freqChange);

                }
                current = freqChange;
            }

        }


    }

}
