class Solution {
    public int[][] matrixBlockSum(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;
        
        // Create a 2D prefix sum array with 1 extra row and column 
        // to handle boundary conditions easily (1-based indexing).
        int[][] prefixSum = new int[m + 1][n + 1];
        
        // Build the prefix sum matrix
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                prefixSum[i][j] = mat[i - 1][j - 1] 
                                + prefixSum[i - 1][j] 
                                + prefixSum[i][j - 1] 
                                - prefixSum[i - 1][j - 1];
            }
        }
        
        int[][] answer = new int[m][n];
        
        // Calculate the block sum for each cell using the inclusion-exclusion principle
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Determine the valid boundaries for the block
                int r1 = Math.max(0, i - k);
                int c1 = Math.max(0, j - k);
                int r2 = Math.min(m - 1, i + k);
                int c2 = Math.min(n - 1, j + k);
                
                // Calculate the sum of the submatrix [r1..r2][c1..c2]
                // We add 1 to the bounds to map to our 1-based prefixSum array
                answer[i][j] = prefixSum[r2 + 1][c2 + 1] 
                             - prefixSum[r1][c2 + 1] 
                             - prefixSum[r2 + 1][c1] 
                             + prefixSum[r1][c1];
            }
        }
        
        return answer;
    }
}
