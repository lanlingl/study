package com.wzw.algorithm.link;


import com.wzw.algorithm.common.ListNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wuzhiwei
 * @team M
 * @owner wuzhiwei
 * @Date 2023/2/22
 */
public class LinkUtil {

    /**
     * 876.链表中间节点，如果有两个中间结点，则返回第二个中间结点。
     * 输入：head = [1,2,3,4,5]
     * 输出：[3,4,5]
     * 解释：链表只有一个中间结点，值为 3
     * @param
     */
    public ListNode middleNode(ListNode head) {
        // 快慢指针i,j，i走2步，j走1步，当i走到末尾时，j刚好走到中间。不过要注意，当偶数个节点时，返回第二个节点。

        if (head == null) {
            return null;
        }

        ListNode i = head;
        ListNode j = head;

        while(i !=null && i.next!=null){
            i = i.next.next;
            j = j.next;
        }
        return j;
    }


    public static void main(String[] args) {

        Map<Integer, Integer> map = new HashMap<>();

    }

}
