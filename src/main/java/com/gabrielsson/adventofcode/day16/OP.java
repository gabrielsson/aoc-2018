package com.gabrielsson.adventofcode.day16;

import java.util.List;

public interface OP {

    public String getName();
    public List<Integer> doOperation(String state, String opcode);
}
