package mptk.com.cn.dao;

public interface AuditDao {
	// 创建稽核计划
	String CREATE_AUDIT_PLAN(String sAuditTemplae, String sPlanName, String sOwner, String sMake, String sVendor,
			String sProduction, String sProject, String sPart, String sPartNo, String sAuditedEmployee,
			String sAuditedEmployeeBoss, String sProductionLine, String sDescription, String sPlanDate, String sType,
			String sStatus, String sOrgid, String sCreated, String sFacility, String sMold, String sMaterial,
			String sColor, String sVersion, String customer,String frequencyId);

	String AUDIT_CATEGORY(String orgId);

	String AUDIT_PROGRAM(String PLAN_NAME_IN, String ORG_ID_IN, String PLAN_DATE_TDESC_IN, String MAKE_FACTORY_NAME_IN,
			String PRODUCTION_PROCESS_MEANING_IN, String PART_NAME_IN, String TYPE_NAME_IN, String STATUS_CODE_DESC_IN,
			String OWNER_ID_IN,String FACILITY_NAME_IN);

	String AUDIT_PLAN(String orgId);

	// 廠別
	String AUDIT_FACTORY(String MAKE_FACTORY_NAME_IN, String orgId);

	// 制程
	String AUDIT_MEANING(String MEANING_IN, String orgId);

	// 品名
	String AUDIT_NAME(String NAME_IN, String orgId);

	// 類別
	String AUDIT_TYPE(String orgId);

	// 狀態
	String AUDIT_DESCRIPTION(String orgId);

	String AUDIT_PROJECT_DETAILS(String planid, String result);

	// 根據稽核類別篩選
	String AUDIT_TEMPLATE_NAME(String AUDIT_CATEGORY_ID_IN,String tempName);

	// 查询機種
	String AUDIT_PROJECT_NAME(String PROJECT_NAME_IN, String orgId);

	// 查詢人員
	String AUDIT_USER_NAME(String NAME_IN, String orgId);

	// 稽核線別
	String AUDIT_LINE_NAME(String nameIn, String orgId);

	// 供應商
	String AUDIT_VENDOR_NAME(String vendorname, String orgId);

	// 類別
	String AUDIT_TYPE_NAME(String orgId);

	// 料號
	String AUDIT_SYSTEM_ITEMS_NAME(String inventory);

	String AUDIT_CREATE_RESULT(String pLAN_ID_IN);

	// 結果分類
	String AUDIT_RESULT_NAME(String name, String orgId);

	// 符合性
	String AUDIT_MATCH(String match, String orgId);

	// 負責部門
	String AUDIT_SHORT(String shortname, String orgId);

	// 項目保存
	String AUDIT_SAVE_RESULTS(String PLAN_ID_IN, String IS_PASSED_IN, String RESULT_CATEGORY_ID_IN,
			String PERSON_RESPONSIBLE_IN, String DEPARTMENT_RESPONSIBLE_IN, String MATCH_ID_IN, String RESULT_ID_IN,
			String ORG_ID_IN, String DESCRIPTION_IN, String RESULT_IN,String confirmResult,String confirmPerson);

	// 項目修改
	String AUDIT_UPDATE_RESULTS(String PLAN_ID_IN, String IS_PASSED_IN, String RESULT_CATEGORY_ID_IN,
			String PERSON_RESPONSIBLE_IN, String DEPARTMENT_RESPONSIBLE_IN, String MATCH_ID_IN, String RESULT_ID_IN,
			String ORG_ID_IN, String DESCRIPTION_IN, String RESULT_IN,String confirmResult,String confirmPerson);

	// 查詢公司ORGID
	String ORG_ID_BY_SITE(String SITE_IN);

	// 查詢版本
	String CHECK_FOR_UPDATES(String P_APP_NAME, String P_APP_DESCRIPTION);

	// 查詢人員姓名
	String EMPNO(String EMPLOYEE_MASTER_ID_IN);

	void AUDIT_MODIFY_CRITERION(String[] criterions, String tYPE_ID_IN, String pART_ID_IN, String iS_CREATE_CRITERION);

	void CREATE_CRITERION(String cRITERION_VALUE, String mEASURE_TOOL_STR, String mEASURE_CODE_STR, String tYPE_ID_IN,
			String pART_ID_IN);

	void UPDATE_CRITERION(String cRITERION_VALUE, String mEASURE_TOOL_STR, String mEASURE_CODE_STR, String tYPE_ID_IN,
			String pART_ID_IN);

	String getModelAndPartByPartNo(String pART_NO_IN);

	// 獲取稽核項目結果信息
	String getAuditResult(String rESULT_ID_IN, String oRG_ID_IN);

	// 掃描機台獲取料號
	String getPartNoByToolName(String toolName);

	/**
	 * 获取稽核结果单项
	 */
	String getPlanResultOne(String oRG_ID_IN, String rESULT_ID_IN, String pLAN_ID_IN);

	/**
	 * 获取用户信息
	 */
	String getUserInfo(String eMPLOYEE_MASTER_ID_IN, String oRG_ID_IN);

	/**
	 * 更新稽核结果单项
	 */
	String updatePlanResultOne(String oRG_ID_IN, String rESULT_ID_IN, String pLAN_ID_IN, String iS_PASSED_IN,
			String dESCRIPTION_IN, String rESULT_IN, String eMPLOYEE_MASTER_ID_IN, String dEPARTMENT_RESPONSIBLE_IN,
			String confirmResult,String confirmPerson);

	/**
	 * 根據樣本獲取製程
	 */
	String getProcessByTemp(String aUDIT_TEMPLATE_ID_IN, String oRG_ID_IN);

	/**
	 * 获取模号MVL
	 * @param oRG_ID_IN
	 * @param customer
	 * @return
	 */
	String getMoldMvl(String oRG_ID_IN, String customer,String MOLD_NO_IN);

	/**
	 * 获取机台列表MVL
	 * @param orgnizationCode
	 * @return
	 */
	String getMvlEquipment(String orgnizationCode);

	/**
	 * 获取检验频率
	 * @param oRG_ID_IN
	 * @return
	 */
	String getFrequency(String oRG_ID_IN);

	/**
	 * 獲取MPT_LOOKUP_VALUES中的值
	 * @param oRG_ID_IN
	 * @param lookType
	 * @param flag
	 * @return
	 */
	String getLookupValues(String oRG_ID_IN, String lookType, String flag);

	/**
	 * 掃描機台獲取料號
	 * @param faNumber
	 * @param orgnizationCode
	 * @return
	 */
	String getPnByFacility(String faNumber, String orgnizationCode);

	/**
	 * 根據工號獲取MasterId
	 * @param empno
	 * @return
	 */
	String getMasterIdByEmpno(String empno,String role);
}
