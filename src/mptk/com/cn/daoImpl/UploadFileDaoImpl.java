package mptk.com.cn.daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

import mptk.com.cn.dao.UploadFileDao;
import mptk.com.cn.utils.DBUtils;

public class UploadFileDaoImpl implements UploadFileDao{
	
	private DBUtils db = new DBUtils();

	public String uploadFile(String fileType,String filePath,String uploadBy){
		Connection conn = null;
		CallableStatement cs = null;
		String result = "N";
		try {
			conn = db.getConnection();
			cs = conn.prepareCall("{call FILE_UPLOAD_UTIL.FILE_UPLOAD(?,?,?,?)}"); 
			cs.setString(1, fileType);
			cs.setString(2, filePath);
			cs.setString(3, uploadBy);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.execute();
			result = cs.getString(4);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				cs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public String getFilePathByTrackProjectId(String trackProjectId) {
		String sql = "SELECT file_upload_util.get_file_path(?) file_path FROM dual";
		Object params [] = new Object[]{trackProjectId};
		List <Map<String,Object>> list = db.execQuery(sql, params);
		return list.get(0).get("FILE_PATH").toString();
	}

	@Override
	public String uploadFile(String fileType, String filePath, String uploadBy, Integer orgId, String resultId) {
		Connection conn = null;
		CallableStatement cs = null;
		String result = "";
		try {
			conn = db.getConnection();
			cs = conn.prepareCall("{?=call FILE_UPLOAD_UTIL.FILE_UPLOAD(?,?,?,?,?,?,?,?)}"); 
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2, fileType);
			cs.setString(3, filePath);
			cs.setString(4, uploadBy);
			cs.setInt(5, orgId);
			cs.setString(6, resultId);
			cs.registerOutParameter(7, Types.VARCHAR);
			cs.registerOutParameter(8, Types.VARCHAR);
			cs.registerOutParameter(9, Types.VARCHAR);
			cs.execute();
			result = cs.getString(8);
		} catch (ClassNotFoundException e) {
			result = "N------服務器異常-請聯繫管理員";
			e.printStackTrace();
		} catch (SQLException e) {
			result = "N------服務器異常-請聯繫管理員";
			e.printStackTrace();
		} finally {
			try {
				cs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public String deleteFile(Integer orgId, String resultId) {
		Connection conn = null;
		CallableStatement cs = null;
		String result = "";
		try {
			conn = db.getConnection();
			cs = conn.prepareCall("{?=call FILE_UPLOAD_UTIL.DELETE_FILE(?,?,?,?,?)}"); 
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setInt(2, orgId);
			cs.setString(3, resultId);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.registerOutParameter(6, Types.VARCHAR);
			cs.execute();
			result = cs.getString(5);
		} catch (ClassNotFoundException e) {
			result = "N------服務器異常-請聯繫管理員";
			e.printStackTrace();
		} catch (SQLException e) {
			result = "N------服務器異常-請聯繫管理員";
			e.printStackTrace();
		} finally {
			try {
				cs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public String uploadFile(String fileName, String fileType, String filePath, Integer masterId, String deptno,
			String itemId, Integer orgId,int ObjectTypeId) {
		Connection conn = null;
		CallableStatement cs = null;
		String result = "";
		try {
			conn = db.getConnection();
			cs = conn.prepareCall("{?=call FILE_UPLOAD_UTIL.FILE_UPLOAD(?,?,?,?,?,?,?,?,?,?,?)}"); 
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2, fileName);
			cs.setString(3, fileType);
			cs.setString(4, filePath);
			cs.setInt(5, masterId);
			cs.setString(6, deptno);
			cs.setString(7, itemId);
			cs.setInt(8, orgId);
			cs.setInt(9, ObjectTypeId);
			cs.registerOutParameter(10, Types.VARCHAR);
			cs.registerOutParameter(11, Types.VARCHAR);
			cs.registerOutParameter(12, Types.VARCHAR);
			cs.execute();
			result = cs.getString(10);
			//System.out.println(result);
		} catch (ClassNotFoundException e) {
			result = "N------服務器異常-請聯繫管理員";
			e.printStackTrace();
		} catch (SQLException e) {
			result = "N------服務器異常-請聯繫管理員";
			e.printStackTrace();
		} finally {
			try {
				cs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
}
