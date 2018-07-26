package org.lanqiao.tjut.controller;

import java.io.IOException;   

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.tjut.bean.TBOperatorBean;
import org.lanqiao.tjut.model.ModelFactory;
import org.lanqiao.tjut.myUtils.MyUtils;

/**
 * Servlet implementation class OpInfoAddServlet
 */
@WebServlet("/OpInfoAddServlet")
@MultipartConfig(maxFileSize=5000000)//�����ļ���С�ĵ�λ�Ǳ���
public class OpInfoAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//ʹ��ʵ������д�����
		
		TBOperatorBean operB=MyUtils.convertRequestParameter2Bean(request, TBOperatorBean.class, "","");
		//����model��������ݿⱣ��		
		int i = ModelFactory.getOperInfoModelInstance().saveOrUpdateOperatorInfo(operB);
		if(i>0){
			request.getRequestDispatcher("WEB-INF/logined/E-Manager/opInfoAddDetail.jsp").forward(request, response);
		}else{
			response.getWriter().write(
					"<script language='javascript'>alert('���ݱ���ʧ��!');"
					+ "location.href='DispatchCommServlet?"
					+ "jp=WEB-INF/logined/E-Manager/opInfoAdd';</script>");
		}
		
	}

}
