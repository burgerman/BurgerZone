package org.lanqiao.tjut.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.lanqiao.tjut.bean.TBOperatorBean;
import org.lanqiao.tjut.db.DBFactory;

public class OperLoginModel {
	
	public List<TBOperatorBean> getOperLoginInfo(TBOperatorBean operB) {
		//��֯��ѯ����sql���
		
		String strSql = "select * from tboperator where opr_account = ? and opr_psw = ?";
		
		//��ѯ���sql����
		List<Object> lstParas = new ArrayList<>();
		//��Ӳ���
		lstParas.add(operB.getOpr_account());
		lstParas.add(operB.getOpr_psw());
		
		//����db��ִ��sql���
		
		List<TBOperatorBean> lstOper = DBFactory.getDBDriverInstance().query(strSql,
				new BeanListHandler<> (TBOperatorBean.class), lstParas.toArray());
		
		//���ص�¼��ѯ���
		return lstOper;
		
	}

}
