package com.six.data_structure.state_machine;

/**
 * @author sixliu
 * @date 2017年12月29日
 * @email 359852326@qq.com
 * @Description
 */
public class CrawlerWorker extends AbstactWorker {

	@Override
	public void start() {
		System.out.println("worker[" + getStateMachine() + "] start");
	}

	@Override
	public void pause() {
		System.out.println("worker[" + getStateMachine() + "] pause");
	}

	@Override
	public void goOn() {
		System.out.println("worker[" + getStateMachine() + "] goOn");
	}

	@Override
	public void stop() {
		System.out.println("worker[" + getStateMachine() + "] stop");
	}

}
