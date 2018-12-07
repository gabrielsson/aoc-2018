package com.gabrielsson.adventofcode;

import java.util.*;
import java.util.stream.Collectors;

public class Day7 {
    public static Object part1(List<String> rows) {
        Map<String, List<String>> nodes = new HashMap<>();
        for (String s : rows) {

            String id = s.substring(5, 6);
            String child = s.substring(36, 37);

            List<String> childs = nodes.get(id);
            if (childs != null) {
                childs.add(child);
            } else {
                ArrayList<String> list = new ArrayList<>();
                list.add(child);
                nodes.put(id, list);
            }


        }

        String result = "";

        boolean hasResult = true;
        while (hasResult) {
            String letterWithNoParents = getLetterWithNoParents(nodes);

            result += letterWithNoParents;

            if (nodes.keySet().size() == 1) {
                result += nodes.get(letterWithNoParents).stream().findFirst().get();
                hasResult = false;
            } else {
                nodes.remove(letterWithNoParents);
            }

        }


        return result;
    }

    private static String getLetterWithNoParents(Map<String, List<String>> nodes) {
        List<String> result = new ArrayList<>();
        nodes.values().forEach(result::addAll);

        Set<String> keys = new HashSet<>();
        keys.addAll(nodes.keySet());
        keys.removeAll(result);


        return keys.stream().min(Comparator.comparing(String::new)).get();
    }

    private static Set<String> getLettersWithNoParents(Map<String, List<String>> nodes) {
        List<String> result = new ArrayList<>();
        nodes.values().forEach(result::addAll);

        Set<String> keys = new HashSet<>();
        keys.addAll(nodes.keySet());
        keys.removeAll(result);


        return keys;
    }

    public static Object part2(List<String> rows, int workers, int duration) {
        Map<String, List<String>> nodes = new HashMap<>();
        for (String s : rows) {

            String id = s.substring(5, 6);
            String child = s.substring(36, 37);

            List<String> childs = nodes.get(id);
            if (childs != null) {
                childs.add(child);
            } else {
                ArrayList<String> list = new ArrayList<>();
                list.add(child);
                nodes.put(id, list);
            }


        }


        boolean hasResult = true;
        String r = "";
        int result = 0;
        int[] elfs = new int[workers];
        for (int i = 0; i < workers; i++) {

            elfs[i] = 0;

        }
        while (hasResult) {
            Set<String> lettersWithNoParents = getLettersWithNoParents(nodes);
            String smallest = lettersWithNoParents.stream().min(Comparator.comparing(String::new)).get();


            List<String> list = lettersWithNoParents.stream().sorted(Comparator.comparing(String::new)).collect(Collectors.toList());
            for (int i = 0; i < list.size(); i++) {

                int value = duration - 64 + ((int) list.get(i).charAt(0));


                int lowestIndex = 0;
                for (int b = 1; b < elfs.length; b++) {
                    if (elfs[b] < elfs[lowestIndex]) lowestIndex = b;
                }
                elfs[lowestIndex] += value;


            }


            r += smallest;
            if (nodes.keySet().size() == 1) {
                r += nodes.get(smallest).stream().findFirst().get();
                hasResult = false;
            } else {
                for(String letterWithNoParents : lettersWithNoParents) {
                    nodes.remove(letterWithNoParents);
                }
            }

            for (int e = 0; e < elfs.length; e++) {
                for (int n = e; n < elfs.length; n++) {
                    if(elfs[e]> elfs[n]) {
                        elfs[n] = elfs[e];
                    }

                }

            }
        }

        int lowestIndex = 0;
        for (int b = 1; b < elfs.length; b++) {
            if (elfs[b] < elfs[lowestIndex]) lowestIndex = b;
        }
        return elfs[lowestIndex];
    }

}
