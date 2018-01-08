package com.six.data_structure.math;

import java.util.Objects;

/**
 * @author sixliu E-mail:359852326@qq.com
 * @version 创建时间：2018年1月8日 下午8:07:12 类说明 数学算法
 */
public class MathAlg {

	/**
	 * int[][] result=a*b,a.row==b.col
	 * @param a
	 * @param b
	 * @return
	 */
	public static int[][] matrixMultiplication(int[][] a, int[][] b) {
		Objects.requireNonNull(a);
		Objects.requireNonNull(b);
		if (a.length <= 0 || b.length <= 0 || a.length != b[0].length) {
			throw new IllegalArgumentException();
		}
		int row = a[0].length;
		int col = b.length;
		int[][] result = new int[b.length][a[0].length];
		int sumCount=a.length;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				int temp = 0;
				for (int m = 0; m < sumCount; m++) {
					int a1=a[m][i];
					int b1=b[j][m];
					temp +=a1 * b1;
				}
				result[j][i] = temp;
			}
		}
		return result;
	}
	
	public static int[][] matrixMultiplicationByStrassen(int[][] a, int[][] b) {
		Objects.requireNonNull(a);
		Objects.requireNonNull(b);
		if (a.length <= 0 || b.length <= 0 || a.length != b[0].length) {
			throw new IllegalArgumentException();
		}
		return null;
		
	}
}
