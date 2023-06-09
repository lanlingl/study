package com.wzw.algorithm.hash;

import java.util.*;

/**
 * @author wuzhiwei
 * @team M
 * @owner wuzhiwei
 * @Date 2023/2/1
 */
public class HashUtil {

    /**
     * 最长连续序列，要求时间复杂度为O(n)
     * https://leetcode.cn/problems/longest-consecutive-sequence/description/?favorite=2cktkvj
     * 输入：nums = [100,4,200,1,3,2]
     * 输出：4
     * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        // 1. 暴力：每个数i为起点，在nums中找nums[i]+1，nums[i]+2，直到找不到为止，用hash存储，如果使用过的数字，不重复使用。但是时间复杂度为：O(n的平方)
        // 2. dp：
        return fun1(nums);
    }
    private static int dp2(int[] nums) {
        // 2. 空间换时间，
        List<HashSet<Integer>> list = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {


        }
        return 0;
    }

   private static int fun1(int[] nums){
        if(nums.length==0){
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int res = 1;

        for (Integer integer : set) {
            int longest = 1;
            int num = integer-1;
            while(set.contains(num)){
                longest++;
                num--;
            }
            int num1= integer+1;
            while(set.contains(num1)){
                longest++;
                num1++;
            }
            res = Math.max(longest, res);
        }
        return res;
    }

    /**
     * 力扣 字母异位词分组
     * @param strs
     * @return
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        // 回溯
        List<List<String>> ans = new ArrayList<>();
//        fun1(ans, strs);
        Map<String, List<String>> map = fun2(strs);
        ans = new ArrayList<>(map.values());
        return ans;

    }

    private static Map<String, List<String>> fun2( String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }
        return map;
    }

    private static void fun1(List<List<String>> ans, String[] strs){
        List<String> used = new ArrayList<>();

        for (int i = 0; i < strs.length; i++) {
            if(used.contains(strs[i])){
                continue;
            }
            Deque<String> tmp = new ArrayDeque<>();
            tmp.add(strs[i]);
            recursion(strs, i,i+1, ans, tmp, used);
        }

    }

    private static void recursion(String[] strs, int start, int index, List<List<String>> ans, Deque<String> tmp, List<String> used){
        if(index > strs.length-1){
            ans.add(new ArrayList<>(tmp));
            used.addAll(new ArrayList<>(tmp));
            return;
        }
        if(similar(strs[index], strs[start])){
            tmp.add(strs[index]);
        }
        recursion(strs, start, index+1, ans, tmp, used);

    }

    private static boolean similar(String str1, String str2){
        // 直接排序，相等的就返回true
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        Arrays.sort(chars1);
        Arrays.sort(chars2);
        String s1 = new String(chars1);
        String s2 = new String(chars2);
        return s1.equals(s2);

    }



    public static void main(String[] args) {
//        String[] strs = new String[]{"eat","tea","tan","ate","nat","bat"};
        String[] strs = new String[]{"ddddddddddg","dgggggggggg"};
        groupAnagrams(strs);

    }
}
