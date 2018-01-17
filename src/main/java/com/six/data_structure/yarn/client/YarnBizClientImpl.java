package com.six.data_structure.yarn.client;

import java.io.IOException;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.yarn.api.records.ApplicationId;
import org.apache.hadoop.yarn.api.records.ApplicationSubmissionContext;
import org.apache.hadoop.yarn.api.records.ContainerLaunchContext;
import org.apache.hadoop.yarn.api.records.NodeReport;
import org.apache.hadoop.yarn.api.records.NodeState;
import org.apache.hadoop.yarn.api.records.Priority;
import org.apache.hadoop.yarn.api.records.Resource;
import org.apache.hadoop.yarn.client.api.YarnClient;
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
public class YarnBizClientImpl implements YarnBizClient {

	final static Logger log = LoggerFactory.getLogger(YarnBizClientImpl.class);

	private JobStorage jobStorage;
	private ApplicationId applicationId;
	private YarnClient yarnClient;

	@Override
	public void start() {
		Configuration config=new Configuration();
		yarnClient = YarnClient.createYarnClient();
		yarnClient.init(config);
		yarnClient.start();
	}

	@Override
	public void setJobStorage(JobStorage jobStorage) {
		this.jobStorage = jobStorage;
	}

	@Override
	public void execute(String jobName) {
		try {
			yarnClient.createApplication();
			List<NodeReport> nodes=yarnClient.getNodeReports(NodeState.NEW);
			System.out.println(nodes);
		} catch (YarnException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Job job = jobStorage.get(jobName);
		if (null != job) {
			String applicationName = job.getName();
			String queue = null;
			Priority priority = Priority.newInstance(job.getPriority());
			ContainerLaunchContext amContainer = null;
			boolean isUnmanagedAM = true;
			boolean cancelTokensWhenComplete = true;
			int maxAppAttempts = job.getMaxAppAttempts();
			Resource resource = null;
			String applicationType = null;// 应用类型，默认为”YARN”;
			ApplicationSubmissionContext context = ApplicationSubmissionContext.newInstance(applicationId,
					applicationName, queue, priority, amContainer, isUnmanagedAM, cancelTokensWhenComplete,
					maxAppAttempts, resource, applicationType);
			try {
				yarnClient.submitApplication(context);
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
		try {
			yarnClient.killApplication(applicationId);
		} catch (YarnException e) {
			log.error("", e);
		} catch (IOException e) {
			log.error("", e);
		}
	}

	@Override
	public void stopAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public void schedule(String jobName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void unschedule(String jobName) {
		// TODO Auto-generated method stub

	}
}
