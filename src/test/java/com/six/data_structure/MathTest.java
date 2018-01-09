package com.six.data_structure;

import com.six.data_structure.math.MathAlg;

/**   
 * @author sixliu   
 * @date   2017年12月23日 
 * @email  359852326@qq.com  
 * @Description 
 */
public class MathTest {

	public static void main(String[] args) {
		double a=111.11d;
		int b=5;
		System.out.println(Math.pow(a,b));
		System.out.println(MathAlg.powByDivision(a,b));
	}

}

