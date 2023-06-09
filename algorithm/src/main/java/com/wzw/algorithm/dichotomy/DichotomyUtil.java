package com.wzw.algorithm.dichotomy;

import com.wzw.algorithm.slidingwindow.SlidingWindowUtil;

import java.util.Arrays;

/**
 * 二分法
 * @author wuzhiwei
 * @team M
 * @owner wuzhiwei
 * @Date 2021/12/14
 */
public class DichotomyUtil {

    private DichotomyUtil(){}

    /**
     * 设计连续子数组时，通常会想到【滑动窗口】和【前缀和】。
     */

    /**
     * 给定一个含有 n 个正整数的数组和一个正整数 target
     * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0
     * 例如：<br>
     * 输入：target = 7, nums = [2,3,1,2,4,3] <br>
     * 输出：2 <br>
     * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。<br>
     *
     * @see SlidingWindowUtil#minSubArrayLen(int, int[]) 同一题不同解
     */
    public static int minSubArrayLen(int target, int[] nums) {
        // 思路1：【暴力法】找到连续子数组，使得子数组之和>=target，可以暴力法，嵌套for循环直接找出。时间复杂度n的平方
        // 思路2【前缀和+二分法】换个角度，用一个数组s[i]保存0～i的和，然后二分找到一个边界bound，使得nums[bound]-s[i]>=target,那么子数组就是bound-i，找到一个最小的bound-i;
        // 这里nums中都是正整数，所以s[i]的是递增的，所以可以用这种方法，
        // 思路3：【滑动窗口】
        // 这里用二分法解决
        if(nums.length==0){
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        // sum[i]表示nums[0]~nums[i]的和
        int [] sums = new int[nums.length+1];
        sums[0] = 0;
        for (int i=1; i<=nums.length;++i){
            sums[i] = sums[i-1] + nums[i-1];
        }
        for (int i = 1; i <=nums.length ; i++) {
            // target与0～i-1的和相加
            int s = target + sums[i-1];
            // 二分查找target，如果不存在，返回应该插入的下标+1的负数
            int bound = Arrays.binarySearch(sums, s);
            // 所以这里需要还原应该插入的下标
            if(bound<0){
                bound = -bound -1;
            }
            if(bound <=nums.length){
                ans = Math.min(ans,bound-(i-1));
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    /**
     * 有一个数组，该数组由一个有序数组n次旋转而来，例如，原数组 nums = [0,1,4,4,5,6,7] 在变化后可能得到：
     * 若旋转 1 次，则可以得到 [7,0,1,4,4,5,6]
     * 若旋转 4 次，则可以得到 [4,5,6,7,0,1,4]
     * 若旋转 7 次，则可以得到 [0,1,4,4,5,6,7]
     * 现在，这个数组nums中可能存在重复值，他是由一个升序排列的数组旋转而来，找出并返回数组中的最小值。
     * 例如：<br>
     * 输入：nums = [2,2,2,0,1] <br>
     * 输出：0 <br>
     */
    public static int findMin1(int[] nums) {
        // 按照之前的类似题目，二分之后把mid和r比较，mid大于r，则最小值在右边，mid小于r, 但是本题数组由重复项，所以当mid等于r的时候，不能确定最小值在左边还是右边，所以把r减一位
        int l=0,r=nums.length-1;
        while(l<r){
            int mid = l+(r-l)/2;
            if(nums[mid] < nums[r]){
                r=mid;
            }else if(nums[mid]>nums[r]){
                l=mid+1;
            }else {
                r--;
            }
        }
        return nums[l];
    }


    /**
     * 一个非降数组（非降序表示：不是严格单调递增，会有重复的递，不包括无序情况），【下标从1开始】找出其中2个数相加之后等于目标值的下，<br>
     * 要求: <br>
     *  1. 如果设这两个数分别是 numbers[index1] 和 numbers[index2] ，则 1 <= index1 < index2 <= numbers.length <br>
     *  2. 常量级的额外空间 <br>
     * 例如：<br>
     * 输入：numbers = [2,7,11,15], target = 9 <br>
     * 输出：[1,2] <br>
     *
     */
    public static int[] twoSum(int[] numbers, int target) {
        /* 可以用二分法，也可以用hashMap ，还可以用双指针*/
        /* 二分法：固定一个数，然后找另一个数*/
        if(numbers.length < 2){
            return new int[]{-1, -1};
        }
        if(numbers.length == 2 && (numbers[0]+numbers[1]!=target)){
            return new int[]{-1, -1};
        }
        for(int i=0;i<numbers.length;++i){
            int l=i+1,r=numbers.length-1;
            while (l<=r){
                int mid = l + (r-l)/2;
                if(numbers[i]+numbers[mid] == target){
                    return new int[]{i+1,mid+1};
                }else if(numbers[i]+numbers[mid] > target){
                    r = mid-1;
                }else{
                    l = mid+1;
                }
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * 整数数组 nums 按非降序排列（非降序表示：不是严格单调递增，会有重复的递，不包括无序情况），<br>
     * 数组中的值可能重复 ，在一个未知的位置进行旋转，<br>
     * 例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2]<br>
     * 现在给一个旋转过的数组，不知道具体旋转点。判断数组中是否有target存在，存在返回下标，不存在返回-1。<br>
     * 例如：<br>
     * 输入：nums = [4,5,6,7,0,1,2], target = 0<br>
     * 输出：true<br>
     */
    public boolean search2(int[] nums, int target) {
        // 前提，非降序表示：不是严格单调递增，会有重复的递，不包括无序情况
        // 二分法，mid左右一边有序一边无序，target有序的一边，直接二分，target在无序一边，则继续二分判断在有序还是无序的一边，
        // 但是有一种情况，如果旋转后变成这种：3,1,2,3,3,3,3，就没法判断那边是有序的
        // 遇到这种情况，去掉第一个数字，继续二分就可以
        if (nums == null || nums.length == 0) {
            return false;
        }
        int l=0, r=nums.length-1;
        while(l<=r){
            int mid = l+(l+r)/2;
            if(nums[mid]==target){
                return true;
            }
            // 左右和mid相等，把第一个干扰项去掉
            if(nums[l]==nums[mid]){
                ++l;
                continue;
            }
            // if(nums[l] <= nums[mid]){ 这里不能有<=，因为只去了左边干扰项，
            // 必须严格nums[l] < nums[mid]，才能判断左边有序
            if(nums[l] < nums[mid]){
                // 左边有序
                // target在左边
                if(nums[l] <= target && target< nums[mid]){
                    r= mid-1;
                }else {
                    l = mid+1;
                }
            }else{
                // 右边有序
                // target在右边
                if(nums[mid] < target && target <= nums[r]){
                    l=mid+1;
                }else{
                    r=mid-1;
                }
            }
        }
        return false;
    }


    /**
     * <p>
     * 给你一个非负整数 x ，计算并返回 x 的算术平方根 。<br>
     * 由于返回类型是整数，结果只保留整数部分，小数部分将被舍去 。<br>
     * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。<br>
     * 例如1：<br>
     * 输入：x = 4 <br>
     * 输出：2 <br>
     * 例如2：<br>
     * 输入：x = 8 <br>
     * 输出：2 <br>
     * 解释：8 的算术平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。<br>
     * </p>
     */
    public static int mySqrt(int x) {
        // 把开方的运算转化成对数运算，Math.exp(0.5 * Math.log(x));
        //二分法， 由于结果只保留整数部分，相当于 k^2 <= x,找到k的最大值
        int l=0,r=x,ans=-1;
        while(l<=r){
            int mid=l +(r-1)/2;
            // 防止溢出
            if((long)mid*mid <= x){
                ans = mid;
                l = mid+1;
            }else{
                r= mid-1;
            }
        }
        return ans;
    }

    /**
     * <p>
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。<br>
     * 如果目标值不存在于数组中，返回它将会被按顺序插入的位置。<br>
     * 请必须使用时间复杂度为 O(log n) 的算法。<br>
     * 例如：<br>
     * 输入：nums = [1,3,5,6], target = 5<br>
     * 输出：2<br>
     * </p>
     */
    public static int searchInsert(int[] nums, int target) {
        // log n 二分法
        // 转化为第一个大于等于target的数，这个数的下标就是target该存储的下标
        int l=0,r= nums.length-1;
        while(l<=r){
            // 当left==right，区间[left, right]依然有效
            int mid = l+(r-l)/2;
            if(nums[mid] == target){
                return mid;
            }
            if(nums[mid] > target){
                r = mid-1;
            }
            if(nums[mid] < target){
                l = mid+1;
            }
        }
        return r+1;
    }


    /**
     * <p>
     * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。<br>
     * 如果数组中不存在目标值 target，返回[-1, -1].<br>
     * 要求：时间复杂度为 O(log n)<br>
     * 例如：<br>
     * 输入：nums = [5,7,7,8,8,10], target = 8<br>
     * 输出：[3,4]<br>
     * </p>
     */
    public static int[] searchRange(int[] nums, int target) {
        int left = searchLower(nums, target);
        int right = searchHigh(nums, target)-1;
        if (left <= right && right < nums.length && nums[left] == target && nums[right] == target) {
            return new int[]{left, right};
        }
        return new int[]{-1, -1};
    }


    /**
     * 整数数组 nums 按升序排列，数组中的值 互不相同 ，在一个未知的位置进行旋转，<br>
     * 例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2]<br>
     * 现在给一个旋转过的数组，不知道具体旋转点。判断数组中是否有target存在，存在返回下标，不存在返回-1。<br>
     * 例如：<br>
     * 输入：nums = [4,5,6,7,0,1,2], target = 0<br>
     * 输出：4<br>
     */
    public static int search(int[] nums, int target) {
        /*
           思路：二分法，找到左右两边哪边是有序的，如果target在有序范围内，则二分继续找。
           如果target在无序范围内，则在无序范围内二分，然后重复上面的循环
         */
        int len = nums.length;
        if(len==0){
            return -1;
        }
        if(len==1){
            return nums[0]==target ? 0 : -1;
        }
        int left=0,right=len-1;
        while(left<=right){
            int mid = (left+right)/2;
            if(nums[mid]==target){
                return mid;
            }
            if(nums[0]<=nums[mid]){
                // 0~mid是有序，并且target在0～mid里面
                if(nums[0]<=target && target<=nums[mid]){
                     right = mid-1;
                }else {
                    left = mid+1;
                }
            }else {
                // mid~right有序
                if(nums[mid]<=target && target<=nums[len-1]){
                    left = mid+1;
                }else {
                    right = mid-1;
                }
            }
        }
        return -1;
    }

    /**
     * <p>
     *  判断一个 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：<br>
     *  1.每行中的整数从左到右按升序排列。<br>
     *  2.每行的第一个整数大于前一行的最后一个整数。<br>
     *  例如：<br>
     *  输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3 <br>
     *  输出：true <br>
     * </p>
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        /*
           思路1：对矩阵第一列进行二分查找，找到最接近的一行，然后对该行进行二分查找
           思路2：把矩阵拼接成一个数组，对数组进行二分查找，总长度是m*n，下标可以利用取余操作来类似地把数组拼接
           思路2只能处理矩阵，非矩阵的二维数组处理不了。
         */
        int first = findFirstColumn(matrix, target);
        if(first<0){
            return false;
        }

        return false;
    }


    /**
     * <p>已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。<br>
     * 例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：<br>
     * 若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]<br>
     * 若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]<br>
     * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。<br>
     * 找出数组中的 最小元素<br>
     *
     * 例如：<br>
     * 输入：nums = [3,4,5,1,2] <br>
     * 输出：1 <br>
     * </p>
     */
    public static int findMin(int[] nums) {
        /*
         * 只要把mid和right比较即可，
         * mid>right，最小mid右边
         * mid<right, 最小在mid左边，或者就是mid
         */
        int l=0,r=nums.length-1;
        while (l<r){
            int mid = l+(r-l)/2;
            if( nums[mid] > nums[r]){
                l = mid+1;
            }else{
                r=mid;
            }
        }
        return nums[l];
    }

    /**
     * <p>
     * 峰值元素是指其值严格大于左右相邻值的元素 <br>
     * 给你一个整数数组 nums，找到峰值元素并返回其索引。<br>
     * 数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可 <br>
     * 你可以假设 nums[-1] = nums[n] = -∞  <br>
     * 时间复杂度为 O(log n)  <br>
     * 例如：<br>
     * 输入：nums = [1,2,1,3,5,6,4] <br>
     * 输出：1 或 5  <br>
     * 解释：你的函数可以返回索引 1，其峰值元素为 2;或者返回索引 5， 其峰值元素为 6  <br>
     * </p>
     *
     */
    public static int findPeakElement(int[] nums) {

        /*
         * 因为只要找出其中一个峰值，所以可以直接用二分法，
         * 当mid<mid+1,mid肯定不是峰值，峰值在右边
         * mid>mid+1时，mid可能是峰值，
         */
        int l = 0, r= nums.length-1;
        if(nums.length==1){
            return 0;
        }
        while(l<r){
            int mid = l+(r-l)/2;
            if(nums[mid]<nums[mid+1]){
                l = mid+1;
            }else{
                r = mid;
            }
        }
        return l;
    }


    private static int findFirstColumn(int[][] matrix, int target){
        // low 从-1开始，如果target不在这里面，就返回-1
        int low=-1,high=matrix.length-1;
        while(low<=high){
            // 这样写，避免 (low+high+1)/2 时low+high的溢出
            int mid = low + (high-low+1)/2 ;
            if(matrix[mid][0]<= target ){
                low = mid;
            }else {
                high = mid-1;
            }
        }
        return low;
    }


        private static int searchLower(int[] nums, int target){
        int left=0,right=nums.length-1,ans=nums.length;
        while(left<=right){
            int mid = (left+right)/2;
            if(nums[mid]>=target){
                right = mid-1;
                ans = mid;
            }else {
                left = mid+1;
            }
        }
        return ans;
    }

    private static int searchHigh(int[] nums, int target){
        int left=0,right=nums.length-1,ans=nums.length;
        while(left<=right){
            int mid = (left+right)/2;
            if(nums[mid] > target){
                right = mid-1;
                ans = mid;
            }else {
                left = mid+1;
            }
        }
        return ans;
    }


    public static void main(String[] args) {

    }
}
