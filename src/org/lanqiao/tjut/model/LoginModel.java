package org.lanqiao.tjut.model;

import java.util.ArrayList;  
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.lanqiao.tjut.bean.TBAdminsBean;
import org.lanqiao.tjut.bean.TBOperatorBean;
import org.lanqiao.tjut.db.DBFactory;

public class LoginModel {
	
	public List<TBAdminsBean> getAdminLoginInfo(TBAdminsBean adminB) {
		
		//组织查询操作sql语句
		
				String strSql = "select * from tbadministrator where admin_name = ? and admin_psw = ?";
				
				//查询语句sql参数
				List<Object> lstParas = new ArrayList<>();
				//添加参数
				lstParas.add(adminB.getAdmin_account());
				lstParas.add(adminB.getAdmin_psw());
				
				//调用db层执行sql语句
				
				List<TBAdminsBean> lstAdmins = DBFactory.getDBDriverInstance().query(strSql,
						new BeanListHandler<> (TBAdminsBean.class), lstParas.toArray());
				
				//返回登录查询结果
				return lstAdmins;
	}
    
	
	
	
	public List<TBOperatorBean> getOperLoginInfo(TBOperatorBean operB) {
		//组织查询操作sql语句
		
		String strSql = "select * from tboperator where opr_account = ? and opr_psw = ?";
		
		//查询语句sql参数
		List<Object> lstParas = new ArrayList<>();
		//添加参数
		lstParas.add(operB.getOpr_account());
		lstParas.add(operB.getOpr_psw());
		
		//调用db层执行sql语句
		
		List<TBOperatorBean> lstOper = DBFactory.getDBDriverInstance().query(strSql,
				new BeanListHandler<> (TBOperatorBean.class), lstParas.toArray());
		
		//返回登录查询结果
		return lstOper;
		
	}
	
	
	
}
