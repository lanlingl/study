package com.wzw.algorithm.tree;

import com.wzw.algorithm.common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 深度优先搜索算法
 * @Date 2021/6/29
 */
public class DFSUtil {


    /**
     * DFS遍历
     * (个人感觉回溯是DFS的一种表现形式)
     * <p>深度优先搜索算法的核心是栈</p>
     * <p>利用栈(递归)的LIFO来实现深度优先遍历</p>
     */
    public static void base(TreeNode root) {
        if (root == null) {
            return;
        }
        base(root.left);
        base(root.right);
    }


    /**
     * 力扣200. 岛屿数量
     * @param grid
     * @return
     */
    public static int numIslands(char[][] grid) {
        //dfs ，遍历grid，遇到grid[i][j]=0,说明右岛屿，此时算一个，然后开始dfs，把每个相邻的1都改为0，直到dfs结束。
        int row = grid.length;
        int column = grid[0].length;
        int res = 0;
        for(int i = 0; i<row;++i ){
            for(int j=0;j<column;++j){
                if(grid[i][j]==1){
                    res++;
                    grid[i][j]=0;
                    dfs(i-1, j, grid);
                    dfs(i+1, j, grid);
                    dfs(i, j-1, grid);
                    dfs(i, j+1, grid);
                }
            }
        }
        return res;
    }

    private static void dfs(int i, int j, char[][] grid) {
        if (i < 0 || i == grid.length || j < 0 || j == grid[0].length) {
            return;
        }

        // 是一个岛
        if (grid[i][j] == 1) {
            // 跑过一次就设置为0，防止重复计算。
            grid[i][j] = 0;
            // 上
            dfs(i - 1, j, grid);
            // 下
            dfs(i + 1, j, grid);
            // 左
            dfs(i, j - 1, grid);
            // 右
            dfs(i, j + 1, grid);

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
         * 回溯：
         * 所有子集问题：每个元素都有2中状态，选或不选，
         *          1
         *        /   \
         *      2      2
         *    / \     / \
         *   3  3    3   3
         * / \ / \  / \ / \
         * 这样就转化成了一个树，直接用dfs就可以解决
         */
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> tmp = new ArrayDeque<>();
        recursion(0, nums,  tmp, res);

        return res;
    }

    private static void recursion(int index, int[] nums, Deque<Integer> tmp, List<List<Integer>> res){
        res.add(new ArrayList<>(tmp));
        for (int i = index; i < nums.length; i++) {
            tmp.add(nums[index]);
            recursion(i+1, nums,  tmp, res);
            tmp.removeLast();
        }
    }


    /**
     * 力扣，跳跃游戏
     * https://leetcode.cn/problems/jump-game/description/?favorite=2cktkvj
     * @param nums
     * @return
     */
    public static boolean canJump(int[] nums) {
        // 回溯
        boolean[] flag = new boolean[]{false};
        fn(nums, 0 , flag);
        return flag[0];

    }

    private static void fn(int[] nums, int index, boolean[] flag){
        if(flag[0]){
            return;
        }
        if(index ==nums.length-1){
            flag[0] = true;
            return;
        }

        for (int i = 1; i <= nums[index]; i++) {
            if(index+i==nums.length-1){
                // 刚好到终点
                flag[0] = true;
            }else if(index+i > nums.length-1){
                return;
            }
            fn(nums, index+i, flag);
            if(flag[0]){
                return;
            }
        }

    }

    /**
     * leecode，组合总和
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // 回溯，递归
        // 思路：【一定要画图，画出图形，更好理解】例7和2367。首先7和2367分别相减，得到4个差，4个差在分别和2367相减，
        // 循环中递归减candidates中的数，直到target小于或者等于0。等于0能得到一组值，小于0就表示这组值不是对应的解，
        // target大于0，则继续跟2367相减，直到小于或者等于0位置。
        // 但是直接递归，会把重复的解返回，比如223和232是重复的。所以2跟367都组合一边之后，到3时只需要跟67组合即可，
        List<List<Integer>> ans = new ArrayList<>();
        Deque<Integer> combine = new ArrayDeque<>();
        if (target == 0) {
            return ans;
        }

        dfs(candidates, 0, target, 0, ans, combine);
        return ans;
    }

    private static void dfs(int[] candidates, int begin, int target, int index, List<List<Integer>> ans, Deque<Integer> combine) {
        if (target < 0) {
            // 结束
            return;
        }

        if (target == 0) {
            // combine为一个解
            ans.add(new ArrayList<>(combine));
            return;
        }
        //
        for (int i = begin; i < candidates.length; i++) {
            combine.addLast(candidates[i]);
            dfs(candidates, i, target - candidates[i], i, ans, combine);
            combine.removeLast();

        }
    }

    /**
     * 全排列
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> permute(int[] nums) {
        // 1. 循环嵌套
        // 2. 回溯
        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < nums.length-1; i++) {
            Deque<Integer> combine = new ArrayDeque<>();
            combine.addLast(nums[i]);
            recursion(nums, i, 0, ans, combine);
        }
        return ans;

    }

    private static void recursion(int[] nums, int begin, int index, List<List<Integer>> ans, Deque<Integer> combine) {
        if (index > nums.length - 1) {
            ans.add(new ArrayList<>(combine));
            return;
        }

        for (int i = index; i < nums.length - 1; i++) {
            if (begin != index) {
                combine.addLast(nums[index]);
            }
            recursion(nums, begin, index + 1, ans, combine);
            combine.removeLast();

        }
    }

    public static void main1(String[] args) {
        int[] nums = new int[]{1,2,3,4};
        final List<List<Integer>> sets = subsets(nums);
        for (List<Integer> subset : sets) {
            System.out.println(subset);

        }

    }

    public static void recursion(TreeNode root, Deque<TreeNode> list1, Deque<TreeNode> list2, List<List<Integer>> res){
        if(root==null){
            return;
        }
        while(!list1.isEmpty()){
            TreeNode node = list1.poll();
            List<Integer> tmp = new ArrayList<>();
            tmp.add(node.val);
            res.add(tmp);
            if(node.left!=null){
                list2.add(node.left);
            }
            if(node.right!=null){
                list2.add(node.right);
            }

        }




    }


}
