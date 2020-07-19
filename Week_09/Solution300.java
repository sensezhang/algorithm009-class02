class Solution300 {
  public int lengthOfLIS(int[] nums) {
    if (nums == null || nums.length < 1){
      return 0;
    }
    int[] dp = new int[nums.length];
    dp[0] = 1;
    int maxRes = 1;
    for (int i = 1; i < nums.length; i++) {
      for (int j = 0; j < i; j++) {
        if (nums[i] > nums[j]) {
          dp[i] = Math.max(dp[i], dp[j]);
        }
      }
      dp[i] = dp[i] + 1;
      maxRes = Math.max(dp[i], maxRes);
    }
    return maxRes;
  }

  /**
   * 二分查找法
   * @param nums
   * @return
   */
  public int lengthOfLIS1(int[] nums) {
    if (nums == null || nums.length < 1){
      return 0;
    }
    int[] dp = new int[nums.length];
    int maxRes = 0;
    for (int i = 0; i < nums.length; i++) {
      // 二分查找
      int left = 0;
      int right = maxRes;
      while (left < right) {
        int mid = left + ((right - left) >> 1);
        if (dp[mid] < nums[i]) {
          left = mid + 1;
        } else {
          right = mid;
        }
      }
      dp[left] = nums[i];
      if (left == maxRes) {
        maxRes++;
      }
    }
    return maxRes;
  }
}