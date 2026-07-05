package dev.atanu.ds.java.graph.word.ladder;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class WordLadder {

	public static void main(String[] args) {
	}

	/**
	 * https://leetcode.com/problems/word-ladder/
	 * 
	 * @param beginWord
	 * @param endWord
	 * @param wordList
	 * @return
	 */
	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		Set<String> visited = new HashSet<>(wordList);
		if (!visited.contains(endWord)) {
			return 0;
		}

		Queue<String> queue = new LinkedList<>();
		queue.offer(beginWord);
		int depth = 0;

		while (!queue.isEmpty()) {
			int size = queue.size();
			depth += 1;
			for (int s = 0; s < size; s++) {
				String word = queue.poll();
				if (word.equals(endWord)) {
					return depth;
				}
				for (int i = 0; i < word.length(); i++) {
					char[] temp = word.toCharArray();
					for (int j = 97; j <= 122; j++) {
						if (temp[i] == (char) j) {
							continue;
						}
						temp[i] = (char) j;
						String tempStr = new String(temp);
						if (visited.contains(tempStr)) {
							queue.offer(tempStr);
							visited.remove(tempStr);
						}
					}
				}
			}
		}
		return 0;
	}

}
