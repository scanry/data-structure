package com.six.data_structure.thread;

import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author sixliu
 * @date 2018年1月9日
 * @email 359852326@qq.com
 * @Description
 */
public class ThreadTest {

	public static void main(String[] args) {
		Thread worker = new Thread(() -> {
			while (true) {
				System.out.println("running.....");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {}
			}
		}, "worker-thread");
		worker.start();
		worker.suspend();
		worker.resume();
		LockSupport.park(worker);
	}

}
