import java.util.ArrayList;
import java.util.List;

class BrowserHistory {
    private List<String> history;
    private int curr;
    private int bound;

    public BrowserHistory(String homepage) {
        history = new ArrayList<>();
        history.add(homepage);
        curr = 0;
        bound = 0;
    }
    
    public void visit(String url) {
        curr++;
        // If we are overwriting forward history, set the new URL at the current index
        if (curr < history.size()) {
            history.set(curr, url);
        } else {
            // Otherwise, append to the end of the list
            history.add(url);
        }
        // Clears all forward history by restricting the upper bound
        bound = curr; 
    }
    
    public String back(int steps) {
        curr = Math.max(0, curr - steps);
        return history.get(curr);
    }
    
    public String forward(int steps) {
        curr = Math.min(bound, curr + steps);
        return history.get(curr);
    }
}
