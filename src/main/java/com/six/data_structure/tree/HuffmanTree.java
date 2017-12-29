package com.six.data_structure.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

/**
 * @author sixliu
 * @date 2017年12月29日
 * @email 359852326@qq.com
 * @Description
 */
public class HuffmanTree<T> {

	public static class HuffmanTreeNode<T> implements Comparable<HuffmanTreeNode<T>> {
		private T value;
		private int weight;
		private HuffmanTreeNode<T> parent;
		private HuffmanTreeNode<T> left;
		private HuffmanTreeNode<T> right;

		public HuffmanTreeNode(T value, int weight) {
			this.value = value;
			this.weight = weight;
		}

		public T getValue() {
			return value;
		}

		public int getWeight() {
			return weight;
		}

		public HuffmanTreeNode<T> getParent() {
			return parent;
		}

		public HuffmanTreeNode<T> getLift() {
			return left;
		}

		public HuffmanTreeNode<T> getRight() {
			return right;
		}

		@Override
		public int compareTo(HuffmanTreeNode<T> other) {
			if (other.getWeight() > this.getWeight()) {
				return 1;
			}
			if (other.getWeight() < this.getWeight()) {
				return -1;
			}
			return 0;
		}

		@Override
		public String toString() {
			return "value:" + this.value + ";weight:" + this.weight;
		}

	}

	private HuffmanTreeNode<T> root;

	public HuffmanTree(List<HuffmanTreeNode<T>> nodes) {
		this.root = build(nodes);
	}

	private static <T> HuffmanTreeNode<T> build(List<HuffmanTreeNode<T>> nodes) {
		HuffmanTreeNode<T> parent = null;
		HuffmanTreeNode<T> left = null;
		HuffmanTreeNode<T> right = null;
		while (nodes.size() > 1) {
			Collections.sort(nodes);
			left = nodes.get(nodes.size() - 1);
			right = nodes.get(nodes.size() - 2);
			parent = new HuffmanTreeNode<T>(null, left.weight + right.weight);
			left.parent=parent;
			right.parent=parent;
			parent.left = left;
			parent.right = right;
			nodes.remove(left);
			nodes.remove(right);
			nodes.add(parent);
		}
		return nodes.get(0);
	}

	public List<HuffmanTreeNode<T>> toList() {
		List<HuffmanTreeNode<T>> list = new ArrayList<HuffmanTreeNode<T>>();
		Queue<HuffmanTreeNode<T>> queue = new ArrayDeque<HuffmanTreeNode<T>>();
		if (root != null) {
			queue.offer(root);
		}
		while (!queue.isEmpty()) {
			list.add(queue.peek());
			HuffmanTreeNode<T> node = queue.poll();

			if (node.left != null) {
				queue.offer(node.left);
			}
			if (node.right != null) {
				queue.offer(node.right);
			}
		}
		return list;
	}
}
