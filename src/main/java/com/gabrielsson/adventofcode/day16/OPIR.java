package com.gabrielsson.adventofcode.day16;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class OPIR implements OP {

    private final BiFunction<Integer, Integer, Integer> function;
    private final String name;

    public OPIR(String name, BiFunction<Integer, Integer, Integer> function) {

        this.function = function;

        this.name = name;
    }


    @Override
    public String getName() {
        return name;
    }

    public List<Integer> doOperation(String state, String opcode) {
        List<Integer> states = new ArrayList<>(4);
        for (String s : state.split(" ")) {
            states.add(Integer.valueOf(s));
        }


        List<Integer> opcodes = new ArrayList<>(4);
        for (String s : opcode.split(" ")) {
            opcodes.add(Integer.valueOf(s));
        }

        int valuea = opcodes.get(1);
        int regb = opcodes.get(2);
        int resultReg = opcodes.get(3);

        states.set(resultReg, function.apply(valuea, states.get(regb)));

        return states;
    }



}

