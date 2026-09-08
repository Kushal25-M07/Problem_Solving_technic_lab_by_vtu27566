import java.io.*;
import java.util.*;

class Printer {
    // The generic method that accepts an array of any type T
    public <T> void printArray(T[] array) {
        for (T element : array) {
            System.out.println(element);
        }
    }
}

public class Solution {
    public static void main(String[] args) {
        Printer myPrinter = new Printer();
        
        // Creating the Integer and String arrays based on the problem description
        Integer[] intArray = {1, 2, 3};
        String[] stringArray = {"Hello", "World"};
        
        // Calling the generic method for both arrays
        myPrinter.printArray(intArray);
        myPrinter.printArray(stringArray);
    }
}
