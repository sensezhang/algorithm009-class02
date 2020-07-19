# 第九周
## 高级动态规划

### 递归
public void recur (int level, int param) {
    // terminator
    if (level > MAX_LEVEL) {
        // process result
       return; 
   }
   // process current logic
   process(level, param);
    
   // drill down
   recur(level + 1, newParam);
   // restore current status
}

### 分治

public void divideConquer(int[] problem, int param1, int param2) {
   // recursion terminator
   if (problem == null) {
       printResult;
       return;
   }
   //prepare data
   data = prepareData(problem);
   Problem[] subproblems = splitProblem(problem, data);
   // conquer subproblems
   subResult1 = self.divideConquer(subproblems[0], p1, p2);
   subResult2 = self.divideConquer(subproblems[1], p1, p2);
   subResult3 = self.divideConquer(subproblems[2], p1, p2);
   // merge
   result = processResult(subResult1,subResult2, subResult3);
}


动态规划和递归或者分治没有根本上的区别（关键看有无最优的子结构）
拥有共性：找到重复子问题

差异性：最优子结构、中途可以淘汰次优解
递归就需要递归到结束，中间可以剪枝

## 不同路径状态转移

一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。

现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？

从finish往上递推
```java
public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;
        int[][] dp = new int[row + 1][col + 1];
        dp[row - 1][col - 1] = 1;
        for (int i = row - 1; i >= 0; i--) {
            for (int j = col - 1; j >= 0; j--) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else if (i != row - 1 || j != col - 1) {
                    dp[i][j] = dp[i + 1][j] + dp[i][j + 1];
                }
            }
        }
        return dp[0][0];
    }
```
为了方便判断最边上的障碍物，就将dp长和高都+ 1，并且值都是0，这样就不需要判断边界情况了；