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

@WebServlet("/OpInfoQueryPagerServlet")
public class OpInfoQueryPagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��ȡ�ͻ���request������

		TBOperatorBean operB = MyUtils.convertRequestParameter2Bean(request, TBOperatorBean.class,null,null);
		
		// ��ѯ���ݿ⣬�����ظ��ͻ���json����		

		List<TBOperatorBean> lstStus = ModelFactory.getOperInfoModelInstance().queryOperInfoByParas(operB);
		// ��֯datagrid��Ҫ�����ݽṹ

		
		// ����ѯ�����ת��Ϊjson����
        System.out.println("1"+lstStus);
		String json = JSON.toJSONStringWithDateFormat(lstStus, "yyyy-MM-dd HH:mm:ss");
		//response.setContentType("text/html;charset=utf-8");

		response.getWriter().write(json);
		response.getWriter().flush();
		response.getWriter().close();
	}

}
