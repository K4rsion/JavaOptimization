package ru.nsu.kgurin;

import java.util.ArrayList;
import java.util.Arrays;

public class Bean {
    char[] name = {'B', 'e', 'a', 'n'};
    ArrayList<Integer> nums = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
    int num1 = 1;
    int num2 = 2;
    Bean nextBean;

    public void setNextBean(Bean nextBean) {
        this.nextBean = nextBean;
    }
}
