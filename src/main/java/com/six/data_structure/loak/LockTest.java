package com.six.data_structure.loak;

import java.util.concurrent.locks.ReentrantLock;

/**
*@author:MG01867
*@date:2018年10月12日
*@email:359852326@qq.com
*@version:
*@describe //TODO
*/
public class LockTest {

	public static void main(String[] args) {
		ReentrantLock lock=new ReentrantLock(true);
		lock.lock();
		lock.lock();
	}

}
