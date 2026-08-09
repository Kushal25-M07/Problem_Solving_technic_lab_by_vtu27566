class Solution {
    public int[] sortArrayByParity(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left < right) {
            // If the left element is even, it's already in the correct place.
            if (nums[left] % 2 == 0) {
                left++;
            }
            // If the right element is odd, it's already in the correct place.
            else if (nums[right] % 2 != 0) {
                right--;
            }
            // If left is odd and right is even, swap them.
            else {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                
                // Move both pointers after swapping
                left++;
                right--;
            }
        }
        
        return nums;
    }
}
