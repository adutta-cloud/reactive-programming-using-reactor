package com.learnreactiveprogramming.functional;

import java.util.List;
import java.util.stream.Collectors;

public class FunctionalExample {
    public static void main(String[] args) {
        var namesList = java.util.List.of("alex", "ben", "chole", "adam", "adam");
        var newnamesList =  namesGraterThanSize(namesList, 3);

        System.out.println("newNamesList : " + newnamesList);
    }

    private static Object namesGraterThanSize(List<String> namesList, int size) {
        return namesList.parallelStream()
                .filter(name -> name.length() > size)
                .map(String::toUpperCase)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
}
