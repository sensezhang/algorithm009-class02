package com.yonyou.letcode.string;

/**
 * @Author: zhangwbin
 * @Date: 2020/7/19
 */
public class Solution541 {
  public String reverseStr(String s, int k) {
    char[] sc = s.toCharArray();

    for (int i = 0; i < sc.length; i += 2 * k) {
      int left = i; int right = Math.min(left + k -1, sc.length - 1);
      while (left < right) {
        char tmp = sc[left];
        sc[left++] = sc[right];
        sc[right--] = tmp;
      }
    }

    return new String(sc);
  }

  public static void main(String[] args) {
    Solution541 solution541 = new Solution541();
    String result = solution541.reverseStr("abcdefg", 2);
    System.out.println(result);
  }
}
