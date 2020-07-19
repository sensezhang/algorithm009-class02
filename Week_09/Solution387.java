package com.yonyou.letcode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhangwbin
 * @Date: 2020/7/19
 */
public class Solution387 {
  public int firstUniqChar(String s) {
    Map<Character, Integer> map = new HashMap<>();
    char[] schar = s.toCharArray();
    // 计数所有的字符
    for (int i = 0; i < schar.length; i++) {
      map.put(schar[i], map.getOrDefault(schar[i], 0) + 1);
    }
    // 从0枚举，查看map中计算的数量，碰到第一个为1的就返回索引
    for (int i = 0; i < schar.length; i++) {
      if (map.get(schar[i]) == 1) {
        return i;
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    Solution387 solution387 = new Solution387();
    int i = solution387.firstUniqChar("loveleetcode");
    System.out.println(i);
  }
}
