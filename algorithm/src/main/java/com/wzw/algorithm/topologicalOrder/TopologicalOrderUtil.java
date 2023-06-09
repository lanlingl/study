package com.wzw.algorithm.topologicalOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * /**
 *
 * @author wuzhiwei
 * @team M
 * @owner wuzhiwei
 * @Date 2023/3/16
 */
public class TopologicalOrderUtil {

    /*
     * 拓扑排序
     * 对一个有向无环图(Directed Acyclic Graph简称DAG)G进行拓扑排序，是将G中所有顶点排成一个线性序列，
     * 使得图中任意一对顶点u和v，若边<u,v>∈E(G)，则u在线性序列中出现在v之前。
     * 简单的说，由某个集合上的一个偏序得到该集合上的一个全序，这个操作称之为拓扑排序。
     *
     */

    /**
     * 力扣 207.课程表
     * 拓扑排序：把一个有向无环图转化成一个有序数列的过程，叫拓扑排序
     * 拓扑排序，可以用BFS和DFS来解决
     */
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        // 先把每个课程和他的前提放到一起
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            map.put(i, new ArrayList<>());
        }
        for (int[] prerequisite : prerequisites) {
            // 找到list中跟prerequisites[i][0]相同的课程，把他的前提条件放进课程对应的list里面
            map.get(prerequisite[0]).add(prerequisite[1]);
        }
        // 表示每个课程的状态，0=没有被访问，1=被当前链路所访问，2=被其他链路所访问
        int[] flag = new int[numCourses];
        // 再进行dfs
        for (int i = 0; i < numCourses; i++) {
            if (!dfs1(map, flag, i)){ return false;}
        }

        return true;
    }

    private static boolean dfs1(Map<Integer, List<Integer>> map, int[] flag, int subject){
        // flag[subject]=1表示在当前dfs中在此遇到subject，此时说明有环。
        if(flag[subject]==1){return false;}
        // flag[subject]=2表示在其他的dfs中已经确认subject可以上完课了，可以直接返回true。
        if(flag[subject]==2){return true;}
        flag[subject]=1;
        for (Integer condition : map.get(subject)) {
            //开始找前提条件课程，并且dfs
            if (!dfs1(map, flag, condition)){ return false;}
        }
        // 当退出dfs时，需要把flag=2，表示当前subject已经走完过了，下次遇到可以直接返回true
        flag[subject]=2;
        return true;

    }


}
