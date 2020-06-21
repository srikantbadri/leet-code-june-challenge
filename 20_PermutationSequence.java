//Link - https://leetcode.com/problems/permutation-sequence/
/**
The set [1,2,3,...,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order, we get the following sequence for n = 3:

"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.

Note:

Given n will be between 1 and 9 inclusive.
Given k will be between 1 and n! inclusive.
Example 1:

Input: n = 3, k = 3
Output: "213"
Example 2:

Input: n = 4, k = 9
Output: "2314"

**/
class Solution {
	public String getPermutation(int n, int k) {
		List<Integer> nums = new ArrayList<>();
		int[] fact = new int[n];
		fact[0] = 1;
        if(n>=2)
		fact[1] = 1;
		for (int i = 2; i < n; i++) {
			fact[i] = fact[i - 1] * i;
		}
		for (int i = 1; i <= n; i++) {
			nums.add(i);
		}
		StringBuilder sb = new StringBuilder();
		int times = n - 1;
		k--;
		while (!nums.isEmpty()) {
			int index = k / fact[times];
			sb.append(nums.get(index));
			k = k % fact[times];
			times--;
			nums.remove(index);
		}
		return sb.toString();
	}

}
