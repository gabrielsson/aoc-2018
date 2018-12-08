package com.gabrielsson.adventofcode;//package com.gabrielsson.adventofcode;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Day8 {
    List<Integer> rows;
    int index = 0;

    public Object part1(List<Integer> rows) {
        this.rows = rows;
        return getChildNode().metaSum;
    }

    public Object part2(List<Integer> rows) {
        this.rows = rows;

        Node node = getChildNode();
        return node.getMetaSum();
    }


    private Node getChildNode() {
        Node node = new Node();

        node.numberOfChildNodes = this.rows.get(index++);
        node.numberOfMetaEntries = this.rows.get(index++);

        for (int i = 0; i < node.numberOfChildNodes; i++) {
            Node child = getChildNode();
            node.children.add(child);
            node.metaSum += child.metaSum;
        }


        for (int i = 0; i < node.numberOfMetaEntries; i++) {
            node.metaentries.add(this.rows.get(index));
            node.metaSum += this.rows.get(index++);
        }
        return node;
    }

    static class Node {
        int metaSum = 0;
        int numberOfChildNodes;
        int numberOfMetaEntries;
        List<Integer> metaentries = new ArrayList<>();
        List<Node> children = new ArrayList<>();

        public int getMetaSum() {
            if (numberOfChildNodes > 0) {
                return metaentries.stream()
                        .filter(metaIndex -> metaIndex - 1 < children.size())
                        .mapToInt(metaIndex -> children.get(metaIndex - 1).getMetaSum())
                        .sum();



            } else {
                return metaentries.stream()
                        .mapToInt(Integer::new)
                        .sum();
            }
        }
    }
}
