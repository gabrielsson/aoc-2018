package com.gabrielsson.adventofcode;//package com.gabrielsson.adventofcode;

import java.util.*;
import java.util.List;
import java.util.stream.IntStream;

public class Day8 {
    List<Integer> rows;
    int index;

    public Object part1(List<Integer> rows) {
        this.rows = rows;
        index = 0;
        return getChildNode().getSumPart1();
    }

    public Object part2(List<Integer> rows) {
        this.rows = rows;
        index = 0;
        return getChildNode().getSumPart2();
    }

    private Node getChildNode() {
        Node node = new Node();
        node.numberOfChildNodes = this.rows.get(index++);
        node.numberOfMetaEntries = this.rows.get(index++);

        IntStream.range(0, node.numberOfChildNodes).forEach((i) ->  node.children.add(getChildNode()));
        IntStream.range(0,node.numberOfMetaEntries).forEach((i) -> node.metaEntries.add(this.rows.get(index++)));
        return node;
    }

    static class Node {
        int numberOfChildNodes;
        int numberOfMetaEntries;
        List<Integer> metaEntries = new ArrayList<>();
        List<Node> children = new ArrayList<>();


        public int getSumPart1() {
            return children.stream()
                    .mapToInt(Node::getSumPart1)
                    .sum() +
                    metaEntries.stream()
                            .mapToInt(Integer::new)
                            .sum();
        }

        public int getSumPart2() {
            if (numberOfChildNodes > 0) {
                return metaEntries.stream()
                        .filter(metaIndex -> metaIndex - 1 < children.size())
                        .mapToInt(metaIndex -> children.get(metaIndex - 1).getSumPart2())
                        .sum();
            } else {
                return metaEntries.stream()
                        .mapToInt(Integer::new)
                        .sum();
            }
        }
    }
}
