package com.six.data_structure.alg;

/**
 * @author sixliu
 * @date 2018年1月6日
 * @email 359852326@qq.com
 * @Description
 */
public class SumMaxSubarray {

	public static MaxSubarray sumMaxSubarray(int[] src) {
		return sumMaxSubarray(src, 0, src.length-1);
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
	}
}
