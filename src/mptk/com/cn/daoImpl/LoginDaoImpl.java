package mptk.com.cn.daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import mptk.com.cn.dao.LoginDao;
import mptk.com.cn.utils.DBUtils;

public class LoginDaoImpl implements LoginDao {

	@Override
	public String login(String empno, String password) {
		Connection conn = null;
		CallableStatement cs = null;
		String result = "";
		try {
			conn = db.getConnection();
			cs = conn.prepareCall("{?=call APP_LOGIN_UTIL.LOGIN_CHECK(?,?,?,?,?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2, empno);
			cs.setString(3, password);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.registerOutParameter(6, Types.VARCHAR);
			cs.execute();
			result = cs.getString(4) + "," + cs.getString(6);
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
	private DBUtils db = new DBUtils();

}
