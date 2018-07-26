package org.lanqiao.tjut.controller;

import java.io.IOException; 

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.tjut.bean.FeeManagerBean;

import org.lanqiao.tjut.model.ModelFactory;
import org.lanqiao.tjut.myUtils.MyUtils;

/**
 * Servlet implementation class OpInfoAddServlet
 */
@WebServlet("/TopUpServlet")
@MultipartConfig(maxFileSize=5000000)//设置文件大小的单位是比特
public class TopUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//使用实体类进行传参数
		
		FeeManagerBean feeB=MyUtils.convertRequestParameter2Bean(request, FeeManagerBean.class, "","");
		//调用model层进行数据库保存		
		int i = ModelFactory.getUserFeeModelInstance().saveOrUpdateUserFee(feeB);
		if(i>0){
			request.getRequestDispatcher("WEB-INF/logined/E-Manager/topUpDetail.jsp").forward(request, response);
		}else{
			response.getWriter().write(
					"<script language='javascript'>alert('数据保存失败!');"
					+ "location.href='DispatchCommServlet?"
					+ "jp=WEB-INF/logined/E-Manager/topUp';</script>");
		}
		
	}

}
