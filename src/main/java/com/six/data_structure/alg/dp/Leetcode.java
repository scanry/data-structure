package com.six.data_structure.alg.dp;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


/**
 * @author:MG01867
 * @date:2018年10月18日
 * @email:359852326@qq.com
 * @version:
 * @describe //TODO
 */
public class Leetcode {

	public static void main(String[] args) {
		int[] nums = new int[] { 2, 7, 11, 15 };
		int target = 9;
		Integer[] result = twoSum(nums, target);
		printlnArray(result);
	}

	/**
	 * 两数求和
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public static Integer[] twoSum(int[] nums, int target) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			int complement = target - nums[i];
			if (map.containsKey(complement)) {
				return new Integer[] { complement, nums[i] };
			}
			map.put(nums[i], i);
		}
		throw new IllegalArgumentException("No two sum solution");
	}

	/**
	 * 给定两个非空链表来表示两个非负整数。位数按照逆序方式存储，它们的每个节点只存储单个数字。将两数相加返回一个新的链表。 你可以假设除了数字 0
	 * 之外，这两个数字都不会以零开头。
	 * 
	 * @param l1
	 * @param l2
	 * @return
	 */
	public LinkedList<Integer> addTwoNumbers(LinkedList<Integer> num1, LinkedList<Integer> num2) {
		return null;
	}

	static <T> void printlnArray(T[] array) {
		if (null != array) {
			for (T item : array) {
				System.out.println(item);
			}
		}
	}

}
