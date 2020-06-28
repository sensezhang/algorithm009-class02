package com.yonyou.letcode;

/**
 * 动态规划解码
 * @Author: zhangwbin
 * @Date: 2020/6/23
 */
public class Solution91 {

  public int numDecodings(String s) {
    int[] dp = new int[s.length() + 1];
    if (s.length() == 0 || s.charAt(0) == '0') {
      return 0;
    }

    dp[0] = 1;
    for (int i = 0; i < s.length(); i++) {
      dp[i + 1] = s.charAt(i) == '0' ? 0 : dp[i];
      if (i > 0 && (s.charAt(i - 1) == '1' || (s.charAt(i - 1) == '2' && s.charAt(i) <= '6'))) {
        dp[i + 1] += dp[i - 1];
      }
    }
    return dp[s.length()];
  }

  public static void main(String[] args) {
    Solution91 solution91 = new Solution91();
    System.out.println(solution91.numDecodings("101"));
  }
}
