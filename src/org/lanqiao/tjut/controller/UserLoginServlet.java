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
	 * ��ʼ������
	 */
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
		//���пͻ���������
		System.out.println("���ǲ���web.xml���õ�servlet�Կͻ���request������");
		//ʹ��request�����getParameter�����ɻ�ȡ�ͻ���ʹ��http��request�����ύ����������form������������
		//����ʹ�ÿؼ���name���Ի�ȡ
		//��ȡ����
	
	    request.setCharacterEncoding("UTF-8");
		
		String strName = request.getParameter("txtUserName");
		
		//��ȡ����
		String strPsw = request.getParameter("txtUserPsw");
		
		System.out.println("��ȡ�Ŀͻ��˷��͵��û�����" +strName);
		System.out.println("��ȡ�Ŀͻ��˷��͵�����:" +strPsw);
			
		String method = request.getParameter("txtNick");
		//ʹ��ʵ������д���
		if(method.equals("����Ա")){
		TBAdminsBean adminB = MyUtils.getNewInstance(TBAdminsBean.class);
		
		//��ǰ̨ҳ�洫��ֵ������ʵ�����
		adminB.setAdmin_account(strName);
		adminB.setAdmin_psw(strPsw);
      		
        //����model����е�¼��֤��ҵ���߼�����
		//��ȡ��¼�û���Ϣ
		
		List<TBAdminsBean> lstAdmins = ModelFactory.getLoginModelInstance().getAdminLoginInfo(adminB);
		
		//��ת����¼�ɹ���ҳ�棬ҳ����ת��Ĵ��벻���ٱ�ִ��
		//�ж��Ƿ��¼�ɹ�
		
		if(lstAdmins != null && lstAdmins.size()>0){
			
			//��¼�ɹ�
			//���û���¼�ɹ���Ϣ���浽session������
			request.getSession().setAttribute("loginedAdminB", lstAdmins.get(0));
			//ʹ��ת��������ת
			
			request.getRequestDispatcher("WEB-INF/logined/adminLogined.jsp").forward(request, response);
		    //ʹ���ض��������ת
			//response.sendRedirect("WEB-INF/logined/adminLogined.jsp");
			
		
		
		}
		}else if(method.equals("����Ա")){
			

			
			TBOperatorBean operB = MyUtils.getNewInstance(TBOperatorBean.class);
			
			//��ǰ̨ҳ�洫��ֵ������ʵ�����
			operB.setOpr_account(strName);
			operB.setOpr_psw(strPsw);
	      		
	        //����model����е�¼��֤��ҵ���߼�����
			//��ȡ��¼�û���Ϣ
			
			List<TBOperatorBean> lstStu = ModelFactory.getOperLoginModelInstance().getOperLoginInfo(operB);
			
			//��ת����¼�ɹ���ҳ�棬ҳ����ת��Ĵ��벻���ٱ�ִ��
			//�ж��Ƿ��¼�ɹ�
			
			if(lstStu != null && lstStu.size()>0){
				
				//��¼�ɹ�
				//���û���¼�ɹ���Ϣ���浽session������
				request.getSession().setAttribute("loginedoperB", lstStu.get(0));
				//ʹ��ת��������ת
				
				request.getRequestDispatcher("WEB-INF/logined/opLogined.jsp").forward(request, response);
			    //ʹ���ض��������ת
				//response.sendRedirect("WEB-INF/logined/opLogined.jsp");
				
			
			
			
			
		}
		}
			
		
		//��¼ʧ��
		response.sendRedirect("login.jsp");
	
		}
    
	
     
}
