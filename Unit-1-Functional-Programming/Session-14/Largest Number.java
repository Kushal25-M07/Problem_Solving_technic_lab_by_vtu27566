import java.util.Arrays;

class Solution {
    public String largestNumber(int[] nums) {
        // Convert the integer array to a string array
        String[] strNums = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strNums[i] = String.valueOf(nums[i]);
        }
        
        // Sort the strings using a custom comparator
        // We compare two strings a and b by combining them: (b + a) vs (a + b)
        Arrays.sort(strNums, (a, b) -> (b + a).compareTo(a + b));
        
        // Edge case: if the largest string after sorting is "0", the result is just "0"
        // (This handles cases like [0, 0, 0])
        if (strNums[0].equals("0")) {
            return "0";
        }
        
        // Append all the strings together to form the largest number
        StringBuilder largestNum = new StringBuilder();
        for (String num : strNums) {
            largestNum.append(num);
        }
        
        return largestNum.toString();
    }
}
