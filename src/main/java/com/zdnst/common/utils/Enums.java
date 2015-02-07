package com.zdnst.common.utils;

public class Enums{

	

	/**
	 * 个人版信息类型
	 * 
	 * @author jun.ma
	 * 
	 */
	public enum MsgPersonalType {
		/**
		 * 消息栏个人版类型，左上角
		 */
		MAIN_MSG_PERSONAL_ONE(1,"新活动"),
		/**
		 * 消息栏个人版类型，右上角
		 */
		MAIN_MSG_PERSONAL_TWO(2,"新投票"),
		/**
		 * 消息栏个人版类型，左下角
		 */
		MAIN_MSG_PERSONAL_THREE(3,"请柬"),
		/**
		 * 消息栏个人版类型，右下角
		 */
		MAIN_MSG_PERSONAL_FOUR(4,"评论信息"),;
		
		private int value;

		private String chinessTip;

		public int getValue() {
			return value;
		}

		public String getChinessTip() {
			return chinessTip;
		}

		private MsgPersonalType(int value, String chinessTip) {
			this.value = value;
			this.chinessTip = chinessTip;
		}
	}
		
	/**
	 * 团队初级版信息类型
	 * 
	 * @author jun.ma
	 * 
	 */
	public enum MsgTeamSimpleType {

			/**
			 * 消息栏团队版初级类型，左上角
			 */
			MAIN_MSG_TEAM_SIMPLE_ONE(1,"新活动"),
			
			/**
			 * 消息栏团队版初级类型，右上角
			 */
			MAIN_MSG_TEAM_SIMPLE_TWO(2,"新投票"),
			
			/**
			 * 消息栏团队版初级类型，左下角
			 */
			MAIN_MSG_TEAM_SIMPLE_THREE(3,"公告"),
			
			/**
			 * 消息栏团队版初级类型，右下角
			 */
			MAIN_MSG_TEAM_SIMPLE_FOUR(4,"评论信息"),;
			
			

			private int value;

			private String chinessTip;

			public int getValue() {
				return value;
			}

			public String getChinessTip() {
				return chinessTip;
			}

			private MsgTeamSimpleType(int value, String chinessTip) {
				this.value = value;
				this.chinessTip = chinessTip;
			}
		
		
	}
		
	/**
	 * 团队高級或企業版信息类型
	 * 
	 * @author jun.ma
	 * 
	 */
	public enum MsgTeamSeniorType {

			
			/**
			 * 消息栏团队或企业版高级类型，左上角
			 */
			MAIN_MSG_TEAM_SENIOR_ONE(1,"公告"),
			
			/**
			 * 消息栏团队或企业版高级类型，右上角
			 */
			MAIN_MSG_TEAM_SENIOR_TWO(2,"评论信息"),
			
			/**
			 * 消息栏团队或企业版高级类型，左下角
			 */
			MAIN_MSG_TEAM_SENIOR_THREE(3,"新讨论"),
			
			/**
			 * 消息栏团队或企业版高级类型，右下角
			 */
			MAIN_MSG_TEAM_SENIOR_FOUR(4,"活动/投票"),
			;
			
			

			private int value;

			private String chinessTip;

			public int getValue() {
				return value;
			}

			public String getChinessTip() {
				return chinessTip;
			}

			private MsgTeamSeniorType(int value, String chinessTip) {
				this.value = value;
				this.chinessTip = chinessTip;
			}
		
		
	}
}