package com.company;

import java.util.List;

public class work1 {

    public static void main(String[] args) {
        List<Integer> test = List.of(1, 2, 20000, -20003, 1000000, 5, -4, 5, 6);
        int begin1 = 0;
        int begin2 = 0;
        int end = -1;
        int all = 0;
        int max = 0;
        for (int i = 0; i < test.size(); i++) {
            if (test.get(i) >= 0) {
                all += test.get(i);
                if (all > max) {
                    begin1 = begin2;
                    max = all;
                    end = i;
                }
            }
            if (test.get(i) < 0) {
                all = all + test.get(i);
                if (all < 0) {
                    begin2 = i;
                    all = 0;
                }
            }
        }
        if (test.get(begin1) < 0) begin1++;
        System.out.println(test);
        for (int i = begin1; i <= end; i++)
            System.out.print(test.get(i) + " ");
    }
}