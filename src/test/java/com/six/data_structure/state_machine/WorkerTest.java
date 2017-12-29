package com.six.data_structure.state_machine;

import com.six.data_structure.state_machine.StateMachine.State;

/**
 * @author sixliu
 * @date 2017年12月29日
 * @email 359852326@qq.com
 * @Description
 */
public class WorkerTest {

	public static void main(String[] args) {
		Worker worker = new CrawlerWorker();
		Thread thread1 = new Thread(() -> {
			worker.request(State.START);
			worker.request(State.PAUSE);
			worker.request(State.PAUSE);
			worker.request(State.START);
			worker.request(State.STOP);
		});
		thread1.start();
		Thread thread2 = new Thread(() -> {
			worker.request(State.START);
			worker.request(State.PAUSE);
			worker.request(State.PAUSE);
			worker.request(State.START);
			worker.request(State.STOP);
		});
		thread2.start();
	}

}
