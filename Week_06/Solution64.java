package com.yonyou.letcode;

/**
 * 最小路径和
 * @Author: zhangwbin
 * @Date: 2020/6/27
 */
public class Solution64 {

  public int minPathSum(int[][] grid) {
    int row = grid.length;
    int col = grid[0].length;
    int[][] dp = new int[row + 1][col + 1];
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (i == 0 && j != 0) {
          dp[i + 1][j + 1] = dp[i + 1][j] + grid[i][j];
        } else if (i != 0 && j == 0) {
          dp[i + 1][j + 1] = dp[i][j + 1] + grid[i][j];
        } else {
          dp[i + 1][j + 1] = Math.min(dp[i][j+1], dp[i + 1][j]) + grid[i][j];
        }
      }
    }
    return dp[row][col];
  }

  public static void main(String[] args) {
    int[][] grid = new int[][]{{1,3,1},{1,5,1},{4,2,1}};
    Solution64 solution64 = new Solution64();
    int minPathSum = solution64.minPathSum(grid);
    System.out.println(minPathSum);
  }

}
