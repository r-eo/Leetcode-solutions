import java.util.*;

/**
 * LeetCode #992: Subarrays with K Different Integers
 * Strategy: Sliding Window (At Most K - At Most K-1)
 */
public class SubarraysWithKDistinct {

    // --- THE LEETCODE FUNCTION ---
    public int subarraysWithKDistinct(int[] nums, int k) {
        // The number of subarrays with exactly K elements is:
        // (Subarrays with <= K) - (Subarrays with <= K-1)
        return atmostK(nums, k) - atmostK(nums, k - 1);
    }

    /**
     * Helper function to find the count of subarrays with at most k distinct integers.
     */
    private int atmostK(int[] nums, int k) {
        int left = 0;
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();

        for (int right = 0; right < nums.length; right++) {
            // Add current number to the frequency map
            map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);

            // If we have more than k distinct elements, shrink the window from the left
            while (map.size() > k) {
                int lnum = nums[left];
                map.put(lnum, map.get(lnum) - 1);

                if (map.get(lnum) == 0) {
                    map.remove(lnum);
                }
                left++;
            }
            
            // The number of subarrays ending at 'right' with at most k distinct elements
            // is equal to the current window size (right - left + 1).
            count += (right - left) + 1;
        }
        return count;
    }

    // --- THE DRIVER (Wrapper) ---
    public static void main(String[] args) {
        SubarraysWithKDistinct solver = new SubarraysWithKDistinct();

        // Test Case 1
        int[] nums1 = {1, 2, 1, 2, 3};
        int k1 = 2;
        // Expected Output: 7
        // Subarrays: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2]
        System.out.println("Test 1 Result: " + solver.subarraysWithKDistinct(nums1, k1));

        // Test Case 2
        int[] nums2 = {1, 2, 1, 3, 4};
        int k2 = 3;
        // Expected Output: 3
        System.out.println("Test 2 Result: " + solver.subarraysWithKDistinct(nums2, k2));
    }
}