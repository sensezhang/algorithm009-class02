package com.yonyou.letcode.recursion;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: zhangwbin
 * @Date: 2020/7/1
 */
public class Solution36 {

  Set<Character>[] row = new HashSet[9];
  Set<Character>[] col = new HashSet[9];
  Set<Character>[] cuw = new HashSet[9];

  public boolean isValidSudoku2(char[][] board) {
    for (int i = 0; i < 9; i++) {
      int row = 0;
      int col = 0;
      int box = 0;
      for (int j = 0; j < 9; j++) {
        int a = board[i][j] - '0';
        int b = board[j][i] - '0';
        int c = board[3 * (i / 3) + j / 3][3 * (i % 3) + j % 3] - '0';
        if (a > 0) {
          row = checkAndSet(a, row);
        }
        if (b > 0) {
          col = checkAndSet(b, col);
        }
        if (c > 0) {
          box = checkAndSet(c, box);
        }

        if (row == -1 || col == -1 || box == -1) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * 1 & 1 = 1； 0 & 1 = 0；
   * 1000 ^ 10000000 = 10001000
   * 判断n是否存在于value，如果存在返回-1，否则把n加入value
   * @param n
   * @param value
   * @return
   */
  private int checkAndSet(int n, int value) {
    return ((value >> n) & 1) == 1 ? -1 : (1 << n) ^ value;
  }

  public boolean isValidSudoku(char[][] board) {
    for (int i = 0; i < 9; i++) {
      row[i] = new HashSet<>();
      col[i] = new HashSet<>();
      cuw[i] = new HashSet<>();
    }
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        if (board[i][j] != '.') {
          if (!checkBoard(board, i, j)) {
            return false;
          }
        }
      }
    }
    return true;
  }

  private boolean checkBoard(char[][] board, int i, int j) {
    char num = board[i][j];
    if (!row[i].contains(num) && !col[j].contains(num) && !cuw[(i / 3) * 3 + j / 3].contains(num)) {
      row[i].add(num);
      col[j].add(num);
      cuw[(i / 3) * 3 + j / 3].add(num);
      return true;
    } else {
      return false;
    }
  }

  public static void main(String[] args) {
    char[][] board = new char[][]{
        {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
        {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
        {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
        {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
        {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
        {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
        {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
        {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
        {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
    Solution36 solution36 = new Solution36();
    System.out.println(solution36.isValidSudoku2(board));
  }
}
