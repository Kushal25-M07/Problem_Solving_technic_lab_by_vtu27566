import java.util.*;

class ThroneInheritance {
    
    private String king;
    private Map<String, List<String>> familyTree;
    private Set<String> dead;

    public ThroneInheritance(String kingName) {
        this.king = kingName;
        this.familyTree = new HashMap<>();
        this.familyTree.put(kingName, new ArrayList<>());
        this.dead = new HashSet<>();
    }
    
    public void birth(String parentName, String childName) {
        // Add child to the parent's list of children
        familyTree.putIfAbsent(parentName, new ArrayList<>());
        familyTree.get(parentName).add(childName);
        
        // Initialize the child's own list in the tree
        familyTree.putIfAbsent(childName, new ArrayList<>());
    }
    
    public void death(String name) {
        // Mark the person as dead
        dead.add(name);
    }
    
    public List<String> getInheritanceOrder() {
        List<String> order = new ArrayList<>();
        dfs(king, order);
        return order;
    }
    
    // Helper method to perform Pre-order Traversal (DFS)
    private void dfs(String current, List<String> order) {
        // If the current person is not dead, add them to the inheritance order
        if (!dead.contains(current)) {
            order.add(current);
        }
        
        // Visit all children recursively
        if (familyTree.containsKey(current)) {
            for (String child : familyTree.get(current)) {
                dfs(child, order);
            }
        }
    }
}

/**
 * Your ThroneInheritance object will be instantiated and called as such:
 * ThroneInheritance obj = new ThroneInheritance(kingName);
 * obj.birth(parentName,childName);
 * obj.death(name);
 * List<String> param_3 = obj.getInheritanceOrder();
 */
