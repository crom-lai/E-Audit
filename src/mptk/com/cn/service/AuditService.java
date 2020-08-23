package mptk.com.cn.service;

public interface AuditService {

	public String AUDIT_CATEGORY(String org_id);

	String AUDIT_PROGRAM(String PLAN_NAME_IN, String ORG_ID_IN, String PLAN_DATE_TDESC_IN, String MAKE_FACTORY_NAME_IN,
			String PRODUCTION_PROCESS_MEANING_IN, String PART_NAME_IN, String TYPE_NAME_IN, String STATUS_CODE_DESC_IN,
			String OWNER_ID_IN,String FACILITY_NAME_IN);

	// 創建稽核計畫
	public String CREATE_AUDIT_PLAN(String sAuditTemplae, String sPlanName, String sOwner, String sMake, String sVendor,
			String sProduction, String sProject, String sPart, String sPartNo, String sAuditedEmployee,
			String sAuditedEmployeeBoss, String sProductionLine, String sDescription, String sPlanDate, String sType,
			String sStatus, String sOrgid, String sCreated, String sFacility, String sMold, String sMaterial,
			String sColor, String sVersion,String customer,String frequencyId);

	public String AUDIT_PLAN(String org_id);

	// 廠別
	public String AUDIT_FACTORY(String makeFactoryIN, String org_id);

	// 制程
	public String AUDIT_MEANING(String MEANING_IN, String org_id);

	// 品名
	public String AUDIT_NAME(String NAME_IN, String org_id);

	public String AUDIT_TYPE(String org_id);

	// 狀態
	public String AUDIT_DESCRIPTION(String org_id);

	// 項目
	public String AUDIT_PROJECT_DETAILS(String planid, String result);

	// 根據稽核類別篩選
	public String AUDIT_TEMPLATE_NAME(String AUDIT_CATEGORY_ID,String tempName);

	// 查询機種
	public String AUDIT_PROJECT_NAME(String PROJECT_NAME_IN, String org_id);

	// 查詢人員
	public String AUDIT_USER_NAME(String NAME_IN, String org_id);

	// 稽核線別
	public String AUDIT_LINE_NAME(String name_in, String org_id);

	// 供應商
	public String AUDIT_VENDOR_NAME(String VENDOR_NAME_IN, String org_id);

	// 類別
	public String AUDIT_TYPE_NAME(String org_id);

	// 模號
	public String AUDIT_SYSTEM_ITEMS_NAME(String INVENTORY_IN);

	public String AUDIT_CREATE_RESULT(String pLAN_ID_IN);

	// 結果分類
	public String AUDIT_RESULT_NAME(String NAME_IN, String pLAN_ID_IN);

	// 符合性
	public String AUDIT_MATCH(String MATCH_NAME_IN, String pLAN_ID_IN);

	// 負責部門
	public String AUDIT_SHORT(String SHORT_NAME_IN, String pLAN_ID_IN);

	// 項目保存
	public String AUDIT_SAVE_RESULTS(String PLAN_ID_IN, String IS_PASSED_IN, String RESULT_CATEGORY_ID_IN,
			String PERSON_RESPONSIBLE_IN, String DEPARTMENT_RESPONSIBLE_IN, String MATCH_ID_IN, String RESULT_ID_IN,
			String ORG_ID_IN, String DESCRIPTION_IN, String RESULT_IN,String confirmResult,String confirmPerson);

	// 項目修改
	public String AUDIT_UPDATE_RESULTS(String PLAN_ID_IN, String IS_PASSED_IN, String RESULT_CATEGORY_ID_IN,
			String PERSON_RESPONSIBLE_IN, String DEPARTMENT_RESPONSIBLE_IN, String MATCH_ID_IN, String RESULT_ID_IN,
			String ORG_ID_IN, String DESCRIPTION_IN, String RESULT_IN,String confirmResult,String confirmPerson);

	// 查詢公司ORGID
	public String ORG_ID_BY_SITE(String SITE_IN);

	// 查詢人員姓名
	public String EMPNO(String EMPLOYEE_MASTER_ID_IN);

	// 查詢版本
	public String CHECK_FOR_UPDATES(String P_APP_NAME, String P_APP_DESCRIPTION);

	// 創建標準值
	public void AUDIT_MODIFY_CRITERION(String[] criterions, String tYPE_ID_IN, String pART_ID_IN,
			String iS_CREATE_CRITERION);

	public void CREATE_CRITERION(String cRITERION_VALUE, String mEASURE_TOOL_STR, String mEASURE_CODE_STR,
		    String tYPE_ID_IN, String pART_ID_IN);

	public void UPDATE_CRITERION(String cRITERION_VALUE, String mEASURE_TOOL_STR, String mEASURE_CODE_STR,
			String tYPE_ID_IN, String pART_ID_IN);

	public String getModelAndPartByPartNo(String pART_NO_IN);

	//獲取稽核項目結果信息
	public String getAuditResult(String rESULT_ID_IN, String oRG_ID_IN);

	//掃描幾台獲取料號
	public String getPartNoByToolName(String toolName);

	//获取稽核结果单项
	public String getPlanResultOne(String oRG_ID_IN, String rESULT_ID_IN, String pLAN_ID_IN);

	//获取用户信息
	public String getUserInfo(String eMPLOYEE_MASTER_ID_IN, String oRG_ID_IN);

	//更新稽核结果单项
	public String updatePlanResultOne(String oRG_ID_IN, String rESULT_ID_IN, String pLAN_ID_IN, String iS_PASSED_IN,
			String dESCRIPTION_IN, String rESULT_IN, String eMPLOYEE_MASTER_ID_IN, String dEPARTMENT_RESPONSIBLE_IN,
			String confirmResult,String confirmPerson);

	//根據樣本獲取製程
	public String getProcessByTemp(String aUDIT_TEMPLATE_ID_IN, String oRG_ID_IN);

	//获取模号MVL
	public String getMoldMvl(String oRG_ID_IN, String customer,String MOLD_NO_IN);

	//获取机台列表MVL
	public String getMvlEquipment(String orgnizationCode);

	//获取检验频率
	public String getFrequency(String oRG_ID_IN);

	//獲取MPT_LOOKUP_VALUES中的值
	public String getLookupValues(String oRG_ID_IN, String lookType, String flag);

	// 掃描機台獲取料號
	public String getPnByFacility(String faNumber, String orgnizationCode);

	// 根據工號獲取MasterId
	public String getMasterIdByEmpno(String empno,String role);
	
}
