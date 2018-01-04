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

		protected Node<T> find(Comparator<T> comparator, T value) {
			Node<T> find = null;
			int cmp = comparator.compare(value, this.value);
			if (cmp < 0) {
				find = this.left;
				if (null == find) {
					return null;
				}
				return find.find(comparator, value);
			} else if (cmp > 0) {
				find = this.right;
				if (null == find) {
					return null;
				}
				return find.find(comparator, value);
			} else {
				return this;
			}
		}

		protected Node<T> add(Comparator<T> comparator, T value) {
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

		protected Node<T> remove(Comparator<T> comparator, Node<T> parent, Node<T> children) {
			Node<T> removePosition = null;
			int cmp = comparator.compare(parent.value, this.value);
			if (cmp < 0) {
				removePosition = this.left;
				int initHeight = this.left.height;
				this.left = removePosition.remove(comparator, parent, children);
				return (left.height == initHeight) ? this : balanceInsert();
			} else if (cmp > 0) {
				removePosition = this.right;
				int initHeight = this.right.height;
				this.right = removePosition.remove(comparator, parent, children);
				return (right.height == initHeight) ? this : balanceInsert();
			} else {
				int initHeight = this.height;
				if (children == this.right) {
					this.right = null;
					if(null!=this.left) {
						this.height--;
					}
				} else {
					this.left = null;
					if(null!=this.right) {
						this.height--;
					}
				}
				return (height == initHeight) ? this : balanceInsert();
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
		Node<T> findNode = null;
		if (null != root) {
			findNode = root.find(comparator, value);
			if (null != findNode) {
				Node<T> parent = null;
				if (null == (parent = findNode.parent)) {
					if (null == findNode.left && null == findNode.right) {
						root = null;
					} else if (null != findNode.left && null == findNode.right) {
						/**
						 * 1.左孩子中顶替当前节点 2.删除节点 
						 */
						root = findNode.left;
						findNode.parent = null;
					} else if (null == findNode.left && null != findNode.right) {
						/**
						 * 1.右孩子中顶替当前节点 2.删除节点 
						 */
						root = findNode.right;
						findNode.parent = null;
					} else {
						/**
						 * 1.从左孩子中找到一个临近节点 2.跟临近孩子节点互换位置 3.删除节点 4.递归调整平衡
						 */
						Node<T> findNearNode = findNearNode(findNode);
						findNearNode.left = findNode.left;
						findNode.left.parent = findNearNode;
						if (findNearNode != findNode.right) {
							findNearNode.right = findNode.right;
							findNode.right.parent = findNearNode;
							if (findNearNode.parent.left == findNearNode) {
								findNearNode.parent.left = findNode;
							} else {
								findNearNode.parent.right = findNode;
							}
							findNode.parent = findNearNode.parent;
							findNode.left = null;
							findNode.right = null;
							root = findNearNode;
							root = root.remove(comparator,findNode.parent,findNode);
						} else {
							findNearNode.parent = null;
							root = findNearNode;
						}
					}
				} else {
					if (null == findNode.left && null == findNode.right) {
						root = root.remove(comparator, parent, findNode);
					} else if (null != findNode.left && null == findNode.right) {
						/**
						 * 1.左孩子中顶替当前节点 2.删除节点 
						 */
						if (parent.left == findNode) {
							parent.left = findNode.left;
						} else {
							parent.right = findNode.left;
						}
						findNode.left.parent=parent;
						findNode.parent=findNode.left;
						findNode.left.left=findNode;
						findNode.left=null;
						root = root.remove(comparator, findNode.parent, findNode);
					} else if (null == findNode.left && null != findNode.right) {
						/**
						 * 1.右孩子中顶替当前节点 2.删除节点 
						 */
						if (parent.left == findNode) {
							parent.left = findNode.right;
						} else {
							parent.right = findNode.right;
						}
						findNode.right.parent=parent;
						findNode.parent=findNode.right;
						findNode.right.right=findNode;
						findNode.right=null;
						root = root.remove(comparator, findNode.parent, findNode);
					} else {
						/**
						 * 1.从左孩子中找到一个临近节点 2.跟临近孩子节点互换位置 3.删除节点 4.递归调整平衡
						 */
						Node<T> findNearNode = findNearNode(findNode);
						if (parent.left == findNode) {
							parent.left = findNearNode;
						} else {
							parent.right = findNearNode;
						}
						
						findNearNode.left = findNode.left;
						findNode.left.parent = findNearNode;
						if (findNearNode != findNode.right) {
							findNearNode.right = findNode.right;
							findNode.right.parent = findNearNode;
							if (findNearNode.parent.left == findNearNode) {
								findNearNode.parent.left = findNode;
							} else {
								findNearNode.parent.right = findNode;
							}
							findNode.parent=findNearNode.parent;
						} else {
							findNearNode.right = findNode;
							findNode.parent = findNearNode;
						}
						findNearNode.parent = parent;
						findNode.left = null;
						findNode.right = null;
						root = root.remove(comparator, findNode.parent, findNode);
					}
				}
			}
		}
		return findNode;
	}

	/**
	 * 寻找临近节点
	 * 
	 * @param current
	 * @return
	 */
	private Node<T> findNearNode(Node<T> current) {
		Node<T> findNearNode = current.right;
		while (null != findNearNode && null != findNearNode.left) {
			findNearNode = findNearNode.left;
		}
		return findNearNode;
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
