package binarySearch.local;

/**
 * @author Chopin
 * @date 2022/8/3
 */
public class Solution {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6};
        System.out.println(search(nums, 5));
    }

    public static int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1 ;

        while (left <= right) {
            int middle = left + (right - left / 2);

            if (nums[middle] == target) {
                return middle;
            } else if (nums[middle] > target) {
                right = middle - 1;
            } else if (nums[middle] < target) {
                left = middle + 1;
            }

        }
        return -1;
    }
}