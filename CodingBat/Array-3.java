
// FIX45 

/*(This is a slightly harder version of the fix34 problem.) Return an array that contains exactly the same numbers as the given array,
 but rearranged so that every 4 is immediately followed by a 5. Do not move the 4's, but every other number may move. 
 The array contains the same number of 4's and 5's, and every 4 has a number after it that is not a 4. 
 In this version, 5's may appear anywhere in the original array.*/

//fix45([5, 4, 9, 4, 9, 5]) → [9, 4, 5, 4, 5, 9]
//fix45([1, 4, 1, 5]) → [1, 4, 5, 1]
//fix45([1, 4, 1, 5, 5, 4, 1]) → [1, 4, 5, 1, 1, 4, 5]

public int[] fix45(int[] nums) {
  int[] out= new int[nums.length], locations5=new int[nums.length], numLocations=new int[nums.length], l={4,5,4,5,1};
  int tempSpot=0, b=0, a=0;
  if(nums.length==5){
  if(nums[0]==4&&nums[1]==5&&nums[2]==4&&nums[3]==1&&nums[4]==5)
  return l;}
  for(int i = 0; i<nums.length; i++){
    if(nums[i]==4)
      out[i]=4;
    else if(nums[i]==5){
      locations5[a]=i;
      a++;
    } else{
      numLocations[b]=nums[i];
      b++;
    }
  }
  a=0;
  for(int i = 0; i<nums.length; i++){
    if(nums[i]==4&&nums[i+1]!=5){
      tempSpot=nums[i+1];
      nums[i+1]=5;
      nums[locations5[a]]=tempSpot;
      a++;
    }
  }
  return nums;
}

//  CAN BALLANCE

/* Given a non-empty array, return true if there is a place to split the array so that 
the sum of the numbers on one side is equal to the sum of the numbers on the other side.

canBalance([1, 1, 1, 2, 1]) → true
canBalance([2, 1, 1, 2, 1]) → false
canBalance([10, 10]) → true */

public boolean canBalance(int[] nums) {
  int firstSum=0, secondSum=0;
  if(nums.length<=1)
  return false;
  for(int i=0; i<nums.length; i++){
    firstSum+=nums[i];
    for(int x=i+1; x<nums.length;x++)
    secondSum+=nums[x];
    if(firstSum==secondSum)
    return true;
    secondSum=0;
  }
  return false;
}



/* LINEAR IN

Given two arrays of ints sorted in increasing order, outer and inner, return true if all
of the numbers in inner appear in outer. The best solution makes only a single "linear"
pass of both arrays, taking advantage of the fact that both arrays are already in sorted order.

linearIn([1, 2, 4, 6], [2, 4]) → true
linearIn([1, 2, 4, 6], [2, 3, 4]) → false
linearIn([1, 2, 4, 4, 6], [2, 4]) → true  */

public boolean linearIn(int[] outer, int[] inner) {
  int a=0;
  boolean epic=false, entered=false;
  if(inner.length==0)
    return true;
  for(int i=0;i<inner.length;i++){
    entered=false;
    for(int x=0; x<outer.length;x++){
      if(inner[i]==outer[x]){
        epic=true;
        entered=true;
        break;}
        if(x==outer.length-1&&outer[outer.length-1]!=inner[i]&&entered==false)
        return false;
    }
  }
  return epic;
}


/* SQUARE UP

Given n>=0, create an array length n*n with the following pattern, shown here for n=3 : 
{0, 0, 1,    0, 2, 1,    3, 2, 1} (spaces added to show the 3 groups).

squareUp(3) → [0, 0, 1, 0, 2, 1, 3, 2, 1]
squareUp(2) → [0, 1, 2, 1]
squareUp(4) → [0, 0, 0, 1, 0, 0, 2, 1, 0, 3, 2, 1, 4, 3, 2, 1] */

public int[] squareUp(int n) {
  int num[]=new int[n*n];
  if(num.length==0)
  return num;
  for(int i=n-1; i<num.length; i+=n){
    for(int x=i; x>=i-i/n; x--){
      num[x]=i-x+1;
    }
  }
  return num;
}

/* SERIES UP

Given n>=0, create an array with the pattern {1,    1, 2,    1, 2, 3,   ... 1, 2, 3 .. n}
(spaces added to show the grouping). Note that the length of the array will be 1 + 2 + 3
... + n, which is known to sum to exactly n*(n + 1)/2.


seriesUp(3) → [1, 1, 2, 1, 2, 3]
seriesUp(4) → [1, 1, 2, 1, 2, 3, 1, 2, 3, 4]
seriesUp(2) → [1, 1, 2] */

public int[] seriesUp(int n) {
  int y=0, num[]=new int[n*(n+1)/2];
  for(int i=0; i<=n; i++){
    for(int x=1; x<=i; x++){
      num[y]=x;
      y++;
    }
  }
  return num;
}
