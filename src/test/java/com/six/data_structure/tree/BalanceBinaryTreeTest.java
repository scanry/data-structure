package com.six.data_structure.tree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import com.six.data_structure.tree.BalanceBinaryTree;
import com.six.data_structure.tree.RedBlackTree;
import com.six.data_structure.tree.TreeBin;

/**
 * @author sixliu
 * @date 2017年12月19日
 * @email 359852326@qq.com
 * @Description
 */
public class BalanceBinaryTreeTest {

	public static void main(String[] args) {
		int size=10;
		Consumer<Integer> consumer = value -> System.out.print(value + "  ");
		BalanceBinaryTree<Integer> tree = new BalanceBinaryTree<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1.compareTo(o2);
			}
		});
		for (int i = 0; i < size; i++) {
			tree.add(i);
		}
		System.out.print("平衡二叉树树测试\n");
		tree.preOrder(consumer);
		System.out.print("\n");
		tree.midOrderByloop(consumer);
		System.out.print("\n");
		tree.postOrder(consumer);
		System.out.print("\n");
		
		RedBlackTree<Integer> redBlackTree = new RedBlackTree<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1.compareTo(o2);
			}
		});
		for (int i = 0; i < size; i++) {
			redBlackTree.add(i);
		}
		System.out.print("红黑树测试\n");
		redBlackTree.preOrder(consumer);
		System.out.print("\n");
		redBlackTree.midOrder(consumer);
		System.out.print("\n");
		redBlackTree.postOrder(consumer);
		System.out.print("\n");
		
		TreeBin<String,Integer> treeBin=new TreeBin<>();
		for (int i = 0; i < size; i++) {
			treeBin.putTreeVal(String.valueOf(i).hashCode(),String.valueOf(i),i);
		}
		BiConsumer<String,Integer> biConsumer = (key,value) -> System.out.print(value + "  ");
		System.out.print("java红黑树测试\n");
		treeBin.preOrder(biConsumer);
//		System.out.print("\n");
//		redBlackTree.midOrder(consumer);
//		System.out.print("\n");
//		redBlackTree.postOrder(consumer);
//		System.out.print("\n");
		
		ArrayList<String> list=new ArrayList<String>();
		list.iterator().forEachRemaining(System.out::println);;
		hashCode(0, ("送").toCharArray());
	}
	
    public static int hashCode(int hash,char[] value) {
        int h = hash;
        if (h == 0 && value.length > 0) {
            char val[] = value;

            for (int i = 0; i < value.length; i++) {
                h = 31 * h + val[i];
            }
            hash = h;
        }
        return h;
    }

}
