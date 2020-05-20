
public class Solution26{

  /**
   * 移除重复元素
   * @param nums
   * @return
   */
  public int removeDuplicates(int[] nums) {

    if (nums == null) {
      return 0;
    }
    int i = 0;
    for (int j = 1; j < nums.length; j++) {
      if (nums[i] != nums[j]) {
        i++;
        nums[i] = nums[j];
      }
    }
    return i + 1;
  }

}