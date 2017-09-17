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
        if (input.isEmpty())
            return List.of();
        else if (input.get(beginMain) < 0 && end >= beginMain) beginMain++;
        if (end == -1) end = 0;
        else end++;
        return input.subList(beginMain, end);
    }
}