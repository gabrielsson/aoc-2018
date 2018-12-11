package com.gabrielsson.adventofcode;//package com.gabrielsson.adventofcode;

public class Day11 {

    public Object part2(int serialNumber) {


        int[][] cells = new int[300][300];

        for(int x = 0; x<300; x++) {
            for(int y = 0; y < 300; y++) {
                cells[x][y] =
                        getHundred((((x + 10) * y) + serialNumber) * (x + 10)) - 5;
            }
        }

        int largestSum = 0;
        int lx = 0;
        int ly = 0;
        int lsize = 0;
        for(int x = 0; x<297; x++) {
            for (int y = 0; y < 297; y++) {
                for(int size = 0; size < 300 - x && size < 300 - y; size++) {
                    int fieldSum = 0;

                    for (int i = 0; i < size; i++) {
                        for (int j = 0; j < size; j++)
                            fieldSum += cells[x + i][y + j];
                    }
                    if(fieldSum > largestSum) {
                        largestSum = fieldSum;
                        lx = x;
                        ly = y;
                        lsize = size;
                    }
                }


            }
        }


        return lx+","+ly+","+lsize;
    }

    private int getHundred(int i) {
        String s = "" + i;
        if(s.length() < 3) {
            return 0;
        } else {
            return Integer.valueOf(s.substring(s.length() -3,s.length() -2));
        }
    }


}
