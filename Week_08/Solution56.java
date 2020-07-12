package com.yonyou.letcode.sort;

import java.util.Arrays;

/**
 * @Author: zhangwbin
 * @Date: 2020/7/10
 */
public class Solution56 {
  public int[][] merge(int[][] intervals) {
    int[][] result = new int[intervals.length][2];
    Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
    int i = 0;
    // 记录合并的次数
    int count = 0;
    int index = 0;
    while (i <= intervals.length - 1) {
      while (i < intervals.length - 1 && !(intervals[i][0] > intervals[i + 1][1] || intervals[i][1] < intervals[i + 1][0])) {
        intervals[i + 1][0] = Math.min(intervals[i][0], intervals[i + 1][0]);
        intervals[i + 1][1] = Math.max(intervals[i][1], intervals[i + 1][1]);
        count++;
        i++;
      }
      result[index] = intervals[i];
      index++;
      i++;
    }
    int[][] r = new int[intervals.length - count][2];
    System.arraycopy(result, 0, r, 0, intervals.length - count);
    return result;
  }

  public static void main(String[] args) {
    Solution56 solution56 = new Solution56();
    solution56.merge(new int[][]{{2,3},{2,2},{3,3},{1,3},{5,7},{2,2},{4,6}});

  }
}
