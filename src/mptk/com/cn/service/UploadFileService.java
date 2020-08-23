package mptk.com.cn.service;

import java.sql.SQLException;

public interface UploadFileService {

	public boolean uploadFile(String fileType,String filePath,String uploadBy) throws ClassNotFoundException, SQLException;

	public String getFilePathByTrackProjectId(String trackProjectId);
	
	public String uploadFile(String filePath,String uploadBy,Integer orgId,String resultId);

	public String deleteFile(Integer orgId, String resultId);

	public String uploadFile(String fileName, Integer masterId, String deptno, String itemId, Integer orgId,int ObjectTypeId);
	
}
