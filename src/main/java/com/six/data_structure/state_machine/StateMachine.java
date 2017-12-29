package com.six.data_structure.state_machine;

/**
 * @author sixliu
 * @date 2017年12月29日
 * @email 359852326@qq.com
 * @Description
 */
public abstract class StateMachine {

	enum State {
		INIT, START, STARTED, PAUSE, PAUSED, STOP, STOPED;
	}

	protected final State state;

	public StateMachine(State state) {
		this.state = state;
	}

	public abstract void handle(Worker worker, State state);

	public String toString() {
		return state.name();
	}
}
