package two.sun.local.violence;

import java.util.Arrays;

/**
 * @Description: TODO
 * @Author chopin
 * @Date 2021/7/3 9:34 下午
 * @Version 1.0
 */
public class Solution {
    public static void main(String[] arg) {
        int[] nums = {2, 7, 11, 15};
        Arrays.stream(twoSum(nums, 9)).forEach(System.out::println);
    }

    public static int[] twoSum(int[] nums, int target) {
        int length = nums.length;
        for (int i = 0; i < length; ++i) {
            for (int j = i + 1; j < length; ++j) {
                if (nums[i] + nums[j] == target) {
                    return new int[] {i, j};
                }
            }
        }
        return new int[0];
    }
}