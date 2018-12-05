package com.gabrielsson.adventofcode;


import java.util.Arrays;

public class Day5 {
    //part2
    public static int part1(String input) {


        char[] list = input.toCharArray();
        //char[] result = getReducedArray(list);


        return getReducedArray(list);//new String(result);


    }

    private static int getReducedArray(char[] input) {
        char[] list = Arrays.copyOf(input, input.length);

        char[] copy = Arrays.copyOf(list, list.length);
        boolean changed = true;
        int length = Integer.MAX_VALUE;

        for (int character = 65; character < 91; character++) {
            list = Arrays.copyOf(input, input.length);

            list = removeAllCharacters(list, character);
            copy = Arrays.copyOf(list, list.length);

            changed = true;

            while (changed) {
                changed = false;
                for (int i = 0; i < list.length - 1; i++) {
                    char l1 = list[i];
                    char l2 = list[i + 1];

                    if (Character.toUpperCase(l1) == Character.toUpperCase(l2)) {
                        if (l1 != l2) {
                            changed = true;
                            copy = remove(copy, i);
                            copy = remove(copy, i);
                            i++;
                            break;
                        }
                    }

                }
                list = copy;

            }

            if (list.length < length) {
                length = list.length;

            }
        }


        return length;


    }

    private static char[] removeAllCharacters(char[] symbols, int  character) {

        String reduced = new String(symbols);
        char upperC = (char) character;
        char lowerC = (char) (character + 32);


        reduced = reduced.replaceAll(String.valueOf(upperC), "");
        reduced = reduced.replaceAll(String.valueOf(lowerC), "");

        return reduced.toCharArray();
    }

    private static char[] remove(char[] symbols, int i) {
        char[] copy = new char[symbols.length - 1];
        System.arraycopy(symbols, 0, copy, 0, i);
        System.arraycopy(symbols, i + 1, copy, i, symbols.length - i - 1);
        return copy;

    }
}
