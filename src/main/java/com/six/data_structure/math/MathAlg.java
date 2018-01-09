package com.six.data_structure.math;

import java.util.Objects;

/**
 * @author sixliu E-mail:359852326@qq.com
 * @version 创建时间：2018年1月8日 下午8:07:12 类说明 数学算法
 */
public class MathAlg {

	/**
	 * n=7= 0000 0111 1= 0000 0001 n=7= (1+2)+4 n=5= 1+4
	 * 矩阵求幂
	 * @param num
	 * @param n
	 * @return
	 */
	public static double powByDivision(double num, int n) {
		if (num == Double.NaN) {
			return 0d;
		}
		if (n == 0) {
			return 1d;
		}
		if (num >= Double.POSITIVE_INFINITY) {
			return Double.POSITIVE_INFINITY;
		}
		if (num <= Double.NEGATIVE_INFINITY) {
			if (n % 2 == 0) {
				return Double.POSITIVE_INFINITY;
			} else {
				return Double.NEGATIVE_INFINITY;
			}
		}
		double temp = num;
		double result = 1;
		for (; n > 0; n >>= 1) {
			if ((n & 1) == 1) {
				result *= temp;
			}
			temp *= temp;
		}
		if (n < 0) {
			result = 1d / result;
		}
		return result;
	}

	/**
	 * int[][] result=a*b,a.row==b.col
	 * 
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
		int sumCount = a.length;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				int temp = 0;
				for (int m = 0; m < sumCount; m++) {
					temp += a[m][i] * b[j][m];
				}
				result[j][i] = temp;
			}
		}
		return result;
	}

	public static int[][] matrixMultiplicationByDivide(int[][] a, int[][] b) {
		Objects.requireNonNull(a);
		Objects.requireNonNull(b);
		if (a.length <= 0 || b.length <= 0 || a.length != b[0].length) {
			throw new IllegalArgumentException();
		}
		return null;

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
