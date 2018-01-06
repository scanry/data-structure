package com.six.data_structure.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author sixliu
 * @date 2018年1月5日
 * @email 359852326@qq.com
 * @Description
 */
public class SortAlgTest {

	@FunctionalInterface
	interface SortProcess<T> {
		void sort(List<T> list);
	}

	public static void main(String[] args) {
		int size = (int) Math.pow(2, 15);
		System.out.println("size:" + size);
		int bound = 100;
		int loopCount =20;
		boolean isPrint = false;
		List<Integer> newList=newList(size, bound);
		totalSpendTime("java sort", (list) -> {
			Collections.sort(list);
		}, newList, loopCount, isPrint);
		totalSpendTime("bubble sort", (list) -> {
			SortAlg.bubbleSort(list);
		}, newList, loopCount, isPrint);
		totalSpendTime("insert sort", (list) -> {
			SortAlg.insetSort(list);
		}, newList, loopCount, isPrint);
		totalSpendTime("merge sort", (list) -> {
			SortAlg.mergeSort(list);
		}, newList, loopCount, isPrint);
	}

	public static <T> void totalSpendTime(String sortName, SortProcess<T> sortProcess, List<T> list, int loopCount,
			boolean isPrint) {
		List<T> copyList=new ArrayList<>(list);
		if (isPrint) {
			prinitList(copyList);
		}
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < loopCount; i++) {
			sortProcess.sort(copyList);
		}
		long endTime = System.currentTimeMillis();
		if (isPrint) {
			prinitList(copyList);
		}
		System.out.println(sortName + ":" + (endTime - startTime) / loopCount);
	}

	protected static List<Integer> newList(int size, int bound) {
		List<Integer> list = new ArrayList<>(size);
		Random random = new Random();
		for (int i = 0; i < size; i++) {
			list.add(random.nextInt(bound));
		}
		return list;
	}

	protected static <T extends Comparable<T>> void doInsertSrot(List<T> list) {
		System.out.println("排序前");
		prinitList(list);
		SortAlg.insetSort(list);
		System.out.println("排序后");
		prinitList(list);
	}

	protected static <T extends Comparable<T>> void doMergeSort(List<T> list) {
		System.out.println("排序前");
		prinitList(list);
		SortAlg.mergeSort(list);
		System.out.println("排序后");
		prinitList(list);
	}

	private static void prinitList(List<?> list) {
		for (Object ob : list) {
			System.out.print(ob + "\t");
		}
		System.out.println();
	}

}
