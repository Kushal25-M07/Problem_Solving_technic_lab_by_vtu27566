import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> result = new ArrayList<>();
        
        for (String word : words) {
            if (matches(word, pattern)) {
                result.add(word);
            }
        }
        
        return result;
    }

    private boolean matches(String word, String pattern) {
        // Arrays to store the mappings of characters
        int[] wordToPattern = new int[26];
        int[] patternToWord = new int[26];

        for (int i = 0; i < word.length(); i++) {
            int w = word.charAt(i) - 'a';
            int p = pattern.charAt(i) - 'a';

            // If neither character has been mapped yet, map them to each other
            if (wordToPattern[w] == 0 && patternToWord[p] == 0) {
                wordToPattern[w] = p + 1; // +1 to distinguish from default 0
                patternToWord[p] = w + 1;
            } 
            // If the current mapping doesn't match the established mapping, it's invalid
            else if (wordToPattern[w] != p + 1 || patternToWord[p] != w + 1) {
                return false;
            }
        }
        
        return true;
    }
}
