package com.six.data_structure.yarn.worker;

import java.nio.ByteBuffer;
import java.util.Map;

import org.apache.hadoop.yarn.api.records.ContainerId;
import org.apache.hadoop.yarn.api.records.ContainerStatus;
import org.apache.hadoop.yarn.api.records.Resource;
import org.apache.hadoop.yarn.client.api.async.NMClientAsync.AbstractCallbackHandler;

/**   
 * @author sixliu   
 * @date   2018年1月17日 
 * @email  359852326@qq.com  
 * @Description 
 */
public class CallbackHandlerImpl extends AbstractCallbackHandler{

	@Override
	public void onContainerResourceIncreased(ContainerId arg0, Resource arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onContainerResourceUpdated(ContainerId arg0, Resource arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onContainerStarted(ContainerId arg0, Map<String, ByteBuffer> arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onContainerStatusReceived(ContainerId arg0, ContainerStatus arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onContainerStopped(ContainerId arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGetContainerStatusError(ContainerId arg0, Throwable arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onIncreaseContainerResourceError(ContainerId arg0, Throwable arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStartContainerError(ContainerId arg0, Throwable arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStopContainerError(ContainerId arg0, Throwable arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpdateContainerResourceError(ContainerId arg0, Throwable arg1) {
		// TODO Auto-generated method stub
		
	}

}

