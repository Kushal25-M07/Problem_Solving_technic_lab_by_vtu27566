class Solution {
    public ArrayList<ArrayList<Integer>> multiply(int[][] mat1, int[][] mat2) {
        int n = mat1.length;
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        
        // Perform matrix multiplication
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                int sum = 0;
                for (int k = 0; k < n; k++) {
                    sum += mat1[i][k] * mat2[k][j];
                }
                row.add(sum);
            }
            res.add(row);
        }
        
        return res;
    }
}
