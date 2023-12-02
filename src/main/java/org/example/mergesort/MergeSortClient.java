package org.example.mergesort;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class MergeSortClient {
    public static void main(String[] args) {
        Random random = new Random();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        int size = 100_000_000;
        int[] arr = new int[size];
        int[] buff = new int[size];
        for (int i = 0; i < arr.length; i++) arr[i] = random.nextInt(Integer.MAX_VALUE);
        MergeSort mergeSort = new MergeSort(executorService, arr, buff, 0, arr.length - 1);
        long before = System.nanoTime();
        mergeSort.sort(arr, buff, 0, arr.length - 1);
        long after = System.nanoTime();
        executorService.shutdown();
        for (int i = 1; i < arr.length; i++)  {
            if (arr[i - 1] > arr[i])
                System.out.println("ARRAY IS NOT SORTED");
        }
        System.out.println("time spent " + ((after - before) / 1_000_00));
        System.out.println(Arrays.toString(arr).substring(0, 400));
    }
}
