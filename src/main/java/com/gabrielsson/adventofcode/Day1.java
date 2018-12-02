package com.gabrielsson.adventofcode;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class Day1 {


    public static Object puzzle(List<Integer> input) {
        return input.stream().reduce(0, Integer::sum);
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
