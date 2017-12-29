package com.six.data_structure.state_machine;

/**
 * @author sixliu
 * @date 2017年12月29日
 * @email 359852326@qq.com
 * @Description
 */
public class InitStateMachine extends StateMachine {

	public InitStateMachine() {
		super(State.INIT);
	}

	@Override
	public void handle(Worker worker, State state) {
		switch (state) {
		case START:
			worker.setStateMachine(StateMachineEnum.START_STATE);
			worker.start();
			break;
		default:
			throw new IllegalArgumentException();
		}
	}

}
