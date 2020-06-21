/**
The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.

The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.

Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms; other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).

In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.

 

Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.

For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.

-2 (K)	-3	3
-5	-10	1
10	30	-5 (P)
 

Note:

The knight's health has no upper bound.
Any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where the princess is imprisoned.


**/

//Problem Link - https://leetcode.com/explore/challenge/card/june-leetcoding-challenge/541/week-3-june-15th-june-21st/3367/
//Youtube Link - https://www.youtube.com/watch?v=jw1dTdpdiAI

//Solving the problem in reverse dp
//Final number to be minimum i.e. 1 because we need to find the minmum value which will give us 1 in the end

class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int rows = dungeon.length;
        int cols = dungeon[0].length;
        int [][]dp = new int[rows+1][cols+1];
        
        //fill with Max Value
        for(int i=0;i<=rows;i++)
            Arrays.fill(dp[i],Integer.MAX_VALUE);
    
        //Setting the final values to be 1
        dp[rows][cols-1]=1;
        dp[rows-1][cols]=1;
        
        for(int i =rows-1;i>=0;i--){
            for(int j=cols-1;j>=0;j--){
                dp[i][j]=Math.min(dp[i][j+1],dp[i+1][j])-dungeon[i][j];
                if(dp[i][j]<=0)
                    dp[i][j]=1;
            }
        }
        return dp[0][0];
    }
}
