class Solution {
    public int strStr(String haystack, String needle) {
        int m = haystack.length();
        int n = needle.length();
        
        // If needle is longer than haystack, it's impossible to find a match
        if (n > m) {
            return -1;
        }
        
        // Slide a window of length 'n' across the haystack
        for (int i = 0; i <= m - n; i++) {
            int j = 0;
            // Check character by character
            while (j < n && haystack.charAt(i + j) == needle.charAt(j)) {
                j++;
            }
            // If we matched all characters in the needle, return the starting index
            if (j == n) {
                return i;
            }
        }
        
        return -1; // No match found
    }
}
