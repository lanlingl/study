package com.wzw.algorithm.xor;

import java.util.Arrays;

/**
 * 异或
 * @author wuzhiwei
 * @team M
 * @owner wuzhiwei
 * @Date 2022/2/9
 */
public class exclusiveORUtil {

    /**
     * 1^0 = 1, 1^1=0, 0^0=1
     */

    /**
     * 一个整数数组，有1一个数重复出现奇数次，其余都出现偶数次，找出出现奇数次的数
     */
    public static void find(int[] nums){
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }
        System.out.println(res);
    }

    /**
     * 一个整数数组，有2一个数重复出现奇数次，其余都出现偶数次，找出出现奇数次的2个数
     */
    public static void find2(int[] nums){
        /* 思路，假设最终2个数是a和b，第一步还是全部异或，得到 a^b
           因为a^b !=0,所以，a和b必定在二进制的某一位上有一位不相同，我们针对这一位，
           比如，选择这一位是0的数，跟nums中所有数进行一次异或，那么，就能得到a或者b，
           （因为其他数都出现偶数次，所以这一位上的异或结果，不会受到其他出现偶数次的数的影响）
        */

        int res1 = 0;
        // res1 = a ^ b
        for (int num : nums) {
            res1 ^= num;
        }
        // 从a^b的结果中找到a和b的最右边(最低位)的不相同的一位，~取反运算符
        // 找到res1最右边的1
        int dif = res1 & (~res1 +1);
        // a和b是两个结果
        int a = res1 ;
        int b = 0 ;
        for (int num : nums) {
            if((num & dif) == 0){
                a = a ^ num;
            }
        }
        b = a ^ res1;
    }

    /**
     * 交换第i位和第j位的值
     */
    public static void swap(int[] nums, int i , int j){
        /* 交换i和j的位置，i、j不能相等*/
        if(i==j){
            return;
        }
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }

    public static void main(String[] args) {


        // 7=0111 -> 0001 , 8=1000->1000
        System.out.println(8 & (~8 +1));
        int dif = 7 & (~7 +1);
        for (int i = 0; i < 10; i++) {
            if((dif & i) == dif){
                System.out.println("最右边是1，i="+i);
            }
//            if((dif & i) == 0){
//                System.out.println("最右边是0，i="+i);
//            }
        }
    }
}
