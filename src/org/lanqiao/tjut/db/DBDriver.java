package org.lanqiao.tjut.db;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;





public class DBDriver {

	 
	
	
   //@param sql��ѯ���
   //@param rsh ���ؽ�������;��
   //@param params��ѯ���Ĳ���
   //@return ���ز�ѯ�����
	
	
	
	public <T> T query(String sql, ResultSetHandler<T> rsh, Object...params) {
		 T tRe = null;
		
		try{
			
			//����dbUtils�Ĳ�ѯ������QueryRunnerʵ������
		
		
		
		
		
		// ���ؽ����
        QueryRunner qr = new QueryRunner(DBSource.getDataSource(),true);
        //ʹ��queryrunner���в�ѯ����
        
        
       
		 tRe = qr.query(sql, rsh, params);
		
	 }catch (SQLException e){
		 
		//DBUtils���ݿ��ѯ�����쳣
		System.out.println("DBUtils���ݿ������ѯ�����쳣��" +e.getMessage()); 
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
    		
    		System.out.println("DBUtils���ݿ��������޸ġ�ɾ�������쳣��"+e.getMessage());
    	    e.printStackTrace();
    	
    	}
    	return iRe;
    }
	
	
	
	
	
	
}
	
