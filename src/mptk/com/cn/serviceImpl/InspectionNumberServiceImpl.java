package mptk.com.cn.serviceImpl;

import mptk.com.cn.dao.InspectionNumberDao;
import mptk.com.cn.daoImpl.InspectionNumberDaoImpl;
import mptk.com.cn.service.InspectionNumberService;

public class InspectionNumberServiceImpl implements InspectionNumberService {

	private InspectionNumberDao inspectionNumberDao = new InspectionNumberDaoImpl();
	
	/**
	 * 獲取巡檢數量明細
	 * @param orgId
	 * @param planId
	 * @return
	 */
	@Override
	public String getInventoryNumberDetail(String orgId, String planId) {
		String result = inspectionNumberDao.getInventoryNumberDetail(orgId,planId);
		return result;
	}

	/**
	 * 維護巡檢時的數量明細
	 */
	@Override
	public String updataInventoryNumberDetail(String planId, String orgId, String masterId, String inventoryNumberId,
			String inventoryTotalNumber, String sendInventoryNumber, String extractInventoryNumber,
			String acceptableInventoryNumber, String unqualifiedInventoryOnSiteNumber,
			String unqualifiedInventoryNumber, String rejectionInventoryNumber) {
		String result = inspectionNumberDao.updataInventoryNumberDetail(planId, orgId, masterId, inventoryNumberId,
				inventoryTotalNumber, sendInventoryNumber, extractInventoryNumber, acceptableInventoryNumber,
				unqualifiedInventoryOnSiteNumber,unqualifiedInventoryNumber,rejectionInventoryNumber);
		return result;
	}

}
