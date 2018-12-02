package com.gabrielsson.adventofcode;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day2 {


    public static Object part1(List<String> rows) {
        AtomicInteger scoredTwos = new AtomicInteger();
        AtomicInteger scoredThrees = new AtomicInteger();

        rows.stream().forEach(row -> {
                    AtomicInteger twos = new AtomicInteger();
                    AtomicInteger threes = new AtomicInteger();

                    getCharacterScores(row).stream().forEach(score -> {
                        increment_when(twos, score == 2);
                        increment_when(threes, score == 3);
                    });

                    increment_when(scoredTwos, thereAreOneOrMore(twos));
                    increment_when(scoredThrees, thereAreOneOrMore(threes));
                }
        );
        return scoredThrees.get() * scoredTwos.get();
    }

    private static void increment_when(AtomicInteger number, boolean condition) {
        if (condition) {
            number.getAndIncrement();
        }
    }

    private static boolean thereAreOneOrMore(AtomicInteger twos) {
        return twos.get() >= 1;
    }

    private static Collection<Integer> getCharacterScores(String row) {
        Map<String, Integer> charMap = new HashMap<>();
        Arrays.stream(row.split("\\B")).forEach(character -> {
            int score = charMap.getOrDefault(character, 0);
            charMap.put(character, score + 1);
        });

        return charMap.values();
    }


    //part2
    public static String part2(List<String> input) {
        return input.stream()
                .map(current -> extractElement(input, current))
                .flatMap(Stream::distinct)
                .findFirst().get();
    }

    private static Stream<String> extractElement(List<String> input, String current) {
        return input.stream()
                .filter(compared -> !current.equals(compared))
                .map(compared -> match(current, compared))
                .filter(Objects::nonNull);
    }

    private static String match(String current, String compared) {
        AtomicReference<String> match = new AtomicReference<>();

        IntStream.range(0, compared.length()).parallel().forEach(i -> {
            String currentReduced = current.substring(0, i) + current.substring(i + 1);
            String comparedReduced = compared.substring(0, i) + compared.substring(i + 1);
            if (currentReduced.equals(comparedReduced)) {
                match.set(comparedReduced);
            }
        });

        return match.get();
    }

}
