package com.gabrielsson.adventofcode;

import com.gabrielsson.adventofcode.day16.OP;
import com.gabrielsson.adventofcode.day16.OPIR;
import com.gabrielsson.adventofcode.day16.OPRI;
import com.gabrielsson.adventofcode.day16.OPRR;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class Day16 {

    Map<String, BiFunction<Integer, Integer, Integer>> functionList = new HashMap<>();
    BiFunction<Integer, Integer, Integer> add = (i1, i2) -> {
        //System.out.println("ADD: " + i1 + "+" + i2);
        return i1 + i2;
    };
    BiFunction<Integer, Integer, Integer> mul = (i1, i2) -> {
        //System.out.println("MUL: " + i1 + "*" + i2);
        return i1 * i2;
    };

    BiFunction<Integer, Integer, Integer> ban = (i1, i2) -> {
        //System.out.println("AND: " + i1 + "&" + i2);

        return i1 & i2;
    };

    BiFunction<Integer, Integer, Integer> bor = (i1, i2) -> {
        //System.out.println("OR: " + i1 + "|" + i2);

        return i1 | i2;
    };
    BiFunction<Integer, Integer, Integer> gt = (i1, i2) -> {
        //System.out.println("GT: " + i1 + ">" + i2);

        return i1 > i2 ? 1 : 0;
    };
    BiFunction<Integer, Integer, Integer> eq = (i1, i2) -> {
        //System.out.println("EQ: " + i1 + "==" + i2);

        return i1 == i2 ? 1 : 0;
    };
    BiFunction<Integer, Integer, Integer> set = (i1, i2) -> {
        //System.out.println("SETR: " + i1);
        return i1;
    };
    List<OP> ops = new ArrayList<>();

    {
        /*
        eqrr=[14]
 borr=[12]
 addi=[4]
 setr=[2]
 gtir=[3]
 bori=[10]
 eqri=[13]
 banr=[11]
 seti=[0]
 bani=[8]
 gtri=[9]
 muli=[5]
 eqir=[1]
 addr=[15]
 gtrr=[7]
 mulr=[6]
         */
        ops.add(new OPRR("15", add));
        ops.add(new OPRI("4", add));
        ops.add(new OPRR("6", mul));
        ops.add(new OPRI("5", mul));
        ops.add(new OPRR("11", ban));
        ops.add(new OPRI("8", ban));
        ops.add(new OPRR("12", bor));
        ops.add(new OPRI("10", bor));
        ops.add(new OPRR("2", set));
        ops.add(new OPIR("0", set));
        ops.add(new OPIR("3", gt));
        ops.add(new OPRI("9", gt));
        ops.add(new OPRR("7", gt));

        ops.add(new OPIR("1", eq));
        ops.add(new OPRI("13", eq));
        ops.add(new OPRR("14",  eq));
    }


    public Object part2(List<String> playArea) {
        String state = "0 0 0 0";
        for (int i = 0; i < playArea.size(); i++) {
            String operationString = playArea.get(i);

            OP operation = ops.stream().filter(op -> op.getName().equals(operationString.substring(0, operationString.indexOf(" ")))).findFirst().get();
            List<Integer> result = operation.doOperation(state, playArea.get(i));
            state = result.stream().map(number -> number+"").collect(Collectors.joining(" "));
        }

        return state.substring(0, state.indexOf(" "));
    }

    public Object part1(List<String> playArea) {
        Map<String, Set<String>> instructionMap = new HashMap<>();
        int scoresMoreThan3 = 0;
        Set<String> instructions = ops.stream().map(o -> o.getName()).collect(Collectors.toSet());

            for (int i = 0; i < playArea.size(); ) {
                Map<String, String> map = new HashMap<>();

                String before = playArea.get(i++);
                String operationString = playArea.get(i++);
                String after = playArea.get(i++);

                int score = 0;


                for (OP operation : ops) {
                    if(!instructions.contains(operation.getName())) {
                        continue;
                    }
                    List<Integer> actual = operation.doOperation(before, operationString);
                    List<Integer> expected = Arrays.stream(after.split(" "))
                            .mapToInt(Integer::valueOf)
                            .boxed()
                            .collect(Collectors.toList());
                    boolean match = true;
                    for (int j = 0; j < actual.size(); j++) {
                        if (expected.get(j) != actual.get(j)) {
                            match = false;
                        }
                    }

                    if (match) {

                        map.put(operation.getName(), operationString.substring(0, operationString.indexOf(" ")));

                        score++;
                    }
                }
                if (score >= 3) {
                    scoresMoreThan3++;
                }


                for(Map.Entry<String, String> entry: map.entrySet()) {
                    Set<String> list = instructionMap.getOrDefault(entry.getKey(), new HashSet<>());
                    list.add(entry.getValue());
                    instructionMap.put(entry.getKey(), list);
                }
            }
        return scoresMoreThan3;
    }

}
