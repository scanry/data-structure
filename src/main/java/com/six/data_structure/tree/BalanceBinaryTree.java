package com.six.data_structure.tree;

import java.util.Comparator;
import java.util.Objects;
import java.util.Stack;
import java.util.function.Consumer;

/**
 * @author sixliu
 * @date 2017年12月19日
 * @email 359852326@qq.com
 * @Description
 */
public class BalanceBinaryTree<T> {

	private static final class Node<T> {
		private T value;
		private Node<T> parent;
		private Node<T> left;
		private Node<T> right;
		private int height;

		Node(T value, Node<T> parent) {
			this.value = value;
			this.parent = parent;
			this.left = null;
			this.right = null;
			this.height = 1;
		}

		Node<T> add(Comparator<T> comparator, T value) {
			Node<T> insertPosition = null;
			int cmp = comparator.compare(value, this.value);
			if (cmp < 0) {
				insertPosition = this.left;
				if (null == insertPosition) {
					this.left = new Node<>(value, this);
					this.left.value = value;
					this.height++;
					return this;
				}
				int initHeight = this.left.height;
				this.left = insertPosition.add(comparator, value);
				return (left.height == initHeight) ? this : balanceInsert();
			} else if (cmp > 0) {
				insertPosition = this.right;
				if (null == insertPosition) {
					this.right = new Node<>(value, this);
					this.right.value = value;
					this.height++;
					return this;
				}
				int initHeight = this.right.height;
				this.right = insertPosition.add(comparator, value);
				return (right.height == initHeight) ? this : balanceInsert();
			} else {
				return this;
			}
		}

		private static int height(Node<?> node) {
			return (node == null) ? 0 : node.height;
		}

		private int balanceFactor() {
			return height(left) - height(right);
		}

		private void recomputeHeight() {
			this.height = 1 + Math.max(height(left), height(right));
		}

		private Node<T> balanceInsert() {
			switch (balanceFactor()) {
			case -2:
				if (right.balanceFactor() > 0) {
					right = right.rotateRight();
				}
				return rotateLeft();
			case 2:
				if (left.balanceFactor() < 0) {
					left = left.rotateLeft();
				}
				return rotateRight();
			default:
				recomputeHeight();
				return this;
			}
		}

		Node<T> rotateRight() {
			Node<T> newTop, pp, lr;
			if ((newTop = this.left) != null) {
				if ((lr = this.left = newTop.right) != null) {
					lr.parent = this;
				}
				if (null != (pp = newTop.parent = this.parent)) {
					if (pp.right == this) {
						pp.right = newTop;
					} else {
						pp.left = newTop;
					}
				}
				newTop.right = this;
				this.parent = newTop;
				this.recomputeHeight();
				newTop.recomputeHeight();
				return newTop;
			} else {
				throw new IllegalStateException();
			}
		}

		Node<T> rotateLeft() {
			Node<T> newTop, pp, rl;
			if ((newTop = this.right) != null) {
				if ((rl = this.right = newTop.left) != null) {
					rl.parent = this;
				}
				if (null != (pp = newTop.parent = this.parent)) {
					if (pp.left == this) {
						pp.left = newTop;
					} else {
						pp.right = newTop;
					}
				}
				newTop.left = this;
				this.parent = newTop;
				this.recomputeHeight();
				newTop.recomputeHeight();
				return newTop;
			} else {
				throw new IllegalStateException();
			}
		}

		public String toString() {
			return "value=" + value;
		}

	}

	private Node<T> root;
	private Comparator<T> comparator;
	private int size;

	public BalanceBinaryTree(Comparator<T> comparator) {
		Objects.requireNonNull(comparator);
		this.comparator = comparator;
	}

	public void add(T value) {
		Objects.requireNonNull(value);
		if (null == root) {
			root = new Node<>(value, null);
			size = 1;
			return;
		} else {
			root = root.add(comparator, value);
			size++;
		}
	}

	public Node<T> remove(T value) {
		Node<T> findNode = findNode(value);
		Node<T> parent = findNode.parent;
		if (null == findNode.left && null == findNode.right) {
			if (null != parent && parent.left == findNode) {
				parent.left = null;
			} else if (null != parent && parent.right == findNode) {
				parent.right = null;
			} else {
				root = null;
			}
		}
		if (root == findNode) {
			root = findNode.left;
		}
		return findNode;
	}

	Node<T> findNode(T value) {
		return null;
	}

	public void preOrder(Consumer<T> consumer) {
		Objects.requireNonNull(consumer);
		preOrder(root, consumer);
	}

	private void preOrder(Node<T> root, Consumer<T> consumer) {
		if (root != null) {
			consumer.accept(root.value);
			preOrder(root.left, consumer);
			preOrder(root.right, consumer);
		}
	}
	
	public void preOrderByloop(Consumer<T> consumer) {
		if (root != null) {
			Stack<Node<T>> stack = new Stack<>();
			Node<T> tempNode = root;
			while (null != tempNode || !stack.isEmpty()) {
				while (null != tempNode) {
					consumer.accept(tempNode.value);
					stack.push(tempNode);
					tempNode = tempNode.left;
				}
				if (!stack.isEmpty()) {
					tempNode = stack.pop();
					tempNode = tempNode.right;
				}
			}
		}
	}

	public void midOrder(Consumer<T> consumer) {
		Objects.requireNonNull(consumer);
		midOrderByRecursion(root, consumer);
	}

	protected void midOrderByRecursion(Node<T> root, Consumer<T> consumer) {
		if (root != null) {
			midOrderByRecursion(root.left, consumer);
			consumer.accept(root.value);
			midOrderByRecursion(root.right, consumer);
		}
	}

	public void midOrderByloop(Consumer<T> consumer) {
		if (root != null) {
			Stack<Node<T>> stack = new Stack<>();
			Node<T> tempNode = root;
			while (null != tempNode || !stack.isEmpty()) {
				while (null != tempNode) {
					stack.push(tempNode);
					tempNode = tempNode.left;
				}
				if (!stack.isEmpty()) {
					tempNode = stack.pop();
					consumer.accept(tempNode.value);
					tempNode = tempNode.right;
				}
			}
		}
	}

	public void postOrder(Consumer<T> consumer) {
		Objects.requireNonNull(consumer);
		postOrder(root, consumer);
	}

	private void postOrder(Node<T> root, Consumer<T> consumer) {
		if (root != null) {
			postOrder(root.left, consumer);
			postOrder(root.right, consumer);
			consumer.accept(root.value);
		}
	}
	
	public void postOrderByloop(Consumer<T> consumer) {
		if (root != null) {
			Stack<Node<T>> stack = new Stack<>();
			Node<T> tempNode = root;
			while (null != tempNode || !stack.isEmpty()) {
				while (null != tempNode) {
					consumer.accept(tempNode.value);
					stack.push(tempNode);
					tempNode = tempNode.left;
				}
				if (!stack.isEmpty()) {
					tempNode = stack.pop();
					tempNode = tempNode.right;
				}
			}
		}
	}

	public int size() {
		return size;
	}

}
