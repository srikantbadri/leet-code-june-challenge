/**

Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

Example 1:

Input: n = 12
Output: 3 
Explanation: 12 = 4 + 4 + 4.
Example 2:

Input: n = 13
Output: 2
Explanation: 13 = 4 + 9.


**/
//Problem - https://leetcode.com/problems/perfect-squares/submissions/
//Solving using lagranges four squares theorem
//Acc to this thm- any number can be made up by adding perfect square (upt 4 nos max): 1,2,3,4 
//the answer will be max - 4
class Solution {
    public int numSquares(int n) {
        if(isPerfectSquare(n))return 1;
        if(check_4(n))return 4;
        if(check_2(n))return 2;
        return 3;
    }
    public boolean isPerfectSquare(int n){
        int sq =(int)Math.sqrt(n);
        return sq*sq==n;
    }
    //any number having 4 as answer can be written as n=4^k*(8*m+7)
    public boolean check_4(int n){
        while(n%4==0){
            n/=4;
        }
        if(n%8==7)return true;
        return false;
    }
    public boolean check_2(int n){
        for(int i=1;i*i<=n;i++){
            if(isPerfectSquare(n-i*i))return true;
        }
        return false;
    }
}

//DP Based Solution
//dp based solution
/**
        // dp[i] = the least number of perfect square numbers 
        // which sum to i. Note that dp[0] is 0.

**/
class Solution {
    public int numSquares(int n) {
        int dp[]=new int[n+1];
        dp[0]=0;//Rest will be initialized to Int Max;
        for(int i=1;i<=n;i++){
            dp[i]=Integer.MAX_VALUE;
            for(int j=1;j*j<=i;j++){
                dp[i]=Math.min(dp[i],dp[i-j*j]+1);                
            }
        }
        return dp[n];
    }
}
