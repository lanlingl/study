package com.wzw.algorithm.dp;


import java.util.ArrayList;
import java.util.List;

/**
 * 动态规划：
 * <p>利用历史记录，避免重复的计算，我们一般用一维、二维数组来保存这些历史记录</p>
 * <p>
 *  步骤1：找到数据关联关系，定义一个数组，来存储这些数据dp[i]或者dp[i][j]
 * </p>
 * <p>
 *  步骤2：找出数组间的关系，确定表达式。比如dp[n]=dp[n-1]+dp[n-2] <br>
 *  【ps:千万不要拘泥于这种形式，只要是能够保存重复计算的结果，然后通过重复计算得到最终结果都是动态规划】
 * </p>
 * <p>
 *  步骤3：找出动态规划的边界，及公式适用范围
 * </p>
 * <p>
 *  步骤4：找出初始值，比如dp[1]=多少，dp[2]=多少。要找出所有的初始值，避免干扰到步骤2中的公式
 * </p>
 * <p>
 *  注意：在状态转移方程中，一定要考虑好转移的顺序
 * </p>
 *
 * @date 2021/3/17
 */
public class DynamicProgramUtil {

    /**
     * 如果需要记录之前的计算值，重复计算记录的值，将大计算划分为阶段性的小计算，
     * 这就是动态规划
     */


    /**
     * m x n 网格的左上角，从左上到右下，路径上节点总和最小值
     * https://leetcode.cn/problems/minimum-path-sum/?favorite=2cktkvj
     */
    public static int minPathSum(int[][] grid) {
        // 动态规划
        // 用dp[i][j]表示i，j位置上的路线最小和，那么，dp[i][j] = min(dp[i-1][j],dp[i][j-1]) + grid[i][j]
        // 边界i>0,j>0
        // 初始值 dp[i][0] = grid[0][0]+grid[1][0]+...+grid[i][0], 0=<i<grid.length
        // 初始值 dp[0][j] = grid[0][0]+grid[0][1]+...+grid[0][j]  0=<j<grid[0].length

        int[][] dp = new int[grid.length][grid[0].length];
        dp[0][0] = grid[0][0];

        for (int i = 1; i < grid.length; i++) {
            dp[i][0] = dp[i-1][0]+grid[i][0];
        }

        for (int j = 1; j < grid[0].length; j++) {
            dp[0][j] += dp[0][j-1] + grid[0][j];
        }

        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
            }
        }
        return dp[grid.length-1][grid[0].length-1];
    }

    /**
     * m x n 网格的左上角，从左上到右下，有多少种路径
     *
     * https://leetcode.cn/problems/unique-paths/?favorite=2cktkvj
     */
    public static int uniquePaths(int m, int n) {
        // 转移方程：dp[i][j]=dp[i-1][j]+dp[i][j-1]，
        // 找到边界：
        // 初始值：dp[i][0]=1,dp[0][j]=1，f[0][0]=1;

        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0]=1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j]=1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j]=dp[i-1][j]+dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }


    /**
     * 0/1背包问题：
     * 有N件物品和一个最多能被重量为W 的背包。第i件物品的重量是weight[i]，对应的价值是value[i] 。每件物品只能用一次，求解将哪些物品装入背包里物品价值总和最大。
     * eg：n=4，wight={1,3,4} value={15,20,30} ,输出=35
     * https://zhuanlan.zhihu.com/p/345364527
     */
    public static int zeroOneBackpack(int n, int[] weight, int[] value){
        // f[i][j]：背包最大容量为j，放进第i个物品时的最大的总价值
        // f[i-1][j] ：背包最大容量为j，第i个物品不放进去时的最大总价值，若j<weight[i]时, f[i][j]=f[i-1][j]（不拿i时）
        // f[i-1][j-weight[i]] ：容量为 j-weight[i] ，不放i时的最大总价值
        // f[i-1][j-weight[i]]+value[i]：容量为 j-weight[i]，放i进去时的最大总价值（拿i时）
        // 放物品i价值=物品本身的价值+剩余背包重量对应前i-1件物品的最大价值
        // 所以当j>=weight[i]时，状态转移方程：f[i][j] = max(f[i-1][j], f[i-1][j-weight[i]]+value[i]);

        // 找到边界：
        // 当j=0，背包最大容量为0时，不论i是多少value都是0


        return 0;
    }



    /**
     * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
     * <a herf='https://leetcode-cn.com/problems/wildcard-matching/'>题目描述</a> <br>
     */
    public static boolean isMatch(String s, String p) {
        /*
         思路：用dp[i][j]表示p的前i个字符跟s的前j个字符是否匹配
         dp[0][0]表示2个都是空字符串
         当i = '?'的时候，dp[i][j] = dp[i-1][j-1]
         当i 是字符的时候，dp[i][j] = dp[i-1][j-1] && s[j]==p[i]
         当i是'*'的时候，有两种情况：【是否只有这2种情况呢？】
            1). 如果dp[i][j-1]是true时dp[i][j]也为true，
                p带*号跟s都匹配了，那么s后面再加一个任意符号都匹配
                也就是说，i之前和j-1之前都匹配了，但我i是*，那么j-1后面加任意字符都匹配，也就是也就是i之前和j之前匹配
                例如：   a b b c
                         a b b *
                这里 dp[4][4] = dp[4][3], dp[4][3]匹配，dp[4][4]也就匹配
            2). 当i是'*'的时候，如果dp[i-1][j]是true时dp[i][j]也为true，
                p不带*都跟s匹配了，那么p后面加个*肯定也匹配
                也就是说，i是*，只要i-1之前都跟j之前匹配，那么i-1后面加上*号肯定也匹配，也就是i之前和j之前匹配
                例子不好举，但是能推导出来。
                例如：   a b b
                         a b b *
         总结就是：当i是'*'的时候，dp[i][j] = dp[i-1][j] || dp[i][j-1]

         边界：
         dp[0][0]表示2个都是空字符串，那么dp[0][0] = true;
         dp[0][j] =false, p为空字符，s不为空时，肯定不跟s匹配，所以dp[0][j]恒等于false
         dp[i][0] 也要分情况，只有p的前i个字符都是*，dp[i][0]才是true，如果>i的不是*，也恒为假
         可以设dp[i][j]其他情况默认为false，减少编码难度
         因为，字符串下标从0开始，dp[1][1]对应s和p的0，所以 s和p的n-1对应dp[n][n]
         */
        int m=s.length();
        int n=p.length();
        boolean [][]dp = new boolean[n+1][m+1];
        dp[0][0] = true;
        for (int i = 1; i <= n; i++) {
            if(p.charAt(i-1) !='*'){
                break;
            }
            dp[i][0] = true;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if(p.charAt(i-1)== '*'){
                    dp[i][j] = dp[i-1][j] || dp[i][j-1];
                }else if(p.charAt(i-1)=='?' || p.charAt(i-1)== s.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }
            }
        }
        return dp[n][m];
    }


    /**
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。<br>
     * <a herf='https://leetcode-cn.com/problems/trapping-rain-water/'>leetcode原题</a> <br>
     */
    public int trap(int[] height) {
        /*
         * 动态规划：
         * i的接水量取决于i左右两边的峰值，
         * 因为峰值决定水位线。两个峰值的最小值就是水位线
         * 那么用：
         * left[i]，表示i的最大左值，
         * right[i],表示i的最大右值。
         * 先找到每个i的最大左值，和最大右值
         * 然后就可以知道，i的接水量 s[i] = min(left[i], right[i])-height[i]
         * 累加每个i的接水量
         */
        int len = height.length;
        // 只有2个柱体，是没发接水的
        if(len < 3){
            return 0;
        }
        int res = 0;
        int[] left = new int[len];
        int[] right = new int[len];
        left[0] = height[0];
        right[len-1] = height[len-1];

        for (int i = 1; i < len; ++i) {
            left[i] = Math.max(left[i-1], height[i]);
        }
        for (int i = len-2; i >=0 ; --i) {
            right[i] = Math.max(right[i+1], height[i]);
        }
        for (int i = 0; i < len; i++) {
            res += Math.min(left[i], right[i]) -height[i];
        }
        return res;
    }


    /**
     * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
     * 例1：<br>
     * 输入: s = "(()"<br>
     * 输出: 2<br>
     * 解释: 最长有效括号子串是 "()"<br>
     */
    public static int longestValidParentheses(String s) {
        /*
        * 思路：用dp[i]表示以i结尾的有效括号长度，
        * 如果，i = ( ,dp[i]=0，
        * 如果，i= ) && i-1 =( , "()"结尾，得出方程 那么dp[i]=dp[i-2]+2
        * 如果 i = ）&& i-1=），"))"结尾，那么第 i-dp[i-1]-1个是 "("
        * 不理解画出图就行
        * 得出方程：dp[i] = dp[i-1]+dp[i-dp[i-1]-2]+2
        */
        int [] dp = new int[s.length()];
        int max = 0;
        for (int i = 1; i < s.length(); i++) {
            if(s.charAt(i) == ')'){
                if(s.charAt(i-1) == '('){
                    dp[i] =(i>=2 ? dp[i-2] :0)+2;
                }else if(i-dp[i-1]-1 >=0 && s.charAt(i-dp[i-1]-1) == '(' ){
                    dp[i] = dp[i-1]+ ((i-dp[i-1])>=2 ? dp[i-dp[i-1]-2] : 0) + 2;
                }
            }
            max = Math.max(max,dp[i]);
        }
        return max;
    }

    /**
     * 给定一个非负索引 rowIndex，返回「杨辉三角」的第 rowIndex 行。
     */
    public static List<Integer> getRow(int rowIndex) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if(j==0 || j==i){
                    row.add(1);
                }else {
                    row.add(res.get(i-1).get(j-1) + res.get(i-1).get(j));
                }
            }
            res.add(row);
        }

        return res.get(rowIndex);
    }


    /**
     * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。<br>
     * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。<br>
     * 每一行的第一个数和最后一个数都是1。<br>
     * 例1：<br>
     * 输入: numRows = 5<br>
     * 输出: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]<br>
     * <br>
     * 例2：<br>
     * 输入: numRows = 1<br>
     * 输出: [[1]]<br>
     */
    public static int[][] generate(int numRows) {
        // 1。找出数据关系：关系很明显，第i行的第j(j>1 & j<i-1)个数，等于第i-1行的第j-1的数+第j的数
        // 2。得出状态转移方程：用dp[i][j]表示第i行第j个数，于是dp[i][j]=dp[i-1][j-1]+dp[i-1][j]
        // 3。找到边界：dp[i][0]=1,dp[i][i]=1
        // 4。初始值：dp[0][0]=1,dp[i][0]=1,dp[i][i]=1
        int[][] dp = new int[numRows][numRows];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j <= i; j++) {
                if(j==0 || j==i){
                    dp[i][j] = 1;
                }else{
                    dp[i][j] = dp[i-1][j-1]+dp[i-1][j];
                }
            }
        }
        return dp;
    }

    public static List<List<Integer>> generateList(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if(j==0 || j==i){
                    row.add(1);
                }else {
                    row.add(res.get(i-1).get(j-1) + res.get(i-1).get(j));
                }
            }
            res.add(row);
        }

        return res;
    }




    /**
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。<br>
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？<br>
     * 例1：<br>
     * 输入：n = 2<br>
     * 输出：2<br>
     * 解释：有两种方法可以爬到楼顶。<br>
     * 1. 1 阶 + 1 阶<br>
     * 2. 2 阶<br>
     * 例2：<br>
     * 输入：n = 3<br>
     * 输出：3<br>
     * 解释：有三种方法可以爬到楼顶。<br>
     * 1. 1 阶 + 1 阶 + 1 阶<br>
     * 2. 1 阶 + 2 阶<br>
     * 3. 2 阶 + 1 阶<br>
     */
    public static int climbStairs(int n) {
        // 1。用f(x)表示第x阶的爬法数，那么最后一步可能爬了1阶，也可能爬了2阶，然后达到第x阶
        // 2。所以f(x)=f(x-1)+f(x-2)    【ps: Fibonacci数列？】
        // 3。边界n>1
        // 4。找到初始值，f(1)=1,f(2)=2,f(3)=3
        int f=0,y=0,r=1;
        for (int i = 1; i <= n; i++) {
            f=y;
            y=r;
            r=f+y;
        }
        return r;
    }

    /**
     * 最长回文串
     * <p>给你一个字符串 s，找到 s 中最长的回文子串</p>
     * <p>
     * 例1：
     * 输入：s = "babad",
     * 输出："bab",
     * 解释："aba"同样是符合题意的答案;
     * </p>
     * <p>
     * 时间复杂度：O(n*n)，空间复杂度O(n*n)
     * </p>
     */
    public static String longestPalindrome(String s){
        String ans = "";
        boolean[][] dp = new boolean[s.length()][s.length()];
        dp[0][0] = true;
        for(int dif=0 ; dif<s.length() ; ++dif){
            for(int i=0,j=i+dif ; j<s.length() ; ++i){
                if(dif==0){
                    dp[i][j] = true;
                }else if(dif==1){
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                }else {
                    dp[i][j] = dp[i+1][j-1] && s.charAt(i) == s.charAt(j);
                }

                if(dp[i][j] && dif+1>ans.length() ){
                    ans = s.substring(i, j+1);
                }
            }
        }
        return ans;
    }

    /**
     * 给你一个字符串s和一个字符规律p，请你来实现一个支持 '.'和'*'的正则表达式匹配。
     *
     * '.' 匹配任意单个字符;
     * '*' 匹配零个或多个前面的那一个元素
     * <p>例子1：
     * 输入：s = "aa" p = "a"，
     * 输出：false，
     * 解释："a" 无法匹配 "aa" 整个字符串。
     * </p>
     * <p>例子2：
     * 输入：s = "aa" p = "a*"，
     * 输出：true，
     * 解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素,
     * 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
     * </p>
     * 0 <= s.length<= 20
     * 0 <= p.length<= 30
     * s可能为空，且只包含从a-z的小写字母。
     * p可能为空，且只包含从a-z的小写字母，以及字符.和*。
     * 保证每次出现字符* 时，前面都匹配到有效的字符
     *
     * @param s 目标字符串
     * @param p 匹配格式
     * @return 是否匹配
     */
    public static boolean pattern(String s,String p){
        int pLen = p.length();
        int sLen = s.length();

        boolean[][] dp = new boolean[sLen+1][pLen+1];
        // dp[0][0]表示s和p为2个空字符串。此时要注意循环下标，而s、p的下标仍然从0开始
        dp[0][0] = true;
        for(int i=0;i<= sLen;++i){
            for (int j = 1; j <= pLen; j++) {
                // p的第j个字符为*，这里这么写，是因为题目说*前面必定有有效字符
                if(p.charAt(j-1)=='*'){
                    dp[i][j] = dp[i][j-2];
                    if(match(s,p,i,j-1)){
                        dp[i][j]=dp[i][j-2] || dp[i-1][j];
                    }
                }else {
                    if(match(s,p,i,j)){
                        dp[i][j]=dp[i-1][j-1];
                    }
                }
            }
        }
        return dp[sLen][pLen];
    }

    /**
     * 判断i和j的前一位是不是匹配
     */
    public static boolean match(String s,String p,int i,int j){
        if(i==0){
          return false;
        }
        if(p.charAt(j-1)=='.'){
            return true;
        }
        return s.charAt(i-1)==p.charAt(j-1);
    }

    public static void main(String[] args) {

    }


}
