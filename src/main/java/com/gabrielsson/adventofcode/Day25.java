package com.gabrielsson.adventofcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day25 {
    public int part1(List<String> listOfRows) {

        List<List<Integer>> timeSpace = new ArrayList<>();

        for (String entry : listOfRows) {
            List<Integer> point = Arrays.stream(entry.split(","))
                    .mapToInt(s -> Integer.valueOf(s))
                    .boxed()
                    .collect(Collectors.toList());
            timeSpace.add(point);
        }


        List<Constallation> constallations = new ArrayList<>();

        for (List<Integer> point : timeSpace) {
            boolean joined = false;
            for (Constallation constallation : constallations) {
                if (isInConstellation(point, constallation)) {
                    constallation.stars.add(point);
                    joined = true;
                }
            }

            if (!joined) {
                constallations.add(new Constallation(point));
            }
        }

        boolean reduced = true;
        while (reduced) {
            reduced = false;
            constallations = constallations.stream().filter(c -> c.stars.size() > 0).collect(Collectors.toList());
            for (Constallation c1 : constallations) {
                for (Constallation c2 : constallations) {
                    if (!c1.stars.equals(c2.stars) && isClose(c1, c2)) {
                        c2.stars.addAll(c1.stars);
                        c1.stars.clear();
                        reduced = true;
                    }
                }
            }
        }


        return constallations.size();

    }


    private boolean isInConstellation(List<Integer> point, Constallation constallation) {
        for (List<Integer> point1 : constallation.stars) {
            if (isClose(point, point1)) {
                return true;
            }
        }
        return false;
    }


    private boolean joinExistingConstallation(List<Integer> star, List<Constallation> constallations) {
        for (Constallation constallation : constallations) {
            for (List<Integer> point : constallation.stars) {
                if (isClose(point, star)) {
                    constallation.stars.add(star);
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isClose(Constallation point, Constallation candidate) {
        for (List<Integer> stars : point.stars) {
            for (List<Integer> candidateStars : candidate.stars) {
                if (isClose(stars, candidateStars)) return true;
            }
        }

        return false;
    }

    private boolean isClose(List<Integer> point, List<Integer> candidate) {
        int distance = 0;
        for (int i = 0; i < point.size(); i++) {
            distance += Math.abs(point.get(i) - candidate.get(i));

        }
        return distance < 4;

    }

    class Constallation {
        List<List<Integer>> stars = new ArrayList<>();


        public Constallation(List<Integer> star) {
            this.stars.add(star);
        }
    }
}
