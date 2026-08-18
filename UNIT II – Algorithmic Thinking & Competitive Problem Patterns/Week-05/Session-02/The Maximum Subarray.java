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
     * Complete the 'maxSubarray' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static List<Integer> maxSubarray(List<Integer> arr) {
        int maxSubarray = arr.get(0);
        int currentMax = arr.get(0);
        
        int maxElement = arr.get(0);
        int positiveSum = 0;
        boolean hasPositive = false;

        for (int i = 0; i < arr.size(); i++) {
            int val = arr.get(i);
            
            // Calculate Maximum Subarray (Kadane's Algorithm)
            if (i > 0) {
                currentMax = Math.max(val, currentMax + val);
                maxSubarray = Math.max(maxSubarray, currentMax);
            }
            
            // Track elements for Maximum Subsequence
            maxElement = Math.max(maxElement, val);
            if (val > 0) {
                positiveSum += val;
                hasPositive = true;
            }
        }
        
        // Calculate Maximum Subsequence
        int maxSubsequence;
        if (hasPositive) {
            maxSubsequence = positiveSum;
        } else {
            maxSubsequence = maxElement; // If all numbers are negative, return the largest negative number
        }

        return Arrays.asList(maxSubarray, maxSubsequence);
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Integer::parseInt)
                    .collect(toList());

                List<Integer> result = Result.maxSubarray(arr);

                bufferedWriter.write(
                    result.stream()
                        .map(Object::toString)
                        .collect(joining(" "))
                    + "\n"
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
