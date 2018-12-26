package com.gabrielsson.adventofcode.day16;

import java.util.List;

public interface OP {

    public String getName();
    public List<Long> doOperation(List<Long> state, String opcode);
}
