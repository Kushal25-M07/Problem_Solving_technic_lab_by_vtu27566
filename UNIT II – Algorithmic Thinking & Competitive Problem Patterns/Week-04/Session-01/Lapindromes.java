import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
    public static void main (String[] args) throws java.lang.Exception
    {
        Scanner sc = new Scanner(System.in);
        if(sc.hasNextInt()){
            int t = sc.nextInt();
            sc.nextLine(); // Consume the newline
            
            while(t-- > 0){
                String s = sc.nextLine();
                int len = s.length();
                
                // Extract the two halves
                String firstHalf = s.substring(0, len / 2);
                String secondHalf;
                
                // If odd, skip the middle character
                if(len % 2 == 0){
                    secondHalf = s.substring(len / 2);
                } else {
                    secondHalf = s.substring((len / 2) + 1);
                }
                
                // Convert to character arrays to sort them
                char[] arr1 = firstHalf.toCharArray();
                char[] arr2 = secondHalf.toCharArray();
                
                Arrays.sort(arr1);
                Arrays.sort(arr2);
                
                // Compare the sorted arrays
                if(Arrays.equals(arr1, arr2)){
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            }
        }
        sc.close();
    }
}
