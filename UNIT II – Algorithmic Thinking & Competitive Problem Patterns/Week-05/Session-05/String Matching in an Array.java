import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> stringMatching(String[] words) {
        List<String> result = new ArrayList<>();
        
        // Check every word against every other word
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length; j++) {
                // If it's not the same word, and words[j] contains words[i]
                if (i != j && words[j].contains(words[i])) {
                    result.add(words[i]);
                    break; // Once we know it's a substring, we can stop checking this word
                }
            }
        }
        
        return result;
    }
}
