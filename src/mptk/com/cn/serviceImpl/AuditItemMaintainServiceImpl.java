package mptk.com.cn.serviceImpl;

import mptk.com.cn.dao.AuditItemMaintainDao;
import mptk.com.cn.daoImpl.AuditItemMaintainDaoImpl;
import mptk.com.cn.service.AuditItemMaintainService;

public class AuditItemMaintainServiceImpl implements AuditItemMaintainService {

	private AuditItemMaintainDao aimd = new AuditItemMaintainDaoImpl();
	
	@Override
	public String AuditItemIsPass(Integer orgId, String resultIdList, Integer masterId) {
		String result = aimd.AuditItemIsPass(orgId,resultIdList,masterId);
		return result;
	}

	@Override
	public String getNameByEmpno(String empno) {
		String result = aimd.getNameByEmpno(empno);
		return result;
	}

	@Override
	public String updatePlanStatus(Integer planId, String empno, Integer orgId) {
		String result = aimd.updatePlanStatus(planId,empno,orgId);
		return result;
	}

	@Override
	public String getPlanInfoByPartNo(String partNo, Integer orgId) {
		String result = aimd.getPlanInfoByPartNo(partNo,orgId);
		return result;
	}

	@Override
	public String getCriterionByPartNo(String partNo, String type) {
		String result = aimd.getCriterionByPartNo(partNo,type);
		return result;
	}

	@Override
	public String getCriterionByPart(String partNo, String type) {
		String result = aimd.getCriterionByPart(partNo,type);
		return result;
	}

	@Override
	public String getPlanStatus(String planId) {
		String result = aimd.getPlanStatus(planId);
		return result;
	}

	/**
	 * 获取用户或站点信息
	 */
	@Override
	public String getSiteOrCustomer(String siteOrCustomer) {
		String result = aimd.getSiteOrCustomer(siteOrCustomer);
		return result;
	}

}
