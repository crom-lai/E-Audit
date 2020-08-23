package mptk.com.cn.service;

public interface AuditItemMaintainService {

	public String AuditItemIsPass(Integer orgId,String resultIdList,Integer masterId);

	public String getNameByEmpno(String empno);

	public String updatePlanStatus(Integer planId, String empno, Integer orgId);

	public String getPlanInfoByPartNo(String partNo, Integer orgId);

	public String getCriterionByPartNo(String partNo, String type);

	public String getCriterionByPart(String partNo, String type);

	public String getPlanStatus(String planId);

	public String getSiteOrCustomer(String siteOrCustomer);

}
