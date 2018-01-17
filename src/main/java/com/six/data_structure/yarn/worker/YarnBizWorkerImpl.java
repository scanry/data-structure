package com.six.data_structure.yarn.worker;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.yarn.client.api.async.NMClientAsync;
import org.apache.hadoop.yarn.client.api.async.NMClientAsync.AbstractCallbackHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author sixliu
 * @date 2018年1月17日
 * @email 359852326@qq.com
 * @Description
 */
public class YarnBizWorkerImpl {

	final static Logger log = LoggerFactory.getLogger(YarnBizWorkerImpl.class);

	private NMClientAsync nmClientAsync;

	public void start() {
		AbstractCallbackHandler callbackHandler = new CallbackHandlerImpl();
		nmClientAsync = NMClientAsync.createNMClientAsync(callbackHandler);
		Configuration config=new Configuration();
		nmClientAsync.init(config);
		nmClientAsync.start();
	}

	public void shutdown() {
		try {
			nmClientAsync.close();
		} catch (IOException e) {
			log.error("", e);
		}
	}
}
