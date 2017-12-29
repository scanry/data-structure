package com.six.data_structure.tree;

import java.lang.reflect.Field;
import java.util.Objects;
import java.util.concurrent.locks.LockSupport;
import java.util.function.BiConsumer;

/**
 * @author sixliu
 * @date 2017年12月18日
 * @email 359852326@qq.com
 * @Description
 */
@SuppressWarnings("restriction")
public class TreeBin<K, V> extends Node<K, V> {
	TreeNode<K, V> root;
	volatile TreeNode<K, V> first;
	volatile Thread waiter;
	volatile int lockState;
	// values for lockState
	static final int WRITER = 1; // set while holding write lock
	static final int WAITER = 2; // set when waiting for write lock
	static final int READER = 4; // increment value for setting read lock
	static final int TREEBIN = -2; // hash for roots of trees

	/**
	 * Tie-breaking utility for ordering insertions when equal hashCodes and
	 * non-comparable. We don't require a total order, just a consistent insertion
	 * rule to maintain equivalence across rebalancings. Tie-breaking further than
	 * necessary simplifies testing a bit.
	 */
	static int tieBreakOrder(Object a, Object b) {
		int d;
		if (a == null || b == null || (d = a.getClass().getName().compareTo(b.getClass().getName())) == 0)
			d = (System.identityHashCode(a) <= System.identityHashCode(b) ? -1 : 1);
		return d;
	}
	public TreeBin() {
		super(TREEBIN, null, null, null);
	}
	/**
	 * Creates bin with initial set of nodes headed by b.
	 */
	public TreeBin(TreeNode<K, V> b) {
		super(TREEBIN, null, null, null);
		this.first = b;
		TreeNode<K, V> r = null;
		for (TreeNode<K, V> x = b, next; x != null; x = next) {
			next = (TreeNode<K, V>) x.next;
			x.left = x.right = null;
			if (r == null) {
				x.parent = null;
				x.red = false;
				r = x;
			} else {
				K k = x.key;
				int h = x.hash;
				Class<?> kc = null;
				for (TreeNode<K, V> p = r;;) {
					int dir, ph;
					K pk = p.key;
					if ((ph = p.hash) > h)
						dir = -1;
					else if (ph < h)
						dir = 1;
					else if ((kc == null && (kc = comparableClassFor(k)) == null)
							|| (dir = compareComparables(kc, k, pk)) == 0)
						dir = tieBreakOrder(k, pk);
					TreeNode<K, V> xp = p;
					if ((p = (dir <= 0) ? p.left : p.right) == null) {
						x.parent = xp;
						if (dir <= 0)
							xp.left = x;
						else
							xp.right = x;
						r = balanceInsertion(r, x);
						break;
					}
				}
			}
		}
		this.root = r;
		assert checkInvariants(root);
	}

	/**
	 * Acquires write lock for tree restructuring.
	 */
	private final void lockRoot() {
		if (!U.compareAndSwapInt(this, LOCKSTATE, 0, WRITER))
			contendedLock(); // offload to separate method
	}

	
	public void preOrder(BiConsumer<K, V> consumer) {
		Objects.requireNonNull(consumer);
		preOrder(root, consumer);
	}
	
	private void preOrder(TreeNode<K, V> root, BiConsumer<K, V> consumer) {
		if (root != null) {
			consumer.accept(root.key, root.val);
			preOrder(root.left, consumer);
			preOrder(root.right, consumer);
		}
	}
	/**
	 * Releases write lock for tree restructuring.
	 */
	private final void unlockRoot() {
		lockState = 0;
	}

	/**
	 * Possibly blocks awaiting root lock.
	 */
	private final void contendedLock() {
		boolean waiting = false;
		for (int s;;) {
			if (((s = lockState) & ~WAITER) == 0) {
				if (U.compareAndSwapInt(this, LOCKSTATE, s, WRITER)) {
					if (waiting)
						waiter = null;
					return;
				}
			} else if ((s & WAITER) == 0) {
				if (U.compareAndSwapInt(this, LOCKSTATE, s, s | WAITER)) {
					waiting = true;
					waiter = Thread.currentThread();
				}
			} else if (waiting)
				LockSupport.park(this);
		}
	}

	/**
	 * Returns matching node or null if none. Tries to search using tree comparisons
	 * from root, but continues linear search when lock not available.
	 */
	final Node<K, V> find(int h, Object k) {
		if (k != null) {
			for (Node<K, V> e = first; e != null;) {
				int s;
				K ek;
				if (((s = lockState) & (WAITER | WRITER)) != 0) {
					if (e.hash == h && ((ek = e.key) == k || (ek != null && k.equals(ek))))
						return e;
					e = e.next;
				} else if (U.compareAndSwapInt(this, LOCKSTATE, s, s + READER)) {
					TreeNode<K, V> r, p;
					try {
						p = ((r = root) == null ? null : r.findTreeNode(h, k, null));
					} finally {
						Thread w;
						if (U.getAndAddInt(this, LOCKSTATE, -READER) == (READER | WAITER) && (w = waiter) != null)
							LockSupport.unpark(w);
					}
					return p;
				}
			}
		}
		return null;
	}

	/**
	 * 1.根节点等于null，直接插入新节点，将颜色设置为黑色 2.如果父节点为黑色，那么直接插入。 3.如果父节点为红色
	 * 
	 * @return null if added
	 */
	public final TreeNode<K, V> putTreeVal(int h, K k, V v) {
		Class<?> kc = null;
		boolean searched = false;
		for (TreeNode<K, V> p = root;;) {
			int dir, ph;
			K pk;
			if (p == null) {
				first = root = new TreeNode<K, V>(h, k, v, null, null);
				break;
			} else if ((ph = p.hash) > h)
				dir = -1;
			else if (ph < h)
				dir = 1;
			else if ((pk = p.key) == k || (pk != null && k.equals(pk)))
				return p;
			else if ((kc == null && (kc = comparableClassFor(k)) == null)
					|| (dir = compareComparables(kc, k, pk)) == 0) {
				if (!searched) {
					TreeNode<K, V> q, ch;
					searched = true;
					if (((ch = p.left) != null && (q = ch.findTreeNode(h, k, kc)) != null)
							|| ((ch = p.right) != null && (q = ch.findTreeNode(h, k, kc)) != null))
						return q;
				}
				dir = tieBreakOrder(k, pk);
			}

			TreeNode<K, V> xp = p;
			if ((p = (dir <= 0) ? p.left : p.right) == null) {
				TreeNode<K, V> x, f = first;
				first = x = new TreeNode<K, V>(h, k, v, f, xp);
				if (f != null)
					f.prev = x;
				if (dir <= 0)
					xp.left = x;
				else
					xp.right = x;
				if (!xp.red)
					x.red = true;
				else {
					lockRoot();
					try {
						root = balanceInsertion(root, x);
					} finally {
						unlockRoot();
					}
				}
				break;
			}
		}
		assert checkInvariants(root);
		return null;
	}

	/**
	 * Removes the given node, that must be present before this call. This is
	 * messier than typical red-black deletion code because we cannot swap the
	 * contents of an interior node with a leaf successor that is pinned by "next"
	 * pointers that are accessible independently of lock. So instead we swap the
	 * tree linkages.
	 *
	 * @return true if now too small, so should be untreeified
	 */
	final boolean removeTreeNode(TreeNode<K, V> p) {
		TreeNode<K, V> next = (TreeNode<K, V>) p.next;
		TreeNode<K, V> pred = p.prev; // unlink traversal pointers
		TreeNode<K, V> r, rl;
		if (pred == null)
			first = next;
		else
			pred.next = next;
		if (next != null)
			next.prev = pred;
		if (first == null) {
			root = null;
			return true;
		}
		if ((r = root) == null || r.right == null || // too small
				(rl = r.left) == null || rl.left == null)
			return true;
		lockRoot();
		try {
			TreeNode<K, V> replacement;
			TreeNode<K, V> pl = p.left;
			TreeNode<K, V> pr = p.right;
			if (pl != null && pr != null) {
				TreeNode<K, V> s = pr, sl;
				while ((sl = s.left) != null) // find successor
					s = sl;
				boolean c = s.red;
				s.red = p.red;
				p.red = c; // swap colors
				TreeNode<K, V> sr = s.right;
				TreeNode<K, V> pp = p.parent;
				if (s == pr) { // p was s's direct parent
					p.parent = s;
					s.right = p;
				} else {
					TreeNode<K, V> sp = s.parent;
					if ((p.parent = sp) != null) {
						if (s == sp.left)
							sp.left = p;
						else
							sp.right = p;
					}
					if ((s.right = pr) != null)
						pr.parent = s;
				}
				p.left = null;
				if ((p.right = sr) != null)
					sr.parent = p;
				if ((s.left = pl) != null)
					pl.parent = s;
				if ((s.parent = pp) == null)
					r = s;
				else if (p == pp.left)
					pp.left = s;
				else
					pp.right = s;
				if (sr != null)
					replacement = sr;
				else
					replacement = p;
			} else if (pl != null)
				replacement = pl;
			else if (pr != null)
				replacement = pr;
			else
				replacement = p;
			if (replacement != p) {
				TreeNode<K, V> pp = replacement.parent = p.parent;
				if (pp == null)
					r = replacement;
				else if (p == pp.left)
					pp.left = replacement;
				else
					pp.right = replacement;
				p.left = p.right = p.parent = null;
			}

			root = (p.red) ? r : balanceDeletion(r, replacement);

			if (p == replacement) { // detach pointers
				TreeNode<K, V> pp;
				if ((pp = p.parent) != null) {
					if (p == pp.left)
						pp.left = null;
					else if (p == pp.right)
						pp.right = null;
					p.parent = null;
				}
			}
		} finally {
			unlockRoot();
		}
		assert checkInvariants(root);
		return false;
	}

	/* ------------------------------------------------------------ */
	// Red-black tree methods, all adapted from CLR

	static <K, V> TreeNode<K, V> rotateLeft(TreeNode<K, V> root, TreeNode<K, V> p) {
		TreeNode<K, V> r, pp, rl;
		if (p != null && (r = p.right) != null) {
			if ((rl = p.right = r.left) != null)
				rl.parent = p;
			if ((pp = r.parent = p.parent) == null)
				(root = r).red = false;
			else if (pp.left == p)
				pp.left = r;
			else
				pp.right = r;
			r.left = p;
			p.parent = r;
		}
		return root;
	}

	static <K, V> TreeNode<K, V> rotateRight(TreeNode<K, V> root, TreeNode<K, V> p) {
		TreeNode<K, V> l, pp, lr;
		if (p != null && (l = p.left) != null) {
			if ((lr = p.left = l.right) != null)
				lr.parent = p;
			if ((pp = l.parent = p.parent) == null)
				(root = l).red = false;
			else if (pp.right == p)
				pp.right = l;
			else
				pp.left = l;
			l.right = p;
			p.parent = l;
		}
		return root;
	}

	static <K, V> TreeNode<K, V> balanceInsertion1(TreeNode<K, V> root, TreeNode<K, V> x) {
		x.red = true;
		TreeNode<K, V> xp, xpp, xppl, xppr = null;
		while(true) {
			xp=x.parent;
			xpp=xp.parent;
			xppl=xpp.left;
			xppr=xpp.right;
			if(xp==xppr) {
				if(null!=xppl) {
					xp.red=false;
					xppl.red=false;
					xpp.red=true;
					x=xpp;
					continue;
				}
			}else {
				if(null!=xppr) {
					xp.red=false;
					xppr.red=false;
					xpp.red=true;
					x=xpp;
					continue;
				}
			}
			break;
		}
		return null;
	}

	static <K, V> TreeNode<K, V> balanceInsertion(TreeNode<K, V> root, TreeNode<K, V> x) {
		x.red = true;
		for (TreeNode<K, V> xp, xpp, xppl, xppr;;) {
			if ((xp = x.parent) == null) {
				x.red = false;
				return x;
			} else if (!xp.red || (xpp = xp.parent) == null)
				return root;
			if (xp == (xppl = xpp.left)) {
				if ((xppr = xpp.right) != null && xppr.red) {
					xppr.red = false;
					xp.red = false;
					xpp.red = true;
					x = xpp;
				} else {
					if (x == xp.right) {
						root = rotateLeft(root, x = xp);
						xpp = (xp = x.parent) == null ? null : xp.parent;
					}
					if (xp != null) {
						xp.red = false;
						if (xpp != null) {
							xpp.red = true;
							root = rotateRight(root, xpp);
						}
					}
				}
			} else {
				if (xppl != null && xppl.red) {
					xppl.red = false;
					xp.red = false;
					xpp.red = true;
					x = xpp;
				} else {
					if (x == xp.left) {
						root = rotateRight(root, x = xp);
						xpp = (xp = x.parent) == null ? null : xp.parent;
					}
					if (xp != null) {
						xp.red = false;
						if (xpp != null) {
							xpp.red = true;
							root = rotateLeft(root, xpp);
						}
					}
				}
			}
		}
	}

	static <K, V> TreeNode<K, V> balanceDeletion(TreeNode<K, V> root, TreeNode<K, V> x) {
		for (TreeNode<K, V> xp, xpl, xpr;;) {
			if (x == null || x == root)
				return root;
			else if ((xp = x.parent) == null) {
				x.red = false;
				return x;
			} else if (x.red) {
				x.red = false;
				return root;
			} else if ((xpl = xp.left) == x) {
				if ((xpr = xp.right) != null && xpr.red) {
					xpr.red = false;
					xp.red = true;
					root = rotateLeft(root, xp);
					xpr = (xp = x.parent) == null ? null : xp.right;
				}
				if (xpr == null)
					x = xp;
				else {
					TreeNode<K, V> sl = xpr.left, sr = xpr.right;
					if ((sr == null || !sr.red) && (sl == null || !sl.red)) {
						xpr.red = true;
						x = xp;
					} else {
						if (sr == null || !sr.red) {
							if (sl != null)
								sl.red = false;
							xpr.red = true;
							root = rotateRight(root, xpr);
							xpr = (xp = x.parent) == null ? null : xp.right;
						}
						if (xpr != null) {
							xpr.red = (xp == null) ? false : xp.red;
							if ((sr = xpr.right) != null)
								sr.red = false;
						}
						if (xp != null) {
							xp.red = false;
							root = rotateLeft(root, xp);
						}
						x = root;
					}
				}
			} else { // symmetric
				if (xpl != null && xpl.red) {
					xpl.red = false;
					xp.red = true;
					root = rotateRight(root, xp);
					xpl = (xp = x.parent) == null ? null : xp.left;
				}
				if (xpl == null)
					x = xp;
				else {
					TreeNode<K, V> sl = xpl.left, sr = xpl.right;
					if ((sl == null || !sl.red) && (sr == null || !sr.red)) {
						xpl.red = true;
						x = xp;
					} else {
						if (sl == null || !sl.red) {
							if (sr != null)
								sr.red = false;
							xpl.red = true;
							root = rotateLeft(root, xpl);
							xpl = (xp = x.parent) == null ? null : xp.left;
						}
						if (xpl != null) {
							xpl.red = (xp == null) ? false : xp.red;
							if ((sl = xpl.left) != null)
								sl.red = false;
						}
						if (xp != null) {
							xp.red = false;
							root = rotateRight(root, xp);
						}
						x = root;
					}
				}
			}
		}
	}

	/**
	 * Recursive invariant check
	 */
	static <K, V> boolean checkInvariants(TreeNode<K, V> t) {
		TreeNode<K, V> tp = t.parent, tl = t.left, tr = t.right, tb = t.prev, tn = (TreeNode<K, V>) t.next;
		if (tb != null && tb.next != t)
			return false;
		if (tn != null && tn.prev != t)
			return false;
		if (tp != null && t != tp.left && t != tp.right)
			return false;
		if (tl != null && (tl.parent != t || tl.hash > t.hash))
			return false;
		if (tr != null && (tr.parent != t || tr.hash < t.hash))
			return false;
		if (t.red && tl != null && tl.red && tr != null && tr.red)
			return false;
		if (tl != null && !checkInvariants(tl))
			return false;
		if (tr != null && !checkInvariants(tr))
			return false;
		return true;
	}

	private static final sun.misc.Unsafe U;
	private static final long LOCKSTATE;
	static {
		try {
			Field theUnsafeInstance = sun.misc.Unsafe.class.getDeclaredField("theUnsafe");
			theUnsafeInstance.setAccessible(true);
			U = (sun.misc.Unsafe) theUnsafeInstance.get(sun.misc.Unsafe.class);
			Class<?> k = TreeBin.class;
			LOCKSTATE = U.objectFieldOffset(k.getDeclaredField("lockState"));
		} catch (Exception e) {
			throw new Error(e);
		}
	}

}
