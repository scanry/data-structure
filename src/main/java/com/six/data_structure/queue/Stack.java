package com.six.data_structure.queue;

/**
 * @author sixliu
 * @date 2018年1月10日
 * @email 359852326@qq.com
 * @Description
 */
public interface Stack<T> {

	void push(T data);

	T pop();

	int size();

	default boolean isEmpty() {
		return 0 == size();
	}
}
