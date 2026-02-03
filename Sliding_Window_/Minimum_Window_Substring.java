import java.util.*;

/**
 * LeetCode #76: Minimum Window Substring
 * Strategy: Sliding Window with a character frequency array (ASCII 128)
 */
public class Minimum_Window_Substring {

    // --- THE LEETCODE FUNCTION ---
    public String minWindow(String s, String t) {
        // Edge case being handled: if input is invalid or s is shorter than t
        if (s == null || t == null || s.length() == 0 || t.length() == 0 || s.length() < t.length())
            return "";

        // Initializing left window, minimum length, and frequency map.
        int left = 0;
        int count = t.length(); // Total characters needed to match t
        int[] map = new int[128];
        int minLength = Integer.MAX_VALUE;
        int startIndex = 0; // Start index to extract the final substring

        // Mapping each character from string t to the frequency array
        for (char charac : t.toCharArray()) {
            map[charac]++;
        }

        // Move the right pointer to expand the window
        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);

            // If the current character is part of string t, decrement count
            if (map[rightChar] > 0) {
                count--;
            }

            // Always decrement the map value for the character seen
            map[rightChar]--;

            // When all characters from t are found in the current window (count == 0)
            while (count == 0) {
                // Update the minimum length and start index if a smaller window is found
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    startIndex = left;
                }

                char leftChar = s.charAt(left);

                // Re-add the left character to the map as we contract the window
                map[leftChar]++;

                // If map value becomes positive, it means we lost a required character from t
                if (map[leftChar] > 0) {
                    count++;
                }
                left++;
            }
        }

        // Return the substring if found, otherwise an empty string
        return minLength == Integer.MAX_VALUE ? "" : s.substring(startIndex, startIndex + minLength);
    }

    // --- THE DRIVER (Wrapper) ---
    public static void main(String[] args) {
        Minimum_Window_Substring solver = new Minimum_Window_Substring();

        // Test Case 1
        String s1 = "ADOBECODEBANC", t1 = "ABC";
        // Expected Output: "BANC"
        System.out.println("Test 1: " + solver.minWindow(s1, t1));

        // Test Case 2
        String s2 = "a", t2 = "a";
        // Expected Output: "a"
        System.out.println("Test 2: " + solver.minWindow(s2, t2));

        // Test Case 3
        String s3 = "a", t3 = "aa";
        // Expected Output: ""
        System.out.println("Test 3: " + solver.minWindow(s3, t3));
    }
}
