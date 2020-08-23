package mptk.com.cn.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import mptk.com.cn.service.UploadFileService;
import mptk.com.cn.serviceImpl.UploadFileServiceImpl;

public class TestAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private File[] mPhoto;
	private String[] mPhotoFileName;
	private String[] mPhotoContentType;
	private String fileType;
	private String trackProjectId;
	private UploadFileService uploadFileService = new UploadFileServiceImpl();

	/**
	 * Post请求上传文件
	 * 
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("deprecation")
	public String doPostUploadFile() throws IOException, ClassNotFoundException, SQLException {
		if (mPhoto.length == 0) {
			System.out.println("upload file is null,please check");
			return null;
		}
		String dir = "C:\\files";
		for (int i = 0; i < mPhoto.length; i++) {
			File file = new File(dir, mPhotoFileName[i]);
			FileUtils.copyFile(mPhoto[i], file);
			FileInputStream fis = new FileInputStream(file);
			boolean result = fis.available() > 1024 * 1024 * 5;
			HttpServletResponse response = ServletActionContext.getResponse();
			if (result) {
				file.delete();
				PrintWriter writer = response.getWriter();
				writer.write("第" + (i + 1) + "张图片上传服务器失败,单张图片大小不能超过5MB");
				writer.flush();
				return null;
			} else {
				if (!uploadFileService.uploadFile("JPG", mPhotoFileName[i], username)) {
					PrintWriter writer = response.getWriter();
					writer.write("第" + (i + 1) + "张图片上传服务器失败");
					writer.flush();
					return null;
				}
			}
			System.out.println("upload by：" + username);
			System.out.println("upload date：" + new Date().toGMTString());
			System.out.println("upload file name：" + mPhotoFileName[i]);
			fis.close();
		}
		return null;
	}

	public String login() throws IOException {
		System.out.println(username + ":" + password);
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter pw = response.getWriter();
		pw.write("login success");
		pw.flush();
		return null;
	}

	public String doPostString() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		ServletInputStream sis = request.getInputStream();

		StringBuilder sb = new StringBuilder();
		int len = 0;
		byte[] buf = new byte[1024];

		while ((len = sis.read(buf)) != -1) {
			sb.append(new String(buf, 0, len));
		}
		System.out.println(sb.toString());
		return null;
	}

	public String doPostFile() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		ServletInputStream sis = request.getInputStream();

		String dir = ServletActionContext.getServletContext().getRealPath("files");
		Long l = System.currentTimeMillis();
		File file = new File(dir, l.toString() + ".jpg");

		FileOutputStream fos = new FileOutputStream(file);

		int len = 0;
		byte[] buf = new byte[1024];

		while ((len = sis.read(buf)) != -1) {
			fos.write(buf, 0, len);
		}
		fos.flush();
		fos.close();
		return null;
	}
	
	public String getFilePathByTrackProjectId() throws IOException{
		String result = uploadFileService.getFilePathByTrackProjectId(trackProjectId);
		HttpServletResponse response = ServletActionContext.getResponse();
		writeToResponse(result,response);
		return null;
	}
	
	private void writeToResponse(String str,HttpServletResponse response) throws IOException{
		PrintWriter pw = response.getWriter();
		pw.write(str);
		pw.flush();
	}

	public String getTrackProjectId() {
		return trackProjectId;
	}

	public void setTrackProjectId(String trackProjectId) {
		this.trackProjectId = trackProjectId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public File[] getmPhoto() {
		return mPhoto;
	}

	public void setmPhoto(File[] mPhoto) {
		this.mPhoto = mPhoto;
	}

	public String[] getmPhotoFileName() {
		return mPhotoFileName;
	}

	public void setmPhotoFileName(String[] mPhotoFileName) {
		this.mPhotoFileName = mPhotoFileName;
	}

	public String[] getmPhotoContentType() {
		return mPhotoContentType;
	}

	public void setmPhotoContentType(String[] mPhotoContentType) {
		this.mPhotoContentType = mPhotoContentType;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

}
