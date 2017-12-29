package com.six.data_structure.tree;

import java.util.Comparator;
import java.util.Objects;
import java.util.Stack;
import java.util.function.Consumer;


/**
 * @author sixliu
 * @date 2017年12月22日
 * @email 359852326@qq.com
 * @Description
 */
public class RedBlackTree<T> {

	private Node<T> root;
	private Comparator<T> comparator;
	private int size;

	public RedBlackTree(Comparator<T> comparator) {
		Objects.requireNonNull(comparator);
		this.comparator = comparator;
	}

	private static final class Node<T> {
		private T value;
		private Node<T> parent;
		private Node<T> left;
		private Node<T> right;
		private byte red;

		Node(T value, Node<T> parent) {
			this.value = value;
			this.parent = parent;
			this.left = null;
			this.right = null;
			this.red = 1;
		}

		private boolean isRed() {
			return red == 1;
		}

		public String toString() {
			return "value=" + value;
		}
		
		Node<T> balanceInsert(){
			return null;
		}
		
		@SuppressWarnings("unused")
		protected Node<T> add(Comparator<T> comparator, T value) {
			Node<T> root=null;
			Node<T> insertPosition = null;
			int cmp = comparator.compare(value, this.value);
			if (cmp < 0) {
				insertPosition = this.left;
				if (null == insertPosition) {
					this.left = new Node<>(value, this);
					this.left.value = value;
					root=this;
				}
				this.left = insertPosition.add(comparator, value);
				if(isRed()) {
					root=balanceInsert();
					if(null==this.parent) {
						this.red=0;
					}
				}
			} else if (cmp > 0) {
				insertPosition = this.right;
				if (null == insertPosition) {
					this.right = new Node<>(value, this);
					this.right.value = value;
					root=this;
				}
				this.right = insertPosition.add(comparator, value);
				if(isRed()) {
					root=balanceInsert();
					if(null==this.parent) {
						this.red=0;
					}
				}
			}
			return root;
		}

	}

	public void add(T value) {
		Objects.requireNonNull(value);
		if (null == root) {
			root = new Node<>(value, null);
			root.red = 0;
			size = 1;
			return;
		} else {
			Node<T> insertPosition = null;
			Node<T> temp = root;
			int cmp = 0;
			while (null != temp) {
				cmp = comparator.compare(value, temp.value);
				insertPosition = temp;
				if (cmp < 0) {
					temp = temp.left;
				} else if (cmp > 0) {
					temp = temp.right;
				}
			}
			Node<T> newNode = new Node<>(value, insertPosition);
			if (cmp < 0) {
				insertPosition.left = newNode;
			} else if (cmp > 0) {
				insertPosition.right = newNode;
			}
			if (insertPosition.isRed()) {
				root = balanceInsert(insertPosition, newNode);
			}
			size++;
		}
	}

	private static <T> Node<T> balanceInsert(Node<T> root, Node<T> x) {
		x.red = 1;
		for (Node<T> xp, xpp, xppr, xppl;;) {
			if ((xp = x.parent) == null) {
				x.red = 0;
				return x;
			} else if (!xp.isRed() || (xpp = xp.parent) == null)
				return root;
			if (xp == (xppl = xpp.left)) {
				if ((xppr = xpp.right) != null && xppr.isRed()) {
					xppr.red = 0;
					xp.red = 0;
					xpp.red = 1;
					x = xpp;
				} else {
					if (x == xp.right) {
						root = rotateLeft(root, x = xp);
						xpp = (xp = x.parent) == null ? null : xp.parent;
					}
					if (xp != null) {
						xp.red = 0;
						if (xpp != null) {
							xpp.red = 1;
							root = rotateRight(root, xpp);
						}
					}
				}
			} else {
				if (xppl != null && xppl.isRed()) {
					xppl.red = 0;
					xp.red = 0;
					xpp.red = 1;
					x = xpp;
				} else {
					if (x == xp.left) {
						root = rotateRight(root, x = xp);
						xpp = (xp = x.parent) == null ? null : xp.parent;
					}
					if (xp != null) {
						xp.red = 0;
						if (xpp != null) {
							xpp.red = 1;
							root = rotateLeft(root, xpp);
						}
					}
				}
			}
		}
	}

	static <T> Node<T> rotateLeft(Node<T> root, Node<T> p) {
		Node<T> r, pp, rl;
		if (p != null && (r = p.right) != null) {
			if ((rl = p.right = r.left) != null)
				rl.parent = p;
			if ((pp = r.parent = p.parent) == null)
				(root = r).red = 0;
			else if (pp.left == p)
				pp.left = r;
			else
				pp.right = r;
			r.left = p;
			p.parent = r;
		}
		return root;
	}

	static <T> Node<T> rotateRight(Node<T> root, Node<T> p) {
		Node<T> l, pp, lr;
		if (p != null && (l = p.left) != null) {
			if ((lr = p.left = l.right) != null)
				lr.parent = p;
			if ((pp = l.parent = p.parent) == null)
				(root = l).red = 0;
			else if (pp.right == p)
				pp.right = l;
			else
				pp.left = l;
			l.right = p;
			p.parent = l;
		}
		return root;
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
