package com.wzw.algorithm.tree;

import com.wzw.algorithm.common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 广度优先算法
 * @Date 2021/6/29
 */
public class BFSUtil {

    /**
     * BFS遍历，层序遍历
     *
     * <p>
     * 跟深度优先搜索算法不同的是，广度优先算法的核心是队列
     * 可以利用双端队列的FIFO来实现广度遍历
     * 例如：root1先进双端队列，然后弹出root1，得到子2、3并放进队列。
     *  2弹出，得到子4、5并放进队列；
     *  3弹出，得到6、7并放进队列；
     *  以此类推，访问所有树节点。
     *
     * 应用场景：
     * 1.层序遍历
     * 2.最短路径
     * </p>
     */
    public static void base(TreeNode root){

        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            System.out.println(node.val);
            if(node.left!=null){
                queue.add(node.left);
            }
            if(node.right!=null){
                queue.add(node.right);
            }
        }
    }

    public static void main(String[] args) {
//        TreeNode root = new TreeNode(1);
//        for (int i = 0; i < 5; i++) {
//            root.left = new TreeNode(i);
//
//        }
        int[] i = new int[0];
        System.out.println(i[0]);
    }

    /**
     * 二叉树层序遍历，一层一个数组
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {

        //层序遍历，也叫广度优先。利用双端队列。但是一个双端队列用来存入弹出，无法完成同一层级在一个数组里，
        // 可以考虑用2个队列
        List<List<Integer>> res = new ArrayList<>();
        Deque<TreeNode> list1 = new ArrayDeque<>();
        Deque<TreeNode> list2 = new ArrayDeque<>();
        list1.add(root);

        recursion(list1, list2, res);

        return res;
    }

    public static void recursion(Deque<TreeNode> list1, Deque<TreeNode> list2, List<List<Integer>> res){

        // 弹出1队列所有节点，然后把所有子节点放进2队列
        if(list1.isEmpty()){
           return;
        }

        List<Integer> tmp = new ArrayList<>();
        while (!list1.isEmpty()){
            TreeNode node = list1.poll();
            tmp.add(node.val);
            if(node.left!=null){
                list2.add(node.left);
            }
            if(node.right!=null){
                list2.add(node.right);
            }
        }
        res.add(tmp);
        // 递归把2队列所有节点弹出，子节点都放进1队列
        recursion(list2, list1, res);

    }

    /**
     * 二叉树展开为链表，展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
     * 展开后的单链表应该与二叉树 先序遍历 顺序相同
     * https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/?favorite=2cktkvj
     * @return
     */
    public void flatten(TreeNode root) {
        // 先序遍历，然后无限把先序遍历的节点放到right子指针上就行
        if(root!=null){
            preOrder(root, root.left, root.right);
        }

    }

    private static TreeNode preOrder(TreeNode tail, TreeNode left, TreeNode right){

        if(left != null ){
            tail.right = left;
            tail.left = null;
            tail = preOrder(tail, left.left, left.right);
        }

        if(right != null){
            tail.right = right;
            tail.left = null;
            tail = preOrder(tail, right.left, right.right);
        }
        return tail;
    }


}
