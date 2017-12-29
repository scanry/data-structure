package com.six.data_structure.tree;

/**
 * @author sixliu
 * @date 2017年12月18日
 * @email 359852326@qq.com
 * @Description
 */
public class TreeNode<K, V> extends Node<K, V> {

	TreeNode<K, V> parent; // red-black tree links
	TreeNode<K, V> left;
	TreeNode<K, V> right;
	TreeNode<K, V> prev; // needed to unlink next upon deletion
	boolean red;

	TreeNode(int hash, K key, V val, Node<K, V> next, TreeNode<K, V> parent) {
		super(hash, key, val, next);
		this.parent = parent;
	}

	Node<K, V> find(int h, Object k) {
		return findTreeNode(h, k, null);
	}

	/**
	 * Returns the TreeNode (or null if not found) for the given key starting at
	 * given root.
	 */
	final TreeNode<K, V> findTreeNode(int h, Object k, Class<?> kc) {
		if (k != null) {
			TreeNode<K, V> p = this;
			do {
				int ph, dir;
				K pk;
				TreeNode<K, V> q;
				TreeNode<K, V> pl = p.left, pr = p.right;
				if ((ph = p.hash) > h)
					p = pl;
				else if (ph < h)
					p = pr;
				else if ((pk = p.key) == k || (pk != null && k.equals(pk)))
					return p;
				else if (pl == null)
					p = pr;
				else if (pr == null)
					p = pl;
				else if ((kc != null || (kc = comparableClassFor(k)) != null)
						&& (dir = compareComparables(kc, k, pk)) != 0)
					p = (dir < 0) ? pl : pr;
				else if ((q = pr.findTreeNode(h, k, kc)) != null)
					return q;
				else
					p = pl;
			} while (p != null);
		}
		return null;
	}
}
