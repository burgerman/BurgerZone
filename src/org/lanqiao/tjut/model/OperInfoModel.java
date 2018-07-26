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
		
	    
	    //��ѯ�����б�
	    List<Object> lstParas = new ArrayList<>();
	    lstParas.add(oprAccount);
	    
	    
	    operB = DBFactory.getDBDriverInstance().query(sbSql.toString(),
	    		new BeanHandler<>(TBOperatorBean.class), lstParas.toArray());
	
	       return operB;
	}
	
	
	public List<TBOperatorBean> queryOperInfoByParas(TBOperatorBean operB){
		List<TBOperatorBean> lstOper = null;
		//��ѯsql
	  
	StringBuilder sbSql = new StringBuilder(" select * from TBOperator where opr_account = ? ");
	 
	//��ѯsql������
	List<Object> lstParas = new ArrayList<>();
	
	//���ݲ�ѯ����ʵ���ද̬ƴд��ѯ����
	if(operB != null){
		
		//ѧ��������Ҫʹ��ģ����ѯ
		String oprAccount = operB.getOpr_account();
		
		if(oprAccount != null && !oprAccount.equals("")){
		//ƴдsql����
	    sbSql.append(" and opr_account like ?");
	    
	    //��Ӳ���
	    lstParas.add("%" + oprAccount + "%");
	    
		}
		
		
			
	}
	//�����ֶε�������
	sbSql.append(" order by opraccount desc ");
	
	//����db��������ݲ�ѯ����
	lstOper = DBFactory.getDBDriverInstance().query(sbSql.toString(),
	 new BeanListHandler<>(TBOperatorBean.class), lstParas.toArray());
	
	//���ز�ѯ�����
	return lstOper;
	}

	
    
	public int saveOrUpdateOperatorInfo(TBOperatorBean operB) {
		
		int re_i = 0;
		//����ѧ����Ϣ��sql���
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
        //ɾ������sql
        
        /*
         * �߼�ɾ��:ʹ��update�޸ı�־λ�ֶμ���
         * ����ɾ��:ʹ��delete������ɾ����������
         * ע: ����ɾ����������where��䣬�������ű��ᱻɾ��
         * 
         */
	     
        String sbSql = "delete tboperator where opr_account = ?";
        
        //��ѯsql������
        List<Object> lstParas = new ArrayList<>();
        lstParas.add(oprAccount);
          //����db��������ݲ�ѯ����
          re_i = DBFactory.getDBDriverInstance().update(sbSql.toString(),lstParas.toArray());
          
          return re_i; 
        
	
	
	
	}
}
