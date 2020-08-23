package mptk.com.cn.action;

import java.io.IOException;

import mptk.com.cn.service.LoginService;
import mptk.com.cn.serviceImpl.LoginServiceImpl;

public class LoginAction extends SuperAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String empno;
	private String password;
	private LoginService ls = new LoginServiceImpl();

	public String login() throws IOException{
		String str = ls.login(empno,password);
		this.outPutString(str, response);
		//System.out.println(str);
		return null;
	}
	
	public String getEmpno() {
		return empno;
	}

	public void setEmpno(String empno) {
		this.empno = empno;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
