package com.gabrielsson.adventofcode.day16;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class OPRR implements OP {

    private final BiFunction<Integer, Integer, Integer> function;
    private final String name;


    public OPRR(String name, BiFunction<Integer, Integer, Integer> function) {


        this.function = function;

        this.name = name;
    }


    @Override
    public String getName() {
        return name;
    }

    public List<Integer> doOperation(String state, String opcode) {
        List<Integer> states;
        List<Integer> opcodes;
        states = new ArrayList<>(4);
        for (String s : state.split(" ")) {
            states.add(Integer.valueOf(s));
        }


        opcodes = new ArrayList<>(4);
        for (String s : opcode.split(" ")) {
            opcodes.add(Integer.valueOf(s));
        }
        int rega = opcodes.get(1);
        int regb = opcodes.get(2);
        int resultReg = opcodes.get(3);

        states.set(resultReg, function.apply(states.get(rega), states.get(regb)));

        return states;
    }


}

