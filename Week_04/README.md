# 第四周
## 广度优先搜索
一层一层往下搜索，比较符合人脑的处理逻辑，一般通过一个队列来一层层的遍历，将下一层的所有节点都加入到队列中，如果队列不为空，就继续从队列中取数，直到队列中没有数就结束了。缩写叫bfs
## 深度优先搜索
比较符合机器的执行逻辑，一条道走到黑，发现没有节点了，就回退到上个节点，继续找，计算机内部维护了一个方法的调用栈，通过方法调用栈进到树的最深处，再一层层返回。深度优先搜索一般都是通过递归来实现，也可以通过手动维护一个栈来迭代实现，算法的时间复杂度为O(n),空间复杂度为O(n),缩写是dfs。

## 贪心算法
贪心算法是一种在每一步选择中都采取在当前状态下最好或最优(即最有利)的选择，从而希望导致结果是全局最好或最优的算法。
贪心算法与动态规划的不同在于它对于每个子问题的解决方案都做出选择，不能回退。动态规划则会保存以前的运算结果，并根据以前的结果对当前进行选择，有回退功能。

## 二分查找
前提：
    1.目标函数单调性（单调递增或者单调递减）
    2.存在上下界（bounded）
    3.能够通过索引访问（index accessible）
## dfs模板
```java
class Dfs{
    List<Integer> list = null;
    public Dfs(){
        list = new ArrayList<>();
    }
    public int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[]{};
        }
        list.add(root.val);
        
        // process current node here.
        // ......
        List<TreeNode> childrens = root.children;
        for (int i = 0; i < chidrens.size(); i++) {
            dfs(chidrens.get(i));
        }
        return Arrays.toArray(list);
    }
}
```

## 单词接龙Ⅱ

dfs,一条路走到黑，发现不对再回溯，递归深度短的时候还行，长的话就超时，用例超时，代码如下：
```json
/**
 * @Author: zhangwbin
 * @Date: 2020/6/10
 */
public class Solution126 {
  List<List<String>> result = new ArrayList<>();
  Set<String> visited = new HashSet<>();
  int minLength = Integer.MAX_VALUE;
  public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
    if (!wordList.contains(endWord)) {
      return result;
    }
    Map<String, List<String>> wordListMap = new HashMap<>();
    for (int i = 0; i < wordList.size(); i++) {
      for (int j = 0; j < beginWord.length(); j++) {
        String key = wordList.get(i).substring(0, j) + "*" + wordList.get(i).substring(j +1);
        if (wordListMap.containsKey(key)) {
          wordListMap.get(key).add(wordList.get(i));
        } else {
          List<String> list = new ArrayList<>();
          list.add(wordList.get(i));
          wordListMap.put(key, list);
        }
      }
    }

    dfs(0, beginWord, endWord, wordListMap, new LinkedList<>());
    return result;
  }

  private void dfs(int level, String beginWord, String endWord, Map<String, List<String>> wordListMap, Deque<String> deque) {
    if (beginWord.equals(endWord)) {
      deque.addLast(beginWord);
      if (minLength >= deque.size()) {
        if (minLength > deque.size()) {
          result.clear();
          minLength = deque.size();
        }
        result.add(new ArrayList<>(deque));
      }
      return;
    }
    if (deque.size() == level) {
     deque.addLast(beginWord);
     visited.add(beginWord);
      for (int i = 0; i < beginWord.length(); i++) {
        String key = beginWord.substring(0, i) + "*" + beginWord.substring(i + 1);
        if (wordListMap.containsKey(key)) {
          List<String> list = wordListMap.get(key);
          for (String str : list) {
             if (!visited.contains(str)) {
               dfs(level + 1, str, endWord, wordListMap, deque);
               visited.remove(deque.removeLast());
             }
          }
        }
      }
    }
    return;
  }

  public static void main(String[] args) {
    Solution126 solution126 = new Solution126();
    String beginWord = "hit";
    String endWord = "cog";
    List<String> wordList = Arrays.asList(new String[]{"hot","dot","dog","lot","log","cog"});
    List<List<String>> result = solution126.findLadders(beginWord, endWord, wordList);
    System.out.println(result);
  }
}
```

使用bfs解决，一层一层往下找，如果在这一层找到了结果，就结束，不再往下找了，代码实现有困难。。。。


## 二分查找代码模板

```java
int left = 0;
int right = arr.length -1;
while (left <= right) {
    int mid = (left + right)/2;
    if (arr[mid] == target) {
        return mid;
    } else if (arr[mid] > target) {
        right = mid - 1;
    } else {
        left = mid + 1;
    }
}
```

## 寻找一个半有序数组中间无序的位置
```java

/**
 * 寻找旋转点
 * @Author: zhangwbin
 * @Date: 2020/6/11
 */
public class FindTurnIndex {

  public int findTurnIndex(int[] nums) {
    // 如果数组为空或最左边的元素小于等于最右边的元素，说明数组是单调递增的，或者只有一个元素，没有找到旋转的位置，返回-1
    if (nums == null || nums[0] <= nums[nums.length - 1]) {
      return -1;
    }
    int left = 0;
    int right = nums.length - 1;
    // 开始二分查找
    while (left <= right) {
      int mid = (left + right) / 2;
      // 如果中间元素位置是0，说明数组最多只有2个元素，如果右边元素比左边元素小，说明右边元素是旋转点
      if (mid == 0 && nums[mid + 1] < nums[mid]) {
        return mid + 1;
      }
      // 因为二分查找只能接近左临界点，右临界点不会到头，所以可以直接判断如果中间大于左边，
      // 并且中间大于右边，说明中间这个位置是数组最大值的位置，旋转点是mid+1
      else if ((mid - 1 >= 0 && nums[mid] > nums[mid - 1]) && (mid + 1 <= nums.length - 1
          && nums[mid] > nums[mid + 1])) {
        return mid + 1;
      } else if (nums[0] < nums[mid]) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    FindTurnIndex findTurnIndex = new FindTurnIndex();
    int[] nums = new int[]{2,3,1};
    int index = findTurnIndex.findTurnIndex(nums);
    System.out.println("期望结果：2，实际结果：" + index);
    nums = new int[]{4, 5, 6, 7, 0, 1, 2};
    index = findTurnIndex.findTurnIndex(nums);
    System.out.println("期望结果：4，实际结果：" + index);
    nums = new int[]{7, 0};
    index = findTurnIndex.findTurnIndex(nums);
    System.out.println("期望结果：1，实际结果：" + index);
  }
}

```

## 55.跳跃游戏

```java

/**
 * 跳跃游戏
 * @Author: zhangwbin
 * @Date: 2020/6/13
 */
public class Solution55 {

  /**
   * 跳就得了，记录下能跳到的最大的位置，如果过不去，就返回false，否则就一直跳，直到结尾
   * 
   * @param nums
   * @return
   */
  public boolean canJump(int[] nums) {
    int jumpMax = 0;
    for (int i = 0; i < nums.length; i++) {
      if (jumpMax < i) {
        return false;
      }
      jumpMax = Math.max(jumpMax, i + nums[i]);
    }
    return true;
  }

  public static void main(String[] args) {
    Solution55 solution55 = new Solution55();
    int[] nums = new int[]{2,3,1,1,4};
    boolean jump = solution55.canJump(nums);
    System.out.println(jump);
  }
}
```

## 跳跃游戏Ⅱ
```java

public class Solution45 {

  public int jump(int[] nums) {
  int start = 0; 
  // 先从第一步开始
  int end = 1;
  int minStep = 0;
    while (end < nums.length) {
        int maxJump = 0;
        for (int i = start; i < end; i++) {
            maxJump = Math.max(maxJump, nums[i] + i);
        }
        start = end;
        end = maxJump + 1;
        minStep++;
    }
    return minStep;
  }

  public static void main(String[] args) {
    Solution45 solution45 = new Solution45();
    int[] nums = new int[]{1, 2, 3};
    int step = solution45.jump(nums);
    System.out.println(step);
  }
}

```
以上代码可以看出内部循环的start就是上次循环的end，也就是说整体还是一次循环到结尾了，所以对上面代码进行优化，记录下end的位置即可

```java

public class Solution45 {

  public int jump(int[] nums) {
     int end = 0;// 一遍循环就需要设置结束点位置为0
     int minStep = 0;
     int maxJump = 0;
     // 结束点是倒数第二个元素，不需要到最后一个元素
     for (int i = 0; i < nums.length - 1; i++) {
         maxJump = Math.max(maxJump, nums[i] + i);
         // 到结束点了，需要跳下一次了，并且刷新下一步能跳到的最大值
         if (i == end) {
             minStep++;
             end = maxJump;
         }
     }
     return minStep;
  }

  public static void main(String[] args) {
    Solution45 solution45 = new Solution45();
    int[] nums = new int[]{1, 2, 3};
    int step = solution45.jump(nums);
    System.out.println(step);
  }
}

```

思考：
就是感觉在不断的刷题，基本碰上一道题都不会做，就看答案，最开始看了答案也不知道怎么做，慢慢的做的多了，熟悉的套路多了，思考的多了，慢慢的看答案也看得快了，但是很多题还是最开始看到不会做。不知道什么时候能够看到题目，大脑里就有一堆解法，然后再众多解法中寻找一种最优解。现在能有一种解法已经不错了，并且一种解法想到最后还是写不出来。感觉报这门课最大的感受就是督促我不断的刷题，不断的熟悉题型，不断的编码，（编码的质量是提高了不少），我觉得不断的可以训练久了也许就达到老师的境界了吧。