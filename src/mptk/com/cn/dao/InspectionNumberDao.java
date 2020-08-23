package mptk.com.cn.dao;

public interface InspectionNumberDao {

	/**
	 * 獲取巡檢數量明細
	 * @param orgId
	 * @param planId
	 * @return
	 */
	public String getInventoryNumberDetail(String orgId, String planId);

	/**
	 * 維護巡檢時的數量明細
	 * @param planId
	 * @param orgId
	 * @param masterId
	 * @param inventoryNumberId
	 * @param inventoryTotalNumber
	 * @param sendInventoryNumber
	 * @param extractInventoryNumber
	 * @param acceptableInventoryNumber
	 * @param unqualifiedInventoryOnSiteNumber
	 * @param unqualifiedInventoryNumber
	 * @param rejectionInventoryNumber
	 * @return
	 */
	public String updataInventoryNumberDetail(String planId, String orgId, String masterId, String inventoryNumberId,
			String inventoryTotalNumber, String sendInventoryNumber, String extractInventoryNumber,
			String acceptableInventoryNumber, String unqualifiedInventoryOnSiteNumber,
			String unqualifiedInventoryNumber, String rejectionInventoryNumber);
	
}
