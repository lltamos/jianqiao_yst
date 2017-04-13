package com.alqsoft.utils;
/**
 * 角色枚举工具
 * 
 * @author 张靠勤
 * @e-mail 627658539@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2013-11-1 下午3:07:26
 * 
 */
public enum SystemRole {
	SUPERADMIN {
		@Override
		public String getName() {
			return "alqsoft";
		}
	},ADMIN {//管理员
		@Override
		public String getName() {
			return "admin";
		}
	},DOCTOR {//医生
		@Override
		public String getName() {
			return "doctor";
		}
	},HOSPITAL {//总院
		@Override
		public String getName() {
			return "hospital";
		}
	},HOSPITAL_BRANCH {//分院
		@Override
		public String getName() {
			return "hospital_branch";
		}
	},RECOMMENDER {//推荐人
		@Override
		public String getName() {
			return "recommender";
		}
	},CUSTOMER {//普通会员
		@Override
		public String getName() {
			return "customer";
		}
	},SESSIONROLE {//角色标识
		@Override
		public String getName() {
			return "sessionrole";
		}
	};
	public abstract String getName();
}
