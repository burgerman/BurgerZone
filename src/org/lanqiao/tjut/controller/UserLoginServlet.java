package org.lanqiao.tjut.controller;

import java.io.IOException;          
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.tjut.bean.TBAdminsBean;
import org.lanqiao.tjut.bean.TBOperatorBean;
import org.lanqiao.tjut.model.ModelFactory;
import org.lanqiao.tjut.myUtils.MyUtils;

@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1l;
	
	/*
	 * 初始化方法
	 */
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
		//进行客户端请求处理
		System.out.println("这是采用web.xml配置的servlet对客户端request请求处理");
		//使用request对象的getParameter方法可获取客户端使用http的request请求提交给服务器的form表单的数据内容
		//必须使用控件的name属性获取
		//获取姓名
	
	    request.setCharacterEncoding("UTF-8");
		
		String strName = request.getParameter("txtUserName");
		
		//获取密码
		String strPsw = request.getParameter("txtUserPsw");
		
		System.out.println("获取的客户端发送的用户名：" +strName);
		System.out.println("获取的客户端发送的密码:" +strPsw);
			
		String method = request.getParameter("txtNick");
		//使用实体类进行传参
		if(method.equals("管理员")){
		TBAdminsBean adminB = MyUtils.getNewInstance(TBAdminsBean.class);
		
		//将前台页面传入值，赋给实体变量
		adminB.setAdmin_account(strName);
		adminB.setAdmin_psw(strPsw);
      		
        //调用model层进行登录验证的业务逻辑处理
		//获取登录用户信息
		
		List<TBAdminsBean> lstAdmins = ModelFactory.getLoginModelInstance().getAdminLoginInfo(adminB);
		
		//跳转到登录成功的页面，页面跳转后的代码不会再被执行
		//判定是否登录成功
		
		if(lstAdmins != null && lstAdmins.size()>0){
			
			//登录成功
			//将用户登录成功信息缓存到session对象中
			request.getSession().setAttribute("loginedAdminB", lstAdmins.get(0));
			//使用转发进行跳转
			
			request.getRequestDispatcher("WEB-INF/logined/adminLogined.jsp").forward(request, response);
		    //使用重定向进行跳转
			//response.sendRedirect("WEB-INF/logined/adminLogined.jsp");
			
		
		
		}
		}else if(method.equals("操作员")){
			

			
			TBOperatorBean operB = MyUtils.getNewInstance(TBOperatorBean.class);
			
			//将前台页面传入值，赋给实体变量
			operB.setOpr_account(strName);
			operB.setOpr_psw(strPsw);
	      		
	        //调用model层进行登录验证的业务逻辑处理
			//获取登录用户信息
			
			List<TBOperatorBean> lstStu = ModelFactory.getOperLoginModelInstance().getOperLoginInfo(operB);
			
			//跳转到登录成功的页面，页面跳转后的代码不会再被执行
			//判定是否登录成功
			
			if(lstStu != null && lstStu.size()>0){
				
				//登录成功
				//将用户登录成功信息缓存到session对象中
				request.getSession().setAttribute("loginedoperB", lstStu.get(0));
				//使用转发进行跳转
				
				request.getRequestDispatcher("WEB-INF/logined/opLogined.jsp").forward(request, response);
			    //使用重定向进行跳转
				//response.sendRedirect("WEB-INF/logined/opLogined.jsp");
				
			
			
			
			
		}
		}
			
		
		//登录失败
		response.sendRedirect("login.jsp");
	
		}
    
	
     
}
