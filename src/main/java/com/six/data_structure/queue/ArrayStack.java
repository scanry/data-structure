package com.six.data_structure.queue;

import java.util.Arrays;

/**
 * @author sixliu
 * @date 2018年1月10日
 * @email 359852326@qq.com
 * @Description
 */
public class ArrayStack<T> implements Stack<T> {

	private Object[] array;
	private int size;

	public ArrayStack(int initialCapacity) {
		array = new Object[initialCapacity];

	}

	@Override
	public void push(T data) {
		if (size == array.length) {
			array = Arrays.copyOf(array, array.length * 2);
		}
		array[size++] = data;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T pop() {
		T data = null;
		if (size > 0) {
			data = (T) array[--size];
		}
		return data;
	}

	@Override
	public int size() {
		return size;
	}

}
