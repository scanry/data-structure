package com.six.data_structure.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author sixliu E-mail:359852326@qq.com
 * @version 创建时间：2018年1月15日 下午9:58:13 类说明
 */
public class ReentrantLockStudy {

	public static void main(String[] args) throws InterruptedException {
		final ReentrantLock lock = new ReentrantLock(true);
		ExecutorService executor = Executors.newFixedThreadPool(3);
		executor.execute(() -> {
			String originalNam = Thread.currentThread().getName();
			Thread.currentThread().setName("thread-1");
			try {
				lock.lock();
				System.out.println("lock");
			} finally {
				Thread.currentThread().setName(originalNam);
				lock.unlock();
			}
		});

		executor.execute(() -> {
			String originalNam = Thread.currentThread().getName();
			Thread.currentThread().setName("thread-2");
			try {
				lock.lock();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("lock");
			} finally {
				Thread.currentThread().setName(originalNam);
				lock.unlock();
			}
		});
		synchronized (ReentrantLockStudy.class) {
			ReentrantLockStudy.class.wait();
		}
	}

}
