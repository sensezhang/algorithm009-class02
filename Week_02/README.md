- [x] 有效的字母异位词（亚马逊、Facebook、谷歌在半年内面试中考过）
Solution
```java
import java.util.HashMap;

/**
 * 异位词
 *
 * @Author: zhangwbin
 * @Date: 2020/5/25
 */
public class Solution242 {

  public boolean isAnagram(String s, String t) {
    if (s.length() != t.length()) {
      return false;
    }
    // 暴力求解 时间复杂度nlogn
    /*char[] a = s.toCharArray();
    char[] b = t.toCharArray();
    Arrays.sort(a);
    Arrays.sort(b);
    return Arrays.equals(a, b);*/
    // hash 空间复杂度O(n)时间复杂度O(n)
    // 使用map保存每个字母在第一个单词中出现的次数，第二个单词通过减hashmap的value，减为0就remove，如果最终hashmap为空，说明是异位词
    HashMap<Character, Integer> map = new HashMap();
    char[] a = s.toCharArray();
    for (int i = 0; i < s.length(); i++) {
      if (map.containsKey(a[i])) {
        map.put(a[i], map.get(a[i]) + 1);
      } else {
        map.put(a[i], 1);
      }
    }
    char[] b = t.toCharArray();
    for (int i = 0; i < t.length(); i++) {
      if (map.containsKey(b[i])) {
        map.put(a[i], map.get(a[i]) - 1);
      } else {
        return false;
      }
      if (map.get(a[i]) == 0) {
        map.remove(a[i]);
      }
    }
    return map.size() == 0;
  }

  public static void main(String[] args) {
    String s = "anagram";
    String t = "nagaram";
    Solution242 solution242 = new Solution242();
    System.out.println(solution242.isAnagram(s, t));
    s = "rat";
    t = "car";
    System.out.println(solution242.isAnagram(s, t));

  }
}

```
- [x] 字母异位词分组（亚马逊在半年内面试中常考）
Solution49
```java

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: zhangwbin
 * @Date: 2020/5/25
 */
public class Solution49 {
  public List<List<String>> groupAnagrams(String[] strs) {
    // 暴力 时间复杂度Knlogn
   /* if(strs == null || strs.length == 0) return new ArrayList<>();
    Map<String, List<String>> map = new HashMap<>();
    for (int i = 0; i< strs.length; i++) {
      String str = strs[i];
      char[] chars = str.toCharArray();
      Arrays.sort(chars);
      String key = Arrays.toString(chars);
      if (map.containsKey(key)) {
        map.get(key).add(str);
      } else {
        List<String> list = new ArrayList<>();
        list.add(str);
        map.put(key, list);
      }
    }
    return new ArrayList<>(map.values());*/
    // hash计数法,时间复杂度n*k
    if(strs == null || strs.length == 0) return new ArrayList<>();
    Map<String, List<String>> map = new HashMap<>();

    for (int i = 0; i< strs.length; i++) {
      String str = strs[i];
      char[] chars = str.toCharArray();
      int[] tmp = new int[26];
      for(int j = 0; j < chars.length; j ++) {
        int index = chars[j] - 'a';
        tmp[index]++;
      }
      String key = Arrays.toString(tmp);
      if (map.containsKey(key)) {
        map.get(key).add(str);
      } else {
        List<String> list = new ArrayList<>();
        list.add(str);
        map.put(key, list);
      }
    }
    return new ArrayList<>(map.values());
  }

  public static void main(String[] args) {
    Solution49 solution49 = new Solution49();
    String[] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
    List<List<String>> lists = solution49.groupAnagrams(strs);
    System.out.println(lists);

  }
}

```
- [x] 两数之和（亚马逊、字节跳动、谷歌、Facebook、苹果、微软、腾讯在半年内面试中常考）
Solution1
```java
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhangwbin
 * @Date: 2020/5/14
 */
public class Solution1 {

  public int[] twoSum(int[] nums, int target) {
    /*// 暴力求解, 时间复杂度n^2
    for (int i = 0; i < nums.length - 1; i++) {
      int m = nums[i];
      for (int j = i + 1; j < nums.length; j++) {
        int n = nums[j];
        if (m + n == target) {
          return new int[]{i, j};
        }
      }
    }
    return null;*/
    // hash法 时间复杂度O(n),空间复杂度O(n)
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i< nums.length; i++) {
      int m = target - nums[i];
      if (map.containsKey(m)) {
        return new int[]{map.get(m), i};
      }
      map.put(nums[i], i);
    }
    return null;
  }

  public static void main(String[] args) {
    int[] nums = new int[]{3, 2, 4};
    Solution1 solution1 = new Solution1();
    int[] result = solution1.twoSum(nums, 6);
    System.out.println(Arrays.toString(result));
  }

}

```
- [x] N 叉树的前序遍历（亚马逊在半年内面试中考过）Solution
- [x] 二叉树的中序遍历（亚马逊、字节跳动、微软在半年内面试中考过）Solution94
前序遍历顺序 根->左->右  
中序遍历顺序 左->根->右
后序遍历顺序 左->右->根
中序遍历也是两种解法，迭代和递归
```java
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
    // 迭代法
    List<Integer> result =  new ArrayList<>();
    if (root == null) {
      return result;
    }
    Stack<TreeNode> stack = new Stack<>();
    TreeNode cur = root;
    while (!stack.isEmpty() || cur != null) {
      while (cur != null) {
        stack.push(cur);
        cur = cur.left;
      }
      TreeNode tmp = stack.pop();
      result.add(tmp.val);
      cur = tmp.right;
    }
    return result;
  }
}
``` 

迭代法：
```java

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

```
- [x] 二叉树的前序遍历（字节跳动、谷歌、腾讯在半年内面试中考过）Solution
- [x] N 叉树的层序遍历（亚马逊在半年内面试中考过）Solution429

两种方法解决，第一种迭代法,使用额外的双端队列内存空间，相当于手动的维护一个栈，空间复杂度O(n)
，时间复杂度O(n)
```java
import java.util.*;

class Solution429{ 
    public List<List<Integer>> levelOrder(Node root) {
     List<List<Integer>> result = new ArrayList<>();
        Deque<Node> deque = new LinkedList<>();
        deque.push(root);
        while (!deque.isEmpty()) {
          List<Integer> level = new ArrayList<>();
          int size = deque.size();
          for (int i = 0; i < size; i++){
            Node node = deque.pop();
            level.add(node.val);
            if (node.children != null) {
              deque.addAll(node.children);
            }
          }
          result.add(level);
        }
        return result;
    }
}
```

第二种解法，使用递归根据level填充数据，把level带到递归的每一层中

```java
import java.util.*;

public class Solution429 {
  List<List<Integer>> result = new ArrayList<>();
  public List<List<Integer>> levelOrder(Node root) {
    traverseNode(root, 0);
    return result;
  }

  private void traverseNode(Node root, int level) {
    // 结束条件
    if (root == null) {
      return;
    }
    // 如果size比当前level小或等，就创建新的数组，方便后续添加数据
    if (result.size() <= level) {
      result.add(new ArrayList<>());
    }
    // 根据层级设置val值
    result.get(level++).add(root.val);
    List<Node> childrens = root.children;
    if (childrens != null) {
      for (int i = 0; i < childrens.size(); i++) {
        traverseNode(childrens.get(i), level);
      }
    }
  }
  public static void main(String[] args) {
    List<Node> children1 = new ArrayList<>();
    List<Node> children2 = new ArrayList<>();
    children2.add(new Node(5));
    children2.add(new Node(6));
    children1.add(new Node(3, children2));
    children1.add(new Node(2));
    children1.add(new Node(4));
    Node node = new Node(1, children1);
    Solution429 solution429 = new Solution429();
    List<List<Integer>> list = solution429.levelOrder(node);
    System.out.println(list);
  }
}

```
- [ ] 丑数（字节跳动在半年内面试中考过）Solution
- [ ] 前 K 个高频元素（亚马逊在半年内面试中常考）Solution
- [x] 最小的 k 个数（字节跳动在半年内面试中考过）Solution 使用优先队列解决
- [ ] 滑动窗口最大值（亚马逊在半年内面试中常考）Solution