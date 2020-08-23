package mptk.com.cn.dao;

import java.sql.SQLException;

public interface UploadFileDao {

	public String uploadFile(String fileType,String filePath,String uploadBy) throws ClassNotFoundException, SQLException;

	public String getFilePathByTrackProjectId(String trackProjectId);

	public String uploadFile(String fileType, String filePath, String uploadBy, Integer orgId, String resultId);

	public String deleteFile(Integer orgId, String resultId);

	public String uploadFile(String fileName, String fileType, String filePath, Integer masterId, String deptno,
			String itemId, Integer orgId,int ObjectTypeId);
	
}
