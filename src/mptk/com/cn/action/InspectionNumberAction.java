package mptk.com.cn.action;

import java.io.IOException;

import mptk.com.cn.service.InspectionNumberService;
import mptk.com.cn.serviceImpl.InspectionNumberServiceImpl;

/**
 * 巡檢數量維護
 * 
 * @author crom.lai
 * @date 2018年7月25日
 * @time 下午4:05:46
 */
public class InspectionNumberAction extends SuperAction {

	private InspectionNumberService inspectionNumberService = new InspectionNumberServiceImpl();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 獲取巡檢數量明細
	 * 
	 * @return
	 * @throws IOException
	 */
	public String getInventoryNumberDetail() throws IOException {
		String result = inspectionNumberService.getInventoryNumberDetail(orgId, planId);
		//System.out.println(result);
		this.outPutString(result, this.response);
		return null;
	}

	/**
	 * 維護巡檢時的數量明細
	 * @return
	 * @throws IOException
	 */
	public String updataInventoryNumberDetail() throws IOException {
		String result = inspectionNumberService.updataInventoryNumberDetail(planId, orgId, masterId, inventoryNumberId,
				inventoryTotalNumber, sendInventoryNumber, extractInventoryNumber, acceptableInventoryNumber,
				unqualifiedInventoryOnSiteNumber,unqualifiedInventoryNumber,rejectionInventoryNumber);
		//System.out.println(result);
		this.outPutString(result, this.response);
		return null;
	}

	private String orgId;
	private String masterId;
	private String planId;
	private String inventoryTotalNumber;// 巡檢總數
	private String sendInventoryNumber;// 送檢總數
	private String extractInventoryNumber;// 抽檢數
	private String acceptableInventoryNumber;// 允收數
	private String unqualifiedInventoryOnSiteNumber;// 巡檢不良數
	private String unqualifiedInventoryNumber;// 不良數
	private String rejectionInventoryNumber;// 拒收數
	private String inventoryNumberId;// 巡檢數量明細Id

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}

	public void setInventoryTotalNumber(String inventoryTotalNumber) {
		this.inventoryTotalNumber = inventoryTotalNumber;
	}

	public void setSendInventoryNumber(String sendInventoryNumber) {
		this.sendInventoryNumber = sendInventoryNumber;
	}

	public void setExtractInventoryNumber(String extractInventoryNumber) {
		this.extractInventoryNumber = extractInventoryNumber;
	}

	public void setAcceptableInventoryNumber(String acceptableInventoryNumber) {
		this.acceptableInventoryNumber = acceptableInventoryNumber;
	}

	public void setUnqualifiedInventoryOnSiteNumber(String unqualifiedInventoryOnSiteNumber) {
		this.unqualifiedInventoryOnSiteNumber = unqualifiedInventoryOnSiteNumber;
	}

	public void setUnqualifiedInventoryNumber(String unqualifiedInventoryNumber) {
		this.unqualifiedInventoryNumber = unqualifiedInventoryNumber;
	}

	public void setRejectionInventoryNumber(String rejectionInventoryNumber) {
		this.rejectionInventoryNumber = rejectionInventoryNumber;
	}

	public void setInventoryNumberId(String inventoryNumberId) {
		this.inventoryNumberId = inventoryNumberId;
	}

}
