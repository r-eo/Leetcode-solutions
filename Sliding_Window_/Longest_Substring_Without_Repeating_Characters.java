
/**
 * LeetCode #3: Longest Substring Without Repeating Characters
 * Approach: Sliding Window with Optimized ASCII Array
 */
public class  Longest_Substring_Without_Repeating_Characters{

    // --- THE LEETCODE FUNCTION ---
    public int lengthOfLongestSubstring(String s) {
        // Integer array to store the last seen index of characters (ASCII 128)
        Integer[] chars = new Integer[128];

        int left = 0;
        int maxlen = 0;

        for (int right = 0; right < s.length(); right++) {
            char r = s.charAt(right);

            // If the character was seen before AND is within the current window
            if (chars[r] != null && chars[r] >= left) {
                // Move the left boundary past the previous occurrence
                left = chars[r] + 1;
            }

            // Calculate current window size: (right - left + 1)
            maxlen = Math.max(maxlen, right - left + 1);

            // Update/Store the last seen index of the character
            chars[r] = right;
        }
        return maxlen;
    }

    // --- THE DRIVER (Wrapper) ---
    public static void main(String[] args) {
        // 1. Instantiate the class
        Longest_Substring_Without_Repeating_Characters solution = new Longest_Substring_Without_Repeating_Characters();

        // 2. Define test cases
        String test1 = "abcabcbb"; // Expected: 3 ("abc")
        String test2 = "bbbbb";    // Expected: 1 ("b")
        String test3 = "pwwkew";   // Expected: 3 ("wke")

        // 3. Execute and Print
        System.out.println("Test Case 1 ('abcabcbb'): " + solution.lengthOfLongestSubstring(test1));
        System.out.println("Test Case 2 ('bbbbb'):    " + solution.lengthOfLongestSubstring(test2));
        System.out.println("Test Case 3 ('pwwkew'):   " + solution.lengthOfLongestSubstring(test3));
    }
}