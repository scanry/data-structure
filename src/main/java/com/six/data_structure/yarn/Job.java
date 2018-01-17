package com.six.data_structure.yarn;

import lombok.Data;

/**
 * @author sixliu
 * @date 2018年1月17日
 * @email 359852326@qq.com
 * @Description
 */
@Data
public class Job {

	private String name;
	private int priority;
	private String cron;
	private int maxAppAttempts;
	private int numContainers;
	private String jobBossClass;
	private String jobWorkerClass;
}
