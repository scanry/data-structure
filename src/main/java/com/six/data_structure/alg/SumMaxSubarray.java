package com.six.data_structure.alg;

import java.util.Objects;

/**
 * @author sixliu
 * @date 2018年1月6日
 * @email 359852326@qq.com
 * @Description
 */
public class SumMaxSubarray {

	/**
	 * 动态规划求最大和子数组 只有数组同时包含正负数才有意义
	 * 
	 * @param src
	 * @return
	 */
	public static MaxSubarray sumMaxSubarrayByLinear(int[] src) {
		Objects.requireNonNull(src);
		Objects.checkFromIndexSize(0, src.length, src.length);
		int maxSum = src[0];
		int low = 0;
		int high = 0;
		int tempSum = 0;
		int tempLow = 0;
		for (int i = 1; i < src.length; i++) {
			tempSum += src[i];
			if (0 == tempLow) {
				tempLow = i;
			}
			if (tempSum > maxSum) {
				low = tempLow;
				maxSum = tempSum;
				high = i;
			}
			// 当统计小于等于0时，那么将tempSum和tempLow重新初始化
			if (tempSum <= 0) {
				tempSum = 0;
				tempLow = 0;
			}
		}
		return new MaxSubarray(low, high, maxSum);
	}

	/**
	 * 分治递归求最大和子数组 只有数组同时包含正负数才有意义
	 * 
	 * @param src
	 * @return
	 */
	public static MaxSubarray sumMaxSubarrayByDivide(int[] src) {
		Objects.requireNonNull(src);
		return sumMaxSubarray(src, 0, src.length - 1);
	}

	private static MaxSubarray findMaxSubarrayCrossing(int[] src, int low, int mid, int high) {
		int sum = 0;
		int leftSum = 0;
		int maxLeft = 0;
		for (int i = mid; i >= low; i--) {
			sum += src[i];
			if (sum > leftSum) {
				leftSum = sum;
				maxLeft = i;
			}
		}
		sum = 0;
		int rightSum = 0;
		int maxRight = 0;
		for (int i = mid + 1; i <= high; i++) {
			sum += src[i];
			if (sum > rightSum) {
				rightSum = sum;
				maxRight = i;
			}
		}
		return new MaxSubarray(maxLeft, maxRight, leftSum + rightSum);
	}

	private static MaxSubarray sumMaxSubarray(int[] src, int low, int high) {
		if (low == high) {
			return new MaxSubarray(low, high, src[low]);
		} else {
			int mid = (low + high) / 2;
			MaxSubarray leftMaxChildrenArray = sumMaxSubarray(src, low, mid);
			MaxSubarray rightMaxChildrenArray = sumMaxSubarray(src, mid + 1, high);
			MaxSubarray crossingMaxChildrenArray = findMaxSubarrayCrossing(src, low, mid, high);
			if (leftMaxChildrenArray.getSum() >= rightMaxChildrenArray.getSum()
					&& leftMaxChildrenArray.getSum() >= crossingMaxChildrenArray.getSum()) {
				return leftMaxChildrenArray;
			} else if (rightMaxChildrenArray.getSum() >= leftMaxChildrenArray.getSum()
					&& rightMaxChildrenArray.getSum() >= crossingMaxChildrenArray.getSum()) {
				return rightMaxChildrenArray;
			} else {
				return crossingMaxChildrenArray;
			}
		}
	}

	public static class MaxSubarray {
		private int low;
		private int high;
		private int sum;

		private MaxSubarray(int low, int high, int sum) {
			this.low = low;
			this.high = high;
			this.sum = sum;
		}

		public int getLow() {
			return low;
		}

		public int getHigh() {
			return high;
		}

		public int getSum() {
			return sum;
		}

		public boolean equals(Object ob) {
			if (null != ob && ob instanceof MaxSubarray) {
				MaxSubarray targetOb = (MaxSubarray) ob;
				if (this.low == targetOb.low && this.high == targetOb.high && this.sum == targetOb.sum) {
					return true;
				}
			}
			return false;
		}
	}
}
