import java.util.*;

public class test {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Deque<Integer> deque = new ArrayDeque<>();
        HashSet<Integer> set = new HashSet<>();
        
        int n = in.nextInt();
        int m = in.nextInt();
        int max = 0;

        for (int i = 0; i < n; i++) {
            int num = in.nextInt();
            
            deque.add(num);
            set.add(num);
            
            // Once the window reaches the required size M
            if (deque.size() == m) {
                // Update the max unique elements found so far
                if (set.size() > max) {
                    max = set.size();
                }
                
                // Short-circuit optimization: if we found a subarray where all elements 
                // are unique, it's the maximum possible, so we can stop searching.
                if (max == m) {
                    break;
                }
                
                // Slide the window by removing the first element
                int first = deque.removeFirst();
                
                // If the removed element doesn't exist anywhere else in the current window,
                // remove it from the unique set as well
                if (!deque.contains(first)) {
                    set.remove(first);
                }
            }
        }
        
        System.out.println(max);
        in.close();
    }
}
