package mptk.com.cn.serviceImpl;

import mptk.com.cn.dao.TrackProjectDao;
import mptk.com.cn.daoImpl.TrackProjectDaoImpl;
import mptk.com.cn.service.TrackProjectService;

public class TrackProjectServiceImpl implements TrackProjectService {

	private TrackProjectDao tpd = new TrackProjectDaoImpl();
	
	@Override
	public String getTrackProjectList(Integer orgId, String ownerName) {
		String result = tpd.getTrackProjectList(orgId, ownerName);
		return result;
	}

	@Override
	public String getAuditResultById(Integer orgId, String resultId) {
		String result = tpd.getAuditResultById(orgId, resultId);
		return result;
	}

	@Override
	public String getTrackProjectItem(Integer orgId, String trackObjectId, String resultId) {
		String result = tpd.getTrackProjectItem(orgId, trackObjectId, resultId);
		return result;
	}

	@Override
	public String updateTrackProjectStrtus(Integer orgId, String resultId, String description,Integer trackObjectId) {
		String result = tpd.updateTrackProjectStrtus(orgId, resultId, description,trackObjectId);
		return result;
	}
	
	/**
	 * 获取用户信息
	 */
	@Override
	public String getUserInfoByMasterId(Integer masterId) {
		String result = tpd.getUserInfoByMasterId(masterId);
		return result;
	}

	@Override
	public String getDepartmentName(String deptNo) {
		String result = tpd.getDepartmentName(deptNo);
		return result;
	}

}
