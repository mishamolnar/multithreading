package org.example.mergesort;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

public class MergeSort implements Runnable {
    private final ExecutorService executorService;
    private final int[] arr;
    private final int[] buff;
    private final int left;
    private final int right;

    public MergeSort(ExecutorService executorService, int[] arr, int[] buff, int left, int right) {
        this.executorService = executorService;
        this.arr = arr;
        this.buff = buff;
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        sort(arr, buff, left, right);
    }


    public int[] sort(int[] arr, int[] buff, int left, int right) {
        if (left >= right) return arr;
        int mid = left + (right - left) / 2;

        if (right - left > 1_000_000) {
            try {
                var one = new Thread(new MergeSort(executorService, arr, buff, left, mid));
                one.start();
                var two = new Thread(new MergeSort(executorService, arr, buff, mid + 1, right));
                two.start();
                two.join();
                one.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else {
            sort(arr, buff, left, mid);
            sort(arr, buff, mid + 1, right);
        }

        System.arraycopy(arr, left, buff, left, right - left + 1);

        int startLeft = left, startRight = mid + 1, pointer = left;
        while (pointer <= right) {
            arr[pointer++] = ((startRight > right || buff[startLeft] < buff[startRight]) && startLeft <= mid) ? buff[startLeft++] : buff[startRight++];
        }
        return arr;
    }
}
