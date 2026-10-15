
import java.io.*;
import java.util.*;

abstract class Tree {
    private int value;
    private int color;
    private int depth;

    public Tree(int value, int color, int depth) {
        this.value = value;
        this.color = color;
        this.depth = depth;
    }

    public int getValue() { return value; }
    public int getColor() { return color; }
    public int getDepth() { return depth; }

    public abstract void accept(TreeVis visitor);
}

class TreeNode extends Tree {
    private List<Tree> children = new ArrayList<>();

    public TreeNode(int value, int color, int depth) {
        super(value, color, depth);
    }

    public void addChild(Tree child) {
        children.add(child);
    }

    public void accept(TreeVis visitor) {
        visitor.visitNode(this);
        for (Tree child : children) child.accept(visitor);
    }
}

class TreeLeaf extends Tree {
    public TreeLeaf(int value, int color, int depth) {
        super(value, color, depth);
    }

    public void accept(TreeVis visitor) {
        visitor.visitLeaf(this);
    }
}

abstract class TreeVis {
    public abstract int getResult();
    public abstract void visitNode(TreeNode node);
    public abstract void visitLeaf(TreeLeaf leaf);
}

class SumInLeavesVisitor extends TreeVis {
    int sum = 0;

    public int getResult() { return sum; }
    public void visitNode(TreeNode node) {}
    public void visitLeaf(TreeLeaf leaf) {
        sum += leaf.getValue();
    }
}

class ProductOfRedNodesVisitor extends TreeVis {
    long product = 1;
    static final int MOD = 1000000007;

    public int getResult() { return (int) product; }

    public void visitNode(TreeNode node) {
        if (node.getColor() == 0)
            product = product * node.getValue() % MOD;
    }

    public void visitLeaf(TreeLeaf leaf) {
        if (leaf.getColor() == 0)
            product = product * leaf.getValue() % MOD;
    }
}

class FancyVisitor extends TreeVis {
    int evenDepthSum = 0, greenLeafSum = 0;

    public int getResult() {
        return Math.abs(evenDepthSum - greenLeafSum);
    }

    public void visitNode(TreeNode node) {
        if (node.getDepth() % 2 == 0)
            evenDepthSum += node.getValue();
    }

    public void visitLeaf(TreeLeaf leaf) {
        if (leaf.getColor() == 1)
            greenLeafSum += leaf.getValue();
    }
}

public class Solution {
    public static Tree solve(Scanner sc) {
        int n = sc.nextInt();
        int[] values = new int[n];
        int[] colors = new int[n];

        for (int i = 0; i < n; i++) values[i] = sc.nextInt();
        for (int i = 0; i < n; i++) colors[i] = sc.nextInt();

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());

        for (int i = 0; i < n - 1; i++) {
            int u = sc.nextInt() - 1;
            int v = sc.nextInt() - 1;
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        Tree[] nodes = new Tree[n];
        nodes[0] = new TreeNode(values[0], colors[0], 0);

        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        boolean[] visited = new boolean[n];
        visited[0] = true;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            TreeNode parent = (TreeNode) nodes[u];

            for (int v : adj.get(u)) {
                if (!visited[v]) {
                    visited[v] = true;
                    int depth = nodes[u].getDepth() + 1;

                    boolean hasChildren = !adj.get(v).isEmpty();
                    nodes[v] = new TreeNode(values[v], colors[v], depth);
                    parent.addChild(nodes[v]);
                    queue.add(v);
                }
            }
        }

        // Convert nodes with no children into leaves.
        return buildTree(0, -1, 0, values, colors, adj);
    }

    private static Tree buildTree(int u, int parent, int depth,
                                  int[] values, int[] colors,
                                  List<List<Integer>> adj) {
        int childCount = 0;
        for (int v : adj.get(u)) if (v != parent) childCount++;

        if (childCount == 0)
            return new TreeLeaf(values[u], colors[u], depth);

        TreeNode node = new TreeNode(values[u], colors[u], depth);
        for (int v : adj.get(u)) {
            if (v != parent)
                node.addChild(buildTree(v, u, depth + 1, values, colors, adj));
        }
        return node;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Tree root = solve(sc);

        SumInLeavesVisitor v1 = new SumInLeavesVisitor();
        ProductOfRedNodesVisitor v2 = new ProductOfRedNodesVisitor();
        FancyVisitor v3 = new FancyVisitor();

        root.accept(v1);
        root.accept(v2);
        root.accept(v3);

        System.out.println(v1.getResult());
        System.out.println(v2.getResult());
        System.out.println(v3.getResult());
        sc.close();
    }
}
