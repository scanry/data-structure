package com.six.data_structure;

import com.six.data_structure.queue.ArrayStack;
import com.six.data_structure.queue.Stack;

/**
 * @author sixliu
 * @date 2018年1月10日
 * @email 359852326@qq.com
 * @Description
 */
public class ArrayStackTest {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		int size=3;
		Stack<String> stack = new ArrayStack<>(size);
		for (int i = 0; i < size; i++) {
			stack.push("" + i);
		}
		String data=null;
		while(null!=(data=stack.pop())) {
			System.out.println(data);
		}
	}

}
