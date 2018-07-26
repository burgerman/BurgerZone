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
		//查询sql
	  
	StringBuilder sbSql = new StringBuilder(" select * from TBUser where user_account = '' ");
	 
	//查询sql参数集
	List<Object> lstAccount = new ArrayList<>();
	
	//根据查询参数实体类动态拼写查询条件
	if(userB != null){
		
		//学生姓名需要使用模糊查询
		String userName = userB.getUser_name();
		
		if(userName != null && !userName.equals("")){
		//拼写sql条件
	    sbSql.append(" and user_name like ?");
	    
	    //添加参数
	    lstAccount.add("%" + userName + "%");
	    
		}
		
		//学生性别查询
		
		String userSex = userB.getUser_sex();
		if(userSex != null && !userSex.equals("")){
			
			//拼写sql条件
			sbSql.append(" and user_sex = ? ");
			//添加参数
			lstAccount.add(userSex);
		}
		
		
		//学生出生日期开始时间
		Date userbirthday = userB.getUser_birthday();
		if(userbirthday != null){
			//拼写sql条件
			sbSql.append(" and user_birthday >= to_date(?,'yyyy-mm-dd hh24:mi:ss') ");
		
		   //添加参数
			lstAccount.add(MyUtils.convertDate2String(userbirthday, "yyyy-mm-dd HH24:mm:ss"));
			
		}              
		
		

		String userAddress = userB.getUser_address();
		if(userAddress != null){
			
			//拼写sql条件
			sbSql.append(" and user_address = ? ");
			//添加参数
			lstAccount.add(userAddress);
		}
		
		String userId = userB.getUser_id();
		if(userId != null) {
			sbSql.append(" and user_id = ?");
			lstAccount.add(userId);
		}
		
		
		Integer userCompany = userB.getUser_company();
		if(userCompany != null){
			
			//拼写sql条件
			sbSql.append(" and user_company = ? ");
			//添加参数
			lstAccount.add(userCompany);
		}
			
	
	
	
	
	}
	//主键字段倒序排序
	sbSql.append(" order by user_account desc ");
	
	//调用db层进行数据查询操作
	lstUser = DBFactory.getDBDriverInstance().query(sbSql.toString(),
	 new BeanListHandler<>(TBUserBean.class), lstAccount.toArray());
	
	//返回查询结果集
	return lstUser;
	}

	
	
	
	public TBUserBean getUserInfoByAccount(String userAccount){
		TBUserBean userB = null;
		//查询sql
		String sbSql = "select * from TBUser where user_account?";
		
		//查询参数列表
		List<Object> lstParas = new ArrayList<>();
		lstParas.add(userAccount);
		
		//调用db层数据查询操作
		userB = DBFactory.getDBDriverInstance().query(sbSql.toString(),
	        new BeanHandler<>(TBUserBean.class), lstParas.toArray());
	       return userB;
	}
	
	
	
	public int saveOrUpdateUserInfo(TBUserBean userB){
		
        int re_i = 0;
        //保存学生信息的sql语句
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
        	//图片为Null则不修改
        }
        //调用数据库层进行数据库保存处理
        
       
      re_i = DBFactory.getDBDriverInstance().update(sbSql.toString(), lstParas.toArray());
        return re_i;
	
	

     }
	
	
	
public int DeleteUserInfoByAccount(String userAccount){
		
        int re_i = 0;
        //删除操作sql
        
        /*
         * 逻辑删除:使用update修改标志位字段即可
         * 物理删除:使用delete语句进行删除操作即可
         * 注: 两种删除操作都需where语句，否则整张表都会被删除
         * 
         */
	     
        String sbSql = "delete tbuser where user_account = ?";
        
        //查询sql参数集
        List<Object> lstParas = new ArrayList<>();
        lstParas.add(userAccount);
          //调用db层进行数据查询操作
          re_i = DBFactory.getDBDriverInstance().update(sbSql.toString(),lstParas.toArray());
          
          return re_i; 
        
	
	
	
	}
	 
	
}