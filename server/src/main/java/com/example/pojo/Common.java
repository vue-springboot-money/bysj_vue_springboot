package com.example.pojo;

public class Common {
	// Controller返回对象的状态码
	public class Http {
		public final static int SUCCESS = 200;
		
		public final static int ERROR = 500;
		
		public final static int NOT_FOUND = 404;
	}
	
	// 用户身份
	public class Identity {
		// 系统管理员
		public final static int ADMIN = 0;
		
		// 学生
		public final static int STUDENT = 1;
		
		// 教师
		public final static int TEACHER = 2;
	}
}
