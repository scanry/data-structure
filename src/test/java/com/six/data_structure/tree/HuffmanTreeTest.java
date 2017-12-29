package com.six.data_structure.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.six.data_structure.tree.HuffmanTree.HuffmanTreeNode;

/**
 * @author sixliu
 * @date 2017年12月29日
 * @email 359852326@qq.com
 * @Description
 */
public class HuffmanTreeTest {

	public static void main(String[] args) {
		String text = "aaaebsdddsa";
		Map<Character, AtomicInteger> totalMap = new HashMap<>();
		for (int i = 0; i < text.length(); i++) {
			totalMap.computeIfAbsent(text.charAt(i), newKey -> {
				return new AtomicInteger(0);
			}).incrementAndGet();
		}
		List<HuffmanTreeNode<Character>> nodes = new ArrayList<>(totalMap.size());
		for (Map.Entry<Character, AtomicInteger> entry : totalMap.entrySet()) {
			nodes.add(new HuffmanTreeNode<Character>(entry.getKey(), entry.getValue().get()));
		}
		HuffmanTree<Character> huffmanTree = new HuffmanTree<Character>(nodes);
		System.out.println(huffmanTree.toList());
	}

}
