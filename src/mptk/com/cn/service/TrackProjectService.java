package mptk.com.cn.service;

public interface TrackProjectService {

	public String getTrackProjectList(Integer orgId,String ownerName);

	public String getAuditResultById(Integer orgId, String resultId);

	public String getTrackProjectItem(Integer orgId, String trackObjectId, String resultId);

	public String updateTrackProjectStrtus(Integer valueOf, String resultId, String description,Integer trackObjectId);
	
	public String getUserInfoByMasterId(Integer masterId);

	public String getDepartmentName(String deptNo);
	
}
