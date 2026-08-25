class Result {

    // Helper function to check if a substring is a palindrome
    public static boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /*
     * Complete the 'palindromeIndex' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING s as parameter.
     */
    public static int palindromeIndex(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            // If characters match, move inwards
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                // Mismatch found. Try skipping the left character...
                if (isPalindrome(s, left + 1, right)) {
                    return left;
                }
                // ...or try skipping the right character
                if (isPalindrome(s, left, right - 1)) {
                    return right;
                }
                // If neither works, it's not possible with one removal
                return -1;
            }
        }
        
        // If the loop finishes without mismatches, it's already a palindrome
        return -1;
    }
}
