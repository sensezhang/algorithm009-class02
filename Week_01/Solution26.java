
public class Solution26{

  /**
   * 移除重复元素
   * @param nums
   * @return
   */
  public int removeDuplicates(int[] nums) {
    // 如果nums是空，返回0
    if (nums == null) {
      return 0;
    }
    // 定义数组当前非重复元素所在位置
    int i = 0;
    for (int j = 1; j < nums.length; j++) {
      // 如果当前重复元素跟当前元素不相同，说明到了下一个重复元素，将下标i++，并且设置nums[i]的值等于当前元素的值
      if (nums[i] != nums[j]) {
        i++;
        nums[i] = nums[j];
      }
    }
    // 返回当前元素的长度，需要+1
    return i + 1;
  }

}