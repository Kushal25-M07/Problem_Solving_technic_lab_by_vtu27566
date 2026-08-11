import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'matrixRotation' function below.
     *
     * The function accepts following parameters:
     *  1. 2D_INTEGER_ARRAY matrix
     *  2. INTEGER r
     */

public static void matrixRotation(List<List<Integer>> matrix, int r) {
        int m = matrix.size();
        int n = matrix.get(0).size();
        int numRings = Math.min(m, n) / 2;
        
        // Use an array to store the rotated results
        int[][] result = new int[m][n];
        
        for (int ring = 0; ring < numRings; ring++) {
            List<int[]> coords = new ArrayList<>();
            
            // Top row: left to right
            for (int j = ring; j < n - ring; j++) 
                coords.add(new int[]{ring, j});
                
            // Right column: top to bottom (excluding corners)
            for (int i = ring + 1; i < m - ring - 1; i++) 
                coords.add(new int[]{i, n - 1 - ring});
                
            // Bottom row: right to left
            for (int j = n - 1 - ring; j >= ring; j--) 
                coords.add(new int[]{m - 1 - ring, j});
                
            // Left column: bottom to top (excluding corners)
            for (int i = m - 1 - ring - 1; i > ring; i--) 
                coords.add(new int[]{i, ring});
                
            int len = coords.size();
            int effectiveRotations = r % len; // Reduce unnecessary full rotations
            
            // Map the old positions to the new positions
            for (int i = 0; i < len; i++) {
                int[] oldPos = coords.get((i + effectiveRotations) % len);
                int[] newPos = coords.get(i);
                result[newPos[0]][newPos[1]] = matrix.get(oldPos[0]).get(oldPos[1]);
            }
        }
        
        // Print the resulting matrix
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(result[i][j] + (j == n - 1 ? "" : " "));
            }
            System.out.println();
        }
    }
}
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int m = Integer.parseInt(firstMultipleInput[0]);

        int n = Integer.parseInt(firstMultipleInput[1]);

        int r = Integer.parseInt(firstMultipleInput[2]);

        List<List<Integer>> matrix = new ArrayList<>();

        IntStream.range(0, m).forEach(i -> {
            try {
                matrix.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        Result.matrixRotation(matrix, r);

        bufferedReader.close();
    }
}
