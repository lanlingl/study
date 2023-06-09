package com.wzw.algorithm.map;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * /**
 *
 * @author wuzhiwei
 * @team M
 * @owner wuzhiwei
 * @Date 2023/3/8
 */
public class MapUtil {

    /**
     * 图
     * 拓扑排序：把一个有向无环图转化成一个有序数列的过程，叫拓扑排序
     * 拓扑排序，可以用BFS和DFS来解决
     */
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        // 从0开始上课，并在prerequisites[i][0]中寻找，如果prerequisites[i][0]没有等于这节课的，则可以上课。
        // 如果有，则开始dfs，判断他是否有闭环，如果没有闭环，则可以上课。
        // if(prerequisites.length==0){
        //     return true;
        // }
        // boolean[] res = new boolean[]{true};
        // for (int i = 0; i < numCourses; i++) {
        //     if(res[0]){
        //         Set<Integer> set = new HashSet<>();
        //         boolean[] exist = new boolean[prerequisites.length];
        //         dfs(prerequisites, i, set, exist, res);
        //     }
        // }
        // return res[0];

        // 上面方案可以，但是超时了，并且参数太多
        // 转到拓扑排序吧TopologicalOrderUtil


        return true;

    }

    private static void dfs(int[][] prerequisites, int condition ,Set<Integer> set,boolean[] exist,boolean[] res){

        for(int i=0; i< prerequisites.length ; ++i){

            if(!exist[i] && res[0]){
                // 前提==当前的课程，就继续找当前课程的前提
                if(condition == prerequisites[i][0]){
                    set.add(prerequisites[i][0]);
                    if(set.add(prerequisites[i][1])){
                        exist[i]=true;
                        dfs(prerequisites, prerequisites[i][1], set, exist, res);
                        set.remove(prerequisites[i][1]);
                    }else {
                        // 重复
                        res[0] = false;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
//        int[][]a = new int[][]{{1,0},{0,3},{0,2},{3,2},{2,5},{4,5},{5,6},{2,4}};
//        System.out.println(canFinish(7, a));
//        int[][]a = new int[][]{{1,0},{0,1}};
//        System.out.println(canFinish(2, a));
        int[][] a = new int[][]{{2, 3}, {3, 2}};
        System.out.println(canFinish(4, a));
//        int[][]a = new int[][]{{0,10},{3,18},{5,5},{6,11},{11,14},{13,1},{15,1},{17,4}};
//        System.out.println(canFinish(20, a));
//        int[][]a = new int[][]{};
//        System.out.println(canFinish(1, a));
//        int[][]a = new int[][]{{1,4},{2,4},{3,1},{3,2}};
//        System.out.println(canFinish(5, a));


    }

}
