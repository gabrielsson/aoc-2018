package com.gabrielsson.adventofcode;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Day3 {

    public static Object part1(List<String> rows) {

        List<Claim> claims = new ArrayList<>();


        Map<Integer, Map<Integer, List<Claim>>> fabricScores = new HashMap<>();

        AtomicInteger count = new AtomicInteger();

        rows.forEach(row ->
                claims.add(new Claim(row))
        );
        List<Claim> copy = new ArrayList<>();
        copy.addAll(claims);


        for(int i = 0; i < claims.size(); i ++) {
            Claim claim = claims.get(i);

            for (int x = claim.x; x < claim.x + claim.width; x++) {
                for (int y = claim.y; y < claim.y + claim.height; y++) {
                    if (!fabricScores.containsKey(x)) {
                        fabricScores.put(x, new HashMap<>());
                    }

                    Map<Integer, List<Claim>> ys = fabricScores.get(x);

                    if (ys.containsKey(y)) {
                        copy.remove(claim);
                        List<Claim> score = ys.get(y);
                        score.add(claim);
                        if(score.size() == 1 ) {
                            count.getAndIncrement();
                        }
                        for(Claim c: score) {
                            copy.remove(c);
                        }

                        ys.put(y, score);
                    } else {
                        List<Claim> score = new ArrayList<>();
                        score.add(claim);
                        ys.put(y, score);
                    }


                    fabricScores.put(x, ys);

                }
            }


        };

        return count;


    }

    public static Object part2(List<String> rows) {

        return null;


    }


    static class Claim {
        int x;
        int y;
        int width;
        int height;

        int id;

        public Claim(String row) {

            this.id = Integer.valueOf(row.substring(1, row.indexOf(" @")));
            this.x = Integer.valueOf(row.substring(row.indexOf("@") + 2, row.indexOf(",")));
            this.y = Integer.valueOf(row.substring(row.indexOf(",") + 1, row.indexOf(":")));
            this.width = Integer.valueOf(row.substring(row.indexOf(": ") + 2, row.indexOf("x")));
            this.height = Integer.valueOf(row.substring(row.indexOf("x") + 1));
        }


    }
}
