package reverse.integer.local;

/**
 * @description: Reverse integer
 * @author chopin
 * @date 2021/7/13 09:01
 * @version 1.0
 */
public class Solution {
    public static void main(String[] args) {
        System.out.println(reverse(521));
    }

    public static int reverse(int x) {
        int ans = 0;
        while (x != 0) {
            int pop = x % 10;
            // Determine weather the result overflows at every step, ans = ans * 10 + pop > MAX_VALUE
            if (ans > Integer.MAX_VALUE / 10 || (ans == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            }
            if (ans < Integer.MIN_VALUE / 10 || (ans == Integer.MIN_VALUE / 10 && pop < -8)) {
                return 0;
            }
            ans = ans * 10 + pop;
            x /= 10;
        }
        return ans;
    }
}