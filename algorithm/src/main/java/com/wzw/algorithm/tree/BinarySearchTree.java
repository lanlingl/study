package com.wzw.algorithm.tree;

import com.wzw.algorithm.common.TreeNode;

/**
 * @author wuzhiwei
 * @team M
 * @owner wuzhiwei
 * @Date 2023/2/17
 */
public class BinarySearchTree {

    /**
     *
     * 不同的二叉搜索树，
     * https://leetcode.cn/problems/unique-binary-search-trees/?favorite=2cktkvj
     * 给一个n，从1到n个数，组成一个二叉搜索树，有多少种
     * 输入：n = 3
     * 输出：5
     *  1   1        2       3    3
     *   \   \      / \     /    /
     *    3 , 2  , 1   3 , 2  , 1
     *   /     \          /      \
     *  2       3        1        2
     * @param n
     * @return
     */
    public int numTrees(int n) {
        // 1. 利用回溯，遍历1～n做根结点，然后寻找左右子节点，继续递归寻找，如果不满足条件，就回到之前状态
        // 如果加上动态规划更好，
        // g[n]表示n个节点的总二叉搜索树的个数，f[i]表示以 i 为根的二叉搜索树的个数，g[n]=f[1]+f[2]+...f[n]
        // 而当 i 为根节点时，其左子树节点个数为 i-1 个，右子树节点为 n-i个,
        // 于是得到：f[i]=g[i-1]*g[n-i]
        // 得到：g[n]=g[0]*g[n-1]+g[1]*[n-2]+...+g[n-1]*g[0],这就是卡兰公式：G(n)= ∑[n,i=1]G(i-1)*G(n-i)
        // 动态规划转移方程就得到了，寻找边界，g[0]=g[1]=1,
        int[] g = new int[n+1];
        g[0]=1;
        g[1]=1;
        // 第一层是寻找根结点i
        for (int i = 2; i <= n; i++) {
            // 根据根节点i，
            for (int j = 1; j <=n; j++) {
                g[i] += g[j-1]*g[i-j];
            }
        }
        return g[n];
    }


    /**
     * 验证二叉搜索树
     * @param root
     * @return
     */
    public static boolean isValidBST(TreeNode root) {
        // 二叉搜索树中序遍历，就是升序排列

        // 因为最左边的子节点最小，所以一开始跟int的最小值比就行
        long[] pre = new long[]{ Long.MIN_VALUE};
        boolean[] res = new boolean[]{true};

        midSearch(root, pre, res);

        return res[0];
    }

    public static void midSearch(TreeNode root, long[] pre, boolean[] res){
        if(root==null || !res[0]){
            return ;
        }
        midSearch(root.left, pre, res);
        if(root.val <= pre[0]){
            res[0] = false;
            return ;
        }
        pre[0] = root.val;
        if(!res[0]){
            return;
        }
        midSearch(root.right, pre, res);

    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode c11 = new TreeNode(1);
        TreeNode c12 = new TreeNode(4);
        root.left=c11;
        root.right=c12;

        TreeNode c21 = new TreeNode(3);
        TreeNode c22 = new TreeNode(6);
        c12.left=c21;
        c12.right=c22;
        System.out.println(isValidBST(root));
    }

}
