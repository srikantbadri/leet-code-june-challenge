/**

Given a non-empty array of integers, every element appears three times except for one, which appears exactly once. Find that single one.

Note:

Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

Example 1:

Input: [2,2,3,2]
Output: 3
Example 2:

Input: [0,1,0,1,0,1,99]
Output: 99


**/
//Problem Link - https://leetcode.com/problems/single-number-ii/
//Youtube Link - https://leetcode.com/problems/single-number-ii/
class Solution {
	public  int singleNumber(int[] nums) {
		int count = 0;
		int result = 0;

		// count 1s on all 32 bits
		// if count of 1s is 3y -- then 0
		// if count of 1s is 3y+1 -- then 1
		for (int i = 1; i <= 32; i++) {
			count = 0;
			for (int num : nums) {
				if ((num & (1 << i)) != 0) {
					count += 1;
				}
				// num & 1<<i --- to see if ith bit in num is set or not
			}
			if (count % 3 != 0) {
				result = result | (1 << i);
			}
		}
		return result;
	}


}
