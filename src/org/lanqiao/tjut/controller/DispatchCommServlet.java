package org.lanqiao.tjut.controller;

import java.io.IOException; 

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**

 * ����ǰ̨ҳ������� web-info�İ�ȫĿ¼�£����Բ��ú�̨ת���ķ�ʽ����ҳ����ת

 */


@WebServlet("/DispatchCommServlet")
public class DispatchCommServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��ȡ��ת��·����

		String strJumpP = request.getParameter("jp") + ".jsp";
		// ת����ǰ̨ҳ��

		request.getRequestDispatcher(strJumpP).forward(request, response);

	}

	

}
