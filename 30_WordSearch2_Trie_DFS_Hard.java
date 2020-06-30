/**

Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

 

Example:

Input: 
board = [
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
words = ["oath","pea","eat","rain"]

Output: ["eat","oath"]
 

Note:

All inputs are consist of lowercase letters a-z.
The values of words are distinct.

**/
//https://leetcode.com/problems/word-search-ii/

class Solution {
	class TrieNode {
		public TrieNode[] nodeArr;
		public boolean isEndOfWord;

		TrieNode() {
			nodeArr = new TrieNode[26];
			isEndOfWord = false;
		}
	}

	class Trie {
		TrieNode root;

		/** Initialize your data structure here. */
		public Trie() {
			root = new TrieNode();
		}

		/** Inserts a word into the trie. */
		public void insert(String word) {
			int len = word.length();
			if (len == 0)
				return;
			TrieNode temp = root;
			for (int i = 0; i < len; i++) {
				if (temp.nodeArr[word.charAt(i) - 'a'] == null)
					temp = temp.nodeArr[word.charAt(i) - 'a'] = new TrieNode();
				else
					temp = temp.nodeArr[word.charAt(i) - 'a'];
			}
			temp.isEndOfWord = true;
		}

		/** Returns if the word is in the trie. */
		public boolean search(String word) {
			int len = word.length();
			TrieNode temp = root;
			for (int i = 0; i < len; i++) {
				if (temp != null && temp.nodeArr[word.charAt(i) - 'a'] != null)
					temp = temp.nodeArr[word.charAt(i) - 'a'];
				else
					return false;
			}
			if (temp != null && temp.isEndOfWord)
				return true;
			return false;
		}

		/**
		 * Returns if there is any word in the trie that starts with the given
		 * prefix.
		 */
		public boolean startsWith(String prefix) {
			int len = prefix.length();
			TrieNode temp = root;
			for (int i = 0; i < len; i++) {
				if (temp != null && temp.nodeArr[prefix.charAt(i) - 'a'] != null)
					temp = temp.nodeArr[prefix.charAt(i) - 'a'];
				else
					return false;

			}
			if (temp != null)
				return true;
			return false;
		}
	}

	/**
	 * Your Trie object will be instantiated and called as such: Trie obj = new
	 * Trie(); obj.insert(word); boolean param_2 = obj.search(word); boolean
	 * param_3 = obj.startsWith(prefix);
	 */

	private Trie trie = null;
	private Set<String> ans = null;

	public List<String> findWords(char[][] board, String[] words) {
		trie = new Trie();
		int rows = board.length;
		int cols = board[0].length;

		for (String str : words) {
			trie.insert(str);
		}

		ans = new HashSet<String>();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				traverse(board, i, j, "", rows, cols);
			}
		}

		return new ArrayList<>(ans);
	}

	private void traverse(char[][] board, int i, int j, String sbWord, int rows, int cols) {
		if (i < 0 || j < 0 || i >= rows || j >= cols || board[i][j] == '#') {
			// trie.insert(sbWord);

			// System.out.println(sbWord.toString());
		} else {
			sbWord += (board[i][j]);

			if (!trie.startsWith(sbWord))
				return;

			if (trie.search(sbWord))
				ans.add(sbWord);

			char ch = board[i][j];
			board[i][j] = '#';
			// left
			traverse(board, i, j - 1, sbWord, rows, cols);
			// right
			traverse(board, i, j + 1, sbWord, rows, cols);
			// up
			traverse(board, i - 1, j, sbWord, rows, cols);
			// down
			traverse(board, i + 1, j, sbWord, rows, cols);
			board[i][j] = ch;

		}
	}
}
