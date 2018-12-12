package com.gabrielsson.adventofcode;//package com.gabrielsson.adventofcode;

import java.util.Arrays;
import java.util.List;

public class Day12 {

    public Object part1(List<String> rules, String state) {


        //Adding 20 .....

        StringBuffer next = new StringBuffer(state);
        int x = 0;
        boolean converging = false;
        for (; x < 20; x++) {
            state = next.toString();
            next = new StringBuffer();
            for (int i = -1; i < state.length() + 1; i++) {
                boolean match = false;
                String nextChar = "";
                for (String rule : rules) {
                    String[] r = rule.substring(0, 5).split("\\B");
                    match = true;
                    nextChar = rule.substring(rule.length() - 1);
                    for (int j = -2; j < 3; j++) {

                        char current = '.';
                        if (i + j < 0) {
                            current = '.';
                        } else if (i + j >= state.length()) {
                            current = '.';
                        } else {
                            current = state.charAt(i + j);
                        }

                        char matcher = rule.charAt(j + 2);

                        if (current != matcher) {
                            match = false;
                            break;
                        }
                    }
                    if (match) {
                        break;
                    }

                }
                if (match) {

                    next.append(nextChar);
                } else {

                    next.append(".");

                }
            }
        }

        long sum = 0;
        String result = next.toString();
        System.out.print(result);
        for (int i = 0; i < result.length(); i++) {
            if ('#' == result.charAt(i)) {
                sum += i - x;
            }
        }
        return sum;
    }

    private int getHundred(int i) {
        String s = "" + i;
        if (s.length() < 3) {
            return 0;
        } else {
            return Integer.valueOf(s.substring(s.length() - 3, s.length() - 2));
        }
    }


}
