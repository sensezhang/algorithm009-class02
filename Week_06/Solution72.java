package com.yonyou.letcode;

/**
 * 编辑距离
 *     h o r s e
 *   0 1 2 3 4 5
 * r 1 1 2 2 3 4
 * o 2 2 1 2 3 4
 * s 3 3 2 2 2 3
 *
 * 如果相等dp[x+1][y+1] = Math.min(dp[x][y+1],Math.min(dp[x+1][y], dp[x][y] - 1)) + 1;
 * 如果不相等dp[x+1][y+1] = Math.min(dp[x][y+1],Math.min(dp[x+1][y], dp[x][y])) + 1;
 * @Author: zhangwbin
 * @Date: 2020/6/27
 */
public class Solution72 {

  public int minDistance(String word1, String word2) {
    int x = word1.length();
    int y = word2.length();
    int[][] dp = new int[y + 1][x + 1];
    if (x * y == 0) {
      return x + y;
    }
    for (int i = 0; i < y; i++) {
      dp[i][0] = i;
    }
    for (int i = 0; i < x; i++) {
      dp[0][i] = i;
    }
    for (int i = 0; i < y; i++) {
      for (int j = 0; j < x; j++) {
        if (word1.charAt(j) == word2.charAt(i)) {
          dp[i + 1][j + 1] = Math.min(dp[i][j + 1], Math.min(dp[i+1][j], dp[i][j] -1)) + 1;
        } else {
          dp[i + 1][j + 1] = Math.min(dp[i][j + 1], Math.min(dp[i+1][j], dp[i][j])) + 1;
        }
      }
    }
    return dp[y][x];
  }

  public static void main(String[] args) {
    Solution72 solution72 = new Solution72();
    int minDistance = solution72.minDistance("horse","ros");
    System.out.println(minDistance);
  }
}
