package org.lanqiao.tjut.controller;


	
	

import java.io.IOException;    
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.tjut.bean.TBOperatorBean;
import org.lanqiao.tjut.model.ModelFactory;
import org.lanqiao.tjut.myUtils.MyUtils;

import com.alibaba.fastjson.JSON;

@WebServlet("/UserInfoQueryPagerServlet")
public class UserInfoQueryPagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取客户端request表单数据

		TBOperatorBean operB = MyUtils.convertRequestParameter2Bean(request, TBOperatorBean.class,null,null);
		
		// 查询数据库，并返回给客户端json对象		

		List<TBOperatorBean> lstUser = ModelFactory.getOperInfoModelInstance().queryOperInfoByParas(operB);
		// 组织datagrid需要的数据结构

		
		// 将查询结果集转换为json对象
        System.out.println("1"+lstUser);
		String json = JSON.toJSONStringWithDateFormat(lstUser, "yyyy-MM-dd HH:mm:ss");
		//response.setContentType("text/html;charset=utf-8");

		response.getWriter().write(json);
		response.getWriter().flush();
		response.getWriter().close();
	}

}
	

