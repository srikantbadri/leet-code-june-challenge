//Problem Link - https://leetcode.com/explore/challenge/card/june-leetcoding-challenge/541/week-3-june-15th-june-21st/3365/
/**

Given a string S, consider all duplicated substrings: (contiguous) substrings of S that occur 2 or more times.  (The occurrences may overlap.)

Return any duplicated substring that has the longest possible length.  (If S does not have a duplicated substring, the answer is "".)

 

Example 1:

Input: "banana"
Output: "ana"
Example 2:

Input: "abcd"
Output: ""
 

Note:

2 <= S.length <= 10^5
S consists of lowercase English letters.

**/

import java.util.HashSet;
import java.util.Set;

public class Solution {
	int prime = 31;

	// Using binary search + rabin karp algorithm
	public String longestDupSubstring(String S) {
		int start = 1;
		int end = S.length() - 1;
		String dup = "";
		String ans = "";
		while (start <= end) {
			int mid = start + (end - start) / 2;
			dup = getDuplicate(S, mid);
			if (dup != null) {
				// probably there must be duplicate of more length
				// lets find that
				ans = dup;
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		return ans;
	}

	private String getDuplicate(String s, int windowSize) {
		int strLen = s.length();
		long hash = getHash(s.substring(0, windowSize), windowSize);
		Set<Long> hashSet = new HashSet<>();
		hashSet.add(hash);
		int start = 1;
		int end = windowSize;

		long primePow = 1;
		// precompute power -- windowsize - 1
		for (int i = 1; i < windowSize; i++) {
			primePow = primePow * prime;
		}
		// sliding window
		while (end < strLen) {
			// left char -- to be removed
			// right char -- to be added
			int left = (s.charAt(start - 1) - 'a' + 1);
			int right = (s.charAt(end) - 'a' + 1);
			hash = calculateNextHash(hash, left, right, primePow);
			if (!hashSet.add(hash)) {
				return s.substring(start, end + 1);
			}
			start++;
			end++;
		}
		return null;
	}

	private long calculateNextHash(long prevHash, int left, int right, long primePow) {
		return (prevHash - (left * primePow)) * prime + right;
	}

	// Hash Function : p^(windowsSize - 1)+...+p^0
	private long getHash(String substring, int windowSize) {
		long hash = 0;
		long primePow = 1;
		for (int i = windowSize - 1; i >= 0; i--) {
			hash += (substring.charAt(i) - 'a' + 1) * primePow;
			primePow = primePow * prime;
		}
		return hash;
	}

	// public static void main(String args[]) {
	// 	Solution solution = new Solution();
	// 	System.out.println(solution.longestDupSubstring("banana"));
	// }

}
