package com.yonyou.letcode.trie;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 单词查找Ⅱ
 *
 * @Author: zhangwbin
 * @Date: 2020/6/29
 */
public class Solution212 {

  // 二维数组中上下左右遍历
  int[] col = new int[]{0, 0, -1, 1};
  int[] row = new int[]{-1, 1, 0, 0};
  Set<String> result = new HashSet<>();
  Set<String> distict = new HashSet<>();
  public List<String> findWords(char[][] board, String[] words) {
    // 先把所有单词转换为字典树
    TrieNode trieNode = new TrieNode();
    for (int i = 0; i < words.length; i++) {
      trieNode.insert(words[i]);
      distict.add(words[i]);
    }

    // 遍历二维数组
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        // 如果字母在字典树中存在，进入递归
        if (trieNode.containsKey(board[i][j])) {
          dfs(trieNode, board, "", i, j);
        }
      }
    }
    return new ArrayList<>(result);
  }

  private void dfs(TrieNode trie, char[][] board, String str, int i, int j) {
    trie = trie.getKey(board[i][j]);
    str += board[i][j];
    // 终止条件
    if (trie.isEnd()) {
      result.add(str);
      trie.setEnd(false);
    }
    char tmp = board[i][j];
    board[i][j] = '@';
    for (int m = 0; m < 4; m++) {
      int y = i + col[m];
      int x = j + row[m];
      if ((x >= 0 && y >= 0 && y < board.length && x < board[0].length) && board[y][x] != '@'
          && trie.containsKey(board[y][x])) {
        dfs(trie, board, str, y, x);

      }
    }
    board[i][j] = tmp;
  }

  /**
   * 实现字典树
   */
  class TrieNode {

    int R = 26;
    TrieNode[] tries = null;
    boolean isEnd;

    public TrieNode() {
      tries = new TrieNode[R];
    }

    public TrieNode getKey(char key) {
      return tries[key - 'a'];
    }

    private void putKey(char key, TrieNode trie) {
      tries[key - 'a'] = trie;
    }

    private boolean containsKey(char key) {
      return tries[key - 'a'] != null;
    }

    public boolean isEnd() {
      return isEnd;
    }

    public void setEnd(boolean end) {
      isEnd = end;
    }

    public void insert(String word) {
      TrieNode trieNode = this;
      for (int i = 0; i < word.length(); i++) {
        char a = word.charAt(i);
        if (trieNode.containsKey(a)) {
          trieNode = trieNode.getKey(a);
        } else {
          TrieNode next = new TrieNode();
          trieNode.putKey(a, next);
          trieNode = next;
        }
      }
      trieNode.setEnd(true);
    }
  }

  public static void main(String[] args) {
    Solution212 solution212 = new Solution212();
    char[][] board = new char[][]{{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'},
        {'i', 'f', 'l', 'v'}};
    String[] words = new String[]{"oath", "pea", "eat", "rain","eate"};
    List<String> r = solution212.findWords(board, words);
    System.out.println(r);
  }
}
