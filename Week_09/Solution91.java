package com.yonyou.letcode;

/**
 * 动态规划解码
 * @Author: zhangwbin
 * @Date: 2020/6/23
 */
public class Solution91 {

  public int numDecodings(String s) {
    if (s.length() == 0 || s.charAt(0) == '0') {
      return 0;
    }
    int[] dp = new int[s.length() + 1];
    dp[0] = 1;
    char[] cs = s.toCharArray();
    for (int i = 0; i < cs.length; i++) {
      dp[i + 1] = cs[i] == '0' ? 0 : dp[i];
      if (i > 0 && (cs[i - 1] == '1' || (cs[i - 1] == '2' && cs[i] <= '6'))) {
        dp[i + 1] += dp[i - 1];
      }
    }
    return dp[cs.length];
  }

  public static void main(String[] args) {
    Solution91 solution91 = new Solution91();
    System.out.println(solution91.numDecodings("101"));
  }
}
