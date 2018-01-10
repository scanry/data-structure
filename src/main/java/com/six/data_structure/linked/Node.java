package com.six.data_structure.linked;

/**
 * @author sixliu
 * @date 2018年1月10日
 * @email 359852326@qq.com
 * @Description
 */
public class Node<T> {
	private T value;
	private Node<T> pre;
	private Node<T> next;

	public Node(T value) {
		this(value, null, null);
	}

	public Node(T value, Node<T> pre) {
		this(value, pre, null);
	}

	public Node(T value, Node<T> pre, Node<T> next) {
		this.value = value;
		this.pre = pre;
		this.next = next;
	}

	public T getValue() {
		return value;
	}

	public Node<T> getPre() {
		return pre;
	}

	public void setPre(Node<T> pre) {
		this.pre = pre;
	}

	public Node<T> getNext() {
		return next;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}

	public String toString() {
		return "value=" + value.toString();
	}
}
