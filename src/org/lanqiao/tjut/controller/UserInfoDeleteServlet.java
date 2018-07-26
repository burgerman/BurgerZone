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
	 * ɾ����¼StudentInfoAjaxDeleteServlet
	 */
	
@WebServlet("/UserInfoDeleteServlet")
	public class UserInfoDeleteServlet extends HttpServlet{
		
		private static final long serialVersionUID = 1L;
		
		protected void service(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException{
			
			//��ȡɾ����¼��id
			String delIndex = request.getParameter("delIndex");
			
			//���ɾ����¼��Ϊnull,�����ɾ������
			if(delIndex != null && !delIndex.equals("")){
				
				//����ɾ������
				//����model�����ɾ������
				
				ModelFactory.getUserInfoModelInstance().DeleteUserInfoByAccount(delIndex);
			}
			
			//����ɾ���ɹ���񣬶�Ҫ����Ajax��ѯ����
			//��ȡ��ѯ����ʵ�������
			
		TBUserBean userB = MyUtils.convertRequest2Bean(request, TBUserBean.class, null , null);
		
		//����model��������ݲ�ѯ
		List<TBUserBean> lstUser = ModelFactory.getUserInfoModelInstance().queryUserInfoByAccount(userB);
		
		//������Ӧ����ǰ���轫���ص�java�����ݽ������ת��Ϊjson����
		
		
		//����ѯ���ת��Ϊjson����
		String json = JSON.toJSONStringWithDateFormat(lstUser, "yyyy-MM-dd HH:mm:ss");
	
		//ʹ��writer���󽫷�������Ӧ���ݷ��ؿͻ���
		PrintWriter pw = response.getWriter();
		pw.write(json);
		pw.flush();
		pw.close();
		}

}

