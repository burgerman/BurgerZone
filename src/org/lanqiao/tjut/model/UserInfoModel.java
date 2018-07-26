package org.lanqiao.tjut.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.lanqiao.tjut.bean.TBUserBean;
import org.lanqiao.tjut.db.DBFactory;
import org.lanqiao.tjut.myUtils.MyUtils;

public class UserInfoModel {
	
	
	
	public List<TBUserBean> queryUserInfoByAccount(TBUserBean userB){
		List<TBUserBean> lstUser = null;
		//��ѯsql
	  
	StringBuilder sbSql = new StringBuilder(" select * from TBUser where user_account = '' ");
	 
	//��ѯsql������
	List<Object> lstAccount = new ArrayList<>();
	
	//���ݲ�ѯ����ʵ���ද̬ƴд��ѯ����
	if(userB != null){
		
		//ѧ��������Ҫʹ��ģ����ѯ
		String userName = userB.getUser_name();
		
		if(userName != null && !userName.equals("")){
		//ƴдsql����
	    sbSql.append(" and user_name like ?");
	    
	    //��Ӳ���
	    lstAccount.add("%" + userName + "%");
	    
		}
		
		//ѧ���Ա��ѯ
		
		String userSex = userB.getUser_sex();
		if(userSex != null && !userSex.equals("")){
			
			//ƴдsql����
			sbSql.append(" and user_sex = ? ");
			//��Ӳ���
			lstAccount.add(userSex);
		}
		
		
		//ѧ���������ڿ�ʼʱ��
		Date userbirthday = userB.getUser_birthday();
		if(userbirthday != null){
			//ƴдsql����
			sbSql.append(" and user_birthday >= to_date(?,'yyyy-mm-dd hh24:mi:ss') ");
		
		   //��Ӳ���
			lstAccount.add(MyUtils.convertDate2String(userbirthday, "yyyy-mm-dd HH24:mm:ss"));
			
		}              
		
		

		String userAddress = userB.getUser_address();
		if(userAddress != null){
			
			//ƴдsql����
			sbSql.append(" and user_address = ? ");
			//��Ӳ���
			lstAccount.add(userAddress);
		}
		
		String userId = userB.getUser_id();
		if(userId != null) {
			sbSql.append(" and user_id = ?");
			lstAccount.add(userId);
		}
		
		
		Integer userCompany = userB.getUser_company();
		if(userCompany != null){
			
			//ƴдsql����
			sbSql.append(" and user_company = ? ");
			//��Ӳ���
			lstAccount.add(userCompany);
		}
			
	
	
	
	
	}
	//�����ֶε�������
	sbSql.append(" order by user_account desc ");
	
	//����db��������ݲ�ѯ����
	lstUser = DBFactory.getDBDriverInstance().query(sbSql.toString(),
	 new BeanListHandler<>(TBUserBean.class), lstAccount.toArray());
	
	//���ز�ѯ�����
	return lstUser;
	}

	
	
	
	public TBUserBean getUserInfoByAccount(String userAccount){
		TBUserBean userB = null;
		//��ѯsql
		String sbSql = "select * from TBUser where user_account?";
		
		//��ѯ�����б�
		List<Object> lstParas = new ArrayList<>();
		lstParas.add(userAccount);
		
		//����db�����ݲ�ѯ����
		userB = DBFactory.getDBDriverInstance().query(sbSql.toString(),
	        new BeanHandler<>(TBUserBean.class), lstParas.toArray());
	       return userB;
	}
	
	
	
	public int saveOrUpdateUserInfo(TBUserBean userB){
		
        int re_i = 0;
        //����ѧ����Ϣ��sql���
        StringBuilder sbSql = new StringBuilder();
        List<Object>lstParas = new ArrayList<>();
        
        if(userB != null){
        	
        	sbSql.append("insert into tbuser(user_account,user_name,user_sex,user_birthday,user_address,user_id,user_company) values(?,?,?,to_date(?,'yyyy-MM-dd hh24:mi:ss'),?,?,?)");
          
            lstParas.add(userB.getUser_account());
            lstParas.add(userB.getUser_name());
            lstParas.add(userB.getUser_sex());
            lstParas.add(MyUtils.convertDate2String(userB.getUser_birthday(), "yyyy-MM-dd HH:mm:ss"));
            lstParas.add(userB.getUser_address());
            lstParas.add(userB.getUser_id());
           
            lstParas.add(userB.getUser_company());
        
        
        }else{
        	//ͼƬΪNull���޸�
        }
        //�������ݿ��������ݿⱣ�洦��
        
       
      re_i = DBFactory.getDBDriverInstance().update(sbSql.toString(), lstParas.toArray());
        return re_i;
	
	

     }
	
	
	
public int DeleteUserInfoByAccount(String userAccount){
		
        int re_i = 0;
        //ɾ������sql
        
        /*
         * �߼�ɾ��:ʹ��update�޸ı�־λ�ֶμ���
         * ����ɾ��:ʹ��delete������ɾ����������
         * ע: ����ɾ����������where��䣬�������ű��ᱻɾ��
         * 
         */
	     
        String sbSql = "delete tbuser where user_account = ?";
        
        //��ѯsql������
        List<Object> lstParas = new ArrayList<>();
        lstParas.add(userAccount);
          //����db��������ݲ�ѯ����
          re_i = DBFactory.getDBDriverInstance().update(sbSql.toString(),lstParas.toArray());
          
          return re_i; 
        
	
	
	
	}
	 
	
}