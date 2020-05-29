package com.yonyou.letcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.TreeMap;

/**
 * @Author: zhangwbin
 * @Date: 2020/5/29
 */
public class Solution94 {
  List<Integer> result =  new ArrayList<>();
  /**
   * 中序遍历
   * @param root
   * @return
   */
  public List<Integer> inorderTraversal(TreeNode root) {
//    // 迭代法
//    List<Integer> result =  new ArrayList<>();
//    if (root == null) {
//      return result;
//    }
//    Stack<TreeNode> stack = new Stack<>();
//    TreeNode cur = root;
//    while (!stack.isEmpty() || cur != null) {
//      while (cur != null) {
//        stack.push(cur);
//        cur = cur.left;
//      }
//      TreeNode tmp = stack.pop();
//      result.add(tmp.val);
//      cur = tmp.right;
//    }
//    return result;

    // 递归法
    inorder(root);
    return result;
  }

  public void inorder(TreeNode root){
    // 结束条件
    if (root == null){
      return;
    }
    // 递归左节点
    inorder(root.left);
    // 递归根节点
    result.add(root.val);
    // 递归右节点
    inorder(root.right);
  }

  public static void main(String[] args) {
    TreeNode treeNode = new TreeNode(1);
    treeNode.right = new TreeNode(2);
    treeNode.right.left = new TreeNode(3);
    Solution94 solution94 = new Solution94();
    List<Integer> list = solution94.inorderTraversal(treeNode);
    System.out.println(list);
  }
}
