package com.yonyou.letcode.sort;

import java.util.Arrays;

/**
 * 冒泡排序  两两比较 时间复杂度n^2
 * @Author: zhangwbin
 * @Date: 2020/7/6
 */
public class MaopaoSort {

  public void sort(int[] arrs) {
    int len = arrs.length;
    for (int i = 0; i < len; i++) {
      for (int j = 0; j < len - i - 1; j++) {
        if(arrs[j] > arrs[j + 1]) {
          int tmp = arrs[j];
          arrs[j] = arrs[j + 1];
          arrs[j + 1] = tmp;
        }
      }
    }
  }

  public static void main(String[] args) {
    MaopaoSort maopaoSort = new MaopaoSort();
    int[] arrs = new int[]{7,3,1,5,9,6};
    maopaoSort.sort(arrs);
    System.out.println(Arrays.toString(arrs));
  }
}
