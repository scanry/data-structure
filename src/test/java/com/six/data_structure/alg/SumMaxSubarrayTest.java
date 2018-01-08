package com.six.data_structure.alg;

import java.util.Random;

import com.six.data_structure.alg.SumMaxSubarray.MaxSubarray;

/**
 * @author sixliu
 * @date 2018年1月6日
 * @email 359852326@qq.com
 * @Description
 */
public class SumMaxSubarrayTest {

	@FunctionalInterface
	interface Process {
		MaxSubarray process();
	}
	static int size = 100000000;
	static int bound = 100000;
	static int loopCount = 3;
	public static void main(String[] args) {
		int[] nums = newNums(size, bound);
		//int[] nums = new int[] {10,-20,10,30,40,-30,50};
		MaxSubarray maxSubarray1=totalSpendTime("sumMaxSubarrayByDivide", () -> {
			System.out.println("----------------------------");
			MaxSubarray maxSubarray = SumMaxSubarray.sumMaxSubarrayByDivide(nums);
			System.out.println("low:" + maxSubarray.getLow());
			System.out.println("high:" + maxSubarray.getHigh());
			System.out.println("sum:" + maxSubarray.getSum());
			System.out.println("实际和:" + sum(nums, maxSubarray.getLow(), maxSubarray.getHigh()));
			return maxSubarray;
		}, loopCount);

		MaxSubarray maxSubarray2=totalSpendTime("sumMaxSubarrayByLinear", () -> {
			MaxSubarray maxSubarray = SumMaxSubarray.sumMaxSubarrayByLinear(nums);
			System.out.println("----------------------------");
			System.out.println("low:" + maxSubarray.getLow());
			System.out.println("high:" + maxSubarray.getHigh());
			System.out.println("sum:" + maxSubarray.getSum());
			System.out.println("实际和:" + sum(nums, maxSubarray.getLow(), maxSubarray.getHigh()));
			return maxSubarray;
		}, loopCount);
		System.out.println("2个算法结果是否一致:" + maxSubarray2.equals(maxSubarray1));
	}

	private static int sum(int[] src, int low, int high) {
		int sum = 0;
		for (int i = low; i <= high; i++) {
			sum += src[i];
		}
		return sum;
	}

	public static MaxSubarray totalSpendTime(String name, Process process, int loopCount) {
		MaxSubarray result=null;
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < loopCount; i++) {
			result=process.process();
		}
		long endTime = System.currentTimeMillis();
		System.out.println(name + ":" + (endTime - startTime) / loopCount);
		return result;
	}

	protected static int[] newNums(int size, int bound) {
		int[] nums = new int[size];
		int[] signs = { 1, -1 };
		Random random = new Random();
		Random signRandom = new Random();
		for (int i = 0; i < size; i++) {
			nums[i] = random.nextInt(bound) * signs[signRandom.nextInt(2)];
		}
		return nums;
	}

}
