package com.six.data_structure.state_machine;


/**   
 * @author sixliu   
 * @date   2017年12月29日 
 * @email  359852326@qq.com  
 * @Description 
 */
public class PauseStateMachine extends StateMachine {

	public PauseStateMachine() {
		super(State.PAUSE);
	}
	
	@Override
	public void handle(Worker worker, State state) {
		switch (state) {
		case PAUSE:
			break;
		case START:
			worker.setStateMachine(StateMachineEnum.START_STATE);
			worker.start();
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
