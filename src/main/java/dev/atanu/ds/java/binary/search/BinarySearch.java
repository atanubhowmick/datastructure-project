/**
 * 
 */
package dev.atanu.ds.java.binary.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Atanu Bhowmick
 *
 */
public class BinarySearch {

	public static void main(String[] args) {
		BinarySearch binarySearch = new BinarySearch();
		int[] arr = {4, 7, 11, 15, 20, 24};
		int searchInt = 7;
		System.out.println(binarySearch.search1(arr, searchInt));
		System.out.println(binarySearch.lengthOfLIS(new int[] {0,1,0,3,2,3}));

	}


	/**
	 * This is classic approach. Next one is better.
	 *
	 * @param arr
	 * @param target
	 * @return
	 */
	public int search(int[] arr, int target) {
		int first = 0;
		int last = arr.length - 1;
		int mid = (first + last) / 2;
		boolean found = false;
		while (first <= last) {
			if (target == arr[mid]) {
				found = true;
				break;
			} else if (target < arr[mid]) {
				last = mid - 1;
				mid = (first + last) / 2;
			} else {
				first = mid + 1;
				mid = (first + last) / 2;
			}
		}
		return found ? mid : -1;
	}

	/**
	 *
	 *
	 * @param arr
	 * @param target
	 * @return
	 */
	public int search1(int[] arr, int target) {
		int first = 0;
		int last = arr.length - 1;
		while (first < last) {
			int mid = (first + last) >>> 1;
			if (target > arr[mid]) {
				first = mid + 1;
			} else {
				last = mid;
			}
		}
		return arr[first] == target ? first : -1;
	}


	/**
	 * https://leetcode.com/problems/search-insert-position
	 *
	 * @param nums
	 * @param target
	 * @return insert position
	 */
	public int searchInsert(int[] nums, int target) {
		int start = 0, end = nums.length - 1;
		while(start < end) {
			int mid = (start + end) >>> 1;
			if(target > nums[mid]) {
				start = mid + 1;
			} else {
				end = mid;
			}
		}
		return target > nums[start] ? start + 1 : start;
	}


	/**
	 * https://leetcode.com/problems/longest-increasing-subsequence/
	 *
	 * Given an integer array nums, return the length of the longest
	 * strictly increasing subsequence.
	 * <br>
	 * Input: nums = [10,9,2,5,3,7,101,18]
	 * Output: 4
	 *
	 * @param nums
	 * @return
	 */
	public int lengthOfLIS(int[] nums) {
		List<Integer> list = new ArrayList<>();
		for(int i = 0; i < nums.length - 1; i++) {
			int num = nums[i];
			if(list.isEmpty() || list.get(list.size() - 1) < num) {
				list.add(num);
			} else {
				int index = getIndex(list, num);
				list.set(index, num);
			}
		}
		return list.size();
	}

	private int getIndex(List<Integer> list, int num) {
		int start = 0, end = list.size() - 1;
		while(start < end) {
			int mid = (start + end) >>> 1;
			if(num > list.get(mid)) {
				start = mid + 1;
			} else {
				end = mid;
			}
		}
		return start;
	}
}
