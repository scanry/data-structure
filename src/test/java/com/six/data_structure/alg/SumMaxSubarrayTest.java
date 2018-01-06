package com.six.data_structure.alg;

import com.six.data_structure.alg.SumMaxSubarray.MaxSubarray;

/**
 * @author sixliu
 * @date 2018年1月6日
 * @email 359852326@qq.com
 * @Description
 */
public class SumMaxSubarrayTest {

	public static void main(String[] args) {
		int[] nums = new int[] { -10, 20, -20, 40, 10, -30, 90 };
		MaxSubarray maxSubarray = SumMaxSubarray.sumMaxSubarray(nums);
		System.out.println("low:" + maxSubarray.getLow());
		System.out.println("high:" + maxSubarray.getHigh());
		System.out.println("sum:" + maxSubarray.getSum());
	}

}
