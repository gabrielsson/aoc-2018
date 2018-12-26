package com.gabrielsson.adventofcode.day16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class OPIR implements OP {

    private final BiFunction<Long, Long, Long> function;
    private final String name;

    public OPIR(String name, BiFunction<Long, Long, Long> function) {

        this.function = function;

        this.name = name;
    }


    @Override
    public String getName() {
        return name;
    }

    public List<Long> doOperation(List<Long> state, String opcode) {
        List<String> opcodes = Arrays.stream(opcode.split(" ")).collect(Collectors.toList());


        long valuea = Long.valueOf(opcodes.get(1));
        long regb = Long.valueOf(opcodes.get(2));
        long resultReg = Long.valueOf(opcodes.get(3));

        if(regb >= state.size()) regb = 0;
        state.set((int)resultReg, function.apply(valuea, state.get((int)regb)));

        return state;
    }



}

