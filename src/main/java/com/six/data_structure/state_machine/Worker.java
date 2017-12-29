package com.six.data_structure.state_machine;

import com.six.data_structure.state_machine.StateMachine.State;

/**
 * @author sixliu
 * @date 2017年12月29日
 * @email 359852326@qq.com
 * @Description
 */
public interface Worker {

	void request(State state);
	
	void setStateMachine(StateMachine stateMachine);
	
	void start();

	void pause();

	void goOn();

	void stop();
}
