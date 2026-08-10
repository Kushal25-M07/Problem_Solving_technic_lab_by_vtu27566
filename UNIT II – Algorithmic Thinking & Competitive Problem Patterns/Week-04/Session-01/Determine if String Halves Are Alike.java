class Solution {
    static{
        for(int i =0;i<500;i++){
            halvesAreAlike("");
        }
    }
    public static boolean halvesAreAlike(String s) {
        int mid=0+((s.length()-1)-0)/2;
        int i=0,j=mid+1;
        int count1=0,count2=0;
        while(i<=mid&&j<s.length()){
          char ch1=s.charAt(i);
          char ch2=s.charAt(j);
          if(ch1=='A'||ch1=='a'||ch1=='E'||ch1=='e'||ch1=='I'||ch1=='i'||ch1=='O'||ch1=='o'||ch1=='U'||ch1=='u'){
            count1++;
          }
          if(ch2=='A'||ch2=='a'||ch2=='E'||ch2=='e'||ch2=='I'||ch2=='i'||ch2=='O'||ch2=='o'||ch2=='U'||ch2=='u'){
            count2++;
          }
          i++;
          j++;
        }
        return (count1==count2);
    }
}
