package mptk.com.cn.daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

import mptk.com.cn.dao.AuditItemMaintainDao;
import mptk.com.cn.utils.DBUtils;
import mptk.com.cn.utils.NullTranslator;

public class AuditItemMaintainDaoImpl implements AuditItemMaintainDao {

	private DBUtils db = new DBUtils();

	@Override
	public String AuditItemIsPass(Integer orgId, String resultIdList, Integer masterId) {
		Connection conn = null;
		CallableStatement cs = null;
		String result = "";
		try {
			conn = db.getConnection();
			cs = conn.prepareCall("{?=call TRACK_PROJECT_UTILS.UPDATE_AUDIT_RESULT_PASS(?,?,?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setInt(2, orgId);
			cs.setString(3, resultIdList);
			cs.setInt(4, masterId);
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.registerOutParameter(6, Types.VARCHAR);
			cs.registerOutParameter(7, Types.VARCHAR);
			cs.execute();
			result = cs.getString(6) + "------" + cs.getString(7);
		} catch (ClassNotFoundException e) {
			result = "N------服務器異常-請聯繫管理員";
			e.printStackTrace();
		} catch (SQLException e) {
			result = "N------服務器異常-請聯繫管理員";
			e.printStackTrace();
		} finally {
			try {
				cs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				result = "N------服務器異常-請聯繫管理員";
			}
		}
		return result;
	}

	/**
	 * 根据工会号获取用户名:模糊查询
	 */
	@Override
	public String getNameByEmpno(String empno) {
		Connection conn = null;
		CallableStatement cs = null;
		String result = "";
		try {
			conn = db.getConnection();
			cs = conn.prepareCall("{?=call TRACK_PROJECT_UTILS.GET_EMPLOYEE_BY_EMPNO(?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2, empno);
			cs.registerOutParameter(3, Types.VARCHAR);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.execute();
			result = cs.getString(3) + "------" + cs.getString(5);
		} catch (ClassNotFoundException e) {
			result = "N------服務器異常-請聯繫管理員";
			e.printStackTrace();
		} catch (SQLException e) {
			result = "N------服務器異常-請聯繫管理員";
			e.printStackTrace();
		} finally {
			try {
				cs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				result = "N------服務器異常-請聯繫管理員";
			}
		}
		return result;
	}

	@Override
	public String updatePlanStatus(Integer planId, String empno, Integer orgId) {
		Connection conn = null;
		CallableStatement cs = null;
		String result = "";
		try {
			conn = db.getConnection();
			cs = conn.prepareCall("{?=call TRACK_PROJECT_UTILS.UPDATE_PLAN_STATUS(?,?,?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setInt(2, planId);
			cs.setString(3, empno);
			cs.setInt(4, orgId);
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.registerOutParameter(6, Types.VARCHAR);
			cs.registerOutParameter(7, Types.VARCHAR);
			cs.execute();
			result = cs.getString(5) + "------" + cs.getString(7);
		} catch (ClassNotFoundException e) {
			result = "N------服務器異常-請聯繫管理員";
			e.printStackTrace();
		} catch (SQLException e) {
			result = "N------服務器異常-請聯繫管理員";
			e.printStackTrace();
		} finally {
			try {
				cs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				result = "N------服務器異常-請聯繫管理員";
			}
		}
		return result;
	}

	@Override
	public String getPlanInfoByPartNo(String partNo, Integer orgId) {
		String result = "";
		/*try {*/
			String sql = " SELECT OWNER_ID,   " + //稽核負責人
					"                   (SELECT NAME  " + 
					"                      FROM PS_EMPLOYEE_ALL PEA  " + 
					"                     WHERE PEA.EMPLOYEE_MASTER_ID = GAP.OWNER_ID  " + 
					"                       AND PEA.ORG_ID = GAP.ORG_ID  " + 
					"                       AND ROWNUM = 1) OWNER_NAME,  " + 
					"                   MAKE_FACTORY_ID,   " + //工廠別
					"                   (SELECT MAKE_FACTORY_NAME  " + 
					"                      FROM GTC_PLM_MAKE_FACTORY_CV GPMFC  " + 
					"                     WHERE ORG_ID = GAP.ORG_ID  " + 
					"                       AND GPMFC.MAKE_FACTORY_ID = GAP.MAKE_FACTORY_ID) MAKE_FACTORY_NAME,  " + 
					"                   VENDOR_ID,   " + //供應商
					"                   (SELECT VENDOR_NAME  " + 
					"                      FROM GTC_SP_VENDORS_CV GSVC  " + 
					"                     WHERE ORG_ID = GAP.ORG_ID  " + 
					"                       AND GSVC.VENDOR_ID = GAP.VENDOR_ID) VENDOR_NAME,  " + 
					"                   PRODUCTION_PROCESS_CODE,   " + //製程
					"                   (SELECT MEANING  " + 
					"                      FROM GTC_PLM_PRODUCTION_PROCESS_CV GPPPC  " + 
					"                     WHERE ORG_ID = GAP.ORG_ID  " + 
					"                       AND GPPPC.LOOKUP_CODE = GAP.PRODUCTION_PROCESS_CODE) MEANING,  " + 
					"                   PROJECT_ID,   " + //機種
					"                   (SELECT PROJECT_NAME  " + 
					"                      FROM GTC_PLM_PROJECTS_CV GPPC  " + 
					"                     WHERE ORG_ID = GAP.ORG_ID  " + 
					"                       AND GPPC.PROJECT_ID = GAP.PROJECT_ID) PROJECT_NAME,  " + 
					"                   PART_ID,   " + //品名
					"                   (SELECT NAME  " + 
					"                      FROM GTC_AUDIT_PARTS GAPS  " + 
					"                     WHERE GAPS.PART_ID = GAP.PART_ID  " + 
					"                       AND ORG_ID = GAP.ORG_ID) PART_NAME1,  " + 
					"                   PART_NO,   " + //料號
					"                   (SELECT SEGMENT1  " + 
					"                      FROM MTL_SYSTEM_ITEMS_105_CV MSIC  " + 
					"                     WHERE MSIC.INVENTORY_ITEM_ID = GAP.PART_NO) PART_NAME2,  " + 
					"                   PRODUCTION_LINE_ID,   " + //稽核線別
					"                   DESCRIPTION,   " + //描述
					"                   TYPE_ID,  " + 
					"                   (SELECT TYPE_NAME  " + 
					"                      FROM GTC_AUDIT_PLAN_TYPES GAPT  " + 
					"                     WHERE ORG_ID = GAP.ORG_ID  " + 
					"                       AND GAPT.TYPE_ID = GAP.TYPE_ID) TYPE_NAME,  " + //類別 
					"                   FACILITY_NAME,   " + //機台
					"                   MOLD_NO,   " + //模號
					"                   SAMPLE_MATERIAL,   " + //材料
					"                   SAMPLE_COLOR,   " + //顏色
					"                   SAMPLE_VERSION,    " + //版本
					"                   GAP.Mold_No MODEL_NO,   " + //模號
					"                   FACILITY_NAME FN   " + //機台
					"              FROM GTC_AUDIT_PLANS GAP  " + 
					"             WHERE PART_NO =  " + 
					"                   (SELECT TO_CHAR(MSIC.INVENTORY_ITEM_ID)  " + 
					"                      FROM MTL_SYSTEM_ITEMS_105_CV MSIC  " + 
					"                     WHERE MSIC.SEGMENT1 = '"+partNo+"' )  " + 
					"               AND ORG_ID = "+orgId+"  " + 
					"             ORDER BY CREATION_DATE DESC ";
			//cs = conn.prepareCall("{?=call TRACK_PROJECT_UTILS.GET_PLAN_INFO_BY_PART_NO(?,?,?,?,?)}");
			List<Map<String, Object>> list = null;
			try {
				list = db.execQuery(sql, null);
			}catch (Exception e) {
				return "N------"+e.getMessage();
			}
//			cs.registerOutParameter(1, Types.VARCHAR);
//			cs.setString(2, partNo);
//			cs.setInt(3, orgId);
//			cs.registerOutParameter(4, Types.VARCHAR);
//			cs.registerOutParameter(5, Types.VARCHAR);
//			cs.registerOutParameter(6, Types.VARCHAR);
//			cs.execute();
			//result = cs.getString(4) + "------" + cs.getString(5) + "------" + cs.getString(6);
			if (NullTranslator.isNullEmptyCollection(list)) {
				return "Y------Y1------NO DATA FOUND";
			} else {
				Map <String,Object> map = list.get(0);
				result = NullTranslator.avoidNull(map.get("PROJECT_ID")) + ",,"
						+ NullTranslator.avoidNull(map.get("PROJECT_NAME")) + ",,"
						+ NullTranslator.avoidNull(map.get("MAKE_FACTORY_ID")) + ",,"
						+ NullTranslator.avoidNull(map.get("MAKE_FACTORY_NAME")) + ",,"
						+ NullTranslator.avoidNull(map.get("PRODUCTION_PROCESS_CODE")) + ",,"
						+ NullTranslator.avoidNull(map.get("MEANING")) + ",,"
						+ NullTranslator.avoidNull(map.get("PART_ID")) + ",,"
						+ NullTranslator.avoidNull(map.get("PART_NAME1")) + ",,"
						+ NullTranslator.avoidNull(map.get("FN")) + ",,"
						+ NullTranslator.avoidNull(map.get("PART_NAME2")) + ",,"
						+ NullTranslator.avoidNull(map.get("TYPE_ID")) + ",,"
						+ NullTranslator.avoidNull(map.get("TYPE_NAME")) + ",,"
						+ NullTranslator.avoidNull(map.get("VENDOR_ID")) + ",,"
						+ NullTranslator.avoidNull(map.get("VENDOR_NAME")) + ",,"
						+ NullTranslator.avoidNull(map.get("SAMPLE_COLOR")) + ",,"
						+ NullTranslator.avoidNull(map.get("SAMPLE_VERSION")) + ",,"
						+ NullTranslator.avoidNull(map.get("SAMPLE_MATERIAL")) + ",,"
						+ NullTranslator.avoidNull(map.get("MODEL_NO")) + ",,"
						+ NullTranslator.avoidNull(map.get("FN"));
			}
		/*} catch (ClassNotFoundException e) {
			result = "N------服務器異常-請聯繫管理員";
			e.printStackTrace();
		} catch (SQLException e) {
			result = "N------服務器異常-請聯繫管理員";
			e.printStackTrace();
		} */
		return "Y------Y------"+result;
	}

	/**
	 * 獲取尺寸標準值
	 */
	@Override
	public String getCriterionByPartNo(String partNo, String type) {
		Connection conn = null;
		CallableStatement cs = null;
		String result = "";
		try {
			conn = db.getConnection();
			cs = conn.prepareCall("{?=call TRACK_PROJECT_UTILS.GET_CRITERION_BY_PART_ID(?,?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2, partNo);
			cs.setString(3, type);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.registerOutParameter(6, Types.VARCHAR);
			cs.execute();
			result = cs.getString(4) + "------" + cs.getString(5) + "------" + cs.getString(6);
		} catch (ClassNotFoundException e) {
			result = "N------服務器異常-請聯繫管理員";
			e.printStackTrace();
		} catch (SQLException e) {
			result = "N------服務器異常-請聯繫管理員";
			e.printStackTrace();
		} finally {
			try {
				cs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				result = "N------服務器異常-請聯繫管理員";
			}
		}
		return result;
	}

	/**
	 * 獲取尺寸標準值
	 */
	@Override
	public String getCriterionByPart(String partNo, String type) {
		Connection conn = null;
		CallableStatement cs = null;
		String result = "";
		try {
			conn = db.getConnection();
			cs = conn.prepareCall("{?=call TRACK_PROJECT_UTILS.GET_CRITERION_BY_PART(?,?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2, partNo);
			cs.setString(3, type);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.registerOutParameter(6, Types.VARCHAR);
			cs.execute();
			result = cs.getString(4) + "------" + cs.getString(5) + "------" + cs.getString(6);
		} catch (ClassNotFoundException e) {
			result = "N------服務器異常-請聯繫管理員";
			e.printStackTrace();
		} catch (SQLException e) {
			result = "N------服務器異常-請聯繫管理員";
			e.printStackTrace();
		} finally {
			try {
				cs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				result = "N------服務器異常-請聯繫管理員";
			}
		}
		return result;
	}

	@Override
	public String getPlanStatus(String planId) {
		Connection conn = null;
		CallableStatement cs = null;
		String result = "";
		try {
			conn = db.getConnection();
			cs = conn.prepareCall("{?=call TRACK_PROJECT_UTILS.GET_PLAN_STATUS(?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2, planId);
			cs.registerOutParameter(3, Types.VARCHAR);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.execute();
			result = cs.getString(3) + "------" + cs.getString(5);
		} catch (ClassNotFoundException e) {
			result = "N------服務器異常-請聯繫管理員";
			e.printStackTrace();
		} catch (SQLException e) {
			result = "N------服務器異常-請聯繫管理員";
			e.printStackTrace();
		} finally {
			try {
				cs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				result = "N------服務器異常-請聯繫管理員";
			}
		}
		return result;
	}

	@Override
	public String getSiteOrCustomer(String siteOrCustomer) {
		Connection conn = null;
		CallableStatement cs = null;
		String result = "";
		try {
			conn = db.getConnection();
			cs = conn.prepareCall("{?=call GTC_APP_AUDIT_API.GET_CUSTOMER_OR_SITE(?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2, siteOrCustomer);
			cs.registerOutParameter(3, Types.VARCHAR);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.execute();
			result = cs.getString(3) + "、、" + cs.getString(5);
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
