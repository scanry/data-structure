package com.six.data_structure.linked;

/**
 * @author sixliu
 * @date 2018年1月10日
 * @email 359852326@qq.com
 * @Description
 */
public class LinkedRingAlgTest {

	public static void main(String[] args) {
		Node<Integer> head = buidNode();
		System.out.println("通过快慢算法判断链表是否有环:"+LinkedRingAlg.isLoopByFastAndSlow(head));
		System.out.println("通过计数算法判断链表是否有环:"+LinkedRingAlg.isLoopByCount(head));
		System.out.println(head);
	}
	
	public static void println(Node<Integer> head) {
		Node<Integer> temp=head;
		while(null!=temp) {
			System.out.println(temp);
			temp=temp.getNext();
		}
	}

	public static Node<Integer> buidNode() {
		Node<Integer> head = new Node<Integer>(0);
		Node<Integer> node1 = new Node<Integer>(1, head);
		head.setNext(node1);

		Node<Integer> node2 = new Node<Integer>(2, node1);
		node1.setNext(node2);

		Node<Integer> node3 = new Node<Integer>(3, node2);
		node2.setNext(node3);

		Node<Integer> node4 = new Node<Integer>(4, node3);
		node3.setNext(node4);

		Node<Integer> node5 = new Node<Integer>(5, node4);
		node4.setNext(node5);

		node5.setNext(node1);
		return head;
	}
}
