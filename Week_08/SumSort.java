package com.yonyou.letcode.sort;

import java.util.Arrays;

/**
 * @Author: zhangwbin
 * @Date: 2020/7/10
 */
public class SumSort {
  public static void main(String[] args) {
    SumSort sumSort = new SumSort();
    int[] arrs = new int[]{5,2,3,1};
    sumSort.sort(arrs);
    System.out.println(Arrays.toString(arrs));
  }

  private void sort(int[] arrs) {
    int[] a = new int[100000];
    for (int i = 0; i < arrs.length; i++) {
      int b = arrs[i];
      a[b + 50000]++;
    }
    int cur = 0;
    for (int i = 1; i < a.length; i++) {
      while(a[i] > 0) {
        arrs[cur++] = i - 50000;
        a[i]--;
      }
    }

  }
}
