package com.wzw.algorithm.backtrack;

import com.wzw.algorithm.tree.DFSUtil;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 回溯
 * 回溯是DFS的一种表现形式
 * @see DFSUtil
 * @author wuzhiwei
 * @team M
 * @owner wuzhiwei
 * @Date 2022/2/9
 */
public class backtrackUtil {

    /**
     * 回溯听着很高大上，但是回溯只是树DFS的一种表现形式，所以只要用树的深度优先遍历就可以很好的理解回溯了，
     * 树的深度优先遍历总会回到父节点，所以这就是回溯。
     */

    /**
     * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。<br>
     * 例1：<br>
     * 输入: n = 3<br>
     * 输出: ["((()))","(()())","(())()","()(())","()()()"]<br>
     */
    public static List<String> generateParenthesis(int n) {
        // 思路：递归回溯，回溯的本质是树的DFS，深度优先遍历
        // 当剩余左括号和右括号一样多，下一个必然是左括号
        // 剩余左括号小于右括号，下一个可以用左括号也可以用右括号
        // 当左右括号剩余都为0时，递归结束。
        List<String> result = new ArrayList<>();
        if(n <= 0){
            return result;
        }
        generate(result, "", n,n);
        return result;
    }

    private static void generate(List<String> result, String row, int left,int right){
        if(left ==0 && right ==0){
            result.add(row);
            return;
        }
        if(left == right){
            // 这里出栈时，重新回到这一行时，row还是之前的状态，并没有这个"("，这样才是回溯。
            // 千万不能用left--，因为等到递归出栈，回到这一行时，left的值不能变，如果用了left--，left就会变掉，没法回溯了
            generate(result, row+"(", left-1,right);
        }else if(left < right){
            if(left>0){
                generate(result, row+"(", left-1,right);
            }
            generate(result, row+")", left,right-1);
        }
    }




    /**
     * 全排列<br>
     * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案<br>
     * 例如：<br>
     * 输入：nums = [1,2,3] <br>
     * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]] <br>
     */
    public static List<List<Integer>> permute(int[] nums) {
        /* 递归回溯 ,向一个空二维数组中填如数字，并交换数组中的所有数字*/
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> output = new ArrayList<>();

        Arrays.stream(nums).forEach(output::add);
        backtrack(nums.length, output, res, 0);
        return res;
    }
    public static void backtrack(int n, List<Integer> output, List<List<Integer>> res, int first){
        // 所有数都填完了
        if(n==first){
            res.add(new ArrayList<>(output));
        }
        for (int i = first; i < n; i++) {
            // 交换first和i
            Collections.swap(output, first, i);
            // 继续递归填下一个数
            backtrack(n, output, res, first + 1);
            // 撤销交换操作
            Collections.swap(output, first, i);
        }

    }




    public static void main(String[] args) {
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        String digit = "234";
        char[] chars = digit.toCharArray();
        List<String> result = new ArrayList<>();
        StringBuilder sub = new StringBuilder();

        List<String> phones = phoneMap.keySet().stream().filter(k -> digit.contains(String.valueOf(k)))
                .map(phoneMap::get).collect(Collectors.toList());


    }



    private static void fun(int index, String digits, List<String> result, StringBuilder sub, Map<Character, String> phoneMap) {
        if (index >= digits.length()) {
            return;
        }
        String letterStr = phoneMap.get(digits.charAt(index));

        char[] letterArray = letterStr.toCharArray();

        for (char letter : letterArray) {
            sub.append(letter);
            // 递归
            fun(index+1, digits, result, sub, phoneMap);
            result.add(sub.toString());
            sub.deleteCharAt(index);

        }




    }
}
