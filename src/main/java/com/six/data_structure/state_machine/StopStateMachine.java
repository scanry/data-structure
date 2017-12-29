package com.six.data_structure.state_machine;

/**
 * @author sixliu
 * @date 2017年12月29日
 * @email 359852326@qq.com
 * @Description
 */
public class StopStateMachine extends StateMachine {

	public StopStateMachine() {
		super(State.STOP);
	}

	@Override
	public void handle(Worker worker, State state) {

	}

}
