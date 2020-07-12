package com.yonyou.letcode.sort;

import java.util.Arrays;

/**
 * 选择排序 每次选择最小的 时间复杂度O(n^2)
 * @Author: zhangwbin
 * @Date: 2020/7/6
 */
public class SelectionSort {

  public static void main(String[] args) {
    SelectionSort selectionSort = new SelectionSort();
    int[] arrs = new int[]{7,3,1,5,9,6};
    selectionSort.sort(arrs);
    System.out.println(Arrays.toString(arrs));
  }

  private void sort(int[] arrs) {
    int len = arrs.length;

    for (int i = 0; i < len; i++) {
      int minIndex = i;
      for (int j = i; j < len; j++) {
        if (arrs[j] < arrs[minIndex]) {
          minIndex = j;
        }
      }
      int tmp = arrs[minIndex];
      arrs[minIndex] = arrs[i];
      arrs[i] = tmp;
    }
  }
}
