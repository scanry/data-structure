package com.six.data_structure.yarn.protocol;

import java.io.IOException;

import org.apache.hadoop.yarn.api.ApplicationClientProtocol;
import org.apache.hadoop.yarn.api.protocolrecords.CancelDelegationTokenRequest;
import org.apache.hadoop.yarn.api.protocolrecords.CancelDelegationTokenResponse;
import org.apache.hadoop.yarn.api.protocolrecords.FailApplicationAttemptRequest;
import org.apache.hadoop.yarn.api.protocolrecords.FailApplicationAttemptResponse;
import org.apache.hadoop.yarn.api.protocolrecords.GetAllResourceTypeInfoRequest;
import org.apache.hadoop.yarn.api.protocolrecords.GetAllResourceTypeInfoResponse;
import org.apache.hadoop.yarn.api.protocolrecords.GetApplicationAttemptReportRequest;
import org.apache.hadoop.yarn.api.protocolrecords.GetApplicationAttemptReportResponse;
import org.apache.hadoop.yarn.api.protocolrecords.GetApplicationAttemptsRequest;
import org.apache.hadoop.yarn.api.protocolrecords.GetApplicationAttemptsResponse;
import org.apache.hadoop.yarn.api.protocolrecords.GetApplicationReportRequest;
import org.apache.hadoop.yarn.api.protocolrecords.GetApplicationReportResponse;
import org.apache.hadoop.yarn.api.protocolrecords.GetApplicationsRequest;
import org.apache.hadoop.yarn.api.protocolrecords.GetApplicationsResponse;
import org.apache.hadoop.yarn.api.protocolrecords.GetClusterMetricsRequest;
import org.apache.hadoop.yarn.api.protocolrecords.GetClusterMetricsResponse;
import org.apache.hadoop.yarn.api.protocolrecords.GetClusterNodeLabelsRequest;
import org.apache.hadoop.yarn.api.protocolrecords.GetClusterNodeLabelsResponse;
import org.apache.hadoop.yarn.api.protocolrecords.GetClusterNodesRequest;
import org.apache.hadoop.yarn.api.protocolrecords.GetClusterNodesResponse;
import org.apache.hadoop.yarn.api.protocolrecords.GetContainerReportRequest;
import org.apache.hadoop.yarn.api.protocolrecords.GetContainerReportResponse;
import org.apache.hadoop.yarn.api.protocolrecords.GetContainersRequest;
import org.apache.hadoop.yarn.api.protocolrecords.GetContainersResponse;
import org.apache.hadoop.yarn.api.protocolrecords.GetDelegationTokenRequest;
import org.apache.hadoop.yarn.api.protocolrecords.GetDelegationTokenResponse;
import org.apache.hadoop.yarn.api.protocolrecords.GetLabelsToNodesRequest;
import org.apache.hadoop.yarn.api.protocolrecords.GetLabelsToNodesResponse;
import org.apache.hadoop.yarn.api.protocolrecords.GetNewApplicationRequest;
import org.apache.hadoop.yarn.api.protocolrecords.GetNewApplicationResponse;
import org.apache.hadoop.yarn.api.protocolrecords.GetNewReservationRequest;
import org.apache.hadoop.yarn.api.protocolrecords.GetNewReservationResponse;
import org.apache.hadoop.yarn.api.protocolrecords.GetNodesToLabelsRequest;
import org.apache.hadoop.yarn.api.protocolrecords.GetNodesToLabelsResponse;
import org.apache.hadoop.yarn.api.protocolrecords.GetQueueInfoRequest;
import org.apache.hadoop.yarn.api.protocolrecords.GetQueueInfoResponse;
import org.apache.hadoop.yarn.api.protocolrecords.GetQueueUserAclsInfoRequest;
import org.apache.hadoop.yarn.api.protocolrecords.GetQueueUserAclsInfoResponse;
import org.apache.hadoop.yarn.api.protocolrecords.KillApplicationRequest;
import org.apache.hadoop.yarn.api.protocolrecords.KillApplicationResponse;
import org.apache.hadoop.yarn.api.protocolrecords.MoveApplicationAcrossQueuesRequest;
import org.apache.hadoop.yarn.api.protocolrecords.MoveApplicationAcrossQueuesResponse;
import org.apache.hadoop.yarn.api.protocolrecords.RenewDelegationTokenRequest;
import org.apache.hadoop.yarn.api.protocolrecords.RenewDelegationTokenResponse;
import org.apache.hadoop.yarn.api.protocolrecords.ReservationDeleteRequest;
import org.apache.hadoop.yarn.api.protocolrecords.ReservationDeleteResponse;
import org.apache.hadoop.yarn.api.protocolrecords.ReservationListRequest;
import org.apache.hadoop.yarn.api.protocolrecords.ReservationListResponse;
import org.apache.hadoop.yarn.api.protocolrecords.ReservationSubmissionRequest;
import org.apache.hadoop.yarn.api.protocolrecords.ReservationSubmissionResponse;
import org.apache.hadoop.yarn.api.protocolrecords.ReservationUpdateRequest;
import org.apache.hadoop.yarn.api.protocolrecords.ReservationUpdateResponse;
import org.apache.hadoop.yarn.api.protocolrecords.SignalContainerRequest;
import org.apache.hadoop.yarn.api.protocolrecords.SignalContainerResponse;
import org.apache.hadoop.yarn.api.protocolrecords.SubmitApplicationRequest;
import org.apache.hadoop.yarn.api.protocolrecords.SubmitApplicationResponse;
import org.apache.hadoop.yarn.api.protocolrecords.UpdateApplicationPriorityRequest;
import org.apache.hadoop.yarn.api.protocolrecords.UpdateApplicationPriorityResponse;
import org.apache.hadoop.yarn.api.protocolrecords.UpdateApplicationTimeoutsRequest;
import org.apache.hadoop.yarn.api.protocolrecords.UpdateApplicationTimeoutsResponse;
import org.apache.hadoop.yarn.exceptions.YarnException;

/**
 * @author sixliu
 * @date 2018年1月17日
 * @email 359852326@qq.com
 * @Description
 */
public class ApplicationClientProtocolImpl implements ApplicationClientProtocol {

	@Override
	public CancelDelegationTokenResponse cancelDelegationToken(CancelDelegationTokenRequest arg0)
			throws YarnException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetApplicationAttemptReportResponse getApplicationAttemptReport(GetApplicationAttemptReportRequest arg0)
			throws YarnException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetApplicationAttemptsResponse getApplicationAttempts(GetApplicationAttemptsRequest arg0)
			throws YarnException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetApplicationReportResponse getApplicationReport(GetApplicationReportRequest arg0)
			throws YarnException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetApplicationsResponse getApplications(GetApplicationsRequest arg0) throws YarnException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetContainerReportResponse getContainerReport(GetContainerReportRequest arg0)
			throws YarnException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetContainersResponse getContainers(GetContainersRequest arg0) throws YarnException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetDelegationTokenResponse getDelegationToken(GetDelegationTokenRequest arg0)
			throws YarnException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RenewDelegationTokenResponse renewDelegationToken(RenewDelegationTokenRequest arg0)
			throws YarnException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReservationDeleteResponse deleteReservation(ReservationDeleteRequest arg0)
			throws YarnException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FailApplicationAttemptResponse failApplicationAttempt(FailApplicationAttemptRequest arg0)
			throws YarnException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KillApplicationResponse forceKillApplication(KillApplicationRequest arg0) throws YarnException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetClusterMetricsResponse getClusterMetrics(GetClusterMetricsRequest arg0)
			throws YarnException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetClusterNodeLabelsResponse getClusterNodeLabels(GetClusterNodeLabelsRequest arg0)
			throws YarnException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetClusterNodesResponse getClusterNodes(GetClusterNodesRequest arg0) throws YarnException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetLabelsToNodesResponse getLabelsToNodes(GetLabelsToNodesRequest arg0) throws YarnException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetNewApplicationResponse getNewApplication(GetNewApplicationRequest arg0)
			throws YarnException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetNewReservationResponse getNewReservation(GetNewReservationRequest arg0)
			throws YarnException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetNodesToLabelsResponse getNodeToLabels(GetNodesToLabelsRequest arg0) throws YarnException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetQueueInfoResponse getQueueInfo(GetQueueInfoRequest arg0) throws YarnException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetQueueUserAclsInfoResponse getQueueUserAcls(GetQueueUserAclsInfoRequest arg0)
			throws YarnException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetAllResourceTypeInfoResponse getResourceTypeInfo(GetAllResourceTypeInfoRequest arg0)
			throws YarnException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReservationListResponse listReservations(ReservationListRequest arg0) throws YarnException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MoveApplicationAcrossQueuesResponse moveApplicationAcrossQueues(MoveApplicationAcrossQueuesRequest arg0)
			throws YarnException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SignalContainerResponse signalToContainer(SignalContainerRequest arg0) throws YarnException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SubmitApplicationResponse submitApplication(SubmitApplicationRequest arg0)
			throws YarnException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReservationSubmissionResponse submitReservation(ReservationSubmissionRequest arg0)
			throws YarnException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UpdateApplicationPriorityResponse updateApplicationPriority(UpdateApplicationPriorityRequest arg0)
			throws YarnException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UpdateApplicationTimeoutsResponse updateApplicationTimeouts(UpdateApplicationTimeoutsRequest arg0)
			throws YarnException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReservationUpdateResponse updateReservation(ReservationUpdateRequest arg0)
			throws YarnException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
