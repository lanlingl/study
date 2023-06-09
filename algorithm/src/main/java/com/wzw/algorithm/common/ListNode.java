package com.wzw.algorithm.common;

/**
 * @author wuzhiwei
 * @team M
 * @owner wuzhiwei
 * @Date 2023/2/22
 */
public class ListNode {

    public int val;

    public ListNode next;

    public ListNode() {}

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
