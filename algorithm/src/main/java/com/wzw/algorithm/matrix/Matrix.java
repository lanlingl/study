package com.wzw.algorithm.matrix;

/**
 * 矩阵
 * @author wuzhiwei
 * @team M
 * @owner wuzhiwei
 * @Date 2023/1/31
 */
public class Matrix {


    /**
     * 矩阵顺时针旋转90度
     */
    public static void rotate(int[][] matrix) {
        //1. 通过画图和列举，可以得出转移的规律：matrix[i][j] -> matrix[j][n-i-1]。在用一个辅助的二维数组，把转移到目标位置数据记录一下。
        // 但是要求原地旋转，所以不能直接用另一个二位数组来辅助
        // 2. 先对角线交换再中轴线交换（比如：左上、右下交换，整体上下交换）
        // 3.经过下面4次复制，就能完成一圈的旋转，此时我们用一个tmp就可以完成这次旋转
        // matrix[i][j] -> matrix[j][n-i-1]，
        // matrix[j][n-i-1] -> matrix[n-i-1][n-j-1],
        // matrix[n-i-1][n-j-1] -> matrix[n-j-1][i],
        // matrix[n-j-1][i] -> matrix[i][j]
        // 要找到原地旋转的元素，i<n/2 ,j<(n+1)/2，j取(n+1)/2是因为当n为奇数是，最中心点一个
        // 元素不用交换，当然交换也没问题，就是无用的计算。

        // 方案3的代码
        int n = matrix.length;
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < (n + 1) / 2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[n-j-1][i];
                matrix[n-j-1][i] = matrix[n-i-1][n-j-1];
                matrix[n-i-1][n-j-1] = matrix[j][n-i-1];
                matrix[j][n-i-1] = tmp;
            }
        }


    }



}
