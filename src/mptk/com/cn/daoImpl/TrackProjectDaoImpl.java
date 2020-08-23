package mptk.com.cn.daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

import mptk.com.cn.dao.TrackProjectDao;
import mptk.com.cn.utils.DBUtils;

public class TrackProjectDaoImpl implements TrackProjectDao {

	private DBUtils db = new DBUtils();

	@Override
	public String getTrackProjectList(Integer orgId, String ownerName) {
		Connection conn = null;
		CallableStatement cs = null;
		String result = "";
		try {
			conn = db.getConnection();
			cs = conn.prepareCall("{?=call TRACK_PROJECT_UTILS.GET_TRACK_PROJECT_LIST(?,?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setInt(2, orgId);
			cs.setString(3, ownerName);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.registerOutParameter(6, Types.VARCHAR);
			cs.execute();
			result = cs.getString(1) + "------" + cs.getString(5) + "------" + cs.getString(6);
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
				result = "N------服務器異常-請聯繫管理員";
			}
		}
		return result;
	}

	@Override
	public String getAuditResultById(Integer orgId, String resultId) {
		Connection conn = null;
		CallableStatement cs = null;
		String result = "";
		try {
			conn = db.getConnection();
			cs = conn.prepareCall("{?=call TRACK_PROJECT_UTILS.GET_AUDIT_RESULT(?,?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setInt(2, orgId);
			cs.setString(3, resultId);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.registerOutParameter(6, Types.VARCHAR);
			cs.execute();
			result = cs.getString(5) + "------" + cs.getString(6);
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
				result = "N------服務器異常-請聯繫管理員";
			}
		}
		return result;
	}

	@Override
	public String getTrackProjectItem(Integer orgId, String trackObjectId, String resultId) {
		Connection conn = null;
		CallableStatement cs = null;
		String result = "";
		try {
			conn = db.getConnection();
			cs = conn.prepareCall("{?=call TRACK_PROJECT_UTILS.GET_TRACK_PROJECT_ITEM(?,?,?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setInt(2, orgId);
			cs.setString(3, trackObjectId);
			cs.setString(4, resultId);
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.registerOutParameter(6, Types.VARCHAR);
			cs.registerOutParameter(7, Types.VARCHAR);
			cs.execute();
			result = cs.getString(6) + "------" + cs.getString(7);
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
				result = "N------服務器異常-請聯繫管理員";
			}
		}
		return result;
	}

	@Override
	public String updateTrackProjectStrtus(Integer orgId, String resultId, String description,Integer trackObjectId) {
		Connection conn = null;
		CallableStatement cs = null;
		String result = "";
		try {
			conn = db.getConnection();
			cs = conn.prepareCall("{?=call TRACK_PROJECT_UTILS.UPDATE_TRACK_ITEM_STATUS(?,?,?,?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setInt(2, orgId);
			cs.setString(3, resultId);
			cs.setInt(4, trackObjectId);
			cs.setString(5, description);
			cs.registerOutParameter(6, Types.VARCHAR);
			cs.registerOutParameter(7, Types.VARCHAR);
			cs.registerOutParameter(8, Types.VARCHAR);
			cs.execute();
			result = cs.getString(7)+"------"+cs.getString(8);
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
				result = "N------服務器異常-請聯繫管理員";
			}
		}
		return result;
	}

	@Override
	public String getUserInfoByMasterId(Integer masterId) {
		Connection conn = null;
		CallableStatement cs = null;
		String result = "";
		try {
			conn = db.getConnection();
			cs = conn.prepareCall("{?=call TRACK_PROJECT_UTILS.GET_USERINFO_BY_MASTERID(?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setInt(2, masterId);
			cs.registerOutParameter(3, Types.VARCHAR);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.execute();
			result = cs.getString(4)+"------"+cs.getString(5);
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
				result = "N------服務器異常-請聯繫管理員";
			}
		}
		return result;
	}

	@Override
	public String getDepartmentName(String deptNo) {
		String sql = "select nvl(TRACK_PROJECT_UTILS.GET_DNAME_BY_NO('"+deptNo+"'),' ') departmentName from dual ";
		List <Map<String,Object>> list = db.execQuery(sql, null);
		return list.get(0).get("DEPARTMENTNAME").toString();
	}

}
