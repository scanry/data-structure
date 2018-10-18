package com.six.data_structure;

import java.util.ArrayList;
import java.util.List;

/**
*@author:MG01867
*@date:2018年10月15日
*@email:359852326@qq.com
*@version:
*@describe //TODO
*/
public class GenericTest {

	public static void main(String[] args) {
		List<? super Request> list=new ArrayList<Message>();
		list.add(new Request());
		list.add(new RpcRequest());
	}
	

	static class Message{
		
	}

	static class Request extends Message{
		
	}
	
	static class RpcRequest extends Request{
		
	}
}
