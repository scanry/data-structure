package com.six.data_structure.yarn.protocol;

import java.io.IOException;

import org.apache.hadoop.yarn.api.ApplicationMasterProtocol;
import org.apache.hadoop.yarn.api.protocolrecords.AllocateRequest;
import org.apache.hadoop.yarn.api.protocolrecords.AllocateResponse;
import org.apache.hadoop.yarn.api.protocolrecords.FinishApplicationMasterRequest;
import org.apache.hadoop.yarn.api.protocolrecords.FinishApplicationMasterResponse;
import org.apache.hadoop.yarn.api.protocolrecords.RegisterApplicationMasterRequest;
import org.apache.hadoop.yarn.api.protocolrecords.RegisterApplicationMasterResponse;
import org.apache.hadoop.yarn.exceptions.YarnException;

/**   
 * @author sixliu   
 * @date   2018年1月17日 
 * @email  359852326@qq.com  
 * @Description 
 */
public class ApplicationMasterProtocolImpl implements ApplicationMasterProtocol{

	@Override
	public AllocateResponse allocate(AllocateRequest arg0) throws YarnException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FinishApplicationMasterResponse finishApplicationMaster(FinishApplicationMasterRequest arg0)
			throws YarnException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RegisterApplicationMasterResponse registerApplicationMaster(RegisterApplicationMasterRequest arg0)
			throws YarnException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

}

