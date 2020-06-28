package com.yonyou.letcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 青蛙过河
 * [0,1,3,5,6,8,12,17]
 * 跳1个单位到第2块石子, 然后跳2个单位到第3块石子, 接着
 * 跳2个单位到第4块石子, 然后跳3个单位到第6块石子,
 * 跳4个单位到第7块石子, 最后，跳5个单位到第8个石子（即最后一块石子）。
 * @Author: zhangwbin
 * @Date: 2020/6/27
 */
public class Soution403 {

  public boolean canCross(int[] stones) {
    Map<Integer, Set<Integer>> map = new HashMap<>();
    for (int i = 0; i < stones.length; i++) {
      map.put(stones[i], new HashSet<>());
    }
    map.get(0).add(0);
    for (int i = 0; i< stones.length; i++) {
      for (int key : map.get(stones[i])) {
        for (int step = key - 1; step <= key + 1; step++) {
          if (step > 0 && map.containsKey(step + stones[i])) {
            map.get(step + stones[i]).add(step);
          }
        }
      }
    }
    return map.get(stones[stones.length - 1]).size() != 0;
  }

  public static void main(String[] args) {
    Soution403 soution403 = new Soution403();
    System.out.println(soution403.canCross(new int[]{0,1,3,5,6,8,12,17}));
  }
}
