import java.io.*;
import java.util.*;

class Result {

    // O(1) Add/Remove Data Structure because our maximum possible value is bounded by 4*N
    static class BucketMax {
        int[] count;
        int max_val;

        BucketMax(int max_possible) {
            count = new int[max_possible + 1];
            max_val = -1;
        }

        void add(int val) {
            if (val < 0) return;
            count[val]++;
            if (val > max_val) {
                max_val = val;
            }
        }

        void remove(int val) {
            if (val < 0) return;
            count[val]--;
            while (max_val >= 0 && count[max_val] == 0) {
                max_val--;
            }
        }

        int getMax() {
            return max_val;
        }
    }

    // Parallel arrays to avoid Java object overhead for millions of events
    static int MAX_EVENTS;
    static int[] eventData;
    static int[] eventNext;
    static int[] eventHead;
    static int eventCount;

    static void addEvent(int time, int type_code, int val, int N) {
        if (time > N) return;
        // Pack value and type into a single int to save memory
        eventData[eventCount] = (val << 3) | type_code;
        eventNext[eventCount] = eventHead[time];
        eventHead[time] = eventCount++;
    }

    public static List<Integer> circularPalindromes(String s) {
        int n = s.length();
        String s2 = s + s;
        int[] p = manacher(s2);

        // 4N centers, max 3 intervals per center, 2 events per interval
        MAX_EVENTS = 24 * n + 10;
        eventData = new int[MAX_EVENTS];
        eventNext = new int[MAX_EVENTS];
        eventHead = new int[n + 1];
        eventCount = 1;
        Arrays.fill(eventHead, 0);

        for (int i = 1; i < 4 * n; i++) {
            // A palindrome inside the window cannot exceed the window's size
            int r = Math.min(p[i], n);
            if (r == 0) continue;

            int s_min = Math.max(0, Math.floorDiv(i - 2 * n, 2) + 1);
            int s_max = Math.min(n - 1, Math.floorDiv(i - 1, 2));

            if (s_min > s_max) continue;

            int s_rt = Math.floorDiv(i + r - 2 * n - 1, 2);
            int s_lt = Math.floorDiv(i - r + 2, 2);

            // Set 3: Right-truncated interval
            int s3_start = s_min;
            int s3_end = Math.min(s_max, s_rt);
            if (s3_start <= s3_end) {
                addEvent(s3_start, 4, 4 * n - i, n);
                addEvent(s3_end + 1, 5, 4 * n - i, n);
            }

            // Set 1: Fully inside interval
            int s1_start = Math.max(s_min, s_rt + 1);
            int s1_end = Math.min(s_max, s_lt - 1);
            if (s1_start <= s1_end) {
                addEvent(s1_start, 0, r, n);
                addEvent(s1_end + 1, 1, r, n);
            }

            // Set 2: Left-truncated interval
            int s2_start = Math.max(s_min, s_lt);
            int s2_end = s_max;
            if (s2_start <= s2_end) {
                addEvent(s2_start, 2, i, n);
                addEvent(s2_end + 1, 3, i, n);
            }
        }

        BucketMax b1 = new BucketMax(n + 1);
        BucketMax b2 = new BucketMax(4 * n + 1);
        BucketMax b3 = new BucketMax(4 * n + 1);

        List<Integer> ans = new ArrayList<>(n);

        // Sweep line over all possible rotations
        for (int step = 0; step < n; step++) {
            int edge = eventHead[step];
            while (edge != 0) {
                int data = eventData[edge];
                int type_code = data & 7;
                int val = data >>> 3;

                if (type_code == 0) b1.add(val);
                else if (type_code == 1) b1.remove(val);
                else if (type_code == 2) b2.add(val);
                else if (type_code == 3) b2.remove(val);
                else if (type_code == 4) b3.add(val);
                else if (type_code == 5) b3.remove(val);

                edge = eventNext[edge];
            }

            int max_len = 0;
            if (b1.getMax() != -1) {
                max_len = Math.max(max_len, b1.getMax());
            }
            if (b2.getMax() != -1) {
                max_len = Math.max(max_len, b2.getMax() - 2 * step);
            }
            if (b3.getMax() != -1) {
                max_len = Math.max(max_len, 2 * step - 2 * n + b3.getMax());
            }

            ans.add(max_len);
        }

        return ans;
    }

    private static int[] manacher(String s) {
        StringBuilder sb = new StringBuilder();
        sb.append('#');
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i)).append('#');
        }
        String t = sb.toString();
        int[] p = new int[t.length()];
        int center = 0, right = 0;

        for (int i = 1; i < t.length() - 1; i++) {
            int mirror = 2 * center - i;
            if (right > i) {
                p[i] = Math.min(right - i, p[mirror]);
            }
            while (i + p[i] + 1 < t.length() && i - p[i] - 1 >= 0
                   && t.charAt(i + p[i] + 1) == t.charAt(i - p[i] - 1)) {
                p[i]++;
            }
            if (i + p[i] > right) {
                center = i;
                right = i + p[i];
            }
        }
        return p;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());
        String s = bufferedReader.readLine();

        List<Integer> result = Result.circularPalindromes(s);

        for (int i = 0; i < result.size(); i++) {
            bufferedWriter.write(String.valueOf(result.get(i)));
            if (i != result.size() - 1) {
                bufferedWriter.write("\n");
            }
        }
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
