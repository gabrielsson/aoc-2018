package com.gabrielsson.adventofcode;//package com.gabrielsson.adventofcode;

import java.util.Arrays;
import java.util.List;

public class Day12 {

    public Object part1(List<String> rules, String state) {

        String[] s = ("...................."+state + "........................................................................................................................").split("\\B");

        //Adding 20 .....

        String[] next = s.clone();

        for (int x = 0; x < 20; x++) {
            s = next.clone();

            for (int i = 0; i < s.length; i++) {
                boolean match = false;
                String nextChar ="";
                for (String rule : rules) {
                    String[] r = rule.substring(0, 5).split("\\B");
                    match = true;
                    nextChar = rule.substring(rule.length() - 1);
                    for (int j = -2; j < 3; j++) {

                        String current = "";
                        if (i + j < 0) {
                            current = ".";
                        } else if (i + j >= s.length) {
                            current = ".";
                        } else {
                            current = s[i + j];
                        }

                        String matcher = r[j + 2];

                        if (!current.equals(matcher)) {
                            match = false;
                            break;
                        }
                    }
                    if(match) {
                        break;
                    }

                }
                if (match) {

                    next[i] = nextChar;
                } else {
                    if (i >= 0)
                        next[i] = ".";
                }
            }
        }

        int sum = 0;
        for(int i = 0; i < next.length;  i++) {
            if ("#".equals(next[i])) {
                sum += i -20;
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
