package com.six.data_structure.state_machine;

import com.six.data_structure.state_machine.StateMachine.State;

/**
 * @author sixliu
 * @date 2017年12月29日
 * @email 359852326@qq.com
 * @Description
 */
public abstract class AbstactWorker implements Worker {

	private volatile StateMachine stateMachine = StateMachineEnum.INIT_STATE;

	@Override
	public void request(State flag) {
		stateMachine.handle(this, flag);
	}

	@Override
	public synchronized void setStateMachine(StateMachine stateMachine) {
		this.stateMachine = stateMachine;
	}

	protected StateMachine getStateMachine() {
		return stateMachine;
	}
}
