package mptk.com.cn.daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import mptk.com.cn.dao.InspectionNumberDao;
import mptk.com.cn.utils.DBUtils;

public class InspectionNumberDaoImpl implements InspectionNumberDao {

	private DBUtils db = new DBUtils();
	
	/**
	 * 獲取巡檢數量明細
	 * @param orgId
	 * @param planId
	 * @return
	 */
	@Override
	public String getInventoryNumberDetail(String orgId, String planId) {
		Connection conn = null;
		CallableStatement cs = null;
		String result = "";
		try {
			conn = db.getConnection();
			cs = conn.prepareCall("{?=call GTC_APP_AUDIT_API.GET_INSPECTION_NUMBER_DETAIL(?,?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2, planId);
			cs.setString(3, orgId);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.registerOutParameter(6, Types.VARCHAR);
			cs.execute();
			result = cs.getString(4) + "、、" + cs.getString(5) + "、、" + cs.getString(6);
		} catch (ClassNotFoundException e) {
			result = "N、、N、、服務器異常-請聯繫管理員";
			e.printStackTrace();
		} catch (SQLException e) {
			result = "N、、N、、服務器異常-請聯繫管理員";
			e.printStackTrace();
		} finally {
			try {
				cs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				result = "N、、N、、服務器異常-請聯繫管理員";
			}
		}
		return result;
	}

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
	@Override
	public String updataInventoryNumberDetail(String planId, String orgId, String masterId, String inventoryNumberId,
			String inventoryTotalNumber, String sendInventoryNumber, String extractInventoryNumber,
			String acceptableInventoryNumber, String unqualifiedInventoryOnSiteNumber,
			String unqualifiedInventoryNumber, String rejectionInventoryNumber) {
		Connection conn = null;
		CallableStatement cs = null;
		String result = "";
		try {
			conn = db.getConnection();
			cs = conn.prepareCall("{?=call GTC_APP_AUDIT_API.UPDATE_INSPECTION_NUMBER(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2, planId);
			cs.setString(3, orgId);
			cs.setString(4, masterId);
			cs.setString(5, inventoryNumberId);
			cs.setString(6, inventoryTotalNumber);
			cs.setString(7, sendInventoryNumber);
			cs.setString(8, extractInventoryNumber);
			cs.setString(9, acceptableInventoryNumber);
			cs.setString(10, unqualifiedInventoryOnSiteNumber);
			cs.setString(11, unqualifiedInventoryNumber);
			cs.setString(12, rejectionInventoryNumber);
			cs.registerOutParameter(13, Types.VARCHAR);
			cs.registerOutParameter(14, Types.VARCHAR);
			cs.registerOutParameter(15, Types.VARCHAR);
			cs.execute();
			result = cs.getString(13) + "、、" + cs.getString(15);
		} catch (ClassNotFoundException e) {
			result = "N、、服務器異常-請聯繫管理員";
			e.printStackTrace();
		} catch (SQLException e) {
			result = "N、、服務器異常-請聯繫管理員";
			e.printStackTrace();
		} finally {
			try {
				cs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				result = "N、、服務器異常-請聯繫管理員";
			}
		}
		return result;
	}

}
