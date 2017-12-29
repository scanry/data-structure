package com.six.data_structure.state_machine;


/**
 * @author sixliu
 * @date 2017年12月29日
 * @email 359852326@qq.com
 * @Description
 */
public class StartStateMachine extends StateMachine {

	public StartStateMachine() {
		super(State.START);
	}
	
	@Override
	public void handle(Worker worker, State state) {
		switch (state) {
		case START:
			break;
		case PAUSE:
			worker.setStateMachine(StateMachineEnum.PAUSE_STATE);
			worker.pause();
			break;
		case STOP:
			worker.setStateMachine(StateMachineEnum.STOP_STATE);
			worker.stop();
			break;
		default:
			throw new IllegalArgumentException();
		}
	}

}
