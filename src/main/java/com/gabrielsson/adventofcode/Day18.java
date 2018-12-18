package com.gabrielsson.adventofcode;

import java.util.*;
import java.util.stream.Collectors;

public class Day18 {
    public Object part1(List<String> playArea) {

        int[][] lumberCollection = getLumberCollection(playArea);
        Set<List<Integer>> set = new HashSet<>();
        System.out.println(this.toString(lumberCollection));
        boolean isCycleFound = false;
        List<Integer> firstMatch = null;
        List<List<Integer>> matches = new ArrayList<>();
        int countCycle = 0;

        while (true) {
            int[][] finalLumberCollection = lumberCollection;
            int[][] tempCollection = Arrays.stream(lumberCollection).map(el -> el.clone()).toArray($ -> finalLumberCollection.clone());
            for (int row = 0; row < tempCollection.length; row++) {
                for (int col = 0; col < tempCollection.length; col++) {

                    int o = lumberCollection[row][col];
                    int[] adjecent = getAdjecentAcres(col, row, lumberCollection);
                    double numberOfTree = Arrays.stream(adjecent).filter(c -> c == '|').count();
                    double numberOfLumberyard = Arrays.stream(adjecent).filter(c -> c == '#').count();
                    if (o == '.' && numberOfTree >= 3) {
                        tempCollection[row][col] = '|';
                    } else if (o == '|' && numberOfLumberyard >= 3) {
                        tempCollection[row][col] = '#';
                    } else if (o == '#' && (numberOfTree < 1 || numberOfLumberyard < 1)) {
                        tempCollection[row][col] = '.';
                    }
                }
            }

            lumberCollection = Arrays.copyOf(tempCollection, tempCollection.length);
            List<Integer> lumberArray = getLumberCollection(lumberCollection);
            boolean isFirstRoundOfCycle = false;
            if (!set.add(lumberArray) && !isCycleFound) {
                isCycleFound = true;
                isFirstRoundOfCycle = true;
                firstMatch = lumberArray;
            }

            if (hasFinalResult(isCycleFound, firstMatch, lumberArray, isFirstRoundOfCycle)) {
                long result = 1000000000l - set.size() -1;
                long rest = result % countCycle;
                double numberOfTree = matches.get((int)rest)
                        .stream()
                        .filter(c -> c == '|').count();

                double numberOfLumberyard = matches.get((int) rest)
                        .stream()
                        .filter(c -> c == '#').count();

                return numberOfTree * numberOfLumberyard;
            }

            if (isCycleFound) {
                matches.add(lumberArray);
                countCycle++;
            }
        }
    }

    private boolean hasFinalResult(boolean isCycleFound, List<Integer> firstMatch, List<Integer> lumberArray, boolean isFirstRoundOfCycle) {
        return isCycleFound &&
                Arrays.equals(lumberArray.toArray(), firstMatch.toArray()) &&
                !isFirstRoundOfCycle;
    }

    private List<Integer> getLumberCollection(int[][] lumberCollection) {
        return Arrays.stream(lumberCollection).flatMapToInt(ints -> Arrays.stream(ints)).boxed().collect(Collectors.toList());
    }

    private int[][] getLumberCollection(List<String> input) {
        int[][] lumberCollection = new int[input.size()][input.size()];

        for (int y = 0; y < lumberCollection.length; y++) {
            String[] row = input.get(y).split("");
            for (int x = 0; x < lumberCollection.length; x++) {
                lumberCollection[y][x] = row[x].charAt(0);
            }
        }

        return lumberCollection;
    }

    private int[] getAdjecentAcres(int x, int y, int[][] lumberCollection) {
        int[] adjecentAcres = new int[8];
        int c = 0;
        for (int i = y - 1; i <= y + 1; i++) {
            for (int j = x - 1; j <= x + 1; j++) {
                if (i >= 0 && i < lumberCollection.length && j >= 0 && j < lumberCollection.length) {
                    if (i == y && j == x) {

                    } else {
                        adjecentAcres[c++] = lumberCollection[i][j];
                    }
                }
            }
        }
        return adjecentAcres;
    }

    private String toString(int[][] lumberCollection) {
        StringBuilder result = new StringBuilder(lumberCollection.length * lumberCollection.length + 1);
        for (int row = 0; row < lumberCollection.length; row++) {
            for (int col = 0; col < lumberCollection.length; col++) {
                result.append((char) lumberCollection[row][col]);
            }
            result.append('\n');
        }
        return result.toString();
    }
}
