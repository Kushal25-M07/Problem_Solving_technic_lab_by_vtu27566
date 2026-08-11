class Solution {
    public int[][] transpose(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        
        // Create a new matrix with swapped dimensions
        int[][] transposedMatrix = new int[n][m];
        
        // Iterate through the original matrix and place elements in their transposed positions
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                transposedMatrix[j][i] = matrix[i][j];
            }
        }
        
        return transposedMatrix;
    }
}
