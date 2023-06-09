package com.wzw.algorithm.recursion;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author wuzhiwei
 * @team M
 * @owner wuzhiwei
 * @Date 2022/2/18
 */
public class Recursionutil {



    /**
     * 152. 乘积最大子数组
     * https://leetcode.cn/problems/maximum-product-subarray/?favorite=2cktkvj
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        // 用回溯吧

        return 0;
    }


    /**
     * 单词搜索,
     * <p>
     * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word="ABCCED"<br>
     * 输出：true<br>
     * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"<br>
     * 输出：false<br>
     * </p>
     * @param board
     * @param word
     * @return
     */
    public static boolean exist(char[][] board, String word) {
        // 回溯，首先遍历 board 的所有元素，先找到和 word 第一个字母相同的元素，然后进入递归流程。
        int row = board.length;
        int column = board[0].length;
        if(row==1&& column==1 && word.length()==1 && word.charAt(0)==board[0][0]){
            return true;
        }
        boolean[][] res = new boolean[1][1];
        boolean[][] tmp = new boolean[row][column];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                tmp[i][j]=true;
                findLetter(tmp, board, i, j, word, 0, res);
                if(res[0][0]){
                    return true;
                }
                tmp[i][j]=false;
            }
        }
        return res[0][0];
    }

    private static void findLetter(boolean[][] tmp, char[][] board, int i, int j, String word, int index, boolean[][] res){
        if(i>board.length-1 || j >board[0].length-1) {
            return;
        }
        if(index==word.length()){
            res[0][0]= true;
            return;
        }

        if(board[i][j]!=word.charAt(index)){
            // 字母不匹配，跳出
            return;
        }
        if(index==word.length()-1){
            res[0][0]= true;
            return;
        }

        // 往下找
        if(!res[0][0] && i<board.length-1 && !tmp[i+1][j]){
            tmp[i+1][j] = true;
            findLetter(tmp, board, i + 1, j, word, index + 1, res);
            tmp[i+1][j] = false;
        }

        // 往上找
        if(!res[0][0] && i>0 && !tmp[i-1][j]){
            tmp[i-1][j] = true;
            findLetter(tmp, board, i-1, j, word, index+1, res);
            tmp[i-1][j] = false;
        }

        // 往左找
        if(!res[0][0] && j>0 && !tmp[i][j-1]){
            tmp[i][j-1] = true;
            findLetter(tmp, board, i, j-1, word, index+1, res);
            tmp[i][j-1] = false;
        }

        // 往右找
        if(!res[0][0] && j<board[0].length-1 && !tmp[i][j+1]){
            tmp[i][j+1] = true;
            findLetter(tmp, board, i, j+1, word, index+1, res);
            tmp[i][j+1] = false;
        }
    }


    /**
     * 返回所有子集，
     * <p>
     * 输入：nums = [1,2,3]<br>
     * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]] <br>
     *
     * 输入：nums = [0]<br>
     * 输出：[[],[0]]
     * </p>
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsets(int[] nums) {
        /*
         *
         * [], [][1],[][1][2],[][1][2][3],到头
         * 把3去掉，加入4，没有4，退回，再把2去掉，加入3，[][1][3]
         * 把3去掉，加入4，没有4，退回，再把1去掉，加入2，加入3，[][2],[][2][3]
         * 把3去掉，加入4，没有4，退回，再把2去掉，加入3，[][3]
         * 把3去掉，加入4，没有4，结束。
         */
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> tmp = new ArrayDeque<>();
        fn1(nums.length-1, nums, tmp, res);
        return res;
    }

    private static void fn1(int index, int[] nums , Deque<Integer> tmp, List<List<Integer>> res) {
        res.add(new ArrayList<>(tmp));
        for (int i = index; i < nums.length; i++) {
            tmp.add(nums[i]);
            fn1(index+1, nums, tmp, res);
            tmp.removeLast();
        }
    }


    private static void fn(int index, int[] nums , Deque<Integer> tmp, List<List<Integer>> res){
        if(index<0){
            return;
        }
        tmp.removeLast();
        int count = 0;
        for (int i = index+1; i < nums.length; i++) {
            tmp.add(nums[i]);
            res.add(new ArrayList<>(tmp));
            count++;
        }

        for (int i = 0; i < count; i++) {
            tmp.removeLast();
        }
        fn(index-1, nums, tmp, res);

    }






    /**
     * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标。
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     * 判断你是否能够到达最后一个下标。
     * 输入：nums = [2,3,1,1,4]
     * 输出：true
     * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标
     * 输入：nums = [3,2,1,0,4]
     * 输出：false
     * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
     */
    public boolean canJump(int[] nums) {
        int len = nums.length;
        /*
          如果 nums[i] + nums[i+nums[i]] .最终等于nums.length，那么可以到达，否则不可以到达
         */
        int sum = next(nums[0], nums, 1);
        return false;
    }

    private static int next(int s, int[] nums, int i){
        if(nums[i] == 0){
            return s;
        }
        if(i+nums[i]>= nums.length){
            return s+nums[i];
        }
        return s+ next(s, nums, i+nums[i]);
    }

    /**
     * 全排列
     * @param nums
     * @return
     */
    public static List<List<Integer>> permute(int[] nums) {
        // 1. 循环嵌套
        // 2. 回溯
        List<List<Integer>> ans = new ArrayList<>();

        Deque<Integer> combine = new ArrayDeque<>();
        boolean[] used = new boolean[nums.length];
        recursion(nums, used, 0, ans, combine);
        return ans;
    }


    private static void recursion(int[] nums, boolean[] used, int index, List<List<Integer>> ans, Deque<Integer> combine) {

        if(index > nums.length-1){
            ans.add(new ArrayList<>(combine));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if(!used[i]){
                combine.addLast(nums[i]);
                used[i] = true;
                recursion(nums, used, index+1, ans, combine);
                combine.removeLast();
                used[i] = false;
            }

        }

    }

    public static void main(String[] args) {
        char[][] board = new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        String word = "ABCCED";
//        char[][] board = new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
//        String word = "SEE";
//        String word = "ABCB";
//        char[][] board = new char[][]{{'a'},{'a'}};
//        String word = "aa";
        System.out.println(exist(board, word));

    }




}
