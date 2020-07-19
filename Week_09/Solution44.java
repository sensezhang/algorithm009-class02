package com.yonyou.letcode.dp;

/**
 * 通配符匹配
 * @Author: zhangwbin
 * @Date: 2020/7/5
 */
public class Solution44 {

  public boolean isMatch(String s, String p) {
    int x = s.length();
    int y = p.length();
    boolean[][] dp = new boolean[y + 1][x + 1];
    dp[0][0] = true;
    // 初始化，如果开头有*，匹配字符串直到其中一个不是字符串
    for (int i = 1; i <= y; i++) {
      dp[i][0] = dp[i - 1][0] && p.charAt(i - 1) == '*';
    }
    for (int i = 0; i < y; i++) {
      for (int j = 0; j < x; j++) {
        // 如果s == p或者i=='?' 看上一个字母匹配情况
        if (s.charAt(j) == p.charAt(i) || p.charAt(i)=='?') {
          dp[i + 1][j + 1] = dp[i][j];
        }
        // 如果p的当前字符为*，看上边（*匹配的是空字符串）或者左边的匹配情况（*匹配的多个字符串情况）
        if (p.charAt(i) == '*') {
          dp[i + 1][j + 1] = dp[i + 1][j] || dp[i][j + 1];
        }
      }
    }

    return dp[y][x];
  }

  public static void main(String[] args) {
    Solution44 solution44 = new Solution44();
    solution44.isMatch("adceb", "*a*b");
  }
}
