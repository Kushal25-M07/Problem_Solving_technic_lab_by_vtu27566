class Solution {
    public static void pivotIndex(int[] nums , int low, int high,String[] nums2){
        if(low>=high){
            return;
        }
        int pivotIndex = partition(nums,low,high,nums2);
        pivotIndex(nums,low,pivotIndex-1,nums2);
        pivotIndex(nums,pivotIndex+1,high,nums2);
    }

    public static int partition(int[] nums,int low, int high , String[] nums2){
        int pivot = nums[high];
        int i = low - 1;

        for(int j = low;j<high;j++){
            if(pivot < nums[j]){
                i++;
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;

                String chn = nums2[i];
                nums2[i] = nums2[j];
                nums2[j] = chn;
            }
        }

        int temp = nums[high];
        nums[high] = nums[i+1];
        nums[i+1] = temp;

        String chn = nums2[high];
        nums2[high] = nums2[i+1];
        nums2[i+1] = chn;


        return i+1;
    }

    public String[] sortPeople(String[] names, int[] heights) {
        pivotIndex(heights,0,heights.length-1,names);
        return names;
    }
}
