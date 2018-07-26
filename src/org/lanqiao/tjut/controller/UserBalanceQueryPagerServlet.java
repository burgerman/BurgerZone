package org.lanqiao.tjut.controller;

import java.io.IOException;   
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.tjut.bean.FeeManagerBean;

import org.lanqiao.tjut.model.ModelFactory;
import org.lanqiao.tjut.myUtils.MyUtils;

import com.alibaba.fastjson.JSON;

@WebServlet("/UserBalanceQueryPagerServlet")
public class UserBalanceQueryPagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��ȡ�ͻ���request������

		FeeManagerBean feeB = MyUtils.convertRequestParameter2Bean(request, FeeManagerBean.class,null,null);
		
		// ��ѯ���ݿ⣬�����ظ��ͻ���json����		

		List<FeeManagerBean> lstUser = ModelFactory.getUserFeeModelInstance().queryUserFeeByParas(feeB);
		// ��֯datagrid��Ҫ�����ݽṹ

		
		// ����ѯ�����ת��Ϊjson����
        System.out.println("1"+lstUser);
		String json = JSON.toJSONStringWithDateFormat(lstUser, "yyyy-MM-dd HH:mm:ss");
		//response.setContentType("text/html;charset=utf-8");

		response.getWriter().write(json);
		response.getWriter().flush();
		response.getWriter().close();
	}

}
	

