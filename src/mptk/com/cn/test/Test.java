package mptk.com.cn.test;

import mptk.com.cn.dao.AuditDao;
import mptk.com.cn.daoImpl.AuditDaoImpl;

public class Test {

	public static void main(String[] args) {
		AuditDao ad = new AuditDaoImpl();
		String str = ad.CHECK_FOR_UPDATES("GetacAudit","GetacAudit-V0.0012(PROD)");
		System.out.println(str);
	}

}
