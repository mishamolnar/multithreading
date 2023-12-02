package org.example.forkjoin.recursiveAction;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class PrintAction extends RecursiveAction {
    private List<Integer> nums;

    public PrintAction(List<Integer> nums) {
        this.nums = nums;
    }

    @Override
    protected void compute() {
        if (nums.size() < 2) {
            for (Integer num : nums) {
                System.out.println(num);
            }
        } else {
            List<Integer> left = nums.subList(0, nums.size() / 2);
            List<Integer> right = nums.subList(nums.size() / 2, nums.size());

            PrintAction leftAction = new PrintAction(left);
            PrintAction rightAction = new PrintAction(right);

            invokeAll(leftAction, rightAction);
        }
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.invoke(new PrintAction(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)));
    }
}
