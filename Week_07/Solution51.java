package com.yonyou.letcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: zhangwbin
 * @Date: 2020/6/5
 */
public class Solution51 {


  boolean col[] = null;
  boolean left[] = null;
  boolean right[] = null;
  List<List<String>> result = new ArrayList<>();
  public List<List<String>> solveNQueens(int n) {
    col = new boolean[n];
    left = new boolean[2 * n - 1];
    right = new boolean[2 * n - 1];
    char[][] qipan = new char[n][n];
    digui(0, n, qipan);
    return result;
  }

  private void digui(int y, int n, char[][] qipan) {
    if (y >= n) {
      List<String> list = new ArrayList<>();
      for (int i = 0; i < n; i++) {
        list.add(new String(qipan[i]));
      }
      result.add(list);
      return;
    }
    // process
    Arrays.fill(qipan[y], '.');
    for (int x = 0; x < n; x++) {
      if (!col[x] && !left[x + y] && !right[y - x + n - 1]) {
        qipan[y][x] = 'Q';
        col[x] = true;
        left[x + y] = true;
        right[y - x + n - 1] = true;
        digui(y + 1, n, qipan);
        col[x] = false;
        left[x + y] = false;
        right[y - x + n - 1] = false;
        qipan[y][x] = '.';
      }
    }
  }


  public static void main(String[] args) {
    Solution51 solution51 = new Solution51();
    List<List<String>> resu = solution51.solveNQueens(8);
    System.out.println(resu);
  }
}
