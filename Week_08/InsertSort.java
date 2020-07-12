package com.yonyou.letcode.sort;

import java.util.Arrays;

/**
 * 插入排序 从第一个位置插入 时间复杂度O(n)
 * @Author: zhangwbin
 * @Date: 2020/7/6
 */
public class InsertSort {

  public static void main(String[] args) {
    InsertSort insertSort = new InsertSort();
    int[] arrs = new int[]{7,3,1,5,9,6};
    insertSort.sort(arrs);
    System.out.println(Arrays.toString(arrs));
  }

  private void sort(int[] arrs) {
    int len = arrs.length;
    for (int i = 1; i < len; i++) {
      int t = arrs[i];
      int j = i - 1;
      while (j >= 0) {
        if (t < arrs[j]) {
          arrs[j + 1] = arrs[j];
        } else {
          break;
        }
        j--;
      }
      arrs[j + 1] = t;
    }
  }

}
