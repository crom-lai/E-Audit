package mptk.com.cn.action;

import java.io.IOException;

import mptk.com.cn.service.TrackProjectService;
import mptk.com.cn.serviceImpl.TrackProjectServiceImpl;

/**
 * 追踪项目Action类
 * @author crom.lai
 *
 */
public class TrackProjectAction extends SuperAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TrackProjectService tps = new TrackProjectServiceImpl();

	//属性
	private String orgId;
	private String ownerName;
	private String resultId;
	private String trackObjectId;
	private String masterId;
	private String deptNo;

	/**
	 * 获取追踪项目列表
	 * 
	 * @return
	 * @throws IOException
	 */
	public String getTrackProjectList() throws IOException {
		String result = tps.getTrackProjectList(Integer.valueOf(orgId), ownerName);
		//System.out.println(result);
		this.outPutString(result, response);
		return null;
	}

	/**
	 * 获取稽核结果,根据稽核结果id
	 * 
	 * @return
	 * @throws IOException
	 */
	public String getAuditResult() throws IOException {
		String result = tps.getAuditResultById(Integer.valueOf(orgId), resultId);
		//System.out.println(result);
		this.outPutString(result, response);
		return null;
	}

	public String getTrackProjectItem() throws IOException {
		String result = tps.getTrackProjectItem(Integer.valueOf(orgId), trackObjectId, resultId);
		//System.out.println(result);
		this.outPutString(result, response);
		return null;
	}
	
	/**
	 * 获取用户信息
	 * @return
	 * @throws IOException 
	 */
	public String getUserInfoByMasterId() throws IOException{
		String result = tps.getUserInfoByMasterId(Integer.valueOf(masterId));
		//System.out.println(result);
		this.outPutString(result, response);
		return null;
	}
	
	public String getDepartmentName() throws IOException{
		String result = tps.getDepartmentName(deptNo);
		////System.out.println(result);
		this.outPutString(result, response);
		return null;
	}

	public String getMasterId() {
		return masterId;
	}

	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}

	public String getResultId() {
		return resultId;
	}

	public void setResultId(String resultId) {
		this.resultId = resultId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getTrackObjectId() {
		return trackObjectId;
	}

	public void setTrackObjectId(String trackObjectId) {
		this.trackObjectId = trackObjectId;
	}

	public String getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}

}
