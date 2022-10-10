package searchInsertPosition.local;

/**
 * @author Chopin
 * @date 2022/8/5
 */
public class Solution {
    public static void main(String[] args) {
        int[] nums = new int[]{1,3,5,6};
        System.out.println(searchInsert(nums, 5));
    }

    public static int searchInsert(int[] nums, int target) {
        int length = nums.length;
        int left = 0, right = length - 1, ans = length- 1;
        while (left <= right) {
            int middle = left + (right - left >> 1);
            if (target <= nums[middle]) {
                ans = middle;
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return ans;
    }
}