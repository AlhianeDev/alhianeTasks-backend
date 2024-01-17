package com.alhianeTasks_backend.utils;

public class Reverse {

    public static <T> void reverseArray(T [] arr, int arrSize) {

        T tmp;

        for (int i = 0; i < arrSize / 2; i++) {

            tmp = arr[i];

            arr[i] = arr[arrSize - i - 1];

            arr[arrSize - i - 1] = tmp;

        }

    }

}
