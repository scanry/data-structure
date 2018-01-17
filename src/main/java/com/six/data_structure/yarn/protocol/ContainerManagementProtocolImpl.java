package com.six.data_structure.yarn.protocol;

import java.io.IOException;

import org.apache.hadoop.yarn.api.ContainerManagementProtocol;
import org.apache.hadoop.yarn.api.protocolrecords.CommitResponse;
import org.apache.hadoop.yarn.api.protocolrecords.ContainerUpdateRequest;
import org.apache.hadoop.yarn.api.protocolrecords.ContainerUpdateResponse;
import org.apache.hadoop.yarn.api.protocolrecords.GetContainerStatusesRequest;
import org.apache.hadoop.yarn.api.protocolrecords.GetContainerStatusesResponse;
import org.apache.hadoop.yarn.api.protocolrecords.IncreaseContainersResourceRequest;
import org.apache.hadoop.yarn.api.protocolrecords.IncreaseContainersResourceResponse;
import org.apache.hadoop.yarn.api.protocolrecords.ReInitializeContainerRequest;
import org.apache.hadoop.yarn.api.protocolrecords.ReInitializeContainerResponse;
import org.apache.hadoop.yarn.api.protocolrecords.ResourceLocalizationRequest;
import org.apache.hadoop.yarn.api.protocolrecords.ResourceLocalizationResponse;
import org.apache.hadoop.yarn.api.protocolrecords.RestartContainerResponse;
import org.apache.hadoop.yarn.api.protocolrecords.RollbackResponse;
import org.apache.hadoop.yarn.api.protocolrecords.SignalContainerRequest;
import org.apache.hadoop.yarn.api.protocolrecords.SignalContainerResponse;
import org.apache.hadoop.yarn.api.protocolrecords.StartContainersRequest;
import org.apache.hadoop.yarn.api.protocolrecords.StartContainersResponse;
import org.apache.hadoop.yarn.api.protocolrecords.StopContainersRequest;
import org.apache.hadoop.yarn.api.protocolrecords.StopContainersResponse;
import org.apache.hadoop.yarn.api.records.ContainerId;
import org.apache.hadoop.yarn.exceptions.YarnException;

/**   
 * @author sixliu   
 * @date   2018年1月17日 
 * @email  359852326@qq.com  
 * @Description 
 */
public class ContainerManagementProtocolImpl implements ContainerManagementProtocol{

	@Override
	public CommitResponse commitLastReInitialization(ContainerId arg0) throws YarnException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetContainerStatusesResponse getContainerStatuses(GetContainerStatusesRequest arg0)
			throws YarnException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IncreaseContainersResourceResponse increaseContainersResource(IncreaseContainersResourceRequest arg0)
			throws YarnException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResourceLocalizationResponse localize(ResourceLocalizationRequest arg0) throws YarnException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReInitializeContainerResponse reInitializeContainer(ReInitializeContainerRequest arg0)
			throws YarnException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RestartContainerResponse restartContainer(ContainerId arg0) throws YarnException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RollbackResponse rollbackLastReInitialization(ContainerId arg0) throws YarnException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SignalContainerResponse signalToContainer(SignalContainerRequest arg0) throws YarnException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StartContainersResponse startContainers(StartContainersRequest arg0) throws YarnException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StopContainersResponse stopContainers(StopContainersRequest arg0) throws YarnException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContainerUpdateResponse updateContainer(ContainerUpdateRequest arg0) throws YarnException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

}

