package com.wzw.algorithm.sort;

import java.util.Arrays;

/**
 * 堆排序
 * @author wuzhiwei
 * @team M
 * @owner wuzhiwei
 * @Date 2023/2/6
 */
public class HeapSort {


    /**
     * 堆本质就是【完全二叉树】
     * 分为【大根堆】和【小根堆】
     * 堆排序步骤：1、建堆，2、排序。
     */
    public void maxHeapSort(int[]arr){
        // 构建原始堆过程
        buildHeap(arr);
        System.out.println("堆：" + Arrays.toString(arr));
        // 根据堆排序的过程，把
        for(int i=arr.length-1; i>0; --i){
            // 把根和i节点交换，i节点变最大
            swap(arr, 0, i);
            // 前i-1个节点进行堆化
            heapify(arr, 0, i);
        }
    }

    private void buildHeap(int[]arr){
        // 这里i从len/2-1开始，是因为len/2-1为最后一个节点的父节点，
        // 从最后一个节点的父节点开始进行堆化（先把最下面的子堆构建完成）
        //在最小堆的基础上，从右往左，从下往上的顺序构造堆（也就是数组的从右往左，也就是i--，直到i=0）
        // 所以在某个节点时，他的子树（不包括他自己）已经是堆有序的。
        for(int i=arr.length/2-1; i>=0; --i){
            heapify(arr, i, arr.length);
        }

    }

    /**
     * 大根堆堆化，
     * 把arr 从i开始往后len长度进行堆化
     */
    private void heapify(int[]arr, int index, int len){
        // index*2+1 是 index的左子节点
        // 找到index的左右子节点，把index与左右子节点中最大的交换。
        // 为什么需要i=i*2+1，因为假如i的左子节点与i替换，那么i的左子节点可能会变成无序堆，所以i的左子节点也需要调整，一直调整到最底层的叶子节点。
        for(int i = index*2+1 ; i<len ; i=i*2+1){
            // 取左右子最大的
            if(i+1<len && arr[i]<arr[i+1] ){
                ++i;
            }
            // index跟左右子最大的交换
            if(arr[index] < arr[i]){
                 swap(arr, i, index);
                 // 这里是为了让下次for循环时，i=i的左子节点。
                 index = i;
            }else{
                // 如果没有进行交换，则直接退出，
                break;
            }
        }

    }

    private void swap(int[]arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int arr[] = {4, 6, 8, 5, 9,7,11,34,23,18};
        System.out.println("排序前" + Arrays.toString(arr));
        HeapSort hs = new HeapSort();
        hs.maxHeapSort(arr);
        System.out.println("排序后" + Arrays.toString(arr));

    }
}
