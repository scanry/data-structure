package com.six.data_structure.math;

/**
 * @author sixliu E-mail:359852326@qq.com
 * @version 创建时间：2018年1月8日 下午8:07:12 类说明 数学算法
 */
public class MathAlgTest {

	public static void main(String[] args) {
		int[][] a = { { 3, -2 }, { -1, 0 }, { 1, 2 }, };// 自己定义矩阵
		int[][] b = { { 1, 1, 2 }, { 0, 2, 1 }, { 0, 0, 3 }, { 0, 0, 4 } };// 自己定义矩阵
		System.out.println("a矩阵--------------");
		printlnMatrix(a);
		System.out.println("b矩阵--------------");
		printlnMatrix(b);
		int[][] result = MathAlg.matrixMultiplication(a, b);
		System.out.println("结果矩阵--------------");
		printlnMatrix(result);
		double resultNum=Math.pow(2, 1.5);
		System.out.println(resultNum);
		int num=100_0000;
		System.out.println(num);
	}

	private static void printlnMatrix(int[][] matrix) {
		int row = matrix[0].length;
		int col = matrix.length;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				System.out.print(matrix[j][i] + "\t");
			}
			System.out.println();
		}
	}
}
