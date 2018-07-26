package org.lanqiao.tjut.model;

import java.util.ArrayList; 
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.lanqiao.tjut.bean.FeeManagerBean;


import org.lanqiao.tjut.db.DBFactory;
import org.lanqiao.tjut.myUtils.MyUtils;

public class UserFeeModel {
	
	
		
		public FeeManagerBean getUserFeeByAccount(String userAccount){
			FeeManagerBean feeB = null;
			//查询sql
			String sbSql = "select * from FeeManager where user_account?";
			
			//查询参数列表
			List<Object> lstFee = new ArrayList<>();
			lstFee.add(userAccount);
			
			//调用db层数据查询操作
			feeB = DBFactory.getDBDriverInstance().query(sbSql.toString(),
		        new BeanHandler<>(FeeManagerBean.class), lstFee.toArray());
		       return feeB;
		}
		
		
		public List<FeeManagerBean> queryUserFeeByParas(FeeManagerBean feeB){
			List<FeeManagerBean> lstFee = null;
			//查询sql
		  
		StringBuilder sbSql = new StringBuilder(" select * from feemanager where user_account = ? ");
		 
		//查询sql参数集
		List<Object> lstParas = new ArrayList<>();
		
		//根据查询参数实体类动态拼写查询条件
		if(feeB != null){
			
			//学生姓名需要使用模糊查询
			String oprAccount = feeB.getOpr_account();
			
			if(oprAccount != null && !oprAccount.equals("")){
			//拼写sql条件
		    sbSql.append(" and user_account like ?");
		    
		    //添加参数
		    lstParas.add("%" + oprAccount + "%");
		    
			}
			
			
				
		}
		//主键字段倒序排序
		sbSql.append(" order by useraccount desc ");
		
		//调用db层进行数据查询操作
		lstFee = DBFactory.getDBDriverInstance().query(sbSql.toString(),
		 new BeanListHandler<>(FeeManagerBean.class), lstParas.toArray());
		
		//返回查询结果集
		return lstFee;
		}
		
		
		
	   
		
		public int saveOrUpdateUserFee(FeeManagerBean feeB){
			
	        int re_i = 0;
	        //保存学生信息的sql语句
	        StringBuilder sbSql = new StringBuilder();
	        List<Object>lstParas = new ArrayList<>();
	        
	        if(feeB != null){
	        	
	        	sbSql.append("insert into feemanager(user_account,user_name,user_charge,user_pay,user_change,user_time,opr_account) values(?,?,?,?,?,to_date(?,'yyyy-MM-dd hh24:mi:ss'),?)");
	          
	            lstParas.add(feeB.getUser_account());
	            lstParas.add(feeB.getUser_name());
	            lstParas.add(feeB.getUser_charge());
	            lstParas.add(feeB.getUser_pay());
	            lstParas.add(feeB.getUser_change());
	            
	            
	            lstParas.add(MyUtils.convertDate2String(feeB.getUser_time(), "yyyy-MM-dd HH:mm:ss"));
	           
	           
	            lstParas.add(feeB.getOpr_account());
	        
	        
	        }else{
	        	//图片为Null则不修改
	        }
	        //调用数据库层进行数据库保存处理
	        
	       
	      re_i = DBFactory.getDBDriverInstance().update(sbSql.toString(), lstParas.toArray());
	        return re_i;
		
		

	     }
		
		
		
		
		
		
	

}
