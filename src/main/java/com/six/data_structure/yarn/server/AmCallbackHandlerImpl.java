package com.six.data_structure.yarn.server;

import java.util.List;

import org.apache.hadoop.yarn.api.records.Container;
import org.apache.hadoop.yarn.api.records.ContainerStatus;
import org.apache.hadoop.yarn.api.records.NodeReport;
import org.apache.hadoop.yarn.api.records.UpdatedContainer;
import org.apache.hadoop.yarn.client.api.async.AMRMClientAsync.AbstractCallbackHandler;

/**   
 * @author sixliu   
 * @date   2018年1月17日 
 * @email  359852326@qq.com  
 * @Description 
 */
public class AmCallbackHandlerImpl extends AbstractCallbackHandler{

	@Override
	public float getProgress() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void onContainersAllocated(List<Container> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onContainersCompleted(List<ContainerStatus> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onContainersUpdated(List<UpdatedContainer> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onError(Throwable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNodesUpdated(List<NodeReport> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onShutdownRequest() {
		// TODO Auto-generated method stub
		
	}

}

