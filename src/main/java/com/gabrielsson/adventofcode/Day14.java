package com.gabrielsson.adventofcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Day14 {

    public Object part1(int end) {
        List<Integer> list = new ArrayList<>();
        int index1 = 0;
        int index2 = 1;
        list.add(3);
        list.add(7);

        int i = 1;
        while(end > list.size() - 10 ) {
            int n1 = list.get(index1);
            int n2 = list.get(index2);
            int sum = n1 + n2;

            String[] s = (sum + "").split("");

            for(String c:s) {
                if(end > list.size() - 10) {
                    list.add(Integer.valueOf(c));
                    i++;
                }


            }

            index1 = getIndex(index1, n1, list.size());
            index2 = getIndex(index2, n2, list.size());


        }


        StringBuffer b = new StringBuffer();
        for(int j = list.size()-10; j < list.size(); j++) {
            b.append(list.get(j).toString());
        }

        return b.toString();
    }

    private int getIndex(int index, int number, int listSize) {
        index += number +1;
        while(index >= listSize) {
            index -= listSize;
        }

        return index;
    }

    public int part2(String input) {

        List<Integer> list = new ArrayList<>();
        int index1 = 0;
        int index2 = 1;
        list.add(3);
        list.add(7);

        int index = 0;
        int i = 1;
        while(true) {
            int n1 = list.get(index1);
            int n2 = list.get(index2);
            int sum = n1 + n2;

            String[] s = (sum + "").split("");

            for(String c:s) {
                list.add(Integer.valueOf(c));
                i++;
            }

            index1 = getIndex(index1, n1, list.size());
            index2 = getIndex(index2, n2, list.size());

            StringBuilder b = new StringBuilder();
            for(int j = list.size() -10; j <list.size(); j++) {
                if(j > 0) {
                    b.append(list.get(j));
                }
            }

            if(b.toString().contains(input)) {
                index = i - input.length() +(10 - b.toString().indexOf(input) +1  - input.length());
                break;
            }
        }

        return index;

    }
}
