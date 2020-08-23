package mptk.com.cn.serviceImpl;

import mptk.com.cn.dao.AuditDao;
import mptk.com.cn.daoImpl.AuditDaoImpl;
import mptk.com.cn.service.AuditService;

public class AuditServiceImpl implements AuditService {

	private AuditDao asd = new AuditDaoImpl();

	// 創建稽核計畫
	@Override
	public String CREATE_AUDIT_PLAN(String sAuditTemplae, String sPlanName, String sOwner, String sMake, String sVendor,
			String sProduction, String sProject, String sPart, String sPartNo, String sAuditedEmployee,
			String sAuditedEmployeeBoss, String sProductionLine, String sDescription, String sPlanDate, String sType,
			String sStatus, String sOrgid, String sCreated, String sFacility, String sMold, String sMaterial,
			String sColor, String sVersion, String customer,String frequencyId) {
		String result = asd.CREATE_AUDIT_PLAN(sAuditTemplae, sPlanName, sOwner, sMake, sVendor, sProduction, sProject,
				sPart, sPartNo, sAuditedEmployee, sAuditedEmployeeBoss, sProductionLine, sDescription, sPlanDate, sType,
				sStatus, sOrgid, sCreated, sFacility, sMold, sMaterial, sColor, sVersion, customer,frequencyId);
		return result;
	}

	@Override
	public String AUDIT_CATEGORY(String orgId) {
		String result = asd.AUDIT_CATEGORY(orgId);
		return result;
	}

	@Override
	public String CHECK_FOR_UPDATES(String P_APP_NAME, String P_APP_DESCRIPTION) {
		String result = asd.CHECK_FOR_UPDATES(P_APP_NAME, P_APP_DESCRIPTION);
		return result;
	}

	@Override
	public String AUDIT_PROGRAM(String PLAN_NAME_IN, String ORG_ID_IN, String PLAN_DATE_TDESC_IN,
			String MAKE_FACTORY_NAME_IN, String PRODUCTION_PROCESS_MEANING_IN, String PART_NAME_IN, String TYPE_NAME_IN,
			String STATUS_CODE_DESC_IN, String OWNER_ID_IN,String FACILITY_NAME_IN) {
		String result = asd.AUDIT_PROGRAM(PLAN_NAME_IN, ORG_ID_IN, PLAN_DATE_TDESC_IN, MAKE_FACTORY_NAME_IN,
				PRODUCTION_PROCESS_MEANING_IN, PART_NAME_IN, TYPE_NAME_IN, STATUS_CODE_DESC_IN, OWNER_ID_IN,FACILITY_NAME_IN);
		return result;
	}

	@Override
	public String AUDIT_PLAN(String orgId) {
		String result = asd.AUDIT_PLAN(orgId);
		return result;
	}

	// 廠別
	@Override
	public String AUDIT_FACTORY(String MAKE_FACTORY_NAME_IN, String orgId) {
		String result = asd.AUDIT_FACTORY(MAKE_FACTORY_NAME_IN, orgId);
		return result;
	}

	// 制程
	@Override
	public String AUDIT_MEANING(String MEANING_IN, String orgId) {
		String result = asd.AUDIT_MEANING(MEANING_IN, orgId);
		return result;
	}

	// 品名
	@Override
	public String AUDIT_NAME(String name, String orgId) {
		String result = asd.AUDIT_NAME(name, orgId);
		return result;
	}

	@Override
	public String AUDIT_TYPE(String orgId) {
		String result = asd.AUDIT_TYPE(orgId);
		return result;
	}

	@Override
	public String AUDIT_DESCRIPTION(String orgId) {
		String result = asd.AUDIT_DESCRIPTION(orgId);
		return result;
	}

	@Override
	public String AUDIT_PROJECT_DETAILS(String planid, String resultId) {
		String result = asd.AUDIT_PROJECT_DETAILS(planid, resultId);
		return result;
	}

	// 根據稽核類別篩選
	public String AUDIT_TEMPLATE_NAME(String AUDIT_CATEGORY_ID,String tempName) {
		String result = asd.AUDIT_TEMPLATE_NAME(AUDIT_CATEGORY_ID,tempName);
		return result;
	}

	// 查询機種
	@Override
	public String AUDIT_PROJECT_NAME(String PROJECT_NAME_IN, String orgId) {
		String result = asd.AUDIT_PROJECT_NAME(PROJECT_NAME_IN, orgId);
		return result;
	}

	// 查詢人員
	@Override
	public String AUDIT_USER_NAME(String NAME_IN, String orgId) {
		String result = asd.AUDIT_USER_NAME(NAME_IN, orgId);
		return result;
	}

	// 稽核線別
	@Override
	public String AUDIT_LINE_NAME(String nameIn, String orgId) {
		String result = asd.AUDIT_LINE_NAME(nameIn, orgId);
		return result;
	}

	// 供應商
	@Override
	public String AUDIT_VENDOR_NAME(String VENDOR_NAME_IN, String orgId) {
		String result = asd.AUDIT_VENDOR_NAME(VENDOR_NAME_IN, orgId);
		return result;
	}

	// 類別
	@Override
	public String AUDIT_TYPE_NAME(String orgId) {
		String result = asd.AUDIT_TYPE_NAME(orgId);
		return result;
	}

	// 料號
	@Override
	public String AUDIT_SYSTEM_ITEMS_NAME(String INVENTORY_IN) {
		String result = asd.AUDIT_SYSTEM_ITEMS_NAME(INVENTORY_IN);
		return result;
	}

	@Override
	public String AUDIT_CREATE_RESULT(String pLAN_ID_IN) {
		String result = asd.AUDIT_CREATE_RESULT(pLAN_ID_IN);
		return result;
	}

	// 結果分類
	@Override
	public String AUDIT_RESULT_NAME(String NAME_IN, String ORG_ID_IN) {
		String result = asd.AUDIT_RESULT_NAME(NAME_IN, ORG_ID_IN);
		return result;
	}

	// 符合性
	@Override
	public String AUDIT_MATCH(String MATCH_NAME_IN, String ORG_ID_IN) {
		String result = asd.AUDIT_MATCH(MATCH_NAME_IN, ORG_ID_IN);
		return result;
	}

	// 符合性
	@Override
	public String AUDIT_SHORT(String SHORT_NAME_IN, String ORG_ID_IN) {
		String result = asd.AUDIT_SHORT(SHORT_NAME_IN, ORG_ID_IN);
		return result;
	}

	// 項目保存
	@Override
	public String AUDIT_SAVE_RESULTS(String PLAN_ID_IN, String IS_PASSED_IN, String RESULT_CATEGORY_ID_IN,
			String PERSON_RESPONSIBLE_IN, String DEPARTMENT_RESPONSIBLE_IN, String MATCH_ID_IN, String RESULT_ID_IN,
			String ORG_ID_IN, String DESCRIPTION_IN, String RESULT_IN,String confirmResult,String confirmPerson) {
		String result = asd.AUDIT_SAVE_RESULTS(PLAN_ID_IN, IS_PASSED_IN, RESULT_CATEGORY_ID_IN, PERSON_RESPONSIBLE_IN,
				DEPARTMENT_RESPONSIBLE_IN, MATCH_ID_IN, RESULT_ID_IN, ORG_ID_IN, DESCRIPTION_IN,
				RESULT_IN,confirmResult,confirmPerson);
		return result;
	}

	// 項目保存
	@Override
	public String AUDIT_UPDATE_RESULTS(String PLAN_ID_IN, String IS_PASSED_IN, String RESULT_CATEGORY_ID_IN,
			String PERSON_RESPONSIBLE_IN, String DEPARTMENT_RESPONSIBLE_IN, String MATCH_ID_IN, String RESULT_ID_IN,
			String ORG_ID_IN, String DESCRIPTION_IN, String RESULT_IN,String confirmResult,String confirmPerson) {
		String result = asd.AUDIT_UPDATE_RESULTS(PLAN_ID_IN, IS_PASSED_IN, RESULT_CATEGORY_ID_IN, PERSON_RESPONSIBLE_IN,
				DEPARTMENT_RESPONSIBLE_IN, MATCH_ID_IN, RESULT_ID_IN, ORG_ID_IN, DESCRIPTION_IN,
				RESULT_IN,confirmResult,confirmPerson);
		return result;
	}

	// 查詢公司orgid
	@Override
	public String ORG_ID_BY_SITE(String SITE_IN) {
		String result = asd.ORG_ID_BY_SITE(SITE_IN);
		return result;
	}

	// 查詢人員姓名
	@Override
	public String EMPNO(String EMPLOYEE_MASTER_ID_IN) {
		String result = asd.EMPNO(EMPLOYEE_MASTER_ID_IN);
		return result;
	}

	@Override
	public void AUDIT_MODIFY_CRITERION(String[] criterions, String tYPE_ID_IN, String pART_ID_IN,
			String iS_CREATE_CRITERION) {
		asd.AUDIT_MODIFY_CRITERION(criterions, tYPE_ID_IN, pART_ID_IN, iS_CREATE_CRITERION);
	}

	@Override
	public void CREATE_CRITERION(String cRITERION_VALUE, String mEASURE_TOOL_STR, String mEASURE_CODE_STR,
			String tYPE_ID_IN, String pART_ID_IN) {
		asd.CREATE_CRITERION(cRITERION_VALUE, mEASURE_TOOL_STR, mEASURE_CODE_STR, tYPE_ID_IN, pART_ID_IN);
	}

	@Override
	public void UPDATE_CRITERION(String cRITERION_VALUE, String mEASURE_TOOL_STR, String mEASURE_CODE_STR,
			String tYPE_ID_IN, String pART_ID_IN) {
		asd.UPDATE_CRITERION(cRITERION_VALUE, mEASURE_TOOL_STR, mEASURE_CODE_STR, tYPE_ID_IN, pART_ID_IN);
	}

	@Override
	public String getModelAndPartByPartNo(String pART_NO_IN) {
		String result = asd.getModelAndPartByPartNo(pART_NO_IN);
		return result;
	}

	// 獲取稽核項目結果信息
	@Override
	public String getAuditResult(String rESULT_ID_IN, String oRG_ID_IN) {
		String result = asd.getAuditResult(rESULT_ID_IN, oRG_ID_IN);
		return result;
	}

	@Override
	public String getPartNoByToolName(String toolName) {
		String result = asd.getPartNoByToolName(toolName);
		return result;
	}

	/**
	 * 获取稽核结果单项
	 */
	@Override
	public String getPlanResultOne(String oRG_ID_IN, String rESULT_ID_IN, String pLAN_ID_IN) {
		String result = asd.getPlanResultOne(oRG_ID_IN, rESULT_ID_IN, pLAN_ID_IN);
		return result;
	}

	/**
	 * 获取用户信息
	 */
	@Override
	public String getUserInfo(String eMPLOYEE_MASTER_ID_IN, String oRG_ID_IN) {
		String result = asd.getUserInfo(eMPLOYEE_MASTER_ID_IN, oRG_ID_IN);
		return result;
	}

	/**
	 * 更新稽核结果单项
	 */
	@Override
	public String updatePlanResultOne(String oRG_ID_IN, String rESULT_ID_IN, String pLAN_ID_IN, String iS_PASSED_IN,
			String dESCRIPTION_IN, String rESULT_IN, String eMPLOYEE_MASTER_ID_IN, String dEPARTMENT_RESPONSIBLE_IN,
			String confirmResult,String confirmPerson) {
		String result = asd.updatePlanResultOne(oRG_ID_IN, rESULT_ID_IN, pLAN_ID_IN, iS_PASSED_IN, dESCRIPTION_IN,
				rESULT_IN, eMPLOYEE_MASTER_ID_IN, dEPARTMENT_RESPONSIBLE_IN,confirmResult,confirmPerson);
		return result;
	}

	/**
	 * 根據樣本獲取製程
	 */
	@Override
	public String getProcessByTemp(String aUDIT_TEMPLATE_ID_IN, String oRG_ID_IN) {
		String result = asd.getProcessByTemp(aUDIT_TEMPLATE_ID_IN, oRG_ID_IN);
		return result;
	}

	/**
	 * 获取模号MVL
	 */
	@Override
	public String getMoldMvl(String oRG_ID_IN, String customer,String MOLD_NO_IN) {
		String result = asd.getMoldMvl(oRG_ID_IN, customer,MOLD_NO_IN);
		return result;
	}

	/**
	 * 获取机台列表MVL
	 */
	@Override
	public String getMvlEquipment(String orgnizationCode) {
		String result = asd.getMvlEquipment(orgnizationCode);
		return result;
	}

	/**
	 * 获取检验频率
	 */
	@Override
	public String getFrequency(String oRG_ID_IN) {
		String result = asd.getFrequency(oRG_ID_IN);
		return result;
	}

	/**
	 * 獲取MPT_LOOKUP_VALUES中的值
	 */
	@Override
	public String getLookupValues(String oRG_ID_IN, String lookType, String flag) {
		String result = asd.getLookupValues(oRG_ID_IN,lookType,flag);
		return result;
	}

	/**
	 * 掃描機台獲取料號
	 */
	@Override
	public String getPnByFacility(String faNumber, String orgnizationCode) {
		String result = asd.getPnByFacility(faNumber,orgnizationCode);
		return result;
	}

	/**
	 * 根據工號獲取MasterId
	 */
	@Override
	public String getMasterIdByEmpno(String empno,String role) {
		String result = asd.getMasterIdByEmpno(empno,role);
		return result;
	}

}
