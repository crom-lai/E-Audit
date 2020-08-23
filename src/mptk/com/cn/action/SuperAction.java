package mptk.com.cn.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

//import net.sf.json.JSONObject;

/**
 * 所有action的父类
 * @author Crom.lai
 */
public class SuperAction extends ActionSupport implements ServletRequestAware,ServletResponseAware,ServletContextAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected HttpServletRequest request;//请求对象
	protected HttpServletResponse response;//相应对象
	protected ServletContext context;//上下文对象
	protected HttpSession session;//会话对象
	
	@Override
	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		this.session = request.getSession();
	}
	
	/*protected void outputJson(JSONObject jo, HttpServletResponse response) throws IOException
	{
		response.setContentType("text/html;charset=UTF-8");
	    PrintWriter out = this.response.getWriter();
	    out.println(jo.toString());
	    out.flush();		
	}*/
	
	/**
	 * 向客户端返回信息
	 * @param str:要返回的字符串
	 * @param response
	 * @throws IOException
	 */
	protected void outPutString(String str,HttpServletResponse response) throws IOException{
		response.setContentType("text/html;charset=UTF-8");
	    PrintWriter out = this.response.getWriter();
	    out.println(str.toString());
	    out.flush();
	}

}
