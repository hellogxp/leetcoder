package RemoveDuplicateFromSortedArray.local;

/** @Description: TODO @Author chopin @Date 2021/6/28 9:04 AM @Version 1.0 */
public class Solution {
  public int removeDuplicates(int[] nums) {
    if (nums.length == 0 || nums == null) {
      return 0;
    }
    int p = 0, q = 1;
    while (q < nums.length) {
      if (nums[p] != nums[q]) {
        if (q - p > 1) {
          nums[p + 1] = nums[q];
        }
        p++;
      }
      q++;
    }
    return p + 1;
  }
}
