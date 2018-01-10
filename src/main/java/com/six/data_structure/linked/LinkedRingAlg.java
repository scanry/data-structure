package com.six.data_structure.linked;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author sixliu
 * @date 2018年1月10日
 * @email 359852326@qq.com
 * @Description
 */
public class LinkedRingAlg {

	/**
	 * fast的速度和slow的速度不能用多少倍来衡量，而应用2者的相对速度S， 为了相遇这个相对速度的取舍应该跟环长L有关，也就是L/S=n，
	 * n是否为整数，这个n就是循环的次数，由于L未知，所以相对速度取1,因为1*N=L。这个N肯定存在。
	 * 
	 * @param head
	 * @return
	 */
	public static <T> boolean isLoopByFastAndSlow(Node<T> head) {
		Node<T> fast = head;
		Node<T> slow = head;
		while (fast != null && fast.getNext() != null) {
			fast = fast.getNext().getNext();
			slow = slow.getNext();
			if (fast == slow) {
				break;
			}
		}
		if (null != fast) {
			return true;
		} else {
			return false;
		}
	}

	public static <T> boolean isLoopByCount(Node<T> head) {
		Map<Node<T>, AtomicInteger> count = new HashMap<>();
		Node<T> temp = head;
		boolean isLoopBy = false;
		while (null != temp) {
			if (count.computeIfAbsent(temp, key -> new AtomicInteger()).incrementAndGet() > 1) {
				isLoopBy = true;
				break;
			} else {
				temp = temp.getNext();
			}
		}
		return isLoopBy;
	}

}
