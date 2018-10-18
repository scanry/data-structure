package com.six.data_structure.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author:MG01867
 * @date:2018年10月18日
 * @email:359852326@qq.com
 * @version:
 * @describe 线程池学习
 */
public class ThreadPoolStudy {

	public static void main(String[] args) {
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(1);
		System.out.println(newFixedThreadPool);

		ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
		System.out.println(newCachedThreadPool);

		ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
		System.out.println(newSingleThreadExecutor);

		ExecutorService newWorkStealingPool = Executors.newWorkStealingPool();
		System.out.println(newWorkStealingPool);

		ExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1);
		System.out.println(newScheduledThreadPool);

		ExecutorService newSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
		System.out.println(newSingleThreadScheduledExecutor);

		ExecutorService forkJoinPool = ForkJoinPool.commonPool();
		System.out.println(forkJoinPool);

		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 60L, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>());
		System.out.println(threadPoolExecutor);
		threadPoolExecutor.execute(()->{
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				
			}
			System.out.println("");
		});
		threadPoolExecutor.execute(()->{
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				
			}
			System.out.println("");
		});
		try {
			Thread.sleep(1000000000);
		} catch (InterruptedException e) {
			
		}
	}

}
