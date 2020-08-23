package mptk.com.cn.action;

import java.io.IOException;

import mptk.com.cn.service.AuditItemMaintainService;
import mptk.com.cn.serviceImpl.AuditItemMaintainServiceImpl;

public class AuditItemMaintainAction extends SuperAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String orgId;//公司別
	private String resultIdList;//
	private String masterId;//masterId
	private String empno;//工號
	private String planId;//計畫ID
	private String partNo;//料號
	private String type;//尺寸類型
	private String siteOrCustomer;
	private AuditItemMaintainService aims = new AuditItemMaintainServiceImpl();
	
	/**
	 * 获取站点或用户信息
	 * @return
	 * @throws IOException
	 */
	public String getSiteOrCustomer() throws IOException{
		String result = aims.getSiteOrCustomer(siteOrCustomer);
		this.outPutString(result, this.response);
		//System.out.println(result);
		return null;
	}
	
	/**
	 * 维护稽核项目是否ok
	 * @return
	 * @throws IOException
	 */
	public String maintainAuditIsPass() throws IOException{
		String result = aims.AuditItemIsPass(Integer.valueOf(orgId), resultIdList, Integer.valueOf(masterId));
		this.outPutString(result, this.response);
		//System.out.println(result);
		return null;
	}
	
	/**
	 * 根据工号获取用户名
	 * @return
	 * @throws IOException
	 */
	public String getNameByEmpno() throws IOException{
		String result = aims.getNameByEmpno(empno);
		this.outPutString(result, this.response);
		//System.out.println(result);
		return null;
	}
	
	/**
	 * 更新稽核計畫狀態
	 * @return
	 * @throws IOException 
	 */
	public String updatePlanStatus() throws IOException{
		String result = aims.updatePlanStatus(Integer.valueOf(planId),empno,Integer.valueOf(orgId));
		this.outPutString(result, this.response);
		//System.out.println(result);
		return null;
	}
	
	/**
	 * 根據料號獲取稽核計畫信息
	 * @return
	 * @throws IOException 
	 */
	public String getPlanInfoByPartNo() throws IOException{
		String result = aims.getPlanInfoByPartNo(partNo,Integer.valueOf(orgId));
		this.outPutString(result, this.response);
		//System.out.println(result);
		return null;
	}
	
	/**
	 * 獲取標準值
	 * @return
	 * @throws IOException
	 */
	public String getCriterionByPartNo() throws IOException{
		String result = aims.getCriterionByPartNo(partNo,type);
		this.outPutString(result, this.response);
		//System.out.println(result);
		return null;
	}	
	
	/**
	 * 獲取標準值
	 * @return
	 * @throws IOException
	 */
	public String getCriterionByPart() throws IOException{
		String result = aims.getCriterionByPart(partNo,type);
		this.outPutString(result, this.response);
		//System.out.println(result);
		return null;
	}
	
	/**
	 * 獲取稽核計畫狀態
	 * @return
	 * @throws IOException
	 */
	public String getPlanStatus() throws IOException{
		String result = aims.getPlanStatus(planId);
		this.outPutString(result, this.response);
		//System.out.println(result);
		return null;
	}


	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getResultIdList() {
		return resultIdList;
	}

	public void setResultIdList(String resultIdList) {
		this.resultIdList = resultIdList;
	}

	public String getMasterId() {
		return masterId;
	}

	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}

	public String getEmpno() {
		return empno;
	}

	public void setEmpno(String empno) {
		this.empno = empno;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public String getPartNo() {
		return partNo;
	}

	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setSiteOrCustomer(String siteOrCustomer) {
		this.siteOrCustomer = siteOrCustomer;
	}

}
