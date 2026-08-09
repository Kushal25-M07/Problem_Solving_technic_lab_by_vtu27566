class Solution {
    public int maximumWealth(int[][] accounts) {
        int maxWealth = 0;
        
        // Iterate over each customer's accounts
        for (int i = 0; i < accounts.length; i++) {
            int currentCustomerWealth = 0;
            
            // Sum the wealth across all banks for the current customer
            for (int j = 0; j < accounts[i].length; j++) {
                currentCustomerWealth += accounts[i][j];
            }
            
            // Update maxWealth if the current customer is richer
            maxWealth = Math.max(maxWealth, currentCustomerWealth);
        }
        
        return maxWealth;
    }
}
