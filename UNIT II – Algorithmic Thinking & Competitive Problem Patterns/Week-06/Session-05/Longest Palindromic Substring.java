class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        
        int start = 0, end = 0;
        
        for (int i = 0; i < s.length(); i++) {
            // Expand around a single character (odd length palindromes)
            int len1 = expandAroundCenter(s, i, i);
            // Expand around two adjacent characters (even length palindromes)
            int len2 = expandAroundCenter(s, i, i + 1);
            
            // Get the maximum length from the two expansions
            int len = Math.max(len1, len2);
            
            // If we found a longer palindrome, update the start and end pointers
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        
        return s.substring(start, end + 1);
    }
    
    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // Return the length of the palindrome
        return right - left - 1;
    }
}
