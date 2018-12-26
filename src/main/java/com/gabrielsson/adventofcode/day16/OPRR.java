package com.gabrielsson.adventofcode.day16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class OPRR implements OP {

    private final BiFunction<Long, Long, Long> function;
    private final String name;


    public OPRR(String name, BiFunction<Long, Long, Long> function) {


        this.function = function;

        this.name = name;
    }


    @Override
    public String getName() {
        return name;
    }

    public List<Long> doOperation(List<Long> state, String opcode) {

        List<String> opcodes = Arrays.stream(opcode.split(" ")).collect(Collectors.toList());



        long rega = Long.valueOf(opcodes.get(1));
        long regb = Long.valueOf(opcodes.get(2));
        long resultReg = Long.valueOf(opcodes.get(3));

        state.set((int)resultReg, function.apply(state.get((int)rega), state.get((int)regb)));

        return state;
    }


}

