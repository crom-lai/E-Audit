package mptk.com.cn.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.io.FileUtils;

import mptk.com.cn.eunm.ServerEnvironmentType;
import mptk.com.cn.service.TrackProjectService;
import mptk.com.cn.service.UploadFileService;
import mptk.com.cn.serviceImpl.TrackProjectServiceImpl;
import mptk.com.cn.serviceImpl.UploadFileServiceImpl;
import mptk.com.cn.utils.PropertiesUtils;

/**
 * 文件上传的Action类
 * 
 * @author icecore.chen
 *
 */
public class FileUploadAction extends SuperAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String username;//用戶名
	private File[] mPhoto;//文件標識
	private String[] mPhotoFileName;//文件名
	private String[] mPhotoContentType;
	private String itemId;//追蹤項目ID
	private String trackObjectId;//追蹤項目ID
	private String orgId;//組織架構ID
	private String description;//描述及其改善
	private String deptno;
	private UploadFileService uploadFileService = new UploadFileServiceImpl();
	private TrackProjectService trackProjectService = new TrackProjectServiceImpl();
	
	/**
	 * 文件上傳的方法
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@SuppressWarnings({ "deprecation", "resource" })
	public String fileUpload() throws IOException{
		if (mPhoto.length == 0) {
			//System.out.println("upload file is null,please check");
			return null;
		}
		String str = "N";
		String DIR = null;
		PropertiesUtils.load("jdbc.properties");
		if(PropertiesUtils.get("jdbc.password").startsWith("web")){
			DIR = ServerEnvironmentType.lINUX.toString();
		}else{
			DIR = ServerEnvironmentType.WINDOWS.toString();
		}
		for (int i = 0; i < mPhoto.length; i++) {
			File file = new File(DIR, mPhotoFileName[i]);
			FileUtils.copyFile(mPhoto[i], file);
			FileInputStream fis = new FileInputStream(file);
			//如果單張圖片大小超過5MB提示,上傳失敗
			boolean result = fis.available() > 1024 * 1024 * 5;
			if (result) {
				file.delete();
				str = "N5";
				//this.outPutString("N5,第" + (i + 1) + "张图片上传服务器失败-单张图片大小不能超过5MB", this.response);
				return null;
			} else {
				final int OBJECT_TYPE_ID = 41;
				//System.out.println(itemId);
				/*str = uploadFileService.uploadFile(mPhotoFileName[i], username, Integer.valueOf(orgId),
						itemId);*/
				str = uploadFileService.uploadFile(mPhotoFileName[i], Integer.valueOf(username),deptno,itemId, Integer.valueOf(orgId),OBJECT_TYPE_ID);
				//this.outPutString(str, this.response);
				//System.out.println(str);
			}
			//System.out.println("upload by：" + username);
			//System.out.println("upload date：" + new Date().toGMTString());
			//System.out.println("upload file name：" + mPhotoFileName[i]);
			fis.close();
		}
		//圖片上傳返回結果如果為N,則說明有異常,將GTC_TRACK_ITEMS表中的FILE_ID清空
		if(!isSuccess(str)){
			uploadFileService.deleteFile(Integer.valueOf(orgId),itemId);
		}else if(isSuccess(str)){
			//圖片上傳完畢後更新該追蹤項目的狀態,完成日期等信息
			str = trackProjectService.updateTrackProjectStrtus(Integer.valueOf(orgId),itemId,description,Integer.valueOf(trackObjectId));
			/*if(!isSuccess(str)){
				//更新狀態如果發生異常,依舊需要回退圖片
				uploadFileService.deleteFile(Integer.valueOf(orgId),itemId);
			}*/
		}
		this.outPutString(str, this.response);
		return null;
	}
	
	public boolean isSuccess(String str){
		String arr [] = str.split("------");
		if(arr[0].contains("N"))
			return false;
		return true;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getTrackObjectId() {
		return trackObjectId;
	}

	public void setTrackObjectId(String trackObjectId) {
		this.trackObjectId = trackObjectId;
	}

	public String getDeptno() {
		return deptno;
	}

	public void setDeptno(String deptno) {
		this.deptno = deptno;
	}

}
