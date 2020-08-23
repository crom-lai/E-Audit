package mptk.com.cn.eunm;

/**
 * 正式庫使用的是linux環境,測試庫使用本地windows環境,文件上傳是路徑不同
 * @author crom.lai
 * @date 2018年7月28日
 * @time 上午10:46:02
 */
public enum ServerEnvironmentType {
	
	    WINDOWS("C:\\files"),
	    lINUX("/home/files");

	    private final String value;

	    private ServerEnvironmentType(String value) {
	        this.value = value;
	    }

	    @Override
	    public String toString() {
	        return value;
	    }

	    public boolean equals(String val) {
	        return value.equals(val);
	    }
}
