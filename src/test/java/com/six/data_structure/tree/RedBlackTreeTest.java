package com.six.data_structure.tree;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * @author sixliu
 * @date 2018年1月11日
 * @email 359852326@qq.com
 * @Description
 */
public class RedBlackTreeTest {

	public static void main(String[] args) {
		int size = 20;
		Consumer<Integer> consumer = value -> System.out.print(value + "  ");
		RedBlackTree<Integer> redBlackTree = new RedBlackTree<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1.compareTo(o2);
			}
		});
		for (int i = 0; i < size; i++) {
			redBlackTree.add(i);
		}
		System.out.print("红黑树测试\n");
		redBlackTree.preOrder(consumer);
		System.out.print("\n");
		redBlackTree.midOrder(consumer);
		System.out.print("\n");
		redBlackTree.postOrder(consumer);
		System.out.print("\n");
	}

}
