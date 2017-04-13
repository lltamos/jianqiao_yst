package com.alqsoft.utils;
 
/**
 * 角色枚举
 * 
 * @author sunhuijie
 *
 * @date 2016年10月13日
 *
 */
public enum SystemRole {
	CITYAGENT {//市代理
		@Override
		public String getName() {
			return "cityAgent";
		}
	},COUNTYAGENT {//县代理
		@Override
		public String getName() {
			return "countyAgent";
		}
	},REGIONAGENT {//社区代理
		@Override
		public String getName() {
			return "regionAgent";
		}
	},MERCHANT {//商户
		@Override
		public String getName() {
			return "merchant";
		}
	},MANAGER {//管理员
		@Override
		public String getName() {
			return "manager";
		}
	},MEMBER {//普通会员  推广师
		@Override
		public String getName() {
			return "member";
		}
	},SESSIONROLE {//角色标识
		@Override
		public String getName() {
			return "sessionrole";
		}
	};
	public abstract String getName();
}
