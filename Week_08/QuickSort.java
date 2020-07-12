package com.yonyou.letcode.sort;

import java.util.Arrays;

/**
 * @Author: zhangwbin
 * @Date: 2020/7/10
 */
public class QuickSort {

  public static void main(String[] args) {
    QuickSort quickSort = new QuickSort();
    int[] arrs = new int[]{7,3,1,5,9,6};
    quickSort.sort(arrs);
    System.out.println(Arrays.toString(arrs));
  }

  private void sort(int[] arrs) {
    sort(arrs, 0, arrs.length - 1);
  }

  private void sort(int[] arrs, int left, int right) {
    if (left > right) {
      return;
    }
    int i = left;
    int j = right;
    int index = arrs[i];
    while (i < j) {
      // 所有右边的元素都比index大
      while (i < j && arrs[j] >= index) {
        j--;
      }
      if (i < j) {
        arrs[i++] = arrs[j];
      }
      // 所有左边的元素都比index小
      while (i < j && arrs[i] < index) {
        i++;
      }
      if (i < j) {
        arrs[j--] = arrs[i];
      }
    }
    arrs[i] = index;
    // 对左边元素快排
    sort(arrs, left, i - 1);
    // 对右边元素快排
    sort(arrs, i + 1, right);
  }
}
