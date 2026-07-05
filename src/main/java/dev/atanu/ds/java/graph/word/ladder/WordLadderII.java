package dev.atanu.ds.java.graph.word.ladder;

import java.util.*;

public class WordLadderII {

	public static void main(String[] args) {

	}

	/**
	 * https://leetcode.com/problems/word-ladder-ii/
	 * 
	 * @param beginWord - beginWord
	 * @param endWord - endWord
	 * @param wordList - wordList
	 * @return words
	 */
	public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
		List<List<String>> paths = new ArrayList<>();
		Set<String> dictionary = new HashSet<>(wordList);
		Map<String, Integer> visited = new HashMap<>();
		Map<String, List<String>> adjacents = new HashMap<>();
		if (!dictionary.contains(endWord)) {
			return paths;
		}

		int depth = 0;
		Queue<String> queue = new LinkedList<>();
		queue.offer(beginWord);

		while (!queue.isEmpty()) {
			int size = queue.size();
			depth += 1;
			for (int s = 0; s < size; s++) {
				String word = queue.poll();
				for (int i = 0; i < word.length(); i++) {
					char[] temp = word.toCharArray();
					if (temp[i] == (char) i) {
						continue;
					}
					for (int j = 97; j <= 122; j++) {
						temp[i] = (char) j;
						String tempStr = new String(temp);
						if (dictionary.contains(tempStr)) {
							if (!visited.containsKey(tempStr)) {
								queue.offer(tempStr);
								visited.put(tempStr, depth + 1);
								List<String> adjs = adjacents.get(word);
								if (adjs == null) {
									adjs = new ArrayList<>();
								}
								adjs.add(tempStr);
								adjacents.put(word, adjs);
							} else if (visited.get(tempStr) == depth + 1) {
								List<String> adjs = adjacents.get(word);
								if (adjs == null) {
									adjs = new ArrayList<>();
								}
								adjs.add(tempStr);
								adjacents.put(word, adjs);
							}
						}
					}
				}

			}
		}
		List<String> path = new ArrayList<>();
		path.add(beginWord);
		findLadders(beginWord, endWord, adjacents, path, paths);
		return paths;
	}

	private void findLadders(String beginWord, String endWord, Map<String, List<String>> adjacents, List<String> path,
			List<List<String>> paths) {
		if (beginWord.equals(endWord)) {
			paths.add(new ArrayList<>(path));
		} else {
			List<String> adjs = adjacents.get(beginWord);
			if (adjs != null) {
				for (String word : adjs) {
					if (!beginWord.equals(word)) {
						path.add(word);
						findLadders(word, endWord, adjacents, path, paths);
						path.remove(path.size() - 1);
					}
				}
			}
		}
	}

}
