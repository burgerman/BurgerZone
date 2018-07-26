package org.lanqiao.tjut.controller;

import java.io.IOException; 

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**

 * 由于前台页面放在了 web-info的安全目录下，所以采用后台转发的方式进行页面跳转

 */


@WebServlet("/DispatchCommServlet")
public class DispatchCommServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取跳转的路径名

		String strJumpP = request.getParameter("jp") + ".jsp";
		// 转发到前台页面

		request.getRequestDispatcher(strJumpP).forward(request, response);

	}

	

}
