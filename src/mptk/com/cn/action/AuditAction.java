package mptk.com.cn.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;

import mptk.com.cn.eunm.ServerEnvironmentType;
import mptk.com.cn.service.AuditService;
import mptk.com.cn.service.UploadFileService;
import mptk.com.cn.serviceImpl.AuditServiceImpl;
import mptk.com.cn.serviceImpl.UploadFileServiceImpl;
import mptk.com.cn.utils.PropertiesUtils;

public class AuditAction extends SuperAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String PLAN_NAME_IN;
	private String ORG_ID_IN;
	private String PLAN_DATE_TDESC_IN;
	private String MAKE_FACTORY_NAME_IN;
	private String PRODUCTION_PROCESS_MEANING_IN;
	private String PART_NAME_IN;
	private String TYPE_NAME_IN;
	private String STATUS_CODE_DESC_IN;
	private String PLAN_ID_IN;
	private String AUDIT_CATEGORY_ID_IN;
	private String PROJECT_NAME_IN;
	private String NAME_IN;
	private String MEANING_IN;
	private String INVENTORY_IN;
	private String P_APP_NAME, P_APP_DESCRIPTION;// APP名稱和描述
	private String ADD_OR_UPDATE;// 表示時更新還是修改
	private String toolName;
	private final String ADD = "add";
	private String customer;
	private String tempName;
	private String orgnizationCode;
	private String frequencyId;
	private String lookType;
	private String flag;//標識,不為空標識需要獲取ID值
	private String faNumber;//機台號

	public void setLookType(String lookType) {
		this.lookType = lookType;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public void setFrequencyId(String frequencyId) {
		this.frequencyId = frequencyId;
	}

	public void setOrgnizationCode(String orgnizationCode) {
		this.orgnizationCode = orgnizationCode;
	}

	public void setTempName(String tempName) {
		this.tempName = tempName;
	}

	public String getP_APP_NAME() {
		return P_APP_NAME;
	}

	public void setP_APP_NAME(String p_APP_NAME) {
		P_APP_NAME = p_APP_NAME;
	}

	public String getP_APP_DESCRIPTION() {
		return P_APP_DESCRIPTION;
	}

	public void setP_APP_DESCRIPTION(String p_APP_DESCRIPTION) {
		P_APP_DESCRIPTION = p_APP_DESCRIPTION;
	}

	private String VENDOR_NAME_IN, AUDIT_TEMPLATE_ID_IN, OWNER_ID_IN, MAKE_FACTORY_ID_IN, VENDOR_ID_IN,
			PRODUCTION_PROCESS_CODE_IN, PROJECT_ID_IN, PART_ID_IN, PART_NO_IN, AUDITED_EMPLOYEE_IN,
			AUDITED_EMPLOYEE_BOSS_IN, PRODUCTION_LINE_ID_IN, DESCRIPTION_IN, PLAN_DATE_IN, TYPE_ID_IN, STATUS_CODE_IN,
			CREATED_BY_IN, FACILITY_NAME_IN, MOLD_NO_IN, SAMPLE_MATERIAL_IN, SAMPLE_COLOR_IN, SAMPLE_VERSION_IN,
			RESULT_ID_IN, MATCH_NAME_IN, SHORT_NAME_IN, IS_PASSED_IN, RESULT_CATEGORY_ID_IN, PERSON_RESPONSIBLE_IN,
			DEPARTMENT_RESPONSIBLE_IN, MATCH_ID_IN, RESULT_IN, EMPLOYEE_MASTER_ID_IN, SITE_IN,
			IS_CREATE_CRITERION, CRITERION_VALUE;

	public String getCRITERION_VALUE() {
		return CRITERION_VALUE;
	}

	public void setCRITERION_VALUE(String cRITERION_VALUE) {
		CRITERION_VALUE = cRITERION_VALUE;
	}

	private File[] mPhoto;// 文件標識
	private String[] mPhotoFileName;// 文件名
	private String[] mPhotoContentType;

	AuditService as = new AuditServiceImpl();

	public String AUDIT_CATEGORY() throws IOException {
		String result = as.AUDIT_CATEGORY(ORG_ID_IN);
		this.outPutString(result, this.response);
		return null;
	}

	// 創建稽核計畫
	public String CREATE_AUDIT_PLAN() throws IOException {
		String result = as.CREATE_AUDIT_PLAN(AUDIT_TEMPLATE_ID_IN, PLAN_NAME_IN, OWNER_ID_IN, MAKE_FACTORY_ID_IN,
				VENDOR_ID_IN, PRODUCTION_PROCESS_CODE_IN, PROJECT_ID_IN, PART_ID_IN, PART_NO_IN, AUDITED_EMPLOYEE_IN,
				AUDITED_EMPLOYEE_BOSS_IN, PRODUCTION_LINE_ID_IN, DESCRIPTION_IN, PLAN_DATE_IN, TYPE_ID_IN,
				STATUS_CODE_IN, ORG_ID_IN, CREATED_BY_IN, FACILITY_NAME_IN, MOLD_NO_IN, SAMPLE_MATERIAL_IN,
				SAMPLE_COLOR_IN, SAMPLE_VERSION_IN, customer,frequencyId);
		this.outPutString(result, this.response);
		return null;
	}

	// 檢測版本
	public String CHECK_FOR_UPDATES() throws IOException {
		String result = as.CHECK_FOR_UPDATES(P_APP_NAME, P_APP_DESCRIPTION);
		this.outPutString(result, this.response);
		return null;
	}
	
	// 掃描機台獲取料號
	public String getPnByFacility() throws IOException{
		String result = as.getPnByFacility(faNumber,orgnizationCode);
		this.outPutString(result, this.response);
		return null;
	}

	public String AUDIT_PROGRAM() throws IOException {
		String result = as.AUDIT_PROGRAM(PLAN_NAME_IN, ORG_ID_IN, PLAN_DATE_TDESC_IN, MAKE_FACTORY_NAME_IN,
				PRODUCTION_PROCESS_MEANING_IN, PART_NAME_IN, TYPE_NAME_IN, STATUS_CODE_DESC_IN, OWNER_ID_IN,FACILITY_NAME_IN);
		this.outPutString(result, this.response);
		return null;
	}
	
	//獲取MPT_LOOKUP_VALUES中的配置值
	public String getLookupValues() throws IOException{
		String result = as.getLookupValues(ORG_ID_IN,lookType,flag);
		this.outPutString(result, this.response);
		return null;
	}
	
	// 根據工號獲取MasterId
	public String getMasterIdByEmpno() throws IOException{
		String result = as.getMasterIdByEmpno(empno,role);
		this.outPutString(result, this.response);
		return null;
	}
	
	//获取机台列表MVL
	public String getMvlEquipment() throws IOException{
		String result = as.getMvlEquipment(orgnizationCode);
		this.outPutString(result, this.response);
		return null;
	}
	
	public String getFrequency() throws IOException{
		String result = as.getFrequency(ORG_ID_IN);
		this.outPutString(result, this.response);
		return null;
	}
	
	//获取模号MVL
	public String getMoldMvl() throws IOException{
		String result = as.getMoldMvl(ORG_ID_IN,customer,MOLD_NO_IN);
		this.outPutString(result, this.response);
		return null;
	}
	
	//根據樣本獲取製程
	public String getProcessByTemp() throws IOException{
		String result = as.getProcessByTemp(AUDIT_TEMPLATE_ID_IN,ORG_ID_IN);
		this.outPutString(result, this.response);
		return null;
	}
	
	// 获取用户信息
	public String getUserInfo() throws IOException {
		String result = as.getUserInfo(EMPLOYEE_MASTER_ID_IN, ORG_ID_IN);
		this.outPutString(result, this.response);
		return null;
	}

	// 获取稽核结果单项
	public String getPlanResultOne() throws IOException {
		String result = as.getPlanResultOne(ORG_ID_IN, RESULT_ID_IN, PLAN_ID_IN);
		this.outPutString(result, this.response);
		return null;
	}

	// 更新稽核结果单项
	public String updatePlanResultOne() throws IOException {
		String str = "Y";
		if (mPhoto == null) {
			//System.out.println("upload file is null,please check");
		} else {
			String DIR = null;
			PropertiesUtils.load("jdbc.properties");
			if(PropertiesUtils.get("jdbc.password").startsWith("web")){
				DIR = ServerEnvironmentType.lINUX.toString();
			}else{
				DIR = ServerEnvironmentType.WINDOWS.toString();
			}
			for (int i = 0; i < mPhoto.length; i++) {
				File file = new File(DIR, mPhotoFileName[i]);
				FileUtils.copyFile(mPhoto[i], file);
				FileInputStream fis = new FileInputStream(file);
				// 如果單張圖片大小超過8MB提示,上傳失敗
				boolean result = fis.available() > 1024 * 1024 * 8;
				if (result) {
					file.delete();
					str = "N、、單張圖片大小不能超過8MB";
				} else {
					final int OBJECT_TYPE_ID = 40;
					str = uploadFileService.uploadFile(mPhotoFileName[i], Integer.valueOf(EMPLOYEE_MASTER_ID_IN),
							DEPARTMENT_RESPONSIBLE_IN, RESULT_ID_IN, Integer.valueOf(ORG_ID_IN), OBJECT_TYPE_ID);
					if (!isSuccess(str)) {
						break;
					}
					//System.out.println(RESULT_ID_IN);
				}
				fis.close();
			}
		}
		if (isSuccess(str)) {
			str = as.updatePlanResultOne(ORG_ID_IN, RESULT_ID_IN, PLAN_ID_IN, IS_PASSED_IN, DESCRIPTION_IN,
					RESULT_IN, EMPLOYEE_MASTER_ID_IN, DEPARTMENT_RESPONSIBLE_IN,confirmResult,confirmPerson);
		}
		this.outPutString(str, this.response);
		return null;
	}

	// 查詢公司ORGID
	public String ORG_ID_BY_SITE() throws IOException {
		String result = as.ORG_ID_BY_SITE(SITE_IN);
		this.outPutString(result, this.response);
		return null;
	}

	// 查詢人員姓名
	public String EMPNO() throws IOException {
		String result = as.EMPNO(EMPLOYEE_MASTER_ID_IN);
		this.outPutString(result, this.response);
		return null;
	}

	public String AUDIT_PLAN() throws IOException {
		String result = as.AUDIT_PLAN(ORG_ID_IN);
		this.outPutString(result, this.response);
		return null;
	}

	// 廠別
	public String AUDIT_FACTORY() throws IOException {
		String result = as.AUDIT_FACTORY(MAKE_FACTORY_NAME_IN, ORG_ID_IN);
		this.outPutString(result, this.response);
		return null;
	}

	// 制程
	public String AUDIT_MEANING() throws IOException {
		String result = as.AUDIT_MEANING(MEANING_IN, ORG_ID_IN);
		this.outPutString(result, this.response);
		return null;
	}

	// 品名
	public String AUDIT_NAME() throws IOException {
		String result = as.AUDIT_NAME(NAME_IN, ORG_ID_IN);
		this.outPutString(result, this.response);
		return null;
	}

	// 結果分類
	public String AUDIT_RESULT_NAME() throws IOException {
		String result = as.AUDIT_RESULT_NAME(NAME_IN, ORG_ID_IN);
		this.outPutString(result, this.response);
		return null;
	}

	// 符合性
	public String AUDIT_MATCH() throws IOException {
		String result = as.AUDIT_MATCH(MATCH_NAME_IN, ORG_ID_IN);
		this.outPutString(result, this.response);
		return null;
	}

	// 負責部門
	public String AUDIT_SHORT() throws IOException {
		String result = as.AUDIT_SHORT(SHORT_NAME_IN, ORG_ID_IN);
		this.outPutString(result, this.response);
		return null;
	}

	public String AUDIT_TYPE() throws IOException {
		String result = as.AUDIT_TYPE(ORG_ID_IN);
		this.outPutString(result, this.response);
		return null;
	}

	// 狀態
	public String AUDIT_DESCRIPTION() throws IOException {
		String result = as.AUDIT_DESCRIPTION(ORG_ID_IN);
		this.outPutString(result, this.response);
		return null;
	}

	public String AUDIT_PROJECT_DETAILS() throws IOException {
		String result = as.AUDIT_PROJECT_DETAILS(PLAN_ID_IN, RESULT_ID_IN);
		this.outPutString(result, this.response);
		return null;
	}

	// 根據稽核類別篩選
	public String AUDIT_TEMPLATE_NAME() throws IOException {
		String result = as.AUDIT_TEMPLATE_NAME(AUDIT_CATEGORY_ID_IN,tempName);
		this.outPutString(result, this.response);
		return null;
	}

	// 查詢機種
	public String AUDIT_PROJECT_NAME() throws IOException {
		String result = as.AUDIT_PROJECT_NAME(PROJECT_NAME_IN, ORG_ID_IN);
		this.outPutString(result, this.response);
		return null;
	}

	// 查詢人員
	public String AUDIT_USER_NAME() throws IOException {
		String result = as.AUDIT_USER_NAME(NAME_IN, ORG_ID_IN);
		this.outPutString(result, this.response);
		return null;
	}

	// 稽核線別
	public String AUDIT_LINE_NAME() throws IOException {
		String result = as.AUDIT_LINE_NAME(NAME_IN, ORG_ID_IN);
		this.outPutString(result, this.response);
		return null;
	}

	// 供應商
	public String AUDIT_VENDOR_NAME() throws IOException {
		String result = as.AUDIT_VENDOR_NAME(VENDOR_NAME_IN, ORG_ID_IN);
		this.outPutString(result, this.response);
		return null;
	}

	// 類別
	public String AUDIT_TYPE_NAME() throws IOException {
		String result = as.AUDIT_TYPE_NAME(ORG_ID_IN);
		this.outPutString(result, this.response);
		return null;
	}

	// 料號
	public String AUDIT_SYSTEM_ITEMS_NAME() throws IOException {
		String result = as.AUDIT_SYSTEM_ITEMS_NAME(INVENTORY_IN);
		this.outPutString(result, this.response);
		return null;
	}

	// 創建稽核項目
	public String AUDIT_CREATE_RESULT() throws IOException {
		String result = as.AUDIT_CREATE_RESULT(PLAN_ID_IN);
		this.outPutString(result, this.response);
		return null;
	}

	// 獲取稽核項目信息
	public String getAuditResult() throws IOException {
		String result = as.getAuditResult(RESULT_ID_IN, ORG_ID_IN);
		this.outPutString(result, this.response);
		return null;
	}

	// 項目保存
	@SuppressWarnings("deprecation")
	public String AUDIT_SAVE_RESULTS() throws IOException {
		String str = "Y";
		if (mPhoto == null) {
			//System.out.println("upload file is null,please check");
		} else {
			String DIR = null;
			PropertiesUtils.load("jdbc.properties");
			if(PropertiesUtils.get("jdbc.password").startsWith("web")){
				DIR = ServerEnvironmentType.lINUX.toString();
			}else{
				DIR = ServerEnvironmentType.WINDOWS.toString();
			}
			for (int i = 0; i < mPhoto.length; i++) {
				File file = new File(DIR, mPhotoFileName[i]);
				FileUtils.copyFile(mPhoto[i], file);
				FileInputStream fis = new FileInputStream(file);
				// 如果單張圖片大小超過5MB提示,上傳失敗
				boolean result = fis.available() > 1024 * 1024 * 8;
				if (result) {
					file.delete();
					str = "N5";
					// this.outPutString("N5,第" + (i + 1) +
					// "张图片上传服务器失败-单张图片大小不能超过5MB", this.response);
				} else {
					//System.out.println(TRACKER_ID_IN);
					String[] strs = RESULT_ID_IN.split("、、");
					//for (int x = 0; x < strs.length; x++) {
						final int OBJECT_TYPE_ID = 40;
						str = uploadFileService.uploadFile(mPhotoFileName[i], Integer.valueOf(PERSON_RESPONSIBLE_IN),
								DEPARTMENT_RESPONSIBLE_IN, strs[0], Integer.valueOf(ORG_ID_IN),
								OBJECT_TYPE_ID);
						if (!isSuccess(str)) {
							break;
						}
					//}
					//System.out.println(str);
				}
				//System.out.println("upload date：" + new Date().toGMTString());
				//System.out.println("upload file name：" + mPhotoFileName[i]);
				fis.close();
			}
		}
		if (isSuccess(str)) {
			if (ADD_OR_UPDATE.equals(ADD)) {// 添加
				str = as.AUDIT_SAVE_RESULTS(PLAN_ID_IN, IS_PASSED_IN, RESULT_CATEGORY_ID_IN, PERSON_RESPONSIBLE_IN,
						DEPARTMENT_RESPONSIBLE_IN, MATCH_ID_IN, RESULT_ID_IN, ORG_ID_IN, DESCRIPTION_IN,
						RESULT_IN,confirmResult,confirmPerson);
			} else {// 修改
				str = as.AUDIT_UPDATE_RESULTS(PLAN_ID_IN, IS_PASSED_IN, RESULT_CATEGORY_ID_IN, PERSON_RESPONSIBLE_IN,
						DEPARTMENT_RESPONSIBLE_IN, MATCH_ID_IN, RESULT_ID_IN, ORG_ID_IN, DESCRIPTION_IN,
						RESULT_IN,confirmResult,confirmPerson);
			}
		}
		if (!"0".equals(TYPE_ID_IN)) {
			String temp1[] = RESULT_IN.split("、、");
			String MEASURE_TOOL_STR = "";
			String MEASURE_CODE_STR = "";
			// String FIXTURE_CODE_STR = "";
			for (int x = 0; x < temp1.length; x++) {
				String temp2[] = temp1[x].split("~!!~");
				if (x == 0) {
					if (temp2[2].equals(" ") || temp2[3].equals(" ")) {
						break;
					}
					MEASURE_TOOL_STR = temp2[2];
					MEASURE_CODE_STR = temp2[3];
					// FIXTURE_CODE_STR = temp2[4];
				} else {
					if (temp2[2].equals(" ") || temp2[3].equals(" ")) {
						break;
					}
					MEASURE_TOOL_STR = MEASURE_TOOL_STR + "、、" + temp2[2];
					MEASURE_CODE_STR = MEASURE_CODE_STR + "、、" + temp2[3];
					// FIXTURE_CODE_STR = FIXTURE_CODE_STR + "、、" + temp2[4];
				}
			}
			if ("true".equals(IS_CREATE_CRITERION)) {
				as.CREATE_CRITERION(CRITERION_VALUE, MEASURE_TOOL_STR, MEASURE_CODE_STR, TYPE_ID_IN, PART_ID_IN);
			} else {
				as.UPDATE_CRITERION(CRITERION_VALUE, MEASURE_TOOL_STR, MEASURE_CODE_STR, TYPE_ID_IN, PART_ID_IN);
			}
		}
		this.outPutString(str, this.response);
		return null;
	}

	public boolean isSuccess(String str) {
		String arr[] = str.split("、、");
		if (arr[0].contains("N"))
			return false;
		return true;
	}

	// Crom.lai add
	public String getModelAndPartByPartNo() throws IOException {
		String result = as.getModelAndPartByPartNo(PART_NO_IN);
		this.outPutString(result, this.response);
		return null;
	}

	// 掃描機台獲取料號
	public String getPartNoByToolName() throws IOException {
		String result = as.getPartNoByToolName(toolName);
		this.outPutString(result, this.response);
		return null;
	}

	private UploadFileService uploadFileService = new UploadFileServiceImpl();

	public String getPLAN_NAME_IN() {
		return PLAN_NAME_IN;
	}

	public void setPLAN_NAME_IN(String pLAN_NAME_IN) {
		PLAN_NAME_IN = pLAN_NAME_IN;
	}

	public String getORG_ID_IN() {
		return ORG_ID_IN;
	}

	public void setORG_ID_IN(String ORG_ID_IN) {
		this.ORG_ID_IN = ORG_ID_IN;
	}

	public String getPLAN_DATE_TDESC_IN() {
		return PLAN_DATE_TDESC_IN;
	}

	public void setPLAN_DATE_TDESC_IN(String pLAN_DATE_TDESC_IN) {
		PLAN_DATE_TDESC_IN = pLAN_DATE_TDESC_IN;
	}

	public String getMAKE_FACTORY_NAME_IN() {
		return MAKE_FACTORY_NAME_IN;
	}

	public void setMAKE_FACTORY_NAME_IN(String mAKE_FACTORY_NAME_IN) {
		MAKE_FACTORY_NAME_IN = mAKE_FACTORY_NAME_IN;
	}

	public String getPRODUCTION_PROCESS_MEANING_IN() {
		return PRODUCTION_PROCESS_MEANING_IN;
	}

	public void setPRODUCTION_PROCESS_MEANING_IN(String pRODUCTION_PROCESS_MEANING_IN) {
		PRODUCTION_PROCESS_MEANING_IN = pRODUCTION_PROCESS_MEANING_IN;
	}

	public String getPART_NAME_IN() {
		return PART_NAME_IN;
	}

	public void setPART_NAME_IN(String pART_NAME_IN) {
		PART_NAME_IN = pART_NAME_IN;
	}

	public String getTYPE_NAME_IN() {
		return TYPE_NAME_IN;
	}

	public void setTYPE_NAME_IN(String tYPE_NAME_IN) {
		TYPE_NAME_IN = tYPE_NAME_IN;
	}

	public String getSTATUS_CODE_DESC_IN() {
		return STATUS_CODE_DESC_IN;
	}

	public void setSTATUS_CODE_DESC_IN(String sTATUS_CODE_DESC_IN) {
		STATUS_CODE_DESC_IN = sTATUS_CODE_DESC_IN;
	}

	public String getPLAN_ID_IN() {
		return PLAN_ID_IN;
	}

	public void setPLAN_ID_IN(String pLAN_ID_IN) {
		PLAN_ID_IN = pLAN_ID_IN;
	}

	public String getVENDOR_NAME_IN() {
		return VENDOR_NAME_IN;
	}

	public void setVENDOR_NAME_IN(String vENDOR_NAME_IN) {
		VENDOR_NAME_IN = vENDOR_NAME_IN;
	}

	public String getINVENTORY_IN() {
		return INVENTORY_IN;
	}

	public void setINVENTORY_IN(String iNVENTORY_IN) {
		INVENTORY_IN = iNVENTORY_IN;
	}

	public String getMEANING_IN() {
		return MEANING_IN;
	}

	public void setMEANING_IN(String mEANING_IN) {
		MEANING_IN = mEANING_IN;
	}

	public String getNAME_IN() {
		return NAME_IN;
	}

	public void setNAME_IN(String nAME_IN) {
		NAME_IN = nAME_IN;
	}

	public String getPROJECT_NAME_IN() {

		return PROJECT_NAME_IN;
	}

	public void setPROJECT_NAME_IN(String pROJECT_NAME_IN) {

		PROJECT_NAME_IN = pROJECT_NAME_IN;
	}

	public String getAUDIT_CATEGORY_ID_IN() {
		return AUDIT_CATEGORY_ID_IN;
	}

	public void setAUDIT_CATEGORY_ID_IN(String aUDIT_CATEGORY_ID_IN) {
		AUDIT_CATEGORY_ID_IN = aUDIT_CATEGORY_ID_IN;
	}

	public File[] getmPhoto() {
		return mPhoto;
	}

	public void setmPhoto(File[] mPhoto) {
		this.mPhoto = mPhoto;
	}

	public String[] getmPhotoFileName() {
		return mPhotoFileName;
	}

	public void setmPhotoFileName(String[] mPhotoFileName) {
		this.mPhotoFileName = mPhotoFileName;
	}

	public String[] getmPhotoContentType() {
		return mPhotoContentType;
	}

	public void setmPhotoContentType(String[] mPhotoContentType) {
		this.mPhotoContentType = mPhotoContentType;
	}

	/*public String getTRACKER_ID_IN() {
		return TRACKER_ID_IN;
	}

	public void setTRACKER_ID_IN(String tRACKER_ID_IN) {
		TRACKER_ID_IN = tRACKER_ID_IN;
	}*/

	public String getMATCH_ID_IN() {
		return MATCH_ID_IN;
	}

	public void setMATCH_ID_IN(String mATCH_ID_IN) {
		MATCH_ID_IN = mATCH_ID_IN;
	}

	public String getDEPARTMENT_RESPONSIBLE_IN() {
		return DEPARTMENT_RESPONSIBLE_IN;
	}

	public void setDEPARTMENT_RESPONSIBLE_IN(String dEPARTMENT_RESPONSIBLE_IN) {
		DEPARTMENT_RESPONSIBLE_IN = dEPARTMENT_RESPONSIBLE_IN;
	}

	public String getPERSON_RESPONSIBLE_IN() {
		return PERSON_RESPONSIBLE_IN;
	}

	public void setPERSON_RESPONSIBLE_IN(String pERSON_RESPONSIBLE_IN) {
		PERSON_RESPONSIBLE_IN = pERSON_RESPONSIBLE_IN;
	}

	public String getRESULT_CATEGORY_ID_IN() {
		return RESULT_CATEGORY_ID_IN;
	}

	public void setRESULT_CATEGORY_ID_IN(String rESULT_CATEGORY_ID_IN) {
		RESULT_CATEGORY_ID_IN = rESULT_CATEGORY_ID_IN;
	}

	public String getIS_PASSED_IN() {
		return IS_PASSED_IN;
	}

	public void setIS_PASSED_IN(String iS_PASSED_IN) {
		IS_PASSED_IN = iS_PASSED_IN;
	}

	public String getSHORT_NAME_IN() {
		return SHORT_NAME_IN;
	}

	public void setSHORT_NAME_IN(String sHORT_NAME_IN) {
		SHORT_NAME_IN = sHORT_NAME_IN;
	}

	public String getMATCH_NAME_IN() {
		return MATCH_NAME_IN;
	}

	public void setMATCH_NAME_IN(String mATCH_NAME_IN) {
		MATCH_NAME_IN = mATCH_NAME_IN;
	}

	public String getRESULT_ID_IN() {
		return RESULT_ID_IN;
	}

	public void setRESULT_ID_IN(String rESULT_ID_IN) {
		RESULT_ID_IN = rESULT_ID_IN;
	}

	public String getCREATED_BY_IN() {
		return CREATED_BY_IN;
	}

	public void setCREATED_BY_IN(String cREATED_BY_IN) {
		CREATED_BY_IN = cREATED_BY_IN;
	}

	public String getMOLD_NO_IN() {
		return MOLD_NO_IN;
	}

	public void setMOLD_NO_IN(String mOLD_NO_IN) {
		MOLD_NO_IN = mOLD_NO_IN;
	}

	public String getPLAN_DATE_IN() {
		return PLAN_DATE_IN;
	}

	public void setPLAN_DATE_IN(String pLAN_DATE_IN) {
		PLAN_DATE_IN = pLAN_DATE_IN;
	}

	public String getTYPE_ID_IN() {
		return TYPE_ID_IN;
	}

	public void setTYPE_ID_IN(String tYPE_ID_IN) {
		TYPE_ID_IN = tYPE_ID_IN;
	}

	public String getSTATUS_CODE_IN() {
		return STATUS_CODE_IN;
	}

	public void setSTATUS_CODE_IN(String sTATUS_CODE_IN) {
		STATUS_CODE_IN = sTATUS_CODE_IN;
	}

	public String getSAMPLE_MATERIAL_IN() {
		return SAMPLE_MATERIAL_IN;
	}

	public void setSAMPLE_MATERIAL_IN(String sAMPLE_MATERIAL_IN) {
		SAMPLE_MATERIAL_IN = sAMPLE_MATERIAL_IN;
	}

	public String getSAMPLE_COLOR_IN() {
		return SAMPLE_COLOR_IN;
	}

	public void setSAMPLE_COLOR_IN(String sAMPLE_COLOR_IN) {
		SAMPLE_COLOR_IN = sAMPLE_COLOR_IN;
	}

	public String getSAMPLE_VERSION_IN() {
		return SAMPLE_VERSION_IN;
	}

	public void setSAMPLE_VERSION_IN(String sAMPLE_VERSION_IN) {
		SAMPLE_VERSION_IN = sAMPLE_VERSION_IN;
	}

	public String getPRODUCTION_LINE_ID_IN() {
		return PRODUCTION_LINE_ID_IN;
	}

	public void setPRODUCTION_LINE_ID_IN(String pRODUCTION_LINE_ID_IN) {
		PRODUCTION_LINE_ID_IN = pRODUCTION_LINE_ID_IN;
	}

	public String getDESCRIPTION_IN() {
		return DESCRIPTION_IN;
	}

	public void setDESCRIPTION_IN(String dESCRIPTION_IN) {
		DESCRIPTION_IN = dESCRIPTION_IN;
	}

	public String getFACILITY_NAME_IN() {
		return FACILITY_NAME_IN;
	}

	public void setFACILITY_NAME_IN(String fACILITY_NAME_IN) {
		FACILITY_NAME_IN = fACILITY_NAME_IN;
	}

	public String getPART_ID_IN() {
		return PART_ID_IN;
	}

	public void setPART_ID_IN(String pART_ID_IN) {
		PART_ID_IN = pART_ID_IN;
	}

	public String getPART_NO_IN() {
		return PART_NO_IN;
	}

	public void setPART_NO_IN(String pART_NO_IN) {
		PART_NO_IN = pART_NO_IN;
	}

	public String getAUDITED_EMPLOYEE_IN() {
		return AUDITED_EMPLOYEE_IN;
	}

	public void setAUDITED_EMPLOYEE_IN(String aUDITED_EMPLOYEE_IN) {
		AUDITED_EMPLOYEE_IN = aUDITED_EMPLOYEE_IN;
	}

	public String getAUDITED_EMPLOYEE_BOSS_IN() {
		return AUDITED_EMPLOYEE_BOSS_IN;
	}

	public void setAUDITED_EMPLOYEE_BOSS_IN(String aUDITED_EMPLOYEE_BOSS_IN) {
		AUDITED_EMPLOYEE_BOSS_IN = aUDITED_EMPLOYEE_BOSS_IN;
	}

	public String getOWNER_ID_IN() {
		return OWNER_ID_IN;
	}

	public void setOWNER_ID_IN(String oWNER_ID_IN) {
		OWNER_ID_IN = oWNER_ID_IN;
	}

	public String getMAKE_FACTORY_ID_IN() {
		return MAKE_FACTORY_ID_IN;
	}

	public void setMAKE_FACTORY_ID_IN(String mAKE_FACTORY_ID_IN) {
		MAKE_FACTORY_ID_IN = mAKE_FACTORY_ID_IN;
	}

	public String getVENDOR_ID_IN() {
		return VENDOR_ID_IN;
	}

	public void setVENDOR_ID_IN(String vENDOR_ID_IN) {
		VENDOR_ID_IN = vENDOR_ID_IN;
	}

	public String getPRODUCTION_PROCESS_CODE_IN() {
		return PRODUCTION_PROCESS_CODE_IN;
	}

	public void setPRODUCTION_PROCESS_CODE_IN(String pRODUCTION_PROCESS_CODE_IN) {
		PRODUCTION_PROCESS_CODE_IN = pRODUCTION_PROCESS_CODE_IN;
	}

	public String getPROJECT_ID_IN() {
		return PROJECT_ID_IN;
	}

	public void setPROJECT_ID_IN(String pROJECT_ID_IN) {
		PROJECT_ID_IN = pROJECT_ID_IN;
	}

	public String getAUDIT_TEMPLATE_ID_IN() {
		return AUDIT_TEMPLATE_ID_IN;
	}

	public void setAUDIT_TEMPLATE_ID_IN(String aUDIT_TEMPLATE_ID_IN) {
		AUDIT_TEMPLATE_ID_IN = aUDIT_TEMPLATE_ID_IN;
	}

	public String getRESULT_IN() {
		return RESULT_IN;
	}

	public void setRESULT_IN(String rESULT_IN) {
		RESULT_IN = rESULT_IN;
	}

	public String getEMPLOYEE_MASTER_ID_IN() {
		return EMPLOYEE_MASTER_ID_IN;
	}

	public void setEMPLOYEE_MASTER_ID_IN(String eMPLOYEE_MASTER_ID_IN) {
		EMPLOYEE_MASTER_ID_IN = eMPLOYEE_MASTER_ID_IN;
	}

	public String getSITE_IN() {
		return SITE_IN;
	}

	public void setSITE_IN(String sITE_IN) {
		SITE_IN = sITE_IN;
	}

	public String getIS_CREATE_CRITERION() {
		return IS_CREATE_CRITERION;
	}

	public void setIS_CREATE_CRITERION(String iS_CREATE_CRITERION) {
		IS_CREATE_CRITERION = iS_CREATE_CRITERION;
	}

	public String getADD_OR_UPDATE() {
		return ADD_OR_UPDATE;
	}

	public void setADD_OR_UPDATE(String aDD_OR_UPDATE) {
		ADD_OR_UPDATE = aDD_OR_UPDATE;
	}

	public String getToolName() {
		return toolName;
	}

	public void setToolName(String toolName) {
		this.toolName = toolName;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public void setFaNumber(String faNumber) {
		this.faNumber = faNumber;
	}
	
	private String empno;
	private String role;
	private String confirmResult;
	private String confirmPerson;

	public void setConfirmResult(String confirmResult) {
		this.confirmResult = confirmResult;
	}

	public void setConfirmPerson(String confirmPerson) {
		this.confirmPerson = confirmPerson;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setEmpno(String empno) {
		this.empno = empno;
	}

}
