import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Read the number of lines
        int n = scanner.nextInt();
        
        // Create an ArrayList of ArrayLists to store the lines
        ArrayList<ArrayList<Integer>> lines = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            int d = scanner.nextInt();
            ArrayList<Integer> currentLine = new ArrayList<>();
            
            for (int j = 0; j < d; j++) {
                currentLine.add(scanner.nextInt());
            }
            lines.add(currentLine);
        }
        
        // Read the number of queries
        int q = scanner.nextInt();
        
        for (int i = 0; i < q; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            
            // Adjust to 0-based index and attempt to fetch the number
            try {
                System.out.println(lines.get(x - 1).get(y - 1));
            } catch (IndexOutOfBoundsException e) {
                System.out.println("ERROR!");
            }
        }
        
        scanner.close();
    }
}
