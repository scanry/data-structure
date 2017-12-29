package com.six.data_structure.state_machine;

/**
 * @author sixliu
 * @date 2017年12月29日
 * @email 359852326@qq.com
 * @Description 状态机枚举
 */
public interface StateMachineEnum {
	/**初始化状态机**/
	StateMachine INIT_STATE = new InitStateMachine();
	StateMachine START_STATE = new StartStateMachine();
	StateMachine PAUSE_STATE = new PauseStateMachine();
	StateMachine STOP_STATE = new StopStateMachine();
	StateMachine FINISH_STATE = new StartStateMachine();
}
