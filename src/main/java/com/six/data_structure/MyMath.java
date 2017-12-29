package com.six.data_structure;


/**   
 * @author sixliu   
 * @date   2017年12月23日 
 * @email  359852326@qq.com  
 * @Description 
 */
public class MyMath {
	
	

	public static double pow(double num,int n) {
		if(0==num) {
			return 0;
		}
		if(0==n) {
			return 1;
		}else {
			double temp=num;
			double result=1;
			for(;n>0;n>>=1) {
				if((n&1)==1) {
					result*=temp;
				}
				temp*=temp;
			}
			return result;
		}
	}
}

