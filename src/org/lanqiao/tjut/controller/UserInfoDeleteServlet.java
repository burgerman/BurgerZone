package org.lanqiao.tjut.controller;

import java.io.IOException; 
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.tjut.bean.TBUserBean;
import org.lanqiao.tjut.model.ModelFactory;
import org.lanqiao.tjut.myUtils.MyUtils;

import com.alibaba.fastjson.JSON;

/*
	 * 删除记录StudentInfoAjaxDeleteServlet
	 */
	
@WebServlet("/UserInfoDeleteServlet")
	public class UserInfoDeleteServlet extends HttpServlet{
		
		private static final long serialVersionUID = 1L;
		
		protected void service(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException{
			
			//获取删除记录的id
			String delIndex = request.getParameter("delIndex");
			
			//如果删除记录不为null,则进行删除操作
			if(delIndex != null && !delIndex.equals("")){
				
				//进行删除操作
				//调用model层进行删除操作
				
				ModelFactory.getUserInfoModelInstance().DeleteUserInfoByAccount(delIndex);
			}
			
			//无论删除成功与否，都要进行Ajax查询处理
			//获取查询表单的实体类参数
			
		TBUserBean userB = MyUtils.convertRequest2Bean(request, TBUserBean.class, null , null);
		
		//调用model层进行数据查询
		List<TBUserBean> lstUser = ModelFactory.getUserInfoModelInstance().queryUserInfoByAccount(userB);
		
		//发回响应内容前，需将返回的java的数据结果对象转换为json对象
		
		
		//将查询结果转换为json对象
		String json = JSON.toJSONStringWithDateFormat(lstUser, "yyyy-MM-dd HH:mm:ss");
	
		//使用writer对象将服务器响应内容发回客户端
		PrintWriter pw = response.getWriter();
		pw.write(json);
		pw.flush();
		pw.close();
		}

}

