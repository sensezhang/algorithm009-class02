class Solution11 {

  public int maxArea(int[] height) {
   /* // 双层嵌套循环，暴力求解法, 时间复杂度O(n^2)
    int max = 0;
    for (int i = 0; i < height.length - 1; i++) {
      for (int j = i + 1; j < height; j++) {
        max = Math.max(max, (j - i) * Math.min(height[i], height[j]));
      }
    }
    return max;*/

    // 双指针解法 时间复杂度O(n)

    int max = 0;
    int front = 0;
    int last = height.length - 1;
    while (front < last) {
      max = Math.max(max, (last - front) * Math.min(height[last], height[front]));
      if (height[last] < heigth[front]) {
        last--;
      } else {
        front++;
      }
    }
    return max;
  }
}