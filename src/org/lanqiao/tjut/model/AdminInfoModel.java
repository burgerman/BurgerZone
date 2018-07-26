package org.lanqiao.tjut.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.lanqiao.tjut.bean.TBAdminsBean;

import org.lanqiao.tjut.db.DBFactory;

public class AdminInfoModel {
	
	public TBAdminsBean getAdminInfoByAccount(String adminAccount){
	    TBAdminsBean adminB = null;
	    
	    String sbSql = "select * from TBAdministrator where admin_account=?";
		
	    
	    //查询参数列表
	    List<Object> lstParas = new ArrayList<>();
	    lstParas.add(adminAccount);
	    
	    
	    adminB = DBFactory.getDBDriverInstance().query(sbSql.toString(),
	    		new BeanHandler<>(TBAdminsBean.class), lstParas.toArray());
	
	       return adminB;
	}
	
	

}
