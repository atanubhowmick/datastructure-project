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
	 * This is classic approach.
	 * I have created two templates to solve all the Binary Search problems.
	 * Take a look into the templates below.
	 *
	 * @param arr
	 * @param target
	 * @return Search result
	 */
	public int search(int[] arr, int target) {
		int start = 0;
		int end = arr.length - 1;
		int mid = (start + end) / 2;
		boolean found = false;
		while (start <= end) {
			if (target == arr[mid]) {
				found = true;
				break;
			} else if (target < arr[mid]) {
				end = mid - 1;
				mid = (start + end) / 2;
			} else {
				start = mid + 1;
				mid = (start + end) / 2;
			}
		}
		return found ? mid : -1;
	}

	/**
	 * Template - 1
	 * Without equals condition inside while loop
	 *
	 * This is for boundary search
	 * Could be as well --> int right = arr.length;
	 *
	 * @param arr
	 * @param target
	 * @return index
	 */
	public int search1(int[] arr, int target) {
		int left = 0;
		int right = arr.length - 1;
		while (left < right) {
			int mid = (left + right) >>> 1;
			if (target > arr[mid]) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return arr[left] == target ? left : -1;
	}


	/**
	 * Template - 2
	 * With equals condition inside while loop.
	 * This approach for exact match.
	 *
	 * @param arr
	 * @param target
	 * @return index
	 */
	public int search2(int[] arr, int target) {
		int start = 0;
		int end = arr.length - 1;

		while (start <= end) {
			int mid = start + (end - start) / 2;

			if (arr[mid] == target) {
				return mid;
			} else if (arr[mid] < target) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}

		return -1;
	}


	/**
	 * https://leetcode.com/problems/search-insert-position
	 * Using Template - 1
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
	 * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array
	 *
	 * Using Template - 1
	 *
	 * @param nums
	 * @param target
	 * @return array
	 */
	public int[] searchRange(int[] nums, int target) {
		if (nums.length == 0) {
			return new int[] {-1, -1};
		}

		int first = findPosition(nums, target);
		if (first == nums.length || nums[first] != target) {
			return new int[] {-1, -1};
		}

		int last = findPosition(nums, target + 1) - 1;
		return new int[] {first, last};
	}

	private int findPosition(int[] nums, int target) {
		int left = 0;
		int right = nums.length;
		while (left < right) {
			int mid = left + (right - left) / 2;
			if (nums[mid] < target) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return left;
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


	/**
	 * https://leetcode.com/problems/search-in-rotated-sorted-array
	 *
	 * Using Template -2
	 *
	 * @param nums
	 * @param target
	 * @return
	 */
	public int searchInRotatedSortedArray(int[] nums, int target) {
		int start = 0, end = nums.length - 1;

		while (start <= end) {
			int mid = start + (end - start) / 2;

			if (nums[mid] == target) {
				return mid;
			}

			if (nums[mid] > nums[end]) {
				// left half is sorted
				if (nums[start] <= target && target < nums[mid]) {
					end = mid - 1;
				} else {
					start = mid + 1;
				}
			} else {
				// right half is sorted
				if (nums[mid] < target && target <= nums[end]) {
					start = mid + 1;
				} else {
					end = mid - 1;
				}
			}
		}
		return -1;
	}


	/**
	 * https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
	 *
	 * Using Template -2
	 * @param nums
	 * @param target
	 * @return boolean
	 */
	public boolean searchInRotatedSortedArrayII(int[] nums, int target) {
		int start = 0, end = nums.length - 1;

		while (start <= end) {
			int mid = start + (end - start) / 2;

			if (nums[mid] == target) {
				return true;
			}

			if (nums[mid] > nums[end]) {
				// left half is sorted
				if (nums[start] <= target && target < nums[mid]) {
					end = mid - 1;
				} else {
					start = mid + 1;
				}
			} else if(nums[mid] < nums[end]) {
				// right half is sorted
				if (nums[mid] < target && target <= nums[end]) {
					start = mid + 1;
				} else {
					end = mid - 1;
				}
			} else {
				end--;
			}
		}
		return false;
	}


	/**
	 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array
	 *
	 * Using Template-1 - Shrink to minimum position
	 *
	 * @param nums
	 * @return
	 */
	public int findMin(int[] nums) {
		int start = 0;
		int end = nums.length - 1;

		while(start < end) {
			int mid = start + (end - start)/2;
			if(nums[mid] > nums[end]) {
				start = mid + 1;
			} else {
				end = mid ;
			}
		}
		return nums[start];
		// return nums[end];  - would also work
	}
}
