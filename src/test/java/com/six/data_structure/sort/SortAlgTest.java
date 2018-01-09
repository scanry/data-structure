package com.six.data_structure.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
		int size = (int) Math.pow(2, 6);
		System.out.println("sort list size:" + size);
		int bound = 100;
		int loopCount = 3;
		boolean isPrint = true;
		List<Integer> newList = newList(size, bound);
		List<Integer> correctList = totalSpendTime("java sort", (list) -> {
			Collections.sort(list);
		}, newList, null, loopCount, false);
		totalSpendTime("bubble sort", (list) -> {
			SortAlg.bubbleSort(list);
		}, newList, correctList, loopCount, isPrint);

		totalSpendTime("insert sort", (list) -> {
			SortAlg.insetSort(list);
		}, newList, correctList, loopCount, isPrint);

		totalSpendTime("merge sort", (list) -> {
			SortAlg.mergeSort(list);
		}, newList, correctList, loopCount, isPrint);

		totalSpendTime("heap sort", (list) -> {
			SortAlg.heapSort(list);
		}, newList, correctList, loopCount, isPrint);

		totalSpendTime("qucik sort", (list) -> {
			SortAlg.quickSort(list);
		}, newList, correctList, loopCount, isPrint);

	}

	@SuppressWarnings("unchecked")
	public static <T> boolean checkSortResult(List<T> correctList, List<T> list) {
		Comparator<T> comparator = (T t1, T t2) -> {
			return ((Comparable<T>) t1).compareTo(t2);
		};
		boolean result = true;
		for (int i = 0, size = correctList.size(); i < size; i++) {
			if (comparator.compare(correctList.get(i), list.get(i)) != 0) {
				result = false;
				break;
			}
		}
		return result;
	}

	public static <T> List<T> totalSpendTime(String sortName, SortProcess<T> sortProcess, List<T> list,
			List<T> correctList, int loopCount, boolean isPrint) {
		List<T> copyList = new ArrayList<>(list);
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
		if (isPrint) {
			System.out.println(sortName + " time:" + (endTime - startTime) / loopCount);
			if (null != correctList && correctList.size() == list.size()) {
				System.out.println(sortName + " 结果:" + checkSortResult(correctList, copyList));
			}
		}
		return copyList;
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
