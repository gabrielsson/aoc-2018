package com.gabrielsson.adventofcode;

import java.util.ArrayList;
import java.util.List;

public class Day23 {
    public Object part1(List<String> input) {

        List<Nano> nanos = new ArrayList<>();
        for (String s : input) {
            nanos.add(new Nano(s));


        }


        return getPart1(nanos).size();

    }
    public Object part2(List<String> input) {

        List<Nano> nanos = new ArrayList<>();
        for (String s : input) {
            nanos.add(new Nano(s));


        }


        return getPart2(nanos);

    }

    private Object getPart2(List<Nano> nanos) {
        List<Nano> all = getPart1(nanos);
        Nano initialGuess = null;
        int numberOfResults = 0;
        for (Nano nano : nanos) {
            List<Nano> result = new ArrayList<>();

            for (Nano nano2 : nanos) {

                if (distanceTo(nano, nano2) <= nano2.r) {
                    result.add(nano);
                }
            }
            if (result.size() > numberOfResults) {
                numberOfResults = result.size();
                initialGuess = nano;
            }

        }

        Nano origo = new Nano();

        Nano coordinateWithMostBotsConnected = new Nano();
        numberOfResults = 0;
        long stepper = 1000000000;

        for (int i = 8; i > 1; i--) {
            stepper = Long.valueOf((stepper + "").substring(0, i)); //10000000,1000000,100000,10000,1000,100,10
            for (long z = initialGuess.z - stepper; z <= initialGuess.z + stepper; z = z + (stepper / 10)) {

                for (long x = initialGuess.x - stepper; x <= initialGuess.x + stepper; x = x + (stepper / 10)) {
                    for (long y = initialGuess.y - stepper; y <= initialGuess.y + stepper; y = y + (stepper / 10)) {

                        boolean isInRangeOfAll = true;
                        Nano tester = new Nano();
                        tester.x = x;
                        tester.y = y;
                        tester.z = z;
                        List<Nano> result = new ArrayList<>();

                        for (Nano nano : nanos) {
                            if (distanceTo(tester, nano) <= nano.r) {
                                result.add(nano);
                            }
                        }

                        if (result.size() == numberOfResults) {
                            if(distanceTo(tester, origo)<distanceTo(coordinateWithMostBotsConnected, origo)) {
                                coordinateWithMostBotsConnected = tester;
                            }
                        } else if (result.size() > numberOfResults) {
                            numberOfResults = result.size();
                            coordinateWithMostBotsConnected = tester;
                        }
                    }
                }
            }

            initialGuess = coordinateWithMostBotsConnected;

            System.out.println(initialGuess);
        }

        long distance = distanceTo(coordinateWithMostBotsConnected, origo);
        System.out.println(coordinateWithMostBotsConnected);
        return distance; //84430346 84427646 84427316  84397316  84337913 !82010396
    }

    private List<Nano> getPart1(List<Nano> nanos) {
        Nano maxNano = nanos.stream()
                .sorted((n1, n2) -> Long.compare(n2.r, n1.r))
                .findFirst()
                .get();

        List<Nano> result = new ArrayList<>();

        for (Nano nano : nanos) {
            if (distanceTo(nano, maxNano) <= maxNano.r) {
                result.add(nano);
            }
        }


        return result;
    }

    private long distanceTo(Nano n1, Nano n2) {
        int distance = 0;

        distance += Math.abs(n1.x - n2.x);
        distance += Math.abs(n1.y - n2.y);
        distance += Math.abs(n1.z - n2.z);


        return distance;

    }

    private class Nano {

        long x, y, z, r = 0;

        public Nano() {
        }

        ;

        public Nano(String s) {
            x = Integer.valueOf(s.substring(5, s.indexOf(",")));
            String stripped = s.substring(s.indexOf(x + "") + (x + "").length() + 1);
            y = Integer.valueOf(stripped.substring(0, stripped.indexOf(",")));
            stripped = stripped.substring(stripped.indexOf(y + "") + (y + "").length() + 1);
            z = Integer.valueOf(stripped.substring(0, stripped.indexOf(">")));
            r = Integer.valueOf(stripped.substring(stripped.indexOf("r=") + 2));

        }

        @Override
        public String toString() {
            return x +
                    "," + y +
                    "," + z;
        }
    }
}
