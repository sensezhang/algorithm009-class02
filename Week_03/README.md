# 第三周

爬楼梯（阿里巴巴、腾讯、字节跳动在半年内面试常考）

括号生成 (字节跳动在半年内面试中考过)
翻转二叉树 (谷歌、字节跳动、Facebook 在半年内面试中考过)
验证二叉搜索树（亚马逊、微软、Facebook 在半年内面试中考过）
二叉树的最大深度（亚马逊、微软、字节跳动在半年内面试中考过）
二叉树的最小深度（Facebook、字节跳动、谷歌在半年内面试中考过）
二叉树的序列化与反序列化（Facebook、亚马逊在半年内面试常考）

如何优雅地计算斐波那契数列

- [x] 二叉树的最近公共祖先（Facebook 在半年内面试常考）
从前序与中序遍历序列构造二叉树（字节跳动、亚马逊、微软在半年内面试中考过）
- [x] 组合（微软、亚马逊、谷歌在半年内面试中考过）
- [x] 全排列（字节跳动在半年内面试常考）
- [x] 全排列 II （亚马逊、字节跳动、Facebook 在半年内面试中考过）

## 递归代码模板：
```java
 public void recur(int level, int param){
     // terminator
     if (level > MAX_LEVEL) {
         // process result;
         return;
     }

     // process current logic
     process(level, param);

    // drill down
    recur(level+1, param);

    //restore current status
 }
```

## n皇后问题
下图为判断皇后攻击范围的图，如图所示，假如皇后在(2, 2)位置，他能攻击到的列可以表示为x=2， 他能攻击到的所有的左对角线是 x+y = 4 的位置，同理可推出右对角线是Math.abs(x-y) = 0,还可以表示为 y - x + n  - 1 = 3.
![](_v_images/20200606104345299_11053.png =1024x)
```java

public class Solution51 {


  // 保存列上的位置是否被皇后攻击
  boolean col[] = null;
  // 保存左对角线上的位置是否被皇后攻击
  boolean left[] = null;、
  // 保存右对角线上位置是否被皇后攻击
  boolean right[] = null;
  // 返回值
  List<List<String>> result = new ArrayList<>();
  public List<List<String>> solveNQueens(int n) {
    // 初始化数组
    col = new boolean[n];
    left = new boolean[2 * n - 1];
    right = new boolean[2 * n - 1];
    // new一个二维数组的棋盘，这里使用char是为方便输出题目要求的字符串
    char[][] qipan = new char[n][n];
    // 调用递归方法
    digui(0, n, qipan);
    return result;
  }

  private void digui(int y, int n, char[][] qipan) {
  // terminator 终止条件为如果y坐标到达n，也就是说所有层级的皇后位置都找到了，使用list封装结果
    if (y >= n) {
      List<String> list = new ArrayList<>();
      for (int i = 0; i < n; i++) {
      // 将char数组输出为string，即...Q格式
        list.add(new String(qipan[i]));
      }
      result.add(list);
      return;
    }
    // process 填充'.'
    Arrays.fill(qipan[y], '.');
    // 遍历每一层的每一列，即X坐标轴从0开始
    for (int x = 0; x < n; x++) {
    // 如果该位置不被皇后攻击到
      if (!col[x] && !left[x + y] && !right[y - x + n - 1]) {
        // 设置皇后的位置
        qipan[y][x] = 'Q';
        // 设置皇后能攻击到的所有列
        col[x] = true;
        // 设置皇后能攻击到的所有左对角线
        left[x + y] = true;
        // 设置皇后能攻击到的所有右对角线
        right[y - x + n - 1] = true;
        // 继续递归下一行，即Y轴坐标+1
        digui(y + 1, n, qipan);
        // 如果递归完找不到皇后的位置，回溯->回退掉上一步设置的皇后位置及其影响力
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
```

## 全排列

```java
public class Solution46 {
  List<List<Integer>> result = new ArrayList<>();
  public List<List<Integer>> permute(int[] nums) {
    if (nums != null && nums.length != 0) {
      recur(0, new ArrayList<>(),  nums);
    }
    return result;
  }

  public void recur(int level, List<Integer> list, int[] nums) {
    // terminator
    if (level >= nums.length) {
      result.add(new ArrayList<>(list));
      return;
    }
    // process
    for (int i = 0; i < nums.length; i++) {
      if (!list.contains(nums[i])) {
        list.add(nums[i]);
        recur(level + 1, list, nums);
        list.remove(list.indexOf(nums[i]));
      }
    }
    // restore
  }

  public static void main(String[] args) {
    Solution46 solution46 = new Solution46();
    List<List<Integer>> result = solution46.permute(new int[]{1,2,3});
    System.out.println(result);
  }
}

```

## 全排列Ⅱ

```java
public class Solution47 {

  List<List<Integer>> result = new ArrayList<>();
  public List<List<Integer>> permuteUnique(int[] nums) {
    if (nums != null && nums.length != 0) {
      Arrays.sort(nums);
      recur(0,nums,new boolean[nums.length], new LinkedList<>());
    }
    return result;
  }

  private void recur(int level, int[] nums, boolean[] used, Deque<Integer> list) {
    if (level >= nums.length) {
      result.add(new ArrayList<>(list));
      return;
    }

    for (int i = 0; i < nums.length; i++) {
      if (used[i]) {
        continue;
      }

      // 剪枝条件：i > 0 是为了保证 nums[i - 1] 有意义
      // 写 !used[i - 1] 是因为 nums[i - 1] 在深度优先遍历的过程中刚刚被撤销选择
      if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
        continue;
      }
      list.addLast(nums[i]);
      used[i] = true;
      recur(level+1, nums, used, list);
      list.removeLast();
      used[i] = false;
    }
  }

  public static void main(String[] args) {
    Solution47 solution47 = new Solution47();
    List<List<Integer>> result = solution47.permuteUnique(new int[]{1,1,2});
    System.out.println(result);
  }
}
```

## 组合

```java
public class Solution77 {

  List<List<Integer>> result = new ArrayList<>();

  public List<List<Integer>> combine(int n, int k) {
    if (n < 1 || k < 1) {
      return result;
    }

    recur(1, k, n, new LinkedList<>());
    return result;
  }

  public void recur(int level, int k, int n, Deque<Integer> deque) {
    if (deque.size() == k) {
      result.add(new ArrayList<>(deque));
      return;
    }
    for (int i = level; i <= n; i++) {
      deque.add(i);
      recur(i + 1, k, n, deque);
      deque.removeLast();
    }
  }

  public static void main(String[] args) {
    Solution77 solution77 = new Solution77();
    List<List<Integer>> result = solution77.combine(4, 2);
    System.out.println(result);
  }
}

```

## 二叉树的最近公共祖先

```java
public class Solution236 {

  private TreeNode ans;

  public Solution236() {
    this.ans = null;
  }

  private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null){
      return false;
    }
    boolean lson = dfs(root.left, p, q);
    boolean rson = dfs(root.right, p, q);
    if ((lson && rson) || ((root.val == p.val || root.val == q.val) && (lson || rson))) {
      ans = root;
    }
    return (lson || rson) || (root.val == p.val || root.val == q.val);
  }

  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    this.dfs(root, p, q);
    return this.ans;
  }

  Map<TreeNode, TreeNode> parent = new HashMap<>();
  Set<TreeNode> visited = new HashSet<>();
  public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
    this.dfs2(root);
    while (p != null) {
      visited.add(p);
      p = parent.get(p);
    }
    while (q != null) {
      if (visited.contains(q)) {
        return q;
      }
      q = parent.get(q);
    }
    return this.ans;
  }

  /**
   * 把所有节点的父节点都保存下来
   * @param root
   */
  private void dfs2(TreeNode root) {
    if (root.left != null) {
      parent.put(root.left, root);
      dfs2(root.left);
    }

    if(root.right != null) {
      parent.put(root.right, root);
      dfs2(root.right);
    }
  }

  public static void main(String[] args) {
    TreeNode treeNode = TreeNodeUtil.buildTreeNode(new int[]{3,5,1,6,2,0,8,-1,-1,7,4});
    Solution236 solution236 = new Solution236();
    TreeNode result = solution236.lowestCommonAncestor(treeNode, new TreeNode(5), new TreeNode(1));
    System.out.println(result.val);
    System.out.println(solution236.lowestCommonAncestor2(treeNode, new TreeNode(5), new TreeNode(1)).val);
  }
}
```