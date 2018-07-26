package org.lanqiao.tjut.model;

public class ModelFactory {
	
	
	/*
	 * 简单工程方法:获取UserLoginModel的实例对象
	 * 
	 * @return UserLoginModel实例
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
