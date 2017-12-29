package com.six.data_structure;

import com.six.data_structure.MyMath;

/**   
 * @author sixliu   
 * @date   2017年12月23日 
 * @email  359852326@qq.com  
 * @Description 
 */
public class MathTest {

	public static void main(String[] args) {
		System.out.println(0x1.0p53);
		long dtal=Double.doubleToRawLongBits(10);
		int dta=(int)(dtal >> 32);
		System.out.println(dta);
		double result=1.0 / 0.0;
		System.out.println(result);
		System.out.println(Math.pow(2,2.5));
		System.out.println(Math.pow(2,2));
		System.out.println(Math.pow(2,0.5));
		System.out.println(Math.pow(2,2)*Math.pow(2,0.5));
		System.out.println(MyMath.pow(1999999999999999999999.0,10));
	}

}

