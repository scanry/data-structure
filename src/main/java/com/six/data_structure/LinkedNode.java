package com.six.data_structure;

import lombok.Data;

/**
*@author:MG01867
*@date:2018年10月18日
*@email:359852326@qq.com
*@version:
*@describe //TODO
*/
@Data
public class LinkedNode<T>{

	private T value;
	private LinkedNode<T> pre;
	private LinkedNode<T> next;
}
