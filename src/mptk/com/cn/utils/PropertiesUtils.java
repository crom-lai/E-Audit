package mptk.com.cn.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {
	
	private static Properties pros = null;// Properties Object

	/**
	 * 加载属性文件
	 * @param fileName
	 */
	public static void load(String fileName) {
		pros = new Properties();// 创建属性对象
		InputStream in = PropertiesUtils.class.getClassLoader().getResourceAsStream(fileName);
		try {
			pros.load(in);//加载属性文件
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据key读取value
	 * @param key
	 * @return
	 */
	public static String get(String key) {
		return pros.getProperty(key);
	}

}
