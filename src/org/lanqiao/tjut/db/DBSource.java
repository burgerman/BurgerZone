package org.lanqiao.tjut.db;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

public class DBSource {
	
	
	/*
	 * ��������ģʽ�����ݿ�Դ����
	 */
	
	//����һ����̬ʵ������
	   public static DataSource dataSource= null;
	   
	   //˽�й��췽��
	   
	   private DBSource(){
		   
		}
	    
	   /*
	    * ��ȡdbcp���ݶ���
	    * 
	    * @return ����Դ����
	    */
	   
	   public static DataSource getDataSource(){
		   
		   if(dataSource==null){
		      try{
			   //���������ļ�����
			   
			   
			   Properties prop = new Properties();
			   prop.load(DBSource.class.getClassLoader().getResourceAsStream("db.properties"));
			   
			   
			   dataSource = BasicDataSourceFactory.createDataSource(prop);
			   
			   
			   
			   
		   }  catch (Exception e){
			   
			   System.out.println("�������ݿ����ӳش���" + e.getMessage());
		   }
	   }
		   return dataSource;
	   }
	
	  
	
	
	

}
