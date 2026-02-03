/**
 * LeetCode #14: Longest Common Prefix
 * Strategy: Vertical Scanning
 */
public class LongestCommonPrefix {

    // --- THE LEETCODE FUNCTION ---
    public String LongestCommonPrefix(String[] strs) {
        // Edge case: if the array is null or empty, return an empty string
        if (strs == null || strs.length == 0) {
            return "";
        }

        // 1. Find the minimum length among all strings to avoid IndexOutOfBounds
        int minLen = Integer.MAX_VALUE;
        for (String s : strs) {
            if (s.length() < minLen) {
                minLen = s.length();
            }
        }

        // 2. Vertically compare characters
        for (int i = 0; i < minLen; i++) {
            char current = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                // If any string differs at this index, return prefix up to i
                if (strs[j].charAt(i) != current) {
                    return strs[0].substring(0, i);
                }
            }
        }

        // 3. If we finish the loop, the entire shortest string is the prefix
        return strs[0].substring(0, minLen);
    }

    // --- THE DRIVER (Wrapper) ---
    public static void main(String[] args) {
        LongestCommonPrefix solver = new LongestCommonPrefix();

        // Test Case 1: Common prefix exists ("fl")
        String[] input1 = {"flower", "flow", "flight"};
        System.out.println("Test 1 Result: '" + solver.LongestCommonPrefix(input1) + "'");

        // Test Case 2: No common prefix
        String[] input2 = {"dog", "racecar", "car"};
        System.out.println("Test 2 Result: '" + solver.LongestCommonPrefix(input2) + "'");

        // Test Case 3: One string is a prefix of others
        String[] input3 = {"inter", "interview", "intermediate"};
        System.out.println("Test 3 Result: '" + solver.LongestCommonPrefix(input3) + "'");
    }
}