package mptk.com.cn.serviceImpl;

import mptk.com.cn.dao.LoginDao;
import mptk.com.cn.daoImpl.LoginDaoImpl;
import mptk.com.cn.service.LoginService;

public class LoginServiceImpl implements LoginService {

	private LoginDao ld = new LoginDaoImpl();

	@Override
	public String login(String empno, String password) {
		return ld.login(empno,password);
	}
	
}
