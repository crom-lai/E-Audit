package mptk.com.cn.daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

import mptk.com.cn.dao.AuditDao;
import mptk.com.cn.utils.DBUtils;
import mptk.com.cn.utils.NullTranslator;
import mptk.com.cn.utils.SFCSDBUtils;

public class AuditDaoImpl implements AuditDao {
	DBUtils db = new DBUtils();
	Connection conn = null;
	CallableStatement cs = null;
	String resultCodeOut = null;
	String returnCodeOut = null;
	String resultMsgOut = null;
	private final String NA = "NA";
	private final String GVL = "1882";

	// 创建稽核计划

	@Override
	public String CREATE_AUDIT_PLAN(String sAuditTemplae, String sPlanName, String sOwner, String sMake, String sVendor,
			String sProduction, String sProject, String sPart, String sPartNo, String sAuditedEmployee,
			String sAuditedEmployeeBoss, String sProductionLine, String sDescription, String sPlanDate, String sType,
			String sStatus, String sOrgid, String sCreated, String sFacility, String sMold, String sMaterial,
			String sColor, String sVersion, String customer,String frequencyId) {
		/*try {
			conn = db.getConnection();
			cs = conn.prepareCall("{?=call gtc_app_audit_api.CREATE_AUDIT_PLAN(?,?,?,?,?,?,?,?,?,?,"
					+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setInt(2, Integer.valueOf(sAuditTemplae.trim()));
			cs.setString(3, sPlanName);
			cs.setString(4, sOwner);
			cs.setString(5, sMake);
			cs.setString(6, sVendor);
			cs.setString(7, sProduction);
			cs.setString(8, sProject);
			cs.setString(9, sPart);
			cs.setString(10, sPartNo);
			cs.setString(11, sAuditedEmployee);
			cs.setString(12, sAuditedEmployeeBoss);
			cs.setString(13, sProductionLine);
			cs.setString(14, sDescription);
			cs.setString(15, sPlanDate);
			cs.setString(16, sType);
			cs.setString(17, sStatus);
			cs.setString(18, sOrgid);
			cs.setString(19, sCreated);
			cs.setString(20, sFacility);
			cs.setString(21, sMold);
			cs.setString(22, sMaterial);
			cs.setString(23, sColor);
			cs.setString(24, sVersion);
			cs.setString(25, customer);
			cs.setString(26, frequencyId);
			cs.registerOutParameter(27, Types.VARCHAR);
			cs.registerOutParameter(28, Types.VARCHAR);
			cs.registerOutParameter(29, Types.CLOB);
			cs.execute();
			resultCodeOut = cs.getString(27);
			returnCodeOut = cs.getString(28);
			resultMsgOut = cs.getString(29);
			//System.out.println(resultMsgOut);
			resultMsgOut = resultCodeOut + "、、" + resultMsgOut;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultMsgOut;*/
		String sql = " SELECT GTC_AUDIT_PLANS_SEQ.NEXTVAL PLAN_ID FROM DUAL ";
		List <Map<String,Object>> list = db.execQuery(sql, null);
		if(NullTranslator.isNullEmptyCollection(list)) {
			return "N、、获取计划Id失败.";
		}
		String planId = NullTranslator.avoidNull(list.get(0).get("PLAN_ID"));
		sql = " INSERT INTO GTC_AUDIT_PLANS  " + 
					"        (AUDIT_TEMPLATE_ID, PLAN_ID, PLAN_NAME, OWNER_ID, MAKE_FACTORY_ID, VENDOR_ID, PRODUCTION_PROCESS_CODE, MODEL, PART_NAME, PART_NO,  AUDITED_EMPLOYEE,  " + 
					"         AUDITED_EMPLOYEE_BOSS,  PRODUCTION_LINE_ID, DESCRIPTION, PLAN_DATE,  TYPE_ID, STATUS_CODE,  ORG_ID, SUB_SYSTEM_ID, CREATION_DATE,  CREATED_BY,  " + 
					"         LAST_UPDATE_DATE,  LAST_UPDATED_BY, LAST_UPDATE_LOGIN,  FACILITY_NAME, MOLD_NO, SAMPLE_MATERIAL, SAMPLE_COLOR, SAMPLE_VERSION,  CUSTOMER, FREQUENCY_ID )  " + 
					"      VALUES  " + 
					"        (?,  " + 
					"         ?,  " + 
					"         ?,  " + 
					"         ?,  " + 
					"         ?,  " + 
					"         ?, " + 
					"         ?,  " + 
					"         ?,  " + 
					"         ?, " + 
					"         ?,  " + 
					"         ?,  " + 
					"         ?,  " + 
					"         ?,  " + 
					"         ?,  " + 
					"         TO_DATE(?, 'yyyy-mm-dd'),  " + 
					"         ?,  " + 
					"         ?,  " + 
					"         ?,  " + 
					"         ?,  " + 
					"         SYSDATE,  " + 
					"         ?,  " + 
					"         SYSDATE,  " + 
					"         ?,  " + 
					"         ?,  " + 
					"         ?, " + 
					"         ?,  " + 
					"         ?,  " + 
					"         ?,  " + 
					"         ?,  " + 
					"         ?,  " + 
					"         ?) ";
		Object[] params = new Object[] { NullTranslator.avoidNull(sAuditTemplae).trim(), planId, sPlanName, sOwner,
				sMake, sVendor, sProduction,
				NullTranslator.avoidNull(sProject).length() > 10 ? NullTranslator.avoidNull(sProject).substring(0, 10)
						: NullTranslator.avoidNull(sProject),
				sPart, sPartNo, sAuditedEmployee, sAuditedEmployeeBoss, sProductionLine, sDescription, sPlanDate, sType,
				sStatus, sOrgid, 500, sCreated, sCreated, sCreated, sFacility, sMold, sMaterial, sColor, sVersion,
				NullTranslator.avoidNull(customer, ""), NullTranslator.avoidNull(frequencyId, "") };
		if(db.execUpdate(sql, params)==0) {
			return "N、、创建稽核结果记录失败.";
		}
		sql = " INSERT  /*+APPEND*/  INTO GTC_AUDIT_RESULTS (PLAN_ID,  RESULT_ID, AUDIT_TEMP_ITEM_ID, RESULT_VALUE,  IS_PASSED, REMARK, ORG_ID,  " + 
					"                                           SUB_SYSTEM_ID, MEASURE_TYPE,  MEASURE_NAME, STANDARD_VALUE, PLUS_TOLERANCE,  " + 
					"                                           NEGATIVE_TOLERANCE, NUMBER_FROM_VALUE, NUMBER_FROM_COMPARISON,  NUMBER_LOGICAL_SYMBOL,  NUMBER_TO_COMPARISON,  " + 
					"                                           NUMBER_TO_VALUE,  CREATION_DATE,  CREATED_BY, LAST_UPDATE_DATE, LAST_UPDATED_BY,  LAST_UPDATE_LOGIN,  REPEAT_TIMES,  " + 
					"                                           AUDIT_TEMP_ITEM_ID_SOURCE,  ENABLED_FLAG) " + 
					"  SELECT "+planId+",  " + 
					"       '',  " + 
					"       AUDIT_TEMP_ITEM_ID,  " + 
					"       '',  " + 
					"       '',  " + 
					"       '',  " + 
					"       ORG_ID,  " + 
					"       500,  " + 
					"       MEASURE_TYPE,  " + 
					"       MEASURE_NAME,  " + 
					"       TOLERANCE_STD_VALUE,  " + 
					"       TOLERANCE_UPPER_VALUE,  " + 
					"       TOLERANCE_LOWER_VALUE,  " + 
					"       NUMBER_FROM_VALUE,  " + 
					"       NUMBER_FROM_COMPARISON,  " + 
					"       NUMBER_LOGICAL_SYMBOL,  " + 
					"       NUMBER_TO_COMPARISON,  " + 
					"       NUMBER_TO_VALUE,  " + 
					"       SYSDATE,  " + 
					"       CREATED_BY,  " + 
					"       SYSDATE,  " + 
					"       LAST_UPDATED_BY,  " + 
					"       LAST_UPDATE_LOGIN,  " + 
					"       0,  " + 
					"       'GTC_AUDIT_TEMP_ITEMS',  " + 
					"       'Y'  " + 
					"  FROM WEB.GTC_AUDIT_TEMP_ITEMS A  " + 
					" WHERE A.AUDIT_TEMPLATE_ID = "+sAuditTemplae+" AND A.ENABLED_FLAG = 'Y' " ;
		if(db.execUpdate(sql, null)==0) {
			try {
				db.getConnection().rollback();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			return "N、、创建稽核结果记录失败.";
		}
		return AUDIT_PROJECT_DETAILS(planId, null);
	}

	@Override
	public String AUDIT_CATEGORY(String orgId) {
		try {
			conn = db.getConnection();
			cs = conn.prepareCall("{?=call gtc_app_audit_api.audit_category(?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2, orgId);
			cs.registerOutParameter(3, Types.VARCHAR);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.execute();
			resultCodeOut = cs.getString(3);
			returnCodeOut = cs.getString(4);
			resultMsgOut = cs.getString(5);
			//System.out.println(resultMsgOut);
			resultMsgOut = resultCodeOut + "、、" + resultMsgOut;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultMsgOut;
	}

	@Override
	public String CHECK_FOR_UPDATES(String P_APP_NAME, String P_APP_DESCRIPTION) {
		try {
			SFCSDBUtils db = new SFCSDBUtils();
			conn = db.getConnection();
			cs = conn.prepareCall("{?=call gtc_pps_app.check_for_updates(?,?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2, P_APP_NAME);
			cs.setString(3, P_APP_DESCRIPTION);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.registerOutParameter(6, Types.VARCHAR);
			cs.execute();
			resultCodeOut = cs.getString(4);
			returnCodeOut = cs.getString(5);
			resultMsgOut = cs.getString(6);
			//System.out.println(resultMsgOut);
			resultMsgOut = resultCodeOut + "," + resultMsgOut;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultMsgOut;
	}

	public String AUDIT_PROGRAM(String PLAN_NAME_IN, String ORG_ID_IN, String PLAN_DATE_TDESC_IN,
			String MAKE_FACTORY_NAME_IN, String PRODUCTION_PROCESS_MEANING_IN, String PART_NAME_IN, String TYPE_NAME_IN,
			String STATUS_CODE_DESC_IN, String OWNER_ID_IN,String FACILITY_NAME_IN) {
		//final String G_MVL_PQC                 = "242"; //MVL PQC巡檢幾個類別ID
		final String G_MPK_START_END           = "221"; //MPK 成型首末件稽核類別ID
		final String G_MPK_FORMING_PATROL_LINE = "261"; //MPK 成型巡線稽核類別ID
		String sql = null;
		if(GVL.equalsIgnoreCase(ORG_ID_IN)) {
			sql = " SELECT GAPC.*,ROWNUM  " + 
					"  FROM (SELECT MST.PLAN_ID, MST.ORG_ID , " + 
					"               MST.PLAN_NAME,  " + 
					"               TO_CHAR(MST.PLAN_DATE, 'yyyy-mm-dd hh24:mi:ss') PLAN_DATE_TDESC,  " + 
					"               PEAC.NAME OWNER_ID_NAME,  " + 
					"               GPMF.MAKE_FACTORY_NAME,  " + 
					"               NVL(GPPR.MEANING,'NA') PRODUCTION_PROCESS_MEANING,  " + 
					"               GAPS.DESCRIPTION STATUS_CODE_DESC,  " + 
					"               GAPT.TYPE_NAME,  " + 
					"               GAP.NAME PART_NAME,  " + 
					"               GAF.FREQUENCY_NAME,  " + 
					"               GATC.TEMPLATE_NAME,  " + 
					"               MST.STATUS_CODE,  " + 
					"               MST.FACILITY_NAME,  " + 
					"               MST.PLAN_DATE  " + 
					"          FROM WEB.GTC_AUDIT_PLANS               MST,  " + 
					"               WEB.GTC_AUDIT_TEMPLATES_CV        GATC,  " + 
					"               WEB.PS_EMPLOYEE_ALL_CV            PEAC,  " + 
					"               WEB.GTC_PLM_MAKE_FACTORY_CV       GPMF,  " + 
					"               WEB.GTC_PLM_PRODUCTION_PROCESS_CV GPPR,  " + 
					"               WEB.GTC_AUDIT_PLANS_STATUSES_CV   GAPS,  " + 
					"               WEB.GTC_AUDIT_PLAN_TYPES          GAPT,  " + 
					"               WEB.GTC_AUDIT_PARTS               GAP,  " + 
					"               WEB.GTC_AUDIT_FREQUENCY           GAF  " + 
					"         WHERE MST.OWNER_ID = PEAC.EMPLOYEE_MASTER_ID  " + 
					"           AND MST.AUDIT_TEMPLATE_ID = GATC.AUDIT_TEMPLATE_ID  " + 
					"           AND MST.ORG_ID = PEAC.ORG_ID  " + 
					"           AND MST.MAKE_FACTORY_ID = GPMF.MAKE_FACTORY_ID(+)  " + 
					"           AND MST.ORG_ID = GPMF.ORG_ID(+)  " + 
					"           AND MST.PRODUCTION_PROCESS_CODE = GPPR.LOOKUP_CODE(+)  " + 
					"           AND MST.ORG_ID = GPPR.ORG_ID(+)  " + 
					"           AND MST.STATUS_CODE = GAPS.MEANING  " + 
					"           AND MST.ORG_ID = GAPS.ORG_ID  " + 
					"           AND MST.TYPE_ID = GAPT.TYPE_ID  " + 
					"           AND MST.ORG_ID = GAPT.ORG_ID  " + 
					"           AND GAP.Part_Id(+) = mst.part_id  " + 
					"           and gap.org_id(+) = mst.org_id  " + 
					"           AND GAF.FREQUENCY_ID(+) = MST.FREQUENCY_ID  " + 
					"         ORDER BY PLAN_ID DESC) GAPC WHERE ORG_ID = '"+ORG_ID_IN+"' AND ROWNUM <=36 ";
		}else {
			/*sql = "SELECT PLAN_ID, " + 
					"         PLAN_NAME, " + 
					"         OWNER_ID_NAME, " + 
					"         NVL (MAKE_FACTORY_NAME, 'NA') MAKE_FACTORY_NAME, " + 
					"         NVL (PRODUCTION_PROCESS_MEANING, 'NA') PRODUCTION_PROCESS_MEANING, " + 
					"         STATUS_CODE_DESC, " + 
					"         PLAN_DATE_TDESC, " + 
					"         TYPE_NAME, " + 
					"         PART_NAME, " + 
					"         NVL (FREQUENCY_NAME, 'NA') FREQUENCY_NAME " + 
					"    FROM WEB.GTC_AUDIT_PLANS_CVV GAPC " + 
					"   WHERE     ORG_ID = '"+ORG_ID_IN+"' " ;*/
			sql = " SELECT GAPC.PLAN_ID,GAPC.PLAN_NAME,GAPC.OWNER_ID_NAME,GAPC.MAKE_FACTORY_NAME,GAPC.PRODUCTION_PROCESS_MEANING,"
					+ "    GAPC.STATUS_CODE_DESC,GAPC.PLAN_DATE_TDESC,"
					+ "    GAPC.TYPE_NAME,GAPC.PART_NAME,GAPC.FREQUENCY_NAME,ROWNUM FROM ( SELECT GAP.PLAN_ID,  " + 
					"         GAP.PLAN_NAME,GAP.ORG_ID,  " + 
					"         NVL (PEA.NAME, 'NA') OWNER_ID_NAME,  " + 
					"         NVL (MAKE_FACTORY_NAME, 'NA') MAKE_FACTORY_NAME,  " + 
					"         NVL (MLVP.MEANING, 'NA') PRODUCTION_PROCESS_MEANING,  " + 
					"         NVL (MLVS.DESCRIPTION, 'NA') STATUS_CODE_DESC,  " + 
					"         TO_CHAR (GAP.PLAN_DATE, 'YYYY-MM-DD') PLAN_DATE_TDESC,  " + 
					"         NVL (GAPT.TYPE_NAME, 'NA') TYPE_NAME,  " + 
					"         NVL (GAPP.NAME, 'NA') PART_NAME,  " + 
					"         NVL (GAF.FREQUENCY_NAME, 'NA') FREQUENCY_NAME,  " + 
					"         GAT.TEMPLATE_NAME,  " + 
					"         TRIM (GAP.FACILITY_NAME) FACILITY_NAME,GAP.AUDIT_TEMPLATE_ID,GAP.STATUS_CODE ,GAP.PLAN_DATE " + 
					"    FROM WEB.GTC_AUDIT_PLANS GAP,  " + 
					"         WEB.PS_EMPLOYEE_ALL PEA,  " + 
					"         WEB.GTC_PLM_MAKE_FACTORY GPMA,  " + 
					"         WEB.GTC_PLM_PRODUCTION_PROCESS_CV MLVP,  " + 
					"         WEB.GTC_AUDIT_PLANS_STATUSES_CV MLVS,  " + 
					"         WEB.GTC_AUDIT_PLAN_TYPES GAPT,  " + 
					"         WEB.GTC_AUDIT_PARTS GAPP,  " + 
					"         WEB.GTC_AUDIT_FREQUENCY GAF,  " + 
					"         WEB.GTC_AUDIT_TEMPLATES GAT  " + 
					"   WHERE     GAP.OWNER_ID = PEA.EMPLOYEE_MASTER_ID  " + 
					"         AND GAP.ORG_ID = PEA.ORG_ID  " + 
					"         AND GAP.MAKE_FACTORY_ID = GPMA.MAKE_FACTORY_ID  " + 
					"         AND GAP.ORG_ID = GPMA.ORG_ID  " + 
					"         AND GAP.TYPE_ID = GAPT.TYPE_ID  " + 
					"         AND GAP.ORG_ID = GAPT.ORG_ID  " + 
					"         AND GAP.PART_ID = GAPP.PART_ID(+)  " + 
					"         AND GAP.ORG_ID = GAPP.ORG_ID(+)  " + 
					"         AND GAP.FREQUENCY_ID = GAF.FREQUENCY_ID(+)  " + 
					"         AND GAP.ORG_ID = GAF.ORG_ID(+)  " + 
					"         AND GAP.PRODUCTION_PROCESS_CODE = MLVP.LOOKUP_CODE  " + 
					"         AND GAP.ORG_ID = MLVP.ORG_ID  " + 
					"         AND GAP.STATUS_CODE = MLVS.MEANING  " + 
					"         AND GAP.ORG_ID = MLVS.ORG_ID  " + 
					"         AND GAP.AUDIT_TEMPLATE_ID = GAT.AUDIT_TEMPLATE_ID  " + 
					"         AND GAP.ORG_ID = GAT.ORG_ID ) GAPC WHERE ORG_ID = '"+ORG_ID_IN+"' AND ROWNUM <=36 " ;
		}
		if(!NA.equalsIgnoreCase(PLAN_NAME_IN)&&!NullTranslator.isNullEmpty(PLAN_NAME_IN)) {
			sql += " AND GAPC.TEMPLATE_NAME = '"+PLAN_NAME_IN+"' ";
		}
		if(!NA.equalsIgnoreCase(MAKE_FACTORY_NAME_IN)&&!NullTranslator.isNullEmpty(MAKE_FACTORY_NAME_IN)) {
			sql += " AND GAPC.MAKE_FACTORY_NAME = '"+MAKE_FACTORY_NAME_IN+"' ";
		}
		if(!NA.equalsIgnoreCase(PRODUCTION_PROCESS_MEANING_IN)&&!NullTranslator.isNullEmpty(PRODUCTION_PROCESS_MEANING_IN)) {
			sql += " AND GAPC.PRODUCTION_PROCESS_MEANING = '"+PRODUCTION_PROCESS_MEANING_IN+"' ";
		}
		/*if(!NA.equalsIgnoreCase(PART_NAME_IN)) {
			sql += " AND GAPC.PART_NAME = '"+PART_NAME_IN+"' ";
		}*/
		if(!NA.equalsIgnoreCase(TYPE_NAME_IN)&&!NullTranslator.isNullEmpty(TYPE_NAME_IN)) {
			sql += " AND GAPC.TYPE_NAME = '"+TYPE_NAME_IN+"' ";
		}
		if(!NA.equalsIgnoreCase(STATUS_CODE_DESC_IN)&&!NullTranslator.isNullEmpty(STATUS_CODE_DESC_IN)) {
			sql += " AND GAPC.STATUS_CODE_DESC = '"+STATUS_CODE_DESC_IN+"' ";
		}
		/*else {
			sql += "         AND GAPC.STATUS_CODE <> 'C' " ;
		}*/
		sql += "         AND GAPC.STATUS_CODE <> 'C' " ;
		if(!NA.equalsIgnoreCase(FACILITY_NAME_IN)&&!NullTranslator.isNullEmpty(FACILITY_NAME_IN)) {
			sql += " AND GAPC.FACILITY_NAME = '"+FACILITY_NAME_IN+"' ";
		}
		if(!NA.equalsIgnoreCase(PLAN_DATE_TDESC_IN)&&!NullTranslator.isNullEmpty(PLAN_DATE_TDESC_IN)) {
			sql += " AND GAPC.PLAN_DATE >= TO_DATE('"+PLAN_DATE_TDESC_IN+"', 'YYYY/MM/DD') ";
		}
		if(!GVL.equalsIgnoreCase(ORG_ID_IN)) {
		sql	+=	"         AND AUDIT_TEMPLATE_ID IN (SELECT AUDIT_TEMPLATE_ID " + 
				"                                     FROM WEB.GTC_AUDIT_TEMPLATES WHERE ENABLED_FLAG = 'Y' " +
			    "    AND AUDIT_CATEGORY_ID IN ('"+G_MPK_START_END+"', '"+G_MPK_FORMING_PATROL_LINE+"')) " ;
		}
		sql	+=	"  ORDER BY PLAN_ID DESC ";
		List<Map<String, Object>> list = null;
		try {
			list = db.execQuery(sql, null);
		}catch (Exception e) {
			return "N,"+e.getMessage();
		}
		if (NullTranslator.isNullEmptyCollection(list)) {
			return "N,沒有找到任何資料";
		} else {
			String result = "";
			for (int x = 0, size = list.size(); x < size; x++) {
				Map<String, Object> map = list.get(x);
				if (x == 0) {
					result = NullTranslator.avoidNull(map.get("PLAN_NAME")) + "、、"
							+ NullTranslator.avoidNull(map.get("OWNER_ID_NAME")) + "、、"
							+ NullTranslator.avoidNull(map.get("MAKE_FACTORY_NAME")) + "、、"
							+ NullTranslator.avoidNull(map.get("PRODUCTION_PROCESS_MEANING")) + "、、"
							+ NullTranslator.avoidNull(map.get("STATUS_CODE_DESC")) + "、、"
							+ NullTranslator.avoidNull(map.get("PLAN_DATE_TDESC")) + "、、"
							+ NullTranslator.avoidNull(map.get("PLAN_ID")) + "、、"
							+ NullTranslator.avoidNull(map.get("FREQUENCY_NAME"));
				} else {
					result = result + "," + NullTranslator.avoidNull(map.get("PLAN_NAME")) + "、、"
							+ NullTranslator.avoidNull(map.get("OWNER_ID_NAME")) + "、、"
							+ NullTranslator.avoidNull(map.get("MAKE_FACTORY_NAME")) + "、、"
							+ NullTranslator.avoidNull(map.get("PRODUCTION_PROCESS_MEANING")) + "、、"
							+ NullTranslator.avoidNull(map.get("STATUS_CODE_DESC")) + "、、"
							+ NullTranslator.avoidNull(map.get("PLAN_DATE_TDESC")) + "、、"
							+ NullTranslator.avoidNull(map.get("PLAN_ID")) + "、、"
							+ NullTranslator.avoidNull(map.get("FREQUENCY_NAME"));
				}
				/*if (x > 35) {
					resultMsgOut = result;
					break;
				} else if(x == size - 1) {
					resultMsgOut = result;
					break;
				}*/
			}
			resultMsgOut = result;
		}
		//System.out.println(resultMsgOut);
		/*try {
			conn = db.getConnection();
			cs = conn.prepareCall("{?=call gtc_app_audit_api.audit_program(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2, PLAN_NAME_IN);
			cs.setString(3, ORG_ID_IN);
			cs.setString(4, PLAN_DATE_TDESC_IN);
			cs.setString(5, MAKE_FACTORY_NAME_IN);
			cs.setString(6, PRODUCTION_PROCESS_MEANING_IN);
			cs.setString(7, PART_NAME_IN);
			cs.setString(8, TYPE_NAME_IN);
			cs.setString(9, STATUS_CODE_DESC_IN);
			cs.setString(10, OWNER_ID_IN);
			cs.setString(11, FACILITY_NAME_IN);
			cs.registerOutParameter(12, Types.VARCHAR);
			cs.registerOutParameter(13, Types.VARCHAR);
			cs.registerOutParameter(14, Types.VARCHAR);
			cs.execute();
			resultCodeOut = cs.getString(12);
			returnCodeOut = cs.getString(13);
			resultMsgOut = cs.getString(14);
			//System.out.println(resultMsgOut);
			resultMsgOut = resultCodeOut + "," + resultMsgOut;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}*/
		return "Y,"+resultMsgOut;
	}

	public String AUDIT_PLAN(String orgId) {
		try {
			conn = db.getConnection();
			cs = conn.prepareCall("{?=call gtc_app_audit_api.AUDIT_PLAN(?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2, orgId);
			cs.registerOutParameter(3, Types.VARCHAR);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.execute();
			resultCodeOut = cs.getString(3);
			returnCodeOut = cs.getString(4);
			resultMsgOut = cs.getString(5);
			//System.out.println(resultMsgOut);
			resultMsgOut = resultCodeOut + "," + resultMsgOut;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultMsgOut;
	}

	// 廠別
	public String AUDIT_FACTORY(String makeFactory, String orgId) {
		try {
			conn = db.getConnection();
			cs = conn.prepareCall("{?=call gtc_app_audit_api.AUDIT_FACTORY(?,?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2, makeFactory);
			cs.setString(3, orgId);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.registerOutParameter(6, Types.VARCHAR);
			cs.execute();
			resultCodeOut = cs.getString(4);
			returnCodeOut = cs.getString(5);
			resultMsgOut = cs.getString(6);
			//System.out.println(resultMsgOut);
			resultMsgOut = resultCodeOut + "," + resultMsgOut;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultMsgOut;
	}

	// 制程
	public String AUDIT_MEANING(String meaning, String orgId) {
		try {
			conn = db.getConnection();
			cs = conn.prepareCall("{?=call gtc_app_audit_api.AUDIT_MEANING(?,?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2, meaning);
			cs.setString(3, orgId);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.registerOutParameter(6, Types.VARCHAR);
			cs.execute();
			resultCodeOut = cs.getString(4);
			returnCodeOut = cs.getString(5);
			resultMsgOut = cs.getString(6);
			//System.out.println(resultMsgOut);
			resultMsgOut = resultCodeOut + "," + resultMsgOut;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultMsgOut;
	}

	// 品名
	public String AUDIT_NAME(String name, String orgId) {
		try {
			conn = db.getConnection();
			cs = conn.prepareCall("{?=call gtc_app_audit_api.AUDIT_NAME(?,?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2, name);
			cs.setString(3, orgId);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.registerOutParameter(6, Types.VARCHAR);
			cs.execute();
			resultCodeOut = cs.getString(4);
			returnCodeOut = cs.getString(5);
			resultMsgOut = cs.getString(6);
			//System.out.println(resultMsgOut);
			resultMsgOut = resultCodeOut + "、、" + resultMsgOut;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultMsgOut;
	}

	public String AUDIT_TYPE(String orgId) {
		try {
			conn = db.getConnection();
			cs = conn.prepareCall("{?=call gtc_app_audit_api.AUDIT_TYPE(?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2, orgId);
			cs.registerOutParameter(3, Types.VARCHAR);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.execute();
			resultCodeOut = cs.getString(3);
			returnCodeOut = cs.getString(4);
			resultMsgOut = cs.getString(5);
			//System.out.println(resultMsgOut);
			resultMsgOut = resultCodeOut + "、、" + resultMsgOut;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultMsgOut;
	}

	// 狀態
	public String AUDIT_DESCRIPTION(String orgId) {
		try {
			conn = db.getConnection();
			cs = conn.prepareCall("{?=call gtc_app_audit_api.AUDIT_DESCRIPTION(?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2, orgId);
			cs.registerOutParameter(3, Types.VARCHAR);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.execute();
			resultCodeOut = cs.getString(3);
			returnCodeOut = cs.getString(4);
			resultMsgOut = cs.getString(5);
			//System.out.println(resultMsgOut);
			resultMsgOut = resultCodeOut + "、、" + resultMsgOut;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultMsgOut;
	}

	public String AUDIT_PROJECT_DETAILS(String planid, String result) {
		String sql = "  SELECT * " + 
				"    FROM (SELECT PLAN_ID, " + 
				"                 RESULT_ID, " + 
				"                 ITEM_NAME, " + 
				"                 NVL (IS_PASSED, 'NEW') IS_PASSED, " + 
				"                 NVL (ITEM_CATEGORYCODE, 0) ITEM_CATEGORYCODE, " + 
				"                 NVL (ITEM_CATEGORYORDER_ID, 0) ITEM_CATEGORYORDER_ID, " + 
				"                 NVL (ITEM_CATEGORY_NUMBER, 0) ITEM_CATEGORY_NUMBER, " + 
				"                 NVL (RESULT1, 'NEW') RESULT1, " + 
				"                 OWNER_ID, " + 
				"                 NVL2 (GARC.RESULT_CATEGORY_ID, " + 
				"                       (SELECT NAME " + 
				"                          FROM WEB.GTC_AUDIT_RESULT_CATES " + 
				"                         WHERE RESULT_CATEGORY_ID = GARC.RESULT_CATEGORY_ID), " + 
				"                       'NULL') " + 
				"                    RESULT_CATEGORY, " + 
				"                 NVL2 (GARC.MATCH_ID, " + 
				"                       (SELECT MATCH_NAME " + 
				"                          FROM WEB.GTC_AUDIT_MATCHES " + 
				"                         WHERE MATCH_ID = GARC.MATCH_ID), " + 
				"                       'NULL') " + 
				"                    MATCH,                                             " + 
				"                 NVL2 ( " + 
				"                    GARC.DEPARTMENT_RESPONSIBLE, " + 
				"                    (SELECT SHORT_NAME " + 
				"                       FROM WEB.PS_DEPARTMENT_ALL " + 
				"                      WHERE     DEPTNO = GARC.DEPARTMENT_RESPONSIBLE " + 
				"                            AND CLOSE_DATE IS NULL), " + 
				"                    'NULL') " + 
				"                    DEPARTMENT_RESPONSIBLE,                              " + 
				"                 PART_NO,                                             " + 
				"                 NVL (RESULT_VALUE, 'NA') RESULT_VALUE, " + 
				"                 NVL (CONFIRM_RESULT, 'NA') CONFIRM_RESULT, " + 
				"                 NVL (REMARK, 'NA') REMARK " + 
				"            FROM WEB.GTC_AUDIT_RESULTS_CVV GARC " + 
				"           WHERE PLAN_ID = '"+planid+"' " ;
		if(!NullTranslator.isNullEmpty(result)) {
			sql += " AND ITEM_CATEGORYCODE = (SELECT NVL(ITEM_CATEGORYCODE,'') FROM WEB.GTC_AUDIT_RESULTS_CVV WHERE RESULT_ID = '"+result+"') ";
		}
		sql	+=	" )  ORDER BY RESULT_ID ";
		List<Map<String, Object>> list = null;
		String resultStr = null;
		try {
			list = db.execQuery(sql, null);
		}catch (Exception e) {
			return "N、、"+e.getMessage();
		}
		if (NullTranslator.isNullEmptyCollection(list)) {
			return "N、、沒有找到任何資料";
		} else {
			String name = NullTranslator.isNullEmpty(result)?"":"NULL";
			for (int x = 0, size = list.size(); x < size; x++) {
				Map<String, Object> map = list.get(x);
				if (x == 0) {
					resultStr = NullTranslator.avoidNull(map.get("ITEM_NAME")) + "~!~"
							+ NullTranslator.avoidNull(map.get("IS_PASSED")) + "~!~"
							+ NullTranslator.avoidNull(map.get("RESULT_ID")) + "~!~"
							+ NullTranslator.avoidNull(map.get("ITEM_CATEGORYCODE")) + "~!~"
							+ NullTranslator.avoidNull(map.get("ITEM_CATEGORYORDER_ID")) + "~!~"
							+ NullTranslator.avoidNull(map.get("ITEM_CATEGORY_NUMBER")) + "~!~" + "NA" + "~!~"
							+ NullTranslator.avoidNull(map.get("RESULT1")) + "~!~" + name + "~!~"
							+ NullTranslator.avoidNull(map.get("RESULT_CATEGORY")) + "~!~"
							+ NullTranslator.avoidNull(map.get("MATCH")) + "~!~"
							+ NullTranslator.avoidNull(map.get("DEPARTMENT_RESPONSIBLE")) + "~!~"
							+ NullTranslator.avoidNull(map.get("PLAN_ID")) + "~!~"
							+ NullTranslator.avoidNull(map.get("PART_NO")) + "~!~"
							+ NullTranslator.avoidNull(map.get("RESULT_VALUE")) + "~!~"
							+ NullTranslator.avoidNull(map.get("CONFIRM_RESULT")) + "~!~"
							+ NullTranslator.avoidNull(map.get("REMARK"));
				} else {
					resultStr = resultStr + "、、" + NullTranslator.avoidNull(map.get("ITEM_NAME")) + "~!~"
							+ NullTranslator.avoidNull(map.get("IS_PASSED")) + "~!~"
							+ NullTranslator.avoidNull(map.get("RESULT_ID")) + "~!~"
							+ NullTranslator.avoidNull(map.get("ITEM_CATEGORYCODE")) + "~!~"
							+ NullTranslator.avoidNull(map.get("ITEM_CATEGORYORDER_ID")) + "~!~"
							+ NullTranslator.avoidNull(map.get("ITEM_CATEGORY_NUMBER")) + "~!~" + "NA" + "~!~"
							+ NullTranslator.avoidNull(map.get("RESULT1")) + "~!~" + name + "~!~"
							+ NullTranslator.avoidNull(map.get("RESULT_CATEGORY")) + "~!~"
							+ NullTranslator.avoidNull(map.get("MATCH")) + "~!~"
							+ NullTranslator.avoidNull(map.get("DEPARTMENT_RESPONSIBLE")) + "~!~"
							+ NullTranslator.avoidNull(map.get("PLAN_ID")) + "~!~"
							+ NullTranslator.avoidNull(map.get("PART_NO")) + "~!~" 
							+ NullTranslator.avoidNull(map.get("RESULT_VALUE")) + "~!~"
							+ NullTranslator.avoidNull(map.get("CONFIRM_RESULT")) + "~!~"
							+ NullTranslator.avoidNull(map.get("REMARK"));
				}
			}
			System.out.println(resultStr);
		}
		/*try {
			conn = db.getConnection();
			cs = conn.prepareCall("{?=call gtc_app_audit_api.AUDIT_PROJECT_DETAILS(?,?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2, planid);
			cs.setString(3, result);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.registerOutParameter(6, Types.CLOB);
			cs.execute();
			resultCodeOut = cs.getString(4);
			returnCodeOut = cs.getString(5);
			resultMsgOut = cs.getString(6);
			//System.out.println(resultMsgOut);
			resultMsgOut = resultCodeOut + "、、" + resultMsgOut;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		return "Y、、"+resultStr;
	}

	// 根據稽核類別篩選
	public String AUDIT_TEMPLATE_NAME(String AUDIT_CATEGORY_ID,String tempName) {
		try {
			conn = db.getConnection();
			cs = conn.prepareCall("{?=call gtc_app_audit_api.AUDIT_TEMPLATE_NAME(?,?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setInt(2, Integer.valueOf(AUDIT_CATEGORY_ID.trim()));
			cs.setString(3, tempName);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.registerOutParameter(6, Types.VARCHAR);
			cs.execute();
			resultCodeOut = cs.getString(4);
			returnCodeOut = cs.getString(5);
			resultMsgOut = cs.getString(6);
			//System.out.println(resultMsgOut);
			resultMsgOut = resultCodeOut + "、、" + resultMsgOut;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultMsgOut;
	}

	// 查询機種
	public String AUDIT_PROJECT_NAME(String projectname, String orgId) {
		try {
			conn = db.getConnection();
			cs = conn.prepareCall("{?=call gtc_app_audit_api.AUDIT_PROJECT_NAME(?,?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2, projectname);
			cs.setString(3, orgId);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.registerOutParameter(6, Types.VARCHAR);
			cs.execute();
			resultCodeOut = cs.getString(4);
			returnCodeOut = cs.getString(5);
			resultMsgOut = cs.getString(6);
			//System.out.println(resultMsgOut);
			resultMsgOut = resultCodeOut + "、、" + resultMsgOut;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultMsgOut;
	}

	// 查詢人員
	public String AUDIT_USER_NAME(String name, String orgId) {
		try {
			conn = db.getConnection();
			cs = conn.prepareCall("{?=call gtc_app_audit_api.AUDIT_USER_NAME(?,?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2, name);
			cs.setString(3, orgId);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.registerOutParameter(6, Types.VARCHAR);
			cs.execute();
			resultCodeOut = cs.getString(4);
			returnCodeOut = cs.getString(5);
			resultMsgOut = cs.getString(6);
			//System.out.println(resultMsgOut);
			resultMsgOut = resultCodeOut + "、、" + resultMsgOut;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultMsgOut;
	}

	// 稽核線別
	public String AUDIT_LINE_NAME(String nameIn, String orgId) {
		try {
			conn = db.getConnection();
			cs = conn.prepareCall("{?=call gtc_app_audit_api.AUDIT_LINE_NAME(?,?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2, nameIn);
			cs.setString(3, orgId);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.registerOutParameter(6, Types.VARCHAR);
			cs.execute();
			resultCodeOut = cs.getString(4);
			returnCodeOut = cs.getString(5);
			resultMsgOut = cs.getString(6);
			//System.out.println(resultMsgOut);
			resultMsgOut = resultCodeOut + "、、" + resultMsgOut;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultMsgOut;
	}

	// 供應商
	public String AUDIT_VENDOR_NAME(String vendorname, String orgId) {
		try {
			conn = db.getConnection();
			cs = conn.prepareCall("{?=call gtc_app_audit_api.AUDIT_VENDOR_NAME(?,?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2, vendorname);
			cs.setString(3, orgId);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.registerOutParameter(6, Types.VARCHAR);
			cs.execute();
			resultCodeOut = cs.getString(4);
			returnCodeOut = cs.getString(5);
			resultMsgOut = cs.getString(6);
			//System.out.println(resultMsgOut);
			resultMsgOut = resultCodeOut + "、、" + resultMsgOut;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultMsgOut;
	}

	// 類別
	public String AUDIT_TYPE_NAME(String orgId) {
		try {
			conn = db.getConnection();
			cs = conn.prepareCall("{?=call gtc_app_audit_api.AUDIT_TYPE_NAME(?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2, orgId);
			cs.registerOutParameter(3, Types.VARCHAR);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.execute();
			resultCodeOut = cs.getString(3);
			returnCodeOut = cs.getString(4);
			resultMsgOut = cs.getString(5);
			//System.out.println(resultMsgOut);
			resultMsgOut = resultCodeOut + "、、" + resultMsgOut;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultMsgOut;
	}

	// 料號
	public String AUDIT_SYSTEM_ITEMS_NAME(String inventory) {
		/*try {
			conn = db.getConnection();
			cs = conn.prepareCall("{?=call gtc_app_audit_api.AUDIT_SYSTEM_ITEMS_NAME(?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2, inventory);
			cs.registerOutParameter(3, Types.VARCHAR);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.execute();
			resultCodeOut = cs.getString(3);
			returnCodeOut = cs.getString(4);
			resultMsgOut = cs.getString(5);
			//System.out.println(resultMsgOut);
			resultMsgOut = resultCodeOut + "、、" + resultMsgOut;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultMsgOut;*/
		String sql = "SELECT INVENTORY_ITEM_ID, SEGMENT1 " + 
				"       FROM (SELECT INVENTORY_ITEM_ID, SEGMENT1 " + 
				"               FROM MTL_SYSTEM_ITEMS " + 
				"              WHERE ORGANIZATION_ID = 105) " + 
				"      WHERE ROWNUM <= 10";
		List <Map<String,Object>> list = null;
		if(NullTranslator.isNullEmpty(inventory)) {
			sql = "SELECT INVENTORY_ITEM_ID, SEGMENT1 " + 
					"       FROM (SELECT INVENTORY_ITEM_ID, SEGMENT1 " + 
					"               FROM MTL_SYSTEM_ITEMS " + 
					"              WHERE ORGANIZATION_ID = 105) " + 
					"      WHERE ROWNUM <= 10";
			list = db.execQuery(sql, null);
		}else {
			sql = "SELECT INVENTORY_ITEM_ID, SEGMENT1 " + 
					"       FROM (SELECT INVENTORY_ITEM_ID, SEGMENT1 " + 
					"               FROM MTL_SYSTEM_ITEMS " + 
					"              WHERE SEGMENT1 LIKE ? AND ORGANIZATION_ID = 105) " + 
					"      WHERE ROWNUM <= 10";
			list = db.execQuery(sql, new Object[] {inventory+"%"});
		}
		if(NullTranslator.isNullEmptyCollection(list)) {
			return "N、、沒有找到任何資料";
		}else {
			String result = "";
			for (Map<String, Object> map : list) {
				if (NullTranslator.isNullEmpty(result)) {
					result = NullTranslator.avoidNull(map.get("INVENTORY_ITEM_ID")) + "~!~"
							+ NullTranslator.avoidNull(map.get("SEGMENT1"));
				} else {
					result = result + "、、" + NullTranslator.avoidNull(map.get("INVENTORY_ITEM_ID")) + "~!~"
							+ NullTranslator.avoidNull(map.get("SEGMENT1"));
				}
			}
			return "Y、、" + result;
		}
	}

	@Override
	public String AUDIT_CREATE_RESULT(String pLAN_ID_IN) {
		try {
			conn = db.getConnection();
			cs = conn.prepareCall("{?=call GTC_AUDIT_RESULTS_XP.CREATE_RESULTS(?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setInt(2, Integer.valueOf(pLAN_ID_IN));
			cs.registerOutParameter(3, Types.VARCHAR);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.execute();
			resultCodeOut = cs.getString(3);
			returnCodeOut = cs.getString(4);
			resultMsgOut = cs.getString(5);
			//System.out.println(resultMsgOut);
			resultMsgOut = resultCodeOut + "、、" + resultMsgOut;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultMsgOut;
	}

	// 結果分類
	public String AUDIT_RESULT_NAME(String name, String orgid) {
		try {
			conn = db.getConnection();
			cs = conn.prepareCall("{?=call gtc_app_audit_api.AUDIT_RESULT_NAME(?,?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2, name);
			cs.setString(3, orgid);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.registerOutParameter(6, Types.VARCHAR);
			cs.execute();
			resultCodeOut = cs.getString(4);
			returnCodeOut = cs.getString(5);
			resultMsgOut = cs.getString(6);
			//System.out.println(resultMsgOut);
			resultMsgOut = resultCodeOut + "、、" + resultMsgOut;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultMsgOut;
	}

	// 符合性
	public String AUDIT_MATCH(String matchname, String orgid) {
		try {
			conn = db.getConnection();
			cs = conn.prepareCall("{?=call gtc_app_audit_api.AUDIT_MATCH(?,?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2, matchname);
			cs.setString(3, orgid);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.registerOutParameter(6, Types.VARCHAR);
			cs.execute();
			resultCodeOut = cs.getString(4);
			returnCodeOut = cs.getString(5);
			resultMsgOut = cs.getString(6);
			//System.out.println(resultMsgOut);
			resultMsgOut = resultCodeOut + "、、" + resultMsgOut;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultMsgOut;
	}

	// 負責部門
	public String AUDIT_SHORT(String shortname, String orgid) {
		try {
			conn = db.getConnection();
			cs = conn.prepareCall("{?=call gtc_app_audit_api.AUDIT_SHORT(?,?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2, shortname);
			cs.setString(3, orgid);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.registerOutParameter(6, Types.VARCHAR);
			cs.execute();
			resultCodeOut = cs.getString(4);
			returnCodeOut = cs.getString(5);
			resultMsgOut = cs.getString(6);
			//System.out.println(resultMsgOut);
			resultMsgOut = resultCodeOut + "、、" + resultMsgOut;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultMsgOut;
	}

	// 項目保存
	public String AUDIT_SAVE_RESULTS(String PLAN_ID_IN, String IS_PASSED_IN, String RESULT_CATEGORY_ID_IN,
			String PERSON_RESPONSIBLE_IN, String DEPARTMENT_RESPONSIBLE_IN, String MATCH_ID_IN, String RESULT_ID_IN,
			String ORG_ID_IN, String DESCRIPTION_IN, String RESULT_IN,String confirmResult,String confirmPerson) {
		try {

			conn = db.getConnection();
			cs = conn.prepareCall("{?=call gtc_app_audit_api.AUDIT_SAVE_RESULTS_CROM(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setInt(2, Integer.valueOf(PLAN_ID_IN.trim()));
			cs.setString(3, IS_PASSED_IN);
			cs.setInt(4, Integer.valueOf(RESULT_CATEGORY_ID_IN.trim()));
			cs.setInt(5, Integer.valueOf(PERSON_RESPONSIBLE_IN.trim()));
			cs.setString(6, DEPARTMENT_RESPONSIBLE_IN);
			cs.setInt(7, Integer.valueOf(MATCH_ID_IN.trim()));
			cs.setString(8, RESULT_ID_IN);
			cs.setInt(9, Integer.valueOf(ORG_ID_IN.trim()));
			cs.setString(10, "-1");
			cs.setString(11, DESCRIPTION_IN);
			cs.setString(12, RESULT_IN);
			cs.setString(13, confirmResult);
			cs.setString(14, confirmPerson);
			cs.registerOutParameter(15, Types.VARCHAR);
			cs.registerOutParameter(16, Types.VARCHAR);
			cs.registerOutParameter(17, Types.VARCHAR);
			cs.execute();
			resultCodeOut = cs.getString(15);
			returnCodeOut = cs.getString(16);
			resultMsgOut = cs.getString(17);
			//System.out.println(resultMsgOut);
			resultMsgOut = resultCodeOut + "、、" + resultMsgOut;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultMsgOut;
	}

	// 項目保存
	public String AUDIT_UPDATE_RESULTS(String PLAN_ID_IN, String IS_PASSED_IN, String RESULT_CATEGORY_ID_IN,
			String PERSON_RESPONSIBLE_IN, String DEPARTMENT_RESPONSIBLE_IN, String MATCH_ID_IN, String RESULT_ID_IN,
			String ORG_ID_IN, String DESCRIPTION_IN, String RESULT_IN,String confirmResult,String confirmPerson) {
		try {

			conn = db.getConnection();
			cs = conn.prepareCall("{?=call gtc_app_audit_api.AUDIT_UPDATE_RESULTS_CROM(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);                                 
			cs.setInt(2, Integer.valueOf(PLAN_ID_IN.trim()));
			cs.setString(3, IS_PASSED_IN);
			cs.setInt(4, Integer.valueOf(RESULT_CATEGORY_ID_IN.trim()));
			cs.setInt(5, Integer.valueOf(PERSON_RESPONSIBLE_IN.trim()));
			cs.setString(6, DEPARTMENT_RESPONSIBLE_IN);
			cs.setInt(7, Integer.valueOf(MATCH_ID_IN.trim()));
			cs.setString(8, RESULT_ID_IN);
			cs.setInt(9, Integer.valueOf(ORG_ID_IN.trim()));
			cs.setString(10, "-1");
			cs.setString(11, DESCRIPTION_IN);
			cs.setString(12, RESULT_IN);
			cs.setString(13, confirmResult);
			cs.setString(14, confirmPerson);
			cs.registerOutParameter(15, Types.VARCHAR);
			cs.registerOutParameter(16, Types.VARCHAR);
			cs.registerOutParameter(17, Types.VARCHAR);
			cs.execute();
			resultCodeOut = cs.getString(15);
			returnCodeOut = cs.getString(16);
			resultMsgOut = cs.getString(17);
			//System.out.println(resultMsgOut);
			resultMsgOut = resultCodeOut + "、、" + resultMsgOut;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultMsgOut;
	}

	// 查詢公司orgid
	public String ORG_ID_BY_SITE(String SITE_IN) {
		try {
			conn = db.getConnection();
			cs = conn.prepareCall("{?=call gtc_app_audit_api.ORG_ID_BY_SITE(?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2, SITE_IN);

			cs.registerOutParameter(3, Types.VARCHAR);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.execute();
			resultCodeOut = cs.getString(3);
			returnCodeOut = cs.getString(4);
			resultMsgOut = cs.getString(5);
			//System.out.println(resultMsgOut);
			resultMsgOut = resultCodeOut + "、、" + resultMsgOut;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultMsgOut;
	}

	// 查詢人員姓名
	public String EMPNO(String EMPLOYEE_MASTER_ID_IN) {
		try {
			conn = db.getConnection();
			cs = conn.prepareCall("{?=call gtc_app_audit_api.EMPNO(?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2, EMPLOYEE_MASTER_ID_IN);

			cs.registerOutParameter(3, Types.VARCHAR);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.execute();
			resultCodeOut = cs.getString(3);
			returnCodeOut = cs.getString(4);
			resultMsgOut = cs.getString(5);
			//System.out.println(resultMsgOut);
			resultMsgOut = resultCodeOut + "、、" + resultMsgOut;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultMsgOut;
	}

	@Override
	public void AUDIT_MODIFY_CRITERION(String[] criterions, String tYPE_ID_IN, String pART_ID_IN,
			String iS_CREATE_CRITERION) {
		Connection conn = null;
		CallableStatement cs = null;
		try {
			conn = db.getConnection();
			cs = conn.prepareCall(
					"{?=call TRACK_PROJECT_UTILS.MODIFY_CRITERION(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2, iS_CREATE_CRITERION);
			cs.setString(3, pART_ID_IN);
			cs.setString(4, tYPE_ID_IN);
			for (int x = 0; x < 15; x++) {
				cs.setString(x + 5, NullTranslator.avoidNull(criterions[x], " "));
			}
			cs.registerOutParameter(20, Types.VARCHAR);
			cs.registerOutParameter(21, Types.VARCHAR);
			cs.registerOutParameter(22, Types.VARCHAR);
			cs.execute();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				cs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void CREATE_CRITERION(String cRITERION_VALUE, String mEASURE_TOOL_STR, String mEASURE_CODE_STR,
			String tYPE_ID_IN, String pART_ID_IN) {
		Connection conn = null;
		CallableStatement cs = null;
		try {
			conn = db.getConnection();
			cs = conn.prepareCall("{?=call TRACK_PROJECT_UTILS.CREATE_CRITERION(?,?,?,?,?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2, pART_ID_IN);
			cs.setString(3, tYPE_ID_IN);
			cs.setString(4, mEASURE_TOOL_STR);
			cs.setString(5, mEASURE_CODE_STR);
			cs.setString(6, cRITERION_VALUE);
			cs.registerOutParameter(7, Types.VARCHAR);
			cs.registerOutParameter(8, Types.VARCHAR);
			cs.registerOutParameter(9, Types.VARCHAR);
			cs.execute();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				cs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void UPDATE_CRITERION(String cRITERION_VALUE, String mEASURE_TOOL_STR, String mEASURE_CODE_STR,
			String tYPE_ID_IN, String pART_ID_IN) {
		Connection conn = null;
		CallableStatement cs = null;
		try {
			conn = db.getConnection();
			cs = conn.prepareCall("{?=call TRACK_PROJECT_UTILS.UPDATE_CRITERION(?,?,?,?,?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2, pART_ID_IN);
			cs.setString(3, tYPE_ID_IN);
			cs.setString(4, mEASURE_TOOL_STR);
			cs.setString(5, mEASURE_CODE_STR);
			cs.setString(6, cRITERION_VALUE);
			cs.registerOutParameter(7, Types.VARCHAR);
			cs.registerOutParameter(8, Types.VARCHAR);
			cs.registerOutParameter(9, Types.VARCHAR);
			cs.execute();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				cs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public String getModelAndPartByPartNo(String pART_NO_IN) {
		Connection conn = null;
		CallableStatement cs = null;
		try {
			conn = db.getConnection();
			cs = conn.prepareCall("{?=call gtc_app_audit_api.GET_MODEL_PART_BY_PART_NO(?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2, pART_NO_IN);
			cs.registerOutParameter(3, Types.VARCHAR);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.execute();
			resultCodeOut = cs.getString(3);
			returnCodeOut = cs.getString(4);
			resultMsgOut = cs.getString(5);
			//System.out.println(resultMsgOut);
			resultMsgOut = resultCodeOut + "、、" + resultMsgOut;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			resultMsgOut = "N、、服務器異常,請聯繫管理員";
		} catch (SQLException e) {
			e.printStackTrace();
			resultMsgOut = "N、、服務器異常,請聯繫管理員";
		} finally {
			try {
				cs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultMsgOut;
	}

	// 獲取稽核結果信息
	@Override
	public String getAuditResult(String rESULT_ID_IN, String oRG_ID_IN) {
		Connection conn = null;
		CallableStatement cs = null;
		try {
			conn = db.getConnection();
			cs = conn.prepareCall("{?=call gtc_app_audit_api.get_audit_resulus(?,?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2, rESULT_ID_IN);
			cs.setString(3, oRG_ID_IN);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.registerOutParameter(6, Types.VARCHAR);
			cs.execute();
			resultCodeOut = cs.getString(4);
			returnCodeOut = cs.getString(5);
			resultMsgOut = cs.getString(6);
			//System.out.println(resultMsgOut);
			resultMsgOut = resultCodeOut + "、、" + resultMsgOut;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			resultMsgOut = "N、、服務器異常,請聯繫管理員";
		} catch (SQLException e) {
			e.printStackTrace();
			resultMsgOut = "N、、服務器異常,請聯繫管理員";
		} finally {
			try {
				cs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultMsgOut;
	}

	// 掃描機台獲取料號
	@Override
	public String getPartNoByToolName(String toolName) {
		Connection conn = null;
		CallableStatement cs = null;
		try {
			conn = db.getConnection();
			cs = conn.prepareCall("{?=call gtc_app_audit_api.GET_PART_NO_BY_TOOL(?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2, toolName);
			cs.registerOutParameter(3, Types.VARCHAR);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.execute();
			resultCodeOut = cs.getString(3);
			returnCodeOut = cs.getString(4);
			resultMsgOut = cs.getString(5);
			//System.out.println(resultMsgOut);
			resultMsgOut = resultCodeOut + "、、" + resultMsgOut;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			resultMsgOut = "N、、服務器異常,請聯繫管理員";
		} catch (SQLException e) {
			e.printStackTrace();
			resultMsgOut = "N、、服務器異常,請聯繫管理員";
		} finally {
			try {
				cs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultMsgOut;
	}
	
	/**
	 * 获取稽核结果单项
	 */
	@Override
	public String getPlanResultOne(String oRG_ID_IN, String rESULT_ID_IN, String pLAN_ID_IN) {
		Connection conn = null;
		CallableStatement cs = null;
		String result = "";
		try {
			conn = db.getConnection();
			cs = conn.prepareCall("{?=call GTC_APP_AUDIT_API.GET_PLAN_ITEM_ONE(?,?,?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2, oRG_ID_IN);
			cs.setString(3, rESULT_ID_IN);
			cs.setString(4, pLAN_ID_IN);
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.registerOutParameter(6, Types.VARCHAR);
			cs.registerOutParameter(7, Types.VARCHAR);
			cs.execute();
			result = cs.getString(5) + "、、" + cs.getString(7);
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

	/**
	 * 获取用户信息
	 */
	@Override
	public String getUserInfo(String eMPLOYEE_MASTER_ID_IN, String oRG_ID_IN) {
		Connection conn = null;
		CallableStatement cs = null;
		String result = "";
		try {
			conn = db.getConnection();
			cs = conn.prepareCall("{?=call GTC_APP_AUDIT_API.GET_USER_INFO(?,?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2, eMPLOYEE_MASTER_ID_IN);
			cs.setString(3, oRG_ID_IN);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.registerOutParameter(6, Types.VARCHAR);
			cs.execute();
			result = cs.getString(4) + "、、" + cs.getString(6);
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

	
	/**
	 * 更新稽核结果单项
	 */
	@Override
	public String updatePlanResultOne(String oRG_ID_IN, String rESULT_ID_IN, String pLAN_ID_IN, String iS_PASSED_IN,
			String dESCRIPTION_IN, String rESULT_IN, String eMPLOYEE_MASTER_ID_IN, String dEPARTMENT_RESPONSIBLE_IN,
			String confirmResult,String confirmPerson) {
		Connection conn = null;
		CallableStatement cs = null;
		String result = "";
		try {
			conn = db.getConnection();
			cs = conn.prepareCall("{?=call GTC_APP_AUDIT_API.UPDATE_PLAN_RESULT_ONE(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setInt(2, Integer.valueOf(oRG_ID_IN));
			cs.setInt(3, Integer.valueOf(rESULT_ID_IN));
			cs.setInt(4, Integer.valueOf(pLAN_ID_IN));
			cs.setString(5, iS_PASSED_IN);
			cs.setString(6, dESCRIPTION_IN);
			cs.setString(7, rESULT_IN);
			cs.setInt(8, Integer.valueOf(eMPLOYEE_MASTER_ID_IN));
			cs.setString(9, dEPARTMENT_RESPONSIBLE_IN);
			cs.setString(10, confirmResult);
			cs.setString(11, confirmPerson);
			cs.registerOutParameter(12, Types.VARCHAR);
			cs.registerOutParameter(13, Types.VARCHAR);
			cs.registerOutParameter(14, Types.VARCHAR);
			cs.execute();
			result = cs.getString(12) + "、、" + cs.getString(14);
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

	/**
	 * 根據樣本獲取製程
	 */
	@Override
	public String getProcessByTemp(String aUDIT_TEMPLATE_ID_IN, String oRG_ID_IN) {
		Connection conn = null;
		CallableStatement cs = null;
		String result = "";
		try {
			conn = db.getConnection();
			cs = conn.prepareCall("{?=call GTC_APP_AUDIT_API.GET_PROCESS_NAME_BY_TEMP(?,?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setObject(2, aUDIT_TEMPLATE_ID_IN);
			cs.setObject(3, oRG_ID_IN);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.registerOutParameter(6, Types.VARCHAR);
			cs.execute();
			result = cs.getString(4) + "、、" +cs.getString(5) + "、、"  + cs.getString(6);
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

	/**
	 * 获取模号MVL
	 */
	@Override
	public String getMoldMvl(String oRG_ID_IN, String customer,String MOLD_NO_IN) {
		Connection conn = null;
		CallableStatement cs = null;
		try {
			conn = db.getConnection();
			cs = conn.prepareCall("{?=call GTC_APP_AUDIT_API.GET_ALL_MODL(?,?,?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setObject(2, oRG_ID_IN);
			cs.setObject(3, customer);
			cs.setObject(4, MOLD_NO_IN);
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.registerOutParameter(6, Types.VARCHAR);
			cs.registerOutParameter(7, Types.VARCHAR);
			cs.execute();
			resultCodeOut = cs.getString(5);
			returnCodeOut = cs.getString(6);
			resultMsgOut = cs.getString(7);
			//System.out.println(resultMsgOut);
			resultMsgOut = resultCodeOut + "、、" + resultMsgOut;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultMsgOut;
	}

	/**
	 * 获取机台列表MVL
	 */
	@Override
	public String getMvlEquipment(String orgnizationCode) {
		Connection conn = null;
		CallableStatement cs = null;
		try {
			conn = db.getConnection();
			cs = conn.prepareCall("{?=call GTC_APP_AUDIT_API.GET_MVL_EQUIPMENT(?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setObject(2, orgnizationCode);
			cs.registerOutParameter(3, Types.VARCHAR);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.execute();
			resultCodeOut = cs.getString(3);
			returnCodeOut = cs.getString(4);
			resultMsgOut = cs.getString(5);
			//System.out.println(resultMsgOut);
			resultMsgOut = resultCodeOut + "、、" + resultMsgOut;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultMsgOut;
	}

	/**
	 * 获取检验频率
	 */
	@Override
	public String getFrequency(String oRG_ID_IN) {
		Connection conn = null;
		CallableStatement cs = null;
		try {
			conn = db.getConnection();
			cs = conn.prepareCall("{?=call GTC_APP_AUDIT_API.GET_MVL_FREQUENCY(?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setObject(2, oRG_ID_IN);
			cs.registerOutParameter(3, Types.VARCHAR);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.execute();
			resultCodeOut = cs.getString(3);
			returnCodeOut = cs.getString(4);
			resultMsgOut = cs.getString(5);
			//System.out.println(resultMsgOut);
			resultMsgOut = resultCodeOut + "、、" + resultMsgOut;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultMsgOut;
	}

	@Override
	public String getLookupValues(String oRG_ID_IN, String lookType, String flag) {
		Connection conn = null;
		CallableStatement cs = null;
		try {
			conn = db.getConnection();
			cs = conn.prepareCall("{?=call GTC_APP_AUDIT_API.GET_LOOPUP_VALUE(?,?,?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setObject(2, oRG_ID_IN);
			cs.setObject(3, lookType);
			cs.setObject(4, flag);
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.registerOutParameter(6, Types.VARCHAR);
			cs.registerOutParameter(7, Types.VARCHAR);
			cs.execute();
			resultCodeOut = cs.getString(5);
			returnCodeOut = cs.getString(6);
			resultMsgOut = cs.getString(7);
			//System.out.println(resultMsgOut);
			resultMsgOut = resultCodeOut + "、、" + resultMsgOut;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultMsgOut;
	}

	/**
	 * 掃描機台獲取料號
	 * @param faNumber
	 * @param orgnizationCode
	 * @return
	 */
	@Override
	public String getPnByFacility(String faNumber, String orgnizationCode) {
		Connection conn = null;
		CallableStatement cs = null;
		try {
			conn = db.getConnection();
			cs = conn.prepareCall("{?=call GTC_APP_AUDIT_API.GET_PN_BY_EQUIPMENT(?,?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setObject(2, faNumber);
			cs.setObject(3, orgnizationCode);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.registerOutParameter(6, Types.VARCHAR);
			cs.execute();
			resultCodeOut = cs.getString(4);
			returnCodeOut = cs.getString(5);
			resultMsgOut = cs.getString(6);
			//System.out.println(resultMsgOut);
			resultMsgOut = resultCodeOut + "、、" + resultMsgOut;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultMsgOut;
	}

	/**
	 * 根據工號獲取MasterId
	 */
	@Override
	public String getMasterIdByEmpno(String empno,String role) {
		Connection conn = null;
		CallableStatement cs = null;
		try {
			conn = db.getConnection();
			cs = conn.prepareCall("{?=call GTC_APP_AUDIT_API.GET_MASTER_ID_BY_EMPNO(?,?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setObject(2, empno);
			cs.setObject(3, role);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.registerOutParameter(6, Types.VARCHAR);
			cs.execute();
			resultCodeOut = cs.getString(4);
			returnCodeOut = cs.getString(5);
			resultMsgOut = cs.getString(6);
			//System.out.println(resultMsgOut);
			resultMsgOut = resultCodeOut + "、、" + resultMsgOut;
		} catch (ClassNotFoundException e) {
			resultMsgOut = "N、、服務區器異常";
			e.printStackTrace();
		} catch (SQLException e) {
			resultMsgOut = "N、、服務區器異常";
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultMsgOut;
	}
}
