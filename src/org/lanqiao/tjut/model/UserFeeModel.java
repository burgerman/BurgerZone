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
			//��ѯsql
			String sbSql = "select * from FeeManager where user_account?";
			
			//��ѯ�����б�
			List<Object> lstFee = new ArrayList<>();
			lstFee.add(userAccount);
			
			//����db�����ݲ�ѯ����
			feeB = DBFactory.getDBDriverInstance().query(sbSql.toString(),
		        new BeanHandler<>(FeeManagerBean.class), lstFee.toArray());
		       return feeB;
		}
		
		
		public List<FeeManagerBean> queryUserFeeByParas(FeeManagerBean feeB){
			List<FeeManagerBean> lstFee = null;
			//��ѯsql
		  
		StringBuilder sbSql = new StringBuilder(" select * from feemanager where user_account = ? ");
		 
		//��ѯsql������
		List<Object> lstParas = new ArrayList<>();
		
		//���ݲ�ѯ����ʵ���ද̬ƴд��ѯ����
		if(feeB != null){
			
			//ѧ��������Ҫʹ��ģ����ѯ
			String oprAccount = feeB.getOpr_account();
			
			if(oprAccount != null && !oprAccount.equals("")){
			//ƴдsql����
		    sbSql.append(" and user_account like ?");
		    
		    //��Ӳ���
		    lstParas.add("%" + oprAccount + "%");
		    
			}
			
			
				
		}
		//�����ֶε�������
		sbSql.append(" order by useraccount desc ");
		
		//����db��������ݲ�ѯ����
		lstFee = DBFactory.getDBDriverInstance().query(sbSql.toString(),
		 new BeanListHandler<>(FeeManagerBean.class), lstParas.toArray());
		
		//���ز�ѯ�����
		return lstFee;
		}
		
		
		
	   
		
		public int saveOrUpdateUserFee(FeeManagerBean feeB){
			
	        int re_i = 0;
	        //����ѧ����Ϣ��sql���
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
	        	//ͼƬΪNull���޸�
	        }
	        //�������ݿ��������ݿⱣ�洦��
	        
	       
	      re_i = DBFactory.getDBDriverInstance().update(sbSql.toString(), lstParas.toArray());
	        return re_i;
		
		

	     }
		
		
		
		
		
		
	

}
