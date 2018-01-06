package com.six.data_structure.sort;

import java.lang.reflect.Array;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

/**
 * @author sixliu
 * @date 2018年1月5日
 * @email 359852326@qq.com
 * @Description
 */
public class SortAlg {

	/**
	 * 冒泡排序
	 * 
	 * @param list
	 */
	public static <T extends Comparable<T>> void bubbleSort(List<T> list) {
		Comparator<T> comparator = (T t1, T t2) -> {
			return ((Comparable<T>) t1).compareTo(t2);
		};
		bubbleSort(list, comparator);
	}

	/**
	 * 冒泡排序
	 * 
	 * @param list
	 * @param comparator
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> void bubbleSort(List<T> list, Comparator<T> comparator) {
		Objects.requireNonNull(list);
		Object[] arrays = list.toArray();
		bubbleSort(arrays, (Comparator) comparator);
		ListIterator<T> i = list.listIterator();
		for (Object e : arrays) {
			i.next();
			i.set((T) e);
		}
	}

	/**
	 * 冒泡排序
	 * 
	 * @param arrays
	 * @param comparator
	 */
	public static <E> void bubbleSort(E[] arrays, Comparator<E> comparator) {
		Objects.requireNonNull(arrays);
		Objects.requireNonNull(comparator);
		if (arrays.length > 1) {
			E temp = null;
			for (int j = 0; j < arrays.length; j++) {
				int end = arrays.length - j - 1;
				for (int i = j; i < end; i++) {
					if (comparator.compare(arrays[i], arrays[i + 1]) > 0) {
						temp = arrays[i];
						arrays[i] = arrays[i + 1];
						arrays[i + 1] = temp;
					}
				}
			}
		}
	}

	/**
	 * 最差执行=n平方次 最好执行=n次 经过测试当n>2的10次方后性能明显低于归并排序
	 * 
	 * @param list
	 * @param comparator
	 */
	public static <T extends Comparable<T>> void insetSort(List<T> list) {
		Comparator<T> comparator = (T t1, T t2) -> {
			return ((Comparable<T>) t1).compareTo(t2);
		};
		insetSort(list, comparator);
	}

	/**
	 * 最差执行=n平方次 最好执行=n次
	 * 
	 * @param list
	 * @param comparator
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> void insetSort(List<T> list, Comparator<T> comparator) {
		Objects.requireNonNull(list);
		Object[] arrays = list.toArray();
		insetSort(arrays, (Comparator) comparator);
		ListIterator<T> i = list.listIterator();
		for (Object e : arrays) {
			i.next();
			i.set((T) e);
		}
	}

	/**
	 * 最差执行=n平方次 最好执行=n次
	 * 
	 * @param list
	 * @param comparator
	 */
	public static <E> void insetSort(E[] arrays, Comparator<E> comparator) {
		Objects.requireNonNull(arrays);
		Objects.requireNonNull(comparator);
		if (arrays.length > 1) {
			E temp = null;
			E compareTemp = null;
			for (int j = 1; j < arrays.length; j++) {
				temp = arrays[j];
				int i = j - 1;
				while (i >= 0 && comparator.compare(temp, compareTemp = arrays[i]) < 0) {
					arrays[i + 1] = compareTemp;
					i--;
				}
				arrays[i + 1] = temp;
			}
		}
	}

	/**
	 * 归并排序
	 * 
	 * @param list
	 */
	public static <T extends Comparable<T>> void mergeSort(List<T> list) {
		Comparator<T> comparator = (T t1, T t2) -> {
			return ((Comparable<T>) t1).compareTo(t2);
		};
		mergeSort(list, comparator);
	}

	/**
	 * 归并排序
	 * 
	 * @param list
	 * @param comparator
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> void mergeSort(List<T> list, Comparator<T> comparator) {
		Objects.requireNonNull(list);
		Object[] array = list.toArray();
		T[] tempArray = (T[]) Array.newInstance(array.getClass().getComponentType(), array.length);
		mergeSort(array, tempArray, (Comparator) comparator);
		ListIterator<T> i = list.listIterator();
		for (Object e : array) {
			i.next();
			i.set((T) e);
		}
	}

	/**
	 * 归并排序
	 * 
	 * @param list
	 * @param comparator
	 */
	public static <T> void mergeSort(T[] array, T[] tempArray, Comparator<T> comparator) {
		Objects.requireNonNull(array);
		Objects.requireNonNull(tempArray);
		Objects.requireNonNull(comparator);
		mergeSort(array, 0, array.length - 1, tempArray, comparator);
	}

	private static <T> void mergeSort(T[] array, int left, int right, T[] tempArray, Comparator<T> comparator) {
		if (left < right) {
			int mid = (left + right) / 2;
			mergeSort(array, left, mid, tempArray, comparator);
			mergeSort(array, mid + 1, right, tempArray, comparator);
			merge(array, left, mid, right, tempArray, comparator);
		}
	}

	private static <T> void merge(T[] array, int left, int mid, int right, T[] tempArray, Comparator<T> comparator) {
		int i = left;// 左序列指针
		int j = mid + 1;// 右序列指针
		int t = 0;// 临时数组指针
		while (i <= mid && j <= right) {
			if (comparator.compare(array[i], array[j]) <= 0) {
				tempArray[t++] = array[i++];
			} else {
				tempArray[t++] = array[j++];
			}
		}
		while (i <= mid) {// 将左边剩余元素填充进temp中
			tempArray[t++] = array[i++];
		}
		while (j <= right) {// 将右序列剩余元素填充进temp中
			tempArray[t++] = array[j++];
		}
		t = 0;
		// 将temp中的元素全部拷贝到原数组中
		int lenght = (right - left) + 1;
		if (lenght < 3) {
			while (left <= right) {
				array[left++] = tempArray[t++];
			}
		} else {
			System.arraycopy(tempArray, 0, array, left, lenght);
		}
	}
}