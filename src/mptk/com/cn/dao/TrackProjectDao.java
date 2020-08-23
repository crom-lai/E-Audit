package mptk.com.cn.dao;

public interface TrackProjectDao {

	public String getTrackProjectList(Integer orgId, String ownerName);

	public String getAuditResultById(Integer orgId, String resultId);

	public String getTrackProjectItem(Integer orgId, String trackObjectId, String resultId);

	public String updateTrackProjectStrtus(Integer orgId, String resultId, String description,Integer trackObjectId);
	
	public String getUserInfoByMasterId(Integer masterId);

	public String getDepartmentName(String deptNo);
	
}
