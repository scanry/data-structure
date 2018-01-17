package com.six.data_structure.yarn.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.yarn.api.ApplicationMasterProtocol;
import org.apache.hadoop.yarn.api.ContainerManagementProtocol;
import org.apache.hadoop.yarn.api.protocolrecords.AllocateRequest;
import org.apache.hadoop.yarn.api.protocolrecords.AllocateRequest.AllocateRequestBuilder;
import org.apache.hadoop.yarn.api.protocolrecords.AllocateResponse;
import org.apache.hadoop.yarn.api.protocolrecords.RegisterApplicationMasterRequest;
import org.apache.hadoop.yarn.api.protocolrecords.StartContainerRequest;
import org.apache.hadoop.yarn.api.protocolrecords.StartContainersRequest;
import org.apache.hadoop.yarn.api.records.ApplicationAccessType;
import org.apache.hadoop.yarn.api.records.Container;
import org.apache.hadoop.yarn.api.records.ContainerLaunchContext;
import org.apache.hadoop.yarn.api.records.LocalResource;
import org.apache.hadoop.yarn.api.records.Priority;
import org.apache.hadoop.yarn.api.records.Resource;
import org.apache.hadoop.yarn.api.records.ResourceRequest;
import org.apache.hadoop.yarn.client.api.async.AMRMClientAsync;
import org.apache.hadoop.yarn.exceptions.YarnException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.six.data_structure.yarn.Job;
import com.six.data_structure.yarn.JobStorage;

/**
 * @author sixliu
 * @date 2018年1月17日
 * @email 359852326@qq.com
 * @Description
 */
public class YarnServerImpl implements YarnServer {

	final static Logger log = LoggerFactory.getLogger(YarnServerImpl.class);

	private ApplicationMasterProtocol applicationMasterProtocol;
	private ContainerManagementProtocol containerManagementProtocol;
	private JobStorage jobStorage;
	private AMRMClientAsync<?> amRMClientAsync;
	private int intervalMs;

	public void start() {
		amRMClientAsync=AMRMClientAsync.createAMRMClientAsync(intervalMs, new AmCallbackHandlerImpl());
		Configuration config=new Configuration();
		amRMClientAsync.init(config);
		amRMClientAsync.start();
		RegisterApplicationMasterRequest registerRquest = new RegisterApplicationMasterRequestImpl();
		try {
			//amRMClientAsync.registerApplicationMaster(null, 1, null);
			applicationMasterProtocol.registerApplicationMaster(registerRquest);
		} catch (Exception e) {
			log.error("", e);
		}
	}

	@Override
	public void setJobStorage(JobStorage jobStorage) {
		this.jobStorage = jobStorage;
	}

	@Override
	public void execute(String jobName) {
		Job job = jobStorage.get(jobName);
		if (null != job) {
			AllocateRequestBuilder allocateRequestBuilder = AllocateRequest.newBuilder();
			Priority priority = Priority.newInstance(job.getPriority());
			String hostName = null;
			Resource capability = Resource.newInstance(1, 1);
			int numContainers = job.getNumContainers();
			ResourceRequest resourceRequest = ResourceRequest.newInstance(priority, hostName, capability,
					numContainers);
			allocateRequestBuilder.askList(Arrays.asList(resourceRequest));
			AllocateRequest allocateRequest = allocateRequestBuilder.build();
			try {
				AllocateResponse allocateResponse = applicationMasterProtocol.allocate(allocateRequest);
				allocateResponse.getUpdatedNodes();
				List<Container> containers = allocateResponse.getAllocatedContainers();
				for (Container container : containers) {
					Map<String, LocalResource> localResources = null;
					Map<String, String> environment = null;
					List<String> commands = null;
					Map<String, ByteBuffer> serviceData = null;
					ByteBuffer tokens = null;
					Map<ApplicationAccessType, String> acls = null;
					ContainerLaunchContext context = ContainerLaunchContext.newInstance(localResources, environment,
							commands, serviceData, tokens, acls);
					StartContainerRequest startContainerRequest = StartContainerRequest.newInstance(context,
							container.getContainerToken());
					StartContainersRequest arg0 = StartContainersRequest
							.newInstance(Arrays.asList(startContainerRequest));
					containerManagementProtocol.startContainers(arg0);
				}
			} catch (YarnException e) {
				log.error("", e);
			} catch (IOException e) {
				log.error("", e);
			}
		}
	}

	@Override
	public void suspend(String jobName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void goOn(String jobName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void stop(String jobName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void stopAll() {
		// TODO Auto-generated method stub

	}
}
