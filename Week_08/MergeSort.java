package com.yonyou.letcode.sort;

import java.util.Arrays;

/**
 * @Author: zhangwbin
 * @Date: 2020/7/6
 */
public class MergeSort {

  public static void main(String[] args) {
    MergeSort mergeSort = new MergeSort();
    int[] arrs = new int[]{5,2,3,1};
    mergeSort.sort(arrs);
    System.out.println(Arrays.toString(arrs));
  }

  private void sort(int[] arrs) {
    int left = 0; int right = arrs.length;
    sort(arrs, left, right - 1);
  }

  private void sort(int[] arrs, int left, int right) {
    if (left >= right) {
      return;
    }
    int mid = left + ((right - left) >> 1);
    sort(arrs, left, mid);
    sort(arrs, mid + 1, right);
    mergeSort(arrs, left, right, mid);
  }

  private void mergeSort(int[] arrs, int left, int right, int mid) {
    int[] tmp = new int[right - left + 1];
    int index = 0;
    int i = left;
    int j = mid + 1;
    // 合并两个有序数组
    while (i <= mid && j <= right) {
      tmp[index++] = arrs[i] <= arrs[j] ? arrs[i++] : arrs[j++];
    }
    while (i <= mid) {
      tmp[index++] = arrs[i++];
    }
    while (j <= right) {
      tmp[index++] = arrs[j++];
    }
    System.arraycopy(tmp, 0, arrs, left, tmp.length);
  }
}
