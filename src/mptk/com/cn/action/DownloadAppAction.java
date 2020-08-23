package mptk.com.cn.action;

import mptk.com.cn.service.AuditService;
import mptk.com.cn.serviceImpl.AuditServiceImpl;

public class DownloadAppAction extends SuperAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String appName;
	private String appDescription;

	AuditService auditService = new AuditServiceImpl();

	public String downloadApp() {
		String result = auditService.CHECK_FOR_UPDATES(appName, appDescription);
		this.request.setAttribute("appLink", result);
		return "SUCCESS";
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppDescription() {
		return appDescription;
	}

	public void setAppDescription(String appDescription) {
		this.appDescription = appDescription;
	}

}
