package com.company;

import java.util.List;

public class work1 {

    public static List searchMass(List<Integer> input) {
        int beginMain = 0;
        int beginNow = 0;
        int end = -1;
        int all = 0;
        int max = 0;
        int index =-1;

        for (int cell: input) {
            index++;
            if (cell >= 0) {
                all += cell;
                if (all > max) {
                    beginMain = beginNow;
                    max = all;
                    end = index;
                }
            } else {
                all += cell;
                if (all < 0) {
                    beginNow = index;
                    all = 0;
                }
            }
        }
        if (input.isEmpty())//  assertEquals(List.of(), work1.searchMass(List.of()));
            return List.of();
        else if (input.get(beginMain) < 0 // assertEquals(List.of(1, 2, 3, 4, 5), work1.searchMass(List.of(1, 2, 3, 4, 5)));
                && end >= beginMain) // assertEquals(List.of(), work1.searchMass(List.of(-10, -10, -10, -10)));
            beginMain++;
        return input.subList(beginMain, end+1);
    }
}