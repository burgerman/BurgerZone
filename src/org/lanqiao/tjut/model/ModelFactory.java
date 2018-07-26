package org.lanqiao.tjut.model;

public class ModelFactory {
	
	
	/*
	 * �򵥹��̷���:��ȡUserLoginModel��ʵ������
	 * 
	 * @return UserLoginModelʵ��
	 */
   
   public static LoginModel getLoginModelInstance(){
	   return new LoginModel();
   }
   
   public static OperLoginModel getOperLoginModelInstance(){
	   return new OperLoginModel();
   }
   
   
   public static OperInfoModel getOperInfoModelInstance(){
	   
	   return new OperInfoModel();
   }
	
   public static AdminInfoModel getAdminInfoModelInstance(){
	   
	   return new AdminInfoModel();
   }
   
   public static UserInfoModel getUserInfoModelInstance(){
	   return new UserInfoModel();
   }
   
 
   
   public static UserFeeModel getUserFeeModelInstance(){
	   return new UserFeeModel();
   }
	
}
