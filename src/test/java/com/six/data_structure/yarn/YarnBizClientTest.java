package com.six.data_structure.yarn;

import com.six.data_structure.yarn.client.YarnBizClient;
import com.six.data_structure.yarn.client.YarnBizClientImpl;

/**   
 * @author sixliu   
 * @date   2018年1月17日 
 * @email  359852326@qq.com  
 * @Description 
 */
public class YarnBizClientTest {

	public static void main(String[] args) {
		YarnBizClient yarnBizClient=new YarnBizClientImpl();
		yarnBizClient.start();
		yarnBizClient.execute("");
	}

}

