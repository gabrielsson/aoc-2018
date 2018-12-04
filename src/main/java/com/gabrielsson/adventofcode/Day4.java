package com.gabrielsson.adventofcode;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day4 {


    public static Object part1(List<String> rows) {


        List<TimeEntity> timeEntries = rows.stream().map(TimeEntity::new).sorted(Comparator.comparing(a -> a.date)).collect(Collectors.toList());
        Map<Integer, int[]> guardScores = getGuardScores(timeEntries);


        Map.Entry<Integer, int[]> e = guardScores.entrySet().stream() //part one sum the values
                .max(Comparator.comparing(entry -> Arrays.stream(entry.getValue()).sum())).get();



        int maxIndex = 0;
        int maxValue = 0;
        for (int i = 0; i < 60; i++) {

            if (maxValue < e.getValue()[i]) {
                maxValue = e.getValue()[i];
                maxIndex = i;
            }

        }


        return e.getKey() * maxIndex;
    }
    public static Object part2(List<String> rows) {


        List<TimeEntity> timeEntries = rows.stream().map(TimeEntity::new).sorted(Comparator.comparing(a -> a.date)).collect(Collectors.toList());
        Map<Integer, int[]> guardScores = getGuardScores(timeEntries);


        Map.Entry<Integer, int[]> e = guardScores.entrySet().stream() //part two get the max minute
                .max(Comparator.comparing(entry -> Arrays.stream(entry.getValue()).max().getAsInt())).get();




        int maxIndex = 0;
        int maxValue = 0;
        for (int i = 0; i < 60; i++) {

            if (maxValue < e.getValue()[i]) {
                maxValue = e.getValue()[i];
                maxIndex = i;
            }

        }


        return e.getKey() * maxIndex;
    }


    private static Map<Integer, int[]> getGuardScores(List<TimeEntity> timeEntries) {
        Map<Integer, int[]> guardScores = new HashMap<>();

        for (int i = 0; i < timeEntries.size(); ) {

            Integer currentId = timeEntries.get(i).id;

            i++; //guard is awake since the shift just started

            int[] minutesAsleep = guardScores.get(currentId);
            if (minutesAsleep == null) {
                minutesAsleep = new int[60];
                for (int min = 0; min < 60; min++) {
                    minutesAsleep[min] = 0;
                }
                guardScores.put(currentId, minutesAsleep);

            }

            while (i < timeEntries.size() && timeEntries.get(i).id == null) {
                minutesAsleep = guardScores.get(currentId);

                if (timeEntries.get(i).isAwake) {
                    i++; //guard is awake, get next time entry
                    continue;
                }
                for (int minute = timeEntries.get(i).date.getMinutes(); minute < timeEntries.get(i + 1).date.getMinutes(); minute++) {
                    minutesAsleep[minute]++;
                }
                guardScores.put(currentId, minutesAsleep);
                i++; //get the next time entry
            }
        }
        return guardScores;
    }


    public static class TimeEntity {
        Integer id;
        Date date;
        Boolean isAwake;

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        TimeEntity(String row) {
            try {
                this.date = format.parse(row.substring(1, 17));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (row.contains("Guard")) {
                this.id = Integer.valueOf(row.substring(row.indexOf("#") + 1, row.indexOf("begins") - 1));
                isAwake = true;
            } else if (row.contains("asleep")) {
                isAwake = false;
            } else {
                isAwake = true;
            }

        }
    }
}
