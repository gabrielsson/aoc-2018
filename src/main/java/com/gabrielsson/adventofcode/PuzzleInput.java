package com.gabrielsson.adventofcode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PuzzleInput {
    private String fileName;

    public PuzzleInput() {
        fileName = "day1.txt";
    }

    public PuzzleInput(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Scanner getInputScanner() {

        try {

            return new Scanner(new File("src/main/resources/" + fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public List<Integer> getListOfNonSeparatedIntegers() {
        return getListOfSeparatedIntegers("\\B");
    }

    public List<Integer> getListOfSeparatedIntegers(String separator) {

        return Arrays.stream(getInputScanner().next().split(separator)).map(s -> Integer.valueOf(s)).collect(Collectors.toList());

    }


    public List<Integer> getListOfIntegers() {
        List<Integer> rows = new ArrayList<>();

        Scanner sc = getInputScanner();
        while(sc.hasNext()) {
            rows.add(Integer.valueOf(sc.next()));
        }

        return rows;

    }
    public List<String> getListOfRows() {
        List<String> rows = new ArrayList<>();
        Scanner scanner = getInputScanner();
        while (scanner.hasNext()) {
            rows.add(scanner.next());
        }

        return rows;
    }
}

