package com.wzw.algorithm.greedy;

import com.wzw.algorithm.doublepointer.DoublePointer;

import java.util.*;

/**
 * @author wuzhiwei
 * @team M
 * @owner wuzhiwei
 * @Date 2022/2/10
 */
public class GreedyUtil {

    /**
     * 力扣56，合并区间
     * https://leetcode.cn/problems/merge-intervals/?favorite=2cktkvj
     * @param intervals
     * @return
     */
    public static int[][] merge(int[][] intervals) {
        // 如果intervals[i][1]>=intervals[i+1][0],row和row+1重合，
        // 如果可以合并，把合并后的值放到intervals[i+1]，然后拿intervals[i+1]继续与下一个比较，直到不能合并
        // 不能合并时，把intervals[i]放进答案中，继续intervals[i+1][1]和intervals[i+2][0]进行比较
        //
        int row = intervals.length;

        if(row<=1){
            return intervals;
        }
        List<int[]> ans = new ArrayList<>();

        //排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0];
            }
        });

        for (int i = 0; i < row-1; i++) {

            if(intervals[i][1]>=intervals[i+1][0]){
                //包含，合并
                if(intervals[i][1]>=intervals[i+1][1]){
                    intervals[i+1] = intervals[i];
                }else{
                    //重合,合并，intervals[i]和intervals[i+1]
                    intervals[i+1] = new int[]{intervals[i][0], intervals[i+1][1]};
                }
                intervals[i]=null;
                if(i==row-2){
                    ans.add(intervals[i+1]);
                }
            }else{
                ans.add(intervals[i]);
                if(i==row-2){
                    ans.add(intervals[i+1]);
                }
            }
        }

        return ans.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        int[][] nums = new int[][]{{1,3},{2,6},{8,10},{15,18}};
        final int[][] merge = merge(nums);
        for (int[] ints : merge) {
            System.out.println(Arrays.toString(ints));
        }
    }

    /**
     * 力扣，跳跃游戏
     * https://leetcode.cn/problems/jump-game/solutions/?favorite=2cktkvj
     * @param nums
     * @return
     */
    public static boolean canJump(int[] nums) {
        /*
        定义一个cover，表示当前元素能跳的最大覆盖范围，每次我都只往右跳一格
        然后更新cover范围，将当前索引的覆盖范围和上一次的覆盖范围cover相比，
        两者中的最大值就是更新后的cover。当最大范围>=数组最后一个索引时，返回true
         */
        int cover = 0;
        for (int i = 0; i <= cover; i++) {
            cover = Math.max(cover,i+nums[i]);
            if(cover >= nums.length-1){
                return true;
            }
        }
        return false;
    }




    /**
     * 给定一个长度为 n 的整数数组 height,
     * height的每个值表示一个柱体，这个数组的height最多能装多少水
     * <a href='https://leetcode-cn.com/problems/container-with-most-water/'>题目解释</>
     * @see DoublePointer#maxArea(int[])
     */
    public int maxArea(int[] height) {
        // 贪心双指针
        return 0;
    }
}
