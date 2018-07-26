package org.lanqiao.tjut.db;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

public class DBSource {
	
	
	/*
	 * 创建单例模式的数据库源对象
	 */
	
	//创建一个静态实例变量
	   public static DataSource dataSource= null;
	   
	   //私有构造方法
	   
	   private DBSource(){
		   
		}
	    
	   /*
	    * 获取dbcp数据对象
	    * 
	    * @return 数据源对象
	    */
	   
	   public static DataSource getDataSource(){
		   
		   if(dataSource==null){
		      try{
			   //属性配置文件对象
			   
			   
			   Properties prop = new Properties();
			   prop.load(DBSource.class.getClassLoader().getResourceAsStream("db.properties"));
			   
			   
			   dataSource = BasicDataSourceFactory.createDataSource(prop);
			   
			   
			   
			   
		   }  catch (Exception e){
			   
			   System.out.println("生成数据库连接池错误：" + e.getMessage());
		   }
	   }
		   return dataSource;
	   }
	
	  
	
	
	

}
