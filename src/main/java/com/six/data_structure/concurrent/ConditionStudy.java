package com.six.data_structure.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author sixliu
 * @date 2018年1月16日
 * @email 359852326@qq.com
 * @Description
 */
public class ConditionStudy {

	/**
	 * await和signal方法都需要在lock和unlock中，await时，如果signal没被调用那么阻塞，
	 * 等待signal线程signal方法修改状态，然后unlock后await线程会被唤醒。如果signal， 再调用await时不会阻塞。
	 * await和signal方法调用的2个线程会被lock互斥
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ReentrantLock lock = new ReentrantLock();
		ExecutorService executor = Executors.newFixedThreadPool(3);
		Condition condition = lock.newCondition();
		executor.execute(() -> {
			String srcName = Thread.currentThread().getName();
			Thread.currentThread().setName("await-thread");
			lock.lock();
			try {
				System.out.println("start await");
				condition.await();
				System.out.println("end await");
			} catch (Exception e) {

			} finally {
				Thread.currentThread().setName(srcName);
				lock.unlock();
			}
		});

		executor.execute(() -> {
			String srcName = Thread.currentThread().getName();
			Thread.currentThread().setName("signal-thread");
			lock.lock();
			try {
				System.out.println("start signal");
				// Thread.sleep(1111111111);
				condition.signal();
				System.out.println("end signal");
			} catch (Exception e) {

			} finally {
				Thread.currentThread().setName(srcName);
				lock.unlock();
			}
		});

	}

}
