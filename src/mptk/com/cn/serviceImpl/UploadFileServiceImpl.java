package mptk.com.cn.serviceImpl;

import java.sql.SQLException;

import mptk.com.cn.dao.UploadFileDao;
import mptk.com.cn.daoImpl.UploadFileDaoImpl;
import mptk.com.cn.service.UploadFileService;

public class UploadFileServiceImpl implements UploadFileService{

	private UploadFileDao uploadFileDao = new UploadFileDaoImpl();
	
	public boolean uploadFile(String fileType,String filePath,String uploadBy) throws ClassNotFoundException, SQLException{
		if(uploadFileDao.uploadFile(fileType, filePath, uploadBy).equals("N"))
			return false;
		else
			return true;
	}

	@Override
	public String getFilePathByTrackProjectId(String trackProjectId) {
		// TODO Auto-generated method stub
		String result = uploadFileDao.getFilePathByTrackProjectId(trackProjectId);
		if(result.equals("N")&&result.length()>1)
			return result;
		else
			return result;
	}

	@Override
	public String uploadFile(String filePath, String uploadBy, Integer orgId, String resultId) {
		String fileType = filePath.substring(filePath.indexOf(".")+1, filePath.length());
		String result = uploadFileDao.uploadFile(fileType,filePath,uploadBy,orgId,resultId);
		return result;
	}

	@Override
	public String deleteFile(Integer orgId, String resultId) {
		String result = uploadFileDao.deleteFile(orgId,resultId);
		return result;
	}

	@Override
	public String uploadFile(String fileName, Integer masterId, String deptno, String itemId, Integer orgId,int ObjectTypeId) {
		String fileType = fileName.substring(fileName.indexOf("."), fileName.length());
		//String filePath = "C:\\files\\"+fileName;
		String filePath = "files/"+fileName;
		String result = uploadFileDao.uploadFile(fileName,fileType,filePath,masterId,deptno,itemId,orgId,ObjectTypeId);
		return result;
	}
	
}
