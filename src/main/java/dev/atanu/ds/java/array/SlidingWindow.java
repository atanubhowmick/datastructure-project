/**
 * 
 */
package dev.atanu.ds.java.array;

/**
 * https://leetcode.com/discuss/study-guide/3722472/mastering-sliding-window-technique-a-comprehensive-guide
 * 
 * @author Atanu Bhowmick
 *
 */
public class SlidingWindow {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SlidingWindow slidingWindow = new SlidingWindow();
		int[] arr = new int[] { 0, 1, 1, 1, 0, 1, 1, 0, 1 };
		System.out.println(slidingWindow.numberOfArithmeticSlices(arr));
	}

	
	/**
	 * https://leetcode.com/problems/arithmetic-slices/
	 * 
	 * @param nums
	 * @return
	 */
	public int numberOfArithmeticSlices(int[] nums) {
		int count = 0;
		for (int i = 0; i < nums.length - 2; i++) {
			int prevDiff = nums[i + 1] - nums[i];
			int j = i + 1;
			while (j + 1 < nums.length && nums[j + 1] - nums[j] == prevDiff) {
				count++;
				j++;
			}
		}

		return count;
	}

	
	/**
	 * https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/
	 * 
	 * @param s
	 * @return
	 */
	public int numberOfSubstrings(String s) {
		int count = 0, n = s.length();
		int p1 = -1, p2 = -1, p3 = -1;

		for (int i = 0; i < n; i++) {
			if (s.charAt(i) == 'a') {
				p1 = i;
			} else if (s.charAt(i) == 'b') {
				p2 = i;
			} else {
				p3 = i;
			}

			if (p1 == -1 || p2 == -1 || p3 == -1) {
				continue;
			}

			int min = Math.min(p1, Math.min(p2, p3));
			count += min + 1;
		}
		return count;
	}
	

	/**
	 * https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/
	 * 
	 * @param s
	 * @return
	 */
	public int numberOfSubstrings1(String s) {
		int count = 0, n = s.length();
		int lastIdx[] = { -1, -1, -1 };

		for (int i = 0; i < n; ++i) {
			lastIdx[s.charAt(i) - 'a'] = i;
			int min = Math.min(lastIdx[0], Math.min(lastIdx[1], lastIdx[2]));
			count += min + 1;
		}
		return count;
	}
}
