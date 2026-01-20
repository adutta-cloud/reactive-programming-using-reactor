package com.learnreactiveprogramming.imperative;

import java.util.ArrayList;
import java.util.List;

public class ImperativeExample {
    public static void main(String[] args) {
        var namesList = List.of("alex", "ben", "chole", "adam", "adam");
        var newnamesList =  namesGraterThanSize(namesList, 3);
        System.out.println("newNamesList : " + newnamesList);
    }

    private static List<String> namesGraterThanSize(List<String> namesList, int size) {
        var newNamesList = new ArrayList<String>();
        for (String name : namesList) {
            if (name.length() > size && !newNamesList.contains(name.toUpperCase())) {
                newNamesList.add(name.toUpperCase());
            }
        }
        return  newNamesList;
    }
}
