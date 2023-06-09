package com.wzw.algorithm;


import com.wzw.algorithm.common.ListNode;

import java.util.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author wuzhiwei
 * @team M
 * @owner wuzhiwei
 * @Date 2023/2/8
 */
public class Util {

    public static ListNode sortList(ListNode head) {
        // 要求时间复杂度为O(nlogn)，空间复杂度为O(1)，则用归并排序，但是递归算法的空间复杂度为O(n)
        //ps:如果要想要O(1)，可以直接逆向归并，用迭代代替递归，当i=1，合并12，34，56，78。i=2时。合并1234，5678
        if(head==null || head.next==null){
            return head;
        }
        ListNode mid = middleNode(head);
        ListNode tmp = mid.next;

        // 把链表断开为2段
        mid.next =null;

        ListNode left = sortList(head);
        ListNode right = sortList(tmp);

        // 一个头节点，辅助合并，合并后的节点都放他后面
        ListNode subHead = new ListNode();
        // 结果从头节点下一个开始取即可。
        ListNode res = subHead;

        // 合并
        while(left !=null && right !=null){
            if(left.val <=right.val){
                subHead.next=left;
                left = left.next;
            }else{
                subHead.next = right;
                right = right.next;
            }
            subHead = subHead.next;
        }
        // 当left或right一方的指针走到null时，另一边应该还有节点要加上，因为左右都是排过序的，所以直接加再后面即可。

        subHead.next = left !=null ? left : right;

        return res.next;

    }

    public static void main(String[] args) {
        ListNode node0 = new ListNode(4);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(3);
        node0.next=node1;
        node1.next=node2;
        node2.next=node3;
        sortList(node0);
    }

    /**
     *  876题，链表中间节点
     */
    private static ListNode middleNode(ListNode head) {
        // 快慢指针i,j，i走2步，j走1步，当i走到末尾时，j刚好走到中间。
        if (head == null || head.next==null ) {
            return head;
        }

        ListNode i = head.next;
        ListNode j = head;

        while(i !=null && i.next!=null){
            i = i.next.next;
            j = j.next;
        }
        return j;
    }


    /**
     * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。
     * 输入: s = "leetcode", wordDict = ["leet", "code"]
     * 输出: true
     * 解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
     *
     * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
     * 输出: true
     * 解释: 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。
     *      注意，你可以重复使用字典中的单词。
     */
    public static boolean wordBreak(String s, List<String> wordDict) {
        // 回溯+dp，把wordDict放到hash中，然后用2个指针i和j遍历s，用子字符串去wordDict中找相同的字符串。
        // 找出重复计算的部分。dp[i]表示一个子问题，从i开始的字符串，是否可以被分解成若干部分，所有部分都能在词典中找到。
        Boolean [] dp = new Boolean[s.length()+1];
        dp[s.length()]=true;
        Set<String> set = new HashSet<>(wordDict);
        return reversion(dp, 0, 0, s, set);
    }

    private static boolean reversion(Boolean [] dp, int start, int index, String s, Set<String> set){
        if(start==s.length()){
            return true;
        }
        if(dp[start] !=null){
            return dp[start];
        }
        for (int i = index; i <= s.length(); i++) {
            String tmp = s.substring(start, i);
            if(set.contains(tmp) &&  reversion(dp, i, i+1, s, set)){
                dp[start]=true;
               return true;
            }
        }
        dp[start]=false;
        return false;
    }


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


    private static void son(int root, int n, int[] res){
        for (int i = 1; i <= n; i++) {

        }
    }



    public static void main1(String[] args) {
//        String s = "aaaaaaa";
//        String s = "leetcode";
        String s = "catsandog";
//        String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
        List<String> wordDict = new ArrayList<>();
//        wordDict.add("aaaa");
//        wordDict.add("aaa");
//        wordDict.add("leet");
//        wordDict.add("code");
        wordDict.add("cats");
        wordDict.add("dog");
        wordDict.add("sand");
        wordDict.add("and");
        wordDict.add("cat");
//        wordDict.add("a");
//        wordDict.add("aa");
//        wordDict.add("aaa");
//        wordDict.add("aaaa");
//        wordDict.add("aaaaa");
//        wordDict.add("aaaaaa");
//        wordDict.add("aaaaaaa");
//        wordDict.add("aaaaaaaa");
//        wordDict.add("aaaaaaaaa");
//        wordDict.add("aaaaaaaaaa");

        System.out.println(wordBreak(s, wordDict));
//
//        System.out.println(s.substring(0,4));
//        System.out.println(s.substring(4,7));

        Deque<String> d = new ArrayDeque<>();

    }






}
