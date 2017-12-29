package com.six.data_structure.tree;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * @author sixliu
 * @date 2017年12月18日
 * @email 359852326@qq.com
 * @Description
 */
public class Node<K, V>{

	final int hash;
	final K key;
	volatile V val;
	volatile Node<K, V> next;

	Node(int hash, K key, V val, Node<K, V> next) {
		this.hash = hash;
		this.key = key;
		this.val = val;
		this.next = next;
	}

	public final K getKey() {
		return key;
	}

	public final V getValue() {
		return val;
	}

	public final int hashCode() {
		return key.hashCode() ^ val.hashCode();
	}

	public final String toString() {
		return key + "=" + val;
	}

	public final V setValue(V value) {
		throw new UnsupportedOperationException();
	}

	public final boolean equals(Object o) {
		Object k, v, u;
		Map.Entry<?, ?> e;
		return ((o instanceof Map.Entry) && (k = (e = (Map.Entry<?, ?>) o).getKey()) != null
				&& (v = e.getValue()) != null && (k == key || k.equals(key)) && (v == (u = val) || v.equals(u)));
	}

	/**
	 * Virtualized support for map.get(); overridden in subclasses.
	 */
	Node<K, V> find(int h, Object k) {
		Node<K, V> e = this;
		if (k != null) {
			do {
				K ek;
				if (e.hash == h && ((ek = e.key) == k || (ek != null && k.equals(ek))))
					return e;
			} while ((e = e.next) != null);
		}
		return null;
	}

	static Class<?> comparableClassFor(Object x) {
		if (x instanceof Comparable) {
			Class<?> c;
			Type[] ts, as;
			Type t;
			ParameterizedType p;
			if ((c = x.getClass()) == String.class) // bypass checks
				return c;
			if ((ts = c.getGenericInterfaces()) != null) {
				for (int i = 0; i < ts.length; ++i) {
					if (((t = ts[i]) instanceof ParameterizedType)
							&& ((p = (ParameterizedType) t).getRawType() == Comparable.class)
							&& (as = p.getActualTypeArguments()) != null && as.length == 1 && as[0] == c) // type arg is
																											// c
						return c;
				}
			}
		}
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" }) // for cast to Comparable
	static int compareComparables(Class<?> kc, Object k, Object x) {
		return (x == null || x.getClass() != kc ? 0 : ((Comparable) k).compareTo(x));
	}
}
