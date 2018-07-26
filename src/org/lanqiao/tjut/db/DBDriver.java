package org.lanqiao.tjut.db;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;





public class DBDriver {

	 
	
	
   //@param sql查询语句
   //@param rsh 返回结果集类型句柄
   //@param params查询语句的参数
   //@return 返回查询结果集
	
	
	
	public <T> T query(String sql, ResultSetHandler<T> rsh, Object...params) {
		 T tRe = null;
		
		try{
			
			//创建dbUtils的查询处理类QueryRunner实例对象
		
		
		
		
		
		// 返回结果集
        QueryRunner qr = new QueryRunner(DBSource.getDataSource(),true);
        //使用queryrunner进行查询操作
        
        
       
		 tRe = qr.query(sql, rsh, params);
		
	 }catch (SQLException e){
		 
		//DBUtils数据库查询操作异常
		System.out.println("DBUtils数据库操作查询操作异常：" +e.getMessage()); 
	    e.printStackTrace();
	 }
	
	return tRe;
	
	
	
}
	
	
    public int update(String sql,Object... params){
    	
    	int iRe= 0;
    	try{
    		
    		QueryRunner qr = new QueryRunner(DBSource.getDataSource(),true);
    		iRe= qr.update(sql,params);
    		
    	}catch (SQLException e){
    		
    		System.out.println("DBUtils数据库新增、修改、删除操作异常："+e.getMessage());
    	    e.printStackTrace();
    	
    	}
    	return iRe;
    }
	
	
	
	
	
	
}
	
