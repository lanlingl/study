package com.wzw.algorithm.doublepointer;

import com.wzw.algorithm.common.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wuzhiwei
 * @team M
 * @owner wuzhiwei
 * @Date 2021/12/27
 */
public class DoublePointer {

    private DoublePointer(){}


    /**
     * 颜色分类，0，1，2三种颜色，升序排序，只遍历一次
     * @param nums
     */
    public void sortColors(int[] nums) {

        // 利用快速排序的partition逻辑，
        // p0表示最左位置，交换一次，就+1，
        // p2表示最右位置，交换过一次，就-1，
        // 核心思想，把0放最左边，把2放最右边，p0和p2用来记录左右两边已经交换到哪里了，确认下一次交换的位置
        // 但是如果num[p2]=2,num[i]=2交换后，i仍然是2，p2左移了一位，这个时候还需要交换，知道i的位置上不是2为止。
        // 当i超过p2时，就不用继续遍历了
        int p0=0;
        int p2=nums.length-1;
        for (int i = 0; i <= p2; i++){
            // 如果num[p2]=2,num[i]=2交换后，i仍然是2，p2左移了一位，这个时候还需要交换，知道i的位置上不是2为止。
            while(i<=p2 && nums[i]==2){
                swap(nums, p2, i);
                p2--;
            }
            if(nums[i]==0){
                swap(nums, p0, i);
                p0++;
            }
        }

    }



    public static void nextPermutation(int[] nums) {
        // 从右往左，找到第一个i，使得nums[i]<nums[i+1]，同时从i+1开始往右都是降序排列（如果从左往右找就没有i+1开始都是降序了）。
        // 从右往左，找到第一个j，使得nums[i]<nums[j]，交换nums[i]和nums[j]，此时，i+1往右仍然是降序
        // 只要反转i+1往右的序列即可。
        // 如果找不到第一个i，说明nums本身就是降序的，直接反转nums即可。
        // ps：反转利用双指针，交换首尾
        int i = 0;
        int len = nums.length-1;
        while(nums[i]>=nums[i+1]){
            if(i<len-1){
                i=0;
                break;
            }
            i++;
        }
        if(i>=0){
             int j = len;
            while(j>=0 && nums[i]>=nums[j] ){
                j--;
            }
            swap(nums, i ,j);
        }
        reverse(nums, i+1);


    }

    /**
     * 交换2个元素
     */
    private static void swap(int[] nums, int i,int j){
        int tmp = nums[i];
        nums[i]=nums[j];
        nums[j]=tmp;
    }

    /**
     * 反转
     */
    private static void reverse(int[] nums, int start){
        int left = start, right = nums.length - 1;
        while(left < right){
            swap(nums, left, right);
            left++;
            right--;
        }
    }


    /**
     * 删除链表倒数第n个节点
     * @param head
     * @param n
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        head = init();
        if(head.next==null){
            return n>0 ? null : head;
        }
        int i=1;
        ListNode del= i==n? head :null;
        ListNode delPrefix=null;
        ListNode current = head;
        while(true){
            if(current.next == null){
                if(del==head){
                    head = head.next;
                }else{
                    delPrefix.next=del.next;
                }
                break;
            }
            i++;
            current = current.next;
            if(i==n){
                del = head;
            }else if(i>n){
                delPrefix = del;
                del = del.next;
            }
        }
        return head;

    }



    private static ListNode init(){
        ListNode head = new ListNode(1);

        ListNode n1 = new ListNode(2);
        head.next = n1;

//        ListNode n2 = new ListNode(3);
//        n1.next = n2;
//
//        ListNode n3 = new ListNode(4);
//        n2.next = n3;
//
//        ListNode n4 = new ListNode(5);
//        n3.next = n4;
        return head;
    }


    /**
     * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点(i,ai) 。
     * 在坐标内画 n 条垂直线，垂直线 i的两个端点分别为(i,ai) 和 (i, 0) 。
     * 找出其中的两条线，使得它们与x轴共同构成的容器可以容纳最多的水。
     *
     * 输入：[1,8,6,2,5,4,8,3,7]
     * 输出：49 = 7*7
     */
    public static int maxArea(int[] height){
        // 双指针，指针指向的值小的移动。
        int left = 0;
        int right = height.length-1;
        int maxArea =0;
        int area = 0;
        while(right>left){
            if(height[left] > height[right] ){
                area = height[right] * (right-left);
                --right;
            }else {
                area = height[left] * (right-left);
                ++left;
            }
            maxArea = Math.max(maxArea,area);
        }
        return maxArea;
    }

    /**
     * <p>
     * 存在一个按升序排列的链表，给你这个链表的头节点 head，<br>
     * 请你删除链表中所有存在数字重复情况的节点，<br>
     * 只保留原始链表中 没有重复出现 的数字<br>
     * 例如：<br>
     * 输入：head = [1,2,3,3,4,4,5]<br>
     * 输出：[1,2,5]<br>
     * </p>
     * @param head 链表头
     */
    public ListNode deleteDuplicates(ListNode head) {
        // 递归，
        // 1.判断当前和next是否重复，重复就删掉next，
        // 2.反复处理，直到当前和next不重复，
        // 3.然后再处理这时的新next，
        // 4.处理完成之后完成当前node
        // 5.在3的步骤里重复123步骤，如此反复，来递归

        if(head == null || head.next ==null){
            return head;
        }
        // 如果当前和下一个不重复，直接处理next节点
        if( head.val != head.next.val){
            head.next = deleteDuplicates(head.next);
        }else {
            // 循环删掉后面重复的节点，直到不重复
            while(head.next !=null && head.val == head.next.val){
                head.next = head.next.next;
            }
            // 删完重复后，再处理next节点，
            // 这里不是head.next = deleteDuplicates(head.next);是因为重复的元素一个也不能留，
            // 所以也去掉了当前节点，
            // 否则就该是head.next = deleteDuplicates(head.next);
            return deleteDuplicates(head.next);
        }
        return head;
    }


    /**
     * <p>
     * 给你一个包含 n 个整数的数组nums，<br>
     * 判断nums中是否存在三个元素a，b，c ，使得a + b + c = 0 ？<br>
     * 请你找出所有和为 0 且不重复的三元组。<br>
     * 答案中不可以包含重复的三元组。<br>
     * 例如：<br>
     * 输入：nums = [-1,0,1,2,-1,-4]<br>
     * 输出：[[-1,-1,2],[-1,0,1]]<br>
     * </p>
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        // 思路：先排序，后用i加上l,r左右双指针，寻找3个等于0的
        // 固定某一个数i，然后l、r只能从两边向中间靠（在i之后）。细节条件就是去重处理
        List<List<Integer>> result = new ArrayList<>();
        if(nums==null || nums.length<3){
            return result;
        }
        int len = nums.length;
        Arrays.sort(nums);
        for(int i=0; i<len; ++i){
            if(nums[i]>0){
                break;
            }
            if(i>0 && nums[i] == nums[i-1]){
                continue;
            }
            int l=i+1, r=len-1;
            while(l<r){
                // 左右相加大于负nums[i]，r左移一位
                if(nums[l]+nums[r]+nums[i] > 0){
                    --r;
                }else if(nums[l]+nums[r]+nums[i] < 0){
                    ++l;
                }else if(nums[i]+nums[l]+nums[r]==0){
                    // 找到合为0的i、l、r
                    result.add(Arrays.asList(nums[i],nums[l],nums[r]));
                    // 缩小左右，继续寻找
                    ++l;
                    --r;
                    // 还需要跳过重复项
                    while(l<r && nums[l]==nums[l-1]){
                        ++l;
                    }
                    while(l<r && nums[r]==nums[r+1]){
                        --r;
                    }
                }
            }

        }
        return result;
    }
    public static void main(String[] args) {
        int[] nums = new int[]{3,2,1};
//        int[] nums = new int[]{1,2,3};
        nextPermutation(nums);
    }
}
