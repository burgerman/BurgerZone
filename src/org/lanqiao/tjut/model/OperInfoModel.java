package org.lanqiao.tjut.model;

import java.util.ArrayList;  
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.lanqiao.tjut.bean.TBOperatorBean;
import org.lanqiao.tjut.db.DBFactory;

public class OperInfoModel {
	
	public TBOperatorBean getOperatorInfoByAccount(String oprAccount){
	    TBOperatorBean operB = null;
	    
	    String sbSql = "select * from TBOperator where opr_account=?";
		
	    
	    //查询参数列表
	    List<Object> lstParas = new ArrayList<>();
	    lstParas.add(oprAccount);
	    
	    
	    operB = DBFactory.getDBDriverInstance().query(sbSql.toString(),
	    		new BeanHandler<>(TBOperatorBean.class), lstParas.toArray());
	
	       return operB;
	}
	
	
	public List<TBOperatorBean> queryOperInfoByParas(TBOperatorBean operB){
		List<TBOperatorBean> lstOper = null;
		//查询sql
	  
	StringBuilder sbSql = new StringBuilder(" select * from TBOperator where opr_account = ? ");
	 
	//查询sql参数集
	List<Object> lstParas = new ArrayList<>();
	
	//根据查询参数实体类动态拼写查询条件
	if(operB != null){
		
		//学生姓名需要使用模糊查询
		String oprAccount = operB.getOpr_account();
		
		if(oprAccount != null && !oprAccount.equals("")){
		//拼写sql条件
	    sbSql.append(" and opr_account like ?");
	    
	    //添加参数
	    lstParas.add("%" + oprAccount + "%");
	    
		}
		
		
			
	}
	//主键字段倒序排序
	sbSql.append(" order by opraccount desc ");
	
	//调用db层进行数据查询操作
	lstOper = DBFactory.getDBDriverInstance().query(sbSql.toString(),
	 new BeanListHandler<>(TBOperatorBean.class), lstParas.toArray());
	
	//返回查询结果集
	return lstOper;
	}

	
    
	public int saveOrUpdateOperatorInfo(TBOperatorBean operB) {
		
		int re_i = 0;
		//保存学生信息的sql语句
		StringBuilder sbSql = new StringBuilder();
		List<Object>lstParas = new ArrayList<>();
		
		if(operB != null){
			
			sbSql.append("insert into TBOperator(opr_account, opr_psw) values(?,?)");
		    
			lstParas.add(operB.getOpr_account());
			lstParas.add(operB.getOpr_psw());
		
		}else{
			
		}
		
		re_i = DBFactory.getDBDriverInstance().update(sbSql.toString(), lstParas.toArray());
		return re_i;
	}
	
	
	
	
public int DeleteOperatorInfoByAccount(String oprAccount){
		
        int re_i = 0;
        //删除操作sql
        
        /*
         * 逻辑删除:使用update修改标志位字段即可
         * 物理删除:使用delete语句进行删除操作即可
         * 注: 两种删除操作都需where语句，否则整张表都会被删除
         * 
         */
	     
        String sbSql = "delete tboperator where opr_account = ?";
        
        //查询sql参数集
        List<Object> lstParas = new ArrayList<>();
        lstParas.add(oprAccount);
          //调用db层进行数据查询操作
          re_i = DBFactory.getDBDriverInstance().update(sbSql.toString(),lstParas.toArray());
          
          return re_i; 
        
	
	
	
	}
}
