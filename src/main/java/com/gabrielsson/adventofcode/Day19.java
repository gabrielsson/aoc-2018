package com.gabrielsson.adventofcode;

import com.gabrielsson.adventofcode.day16.OP;
import com.gabrielsson.adventofcode.day16.OPIR;
import com.gabrielsson.adventofcode.day16.OPRI;
import com.gabrielsson.adventofcode.day16.OPRR;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day19 {

    Map<String, BiFunction<Long, Long, Long>> functionList = new HashMap<>();
    BiFunction<Long, Long, Long> add = (i1, i2) -> {
        //System.out.println("ADD: " + i1 + "+" + i2);
        return i1 + i2;
    };
    BiFunction<Long, Long, Long> mul = (i1, i2) -> {
        //System.out.println("MUL: " + i1 + "*" + i2);
        return i1 * i2;
    };

    BiFunction<Long, Long, Long> ban = (i1, i2) -> {
        //System.out.println("AND: " + i1 + "&" + i2);

        return i1 & i2;
    };

    BiFunction<Long, Long, Long> bor = (i1, i2) -> {
        //System.out.println("OR: " + i1 + "|" + i2);

        return i1 | i2;
    };
    BiFunction<Long, Long, Long> gt = (i1, i2) -> {
        //System.out.println("GT: " + i1 + ">" + i2);

        return i1 > i2 ? 1l : 0l;
    };
    BiFunction<Long, Long, Long> eq = (i1, i2) -> {
        //System.out.println("EQ: " + i1 + "==" + i2);

        return i1.equals(i2) ? 1l : 0l;
    };
    BiFunction<Long, Long, Long> set = (i1, i2) -> {
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
        ops.add(new OPRR("addr", add));
        ops.add(new OPRI("addi", add));
        ops.add(new OPRR("mulr", mul));
        ops.add(new OPRI("muli", mul));
        ops.add(new OPRR("banr", ban));
        ops.add(new OPRI("bani", ban));
        ops.add(new OPRR("borr", bor));
        ops.add(new OPRI("bori", bor));
        ops.add(new OPRR("setr", set));
        ops.add(new OPIR("seti", set));
        ops.add(new OPIR("gtir", gt));
        ops.add(new OPRI("gtri", gt));
        ops.add(new OPRR("gtrr", gt));
        ops.add(new OPIR("eqir", eq));
        ops.add(new OPRI("eqri", eq));
        ops.add(new OPRR("eqrr", eq));
    }


    public Object part1(List<String> program) {
        int ipRegister = Integer.valueOf(program.remove(0).substring(4));

        ArrayLimitedQueue<String> stateQueue = new ArrayLimitedQueue<>(1000);
        String initialState = "1 0 0 0 0 0";//"6 5 9 10551356 0 10551355";//"1 2 9 10551356 0 10551355";//"6 5 9 10551355 0 10551355";
        //"1 5 4 2110270 0 10551355";//"1 2 4 579343 0 10551355";//"0 1 10 954 955";
        List<Long> stateList = Arrays.asList(initialState.split(" ")).stream()
                .map(Long::new)
                .collect(Collectors.toList());

        int i = -1;
        while (true) {

            if (i >= program.size()) break;
            i = Math.toIntExact(stateList.get(ipRegister));

            stateList.set(ipRegister, Long.valueOf(i));
            String operationString = program.get(i);
            List<Long> before = new ArrayList<>(stateList);
            OP operation = ops.stream().filter(op -> op.getName().equals(operationString.substring(0, operationString.indexOf(" ")))).findFirst().get();
            List<Long> result = new ArrayList<>(operation.doOperation(stateList, program.get(i)));


            //if(before.get(1) != result.get(1)) {
              //  System.out.println("ip=" + i + "[" + before + "]\t\t\t" + operationString + " [" + stateList + "]");

            //}
            stateList = result;
            //System.out.println("ip=" + i + "[" + before + "]\t\t\t" + operationString + " [" + stateList + "]");

            stateQueue.addLast("ip=" + i + "[" + before + "]\t\t\t" + operationString + " [" + stateList + "]");
            i = Math.toIntExact(stateList.get(ipRegister));

            i++;


            if(i < program.size())
                stateList.set(ipRegister, (long) i);


            if (stateList.get(ipRegister) == 3)
            {
                if (stateList.get(5) % stateList.get(1) == 0)
                {
                    System.out.println("ip=" + i + "[" + before + "]\t\t\t" + operationString + " [" + stateList + "]");

                    stateList.set(3, stateList.get(5));
                    stateList.set(4,  1l);
                    stateList.set(ipRegister, 7l);
                }
                else
                {
                    stateList.set(3, stateList.get(1)+ 1);
                    stateList.set(4, 1l);
                    stateList.set(ipRegister,12l);
                }
            }
        }

        stateQueue.stream().forEach(System.out::println);
        return stateList.get(0);
    }
    public class ArrayLimitedQueue<E> extends ArrayDeque<E> {

        private int limit;

        public ArrayLimitedQueue(int limit) {
            super(limit + 1);
            this.limit = limit;
        }

        @Override
        public boolean add(E o) {
            boolean added = super.add(o);
            while (added && size() > limit) {
                super.remove();
            }
            return added;
        }

        @Override
        public void addLast(E e) {
            super.addLast(e);
            while (size() > limit) {
                super.removeFirst();
            }
        }

        @Override
        public boolean offerLast(E e) {
            boolean added = super.offerLast(e);
            while (added && size() > limit) {
                super.pollLast();
            }
            return added;
        }
    }
}
