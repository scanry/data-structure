package com.six.data_structure.yarn.client;

import com.six.data_structure.yarn.JobStorage;

/**
 * @author sixliu
 * @date 2018年1月17日
 * @email 359852326@qq.com
 * @Description
 */
public interface YarnBizClient {

	void start();
	
	void setJobStorage(JobStorage jobStorage);

	void execute(String jobName);

	void suspend(String jobName);

	void goOn(String jobName);

	void stop(String jobName);

	void stopAll();

	void schedule(String jobName);

	void unschedule(String jobName);
}
