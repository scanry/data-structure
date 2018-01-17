package com.six.data_structure.yarn.server;

import org.apache.hadoop.yarn.api.protocolrecords.RegisterApplicationMasterRequest;

/**
 * @author sixliu
 * @date 2018年1月17日
 * @email 359852326@qq.com
 * @Description
 */
public class RegisterApplicationMasterRequestImpl extends RegisterApplicationMasterRequest {

	private String host;
	private int port;
	private String trackingUrl;

	@Override
	public String getHost() {
		return host;
	}

	@Override
	public int getRpcPort() {
		return port;
	}

	@Override
	public String getTrackingUrl() {
		return trackingUrl;
	}

	@Override
	public void setHost(String host) {
		this.host = host;
	}

	@Override
	public void setRpcPort(int port) {
		this.port = port;
	}

	@Override
	public void setTrackingUrl(String trackingUrl) {
		this.trackingUrl = trackingUrl;
	}

}
