/**
 * LeetCode #2401: Longest Nice Subarray
 * Strategy: Sliding Window with Bitwise OR/XOR
 */
public class LongestNiceSubarray {

    // --- THE LEETCODE FUNCTION ---
    public int longestNiceSubarray(int[] nums) {
        // usedBits tracks all bits currently "occupied" by numbers in the window
        int usedBits = 0; 
        int left = 0;
        int maxLen = 0;

        for (int right = 0; right < nums.length; right++) {
            // If (usedBits & nums[right]) != 0, it means nums[right] shares 
            // at least one bit with the current window. We must shrink from the left.
            while ((usedBits & nums[right]) != 0) {
                // XORing out the left element removes its bits from usedBits
                usedBits ^= nums[left];
                left++;
            }

            // Add the current number's bits to our tracker
            usedBits |= nums[right];
            
            // Update the maximum length of the "nice" window
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    // --- THE DRIVER (Wrapper) ---
    public static void main(String[] args) {
        LongestNiceSubarray solver = new LongestNiceSubarray();

        // Test Case 1: [1, 3, 8, 48, 10]
        // Binary: 1(01), 3(11) -> These share a bit, window must shift.
        int[] nums1 = {1, 3, 8, 48, 10};
        System.out.println("Test 1 Result: " + solver.longestNiceSubarray(nums1)); 

        // Test Case 2: [2, 1, 5, 12, 8]
        int[] nums2 = {2, 1, 5, 12, 8};
        System.out.println("Test 2 Result: " + solver.longestNiceSubarray(nums2));
    }
}