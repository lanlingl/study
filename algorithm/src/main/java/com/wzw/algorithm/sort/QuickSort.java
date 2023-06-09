package com.wzw.algorithm.sort;

/**
 * 快速排序
 * @author wuzhiwei
 * @team M
 * @owner wuzhiwei
 * @Date 2023/2/6
 */
public class QuickSort {

    /**
     * 快速排序
     * <p>
     * 核心思想，每一次遍历都找出一个key，以key作为基准，然后把小于key的放key左边，大于key的放右边，
     * 然后递归操作
     * </p>
     */
    public void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        // 找到基准数，把基准数放到正确的位置，即大于基准的放左，小于基准的放右边。得到基准数的index。
        // 而【把基准数放到正确的位置】这一次操作可以有多种实现
        int index = quickSubSort(nums, left, right);
        // 从index把数组分为2断，递归执行上面的操作。所有index都归位，整个nums数组就排序好了。
        quickSort(nums, left, index-1);
        quickSort(nums, index+1, right);
    }

    public int quickSubSort(int[] nums, int left, int right) {
        //1. 【hoare方法】双指针，左边找大于key的位置i，右边找小于key的位置j，然后把i和j上的两个数交换，直到双指针相遇
        //2. 挖坑法，
        return 0;
    }


}
