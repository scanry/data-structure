package com.six.data_structure.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author sixliu E-mail:359852326@qq.com
 * @version 创建时间：2018年1月15日 下午10:47:44 类说明
 */
public class ReentrantWriteAndReadLockStudy {

	/**
	 * 把读写可重入锁看成内部为了2个锁，读锁共享一个公用的Thread实例作为锁的拥有线程，写锁使用当前线程作为锁的拥有线程。
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);
		ExecutorService executor = Executors.newFixedThreadPool(3);
		executor.execute(() -> {
			String originalNam = Thread.currentThread().getName();
			Thread.currentThread().setName("thread-1");
			try {
				lock.readLock().lock();;
				
				System.out.println("thread-1 lock");
			} finally {
				Thread.currentThread().setName(originalNam);
				lock.writeLock().unlock();
			}
		});

		executor.execute(() -> {
			String originalNam = Thread.currentThread().getName();
			Thread.currentThread().setName("thread-2");
			try {
				Thread.sleep(10000000);
				lock.readLock().lock();
				System.out.println("thread-2 lock");
			}catch (Exception e) {
				e.printStackTrace();
			} finally {
				Thread.currentThread().setName(originalNam);
				lock.readLock().unlock();
			}
		});
		synchronized (ReentrantLockStudy.class) {
			ReentrantLockStudy.class.wait();
		}

	}

}
