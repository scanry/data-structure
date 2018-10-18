package com.six.data_structure;

/**
 * @author:MG01867
 * @date:2018年10月18日
 * @email:359852326@qq.com
 * @version:
 * @describe //TODO
 */
public class LinkedList<T> {

	private int size;
	private LinkedNode<T> head;
	private LinkedNode<T> tail;

	public void addHead(T value) {
		if (null == head) {
			head = new LinkedNode<>();
			head.setValue(value);
			tail = head;
		} else {
			LinkedNode<T> newHead = new LinkedNode<>();
			newHead.setNext(head);
			this.head = newHead;
		}
		size++;
	}
	
	public void addTail(T value) {
		if (null == head) {
			head = new LinkedNode<>();
			head.setValue(value);
			tail = head;
		} else {
			LinkedNode<T> newTail = new LinkedNode<>();
			tail.setNext(newTail);
			tail=newTail;
		}
		size++;
	}
}
