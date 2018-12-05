package com.gabrielsson.adventofcode;


public class Day5 {
    public static int part1(String input) {

        return getReducedString(input).length();

    }

    public static Object part2(String input) {

        int length = Integer.MAX_VALUE;
        for (int character = 65; character < 91; character++) {
            String copy = input;
            String upperC = String.valueOf((char) character);
            String lowerC = String.valueOf((char) (character + 32));
            copy = copy.replaceAll(upperC, "");
            copy = copy.replaceAll(lowerC, "");
            copy = getReducedString(copy);

            if(length> copy.length()) length = copy.length();
        }

        return length;
    }

    private static String getReducedString(String input) {
        boolean changedouter = true;
        while (changedouter) {
            changedouter = false;
            for (int character = 65; character < 91; character++) {

                boolean changed = true;
                String upperC = String.valueOf((char) character);
                String lowerC = String.valueOf((char) (character + 32));

                while (changed) {
                    changed = false;

                    String copy = input.replaceAll(upperC + lowerC, "");
                    copy = copy.replaceAll(lowerC + upperC, "");

                    if (!copy.equals(input)) {
                        changed = true;
                        changedouter = true;
                    }

                    input = copy;
                }
            }
        }

        return input;
    }
}
