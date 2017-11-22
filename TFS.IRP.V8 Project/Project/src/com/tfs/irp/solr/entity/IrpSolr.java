package com.tfs.irp.solr.entity;

public class IrpSolr {
         /**
          * 个人文档链接
          * @return
          */
		public static final String COLLECTION_DOCUMENT_PERSONAL = "DOCUMENT_ADDRESS_PERSONAL";
        /**
         * 企业文档链接
         * @return
         */
		public static final String COLLECTION_DOCUMENT_ENTERPRISE = "DOCUMENT_ADDRESS_ENTERPRISE";		
        /**
         * 微知
         * @return
         */
		public static final String COLLECTION_MICROBLOG = "MICROBLOG_ADDRESS";
		/**
		 * 问答
		 * @return
		 */
		public static final String COLLECTION_QUESTION = "QUESTION_ADDRESS";
		
		/**
		 * 知识 搜索结果每页显示多少条
		 * @return
		 */
		public static final String SEARCH_PAGENUM_KNOWLEDGE = "SEARCH_PAGENUM_KNOWLEDGE";
		/**
		 * 是否开启高亮显示
		 * @return
		 */
		public static final String SIGHHIGHT_VIEW = "SIGHHIGHT_VIEW";
		/**
		 * 检索知识
		 * 内容显示多少
		 * @return
		 */
		public static final String SIGHVIEW_SIZE = "SIGHVIEW_SIZE";
		/**
		 * 检索知识
		 * 内容从何处开始和结束
		 * @return
		 */
		public static final String SIGHVIEW_SIZE_STARTEND = "SIGHVIEW_SIZE_STARTEND";
		/**
		 * 标识特殊开始
		 * @return
		 */
		public static final String  STAYLE_START ="STAYLE_START";
		/**
		 * 标识特殊结束
		 * @return
		 */
		public static final String  STAYLE_END ="STAYLE_END";
		/**
		 * 片段重叠时会合并
		 * @return
		 */
		public static final String  SNIPPETMERGE="SNIPPETMERGE";
		/**
		 * 高亮字段 检索知识显示多少
		 * @return
		 */
		public static final String SNIPPETLENGTH="SNIPPETLENGTH";
		  /******************
		   * 配置检索微知
		   */
		//检索微知  每页显示多少条
		public static final String  MICR_PAGENUM = "MICR_PAGENUM";
		//检索微知————片段重叠时是否合并
		public static final String  MICR_SNIPPETMERGE = "MICR_SNIPPETMERGE";
		//检索微知————标识高亮显示(上)
		public static final String  MICR_HIGHT_START = "MICR_HIGHT_START";
		//检索微知————标识高亮显示(下)		
		public static final String  MICR_HIGHT_END = "MICR_HIGHT_END";
		//检索微知————检索字段显示多少
		public static final String  MICR_SNIPPET_SIZE = "MICR_SNIPPET_SIZE";
		//检索微知————检索字段从何处显示(如:显示100 配置0.6 则 显示 40-160)		
		public static final String  MICR_SNIPPET_STARTEND = "MICR_SNIPPET_STARTEND";
		//检索微知————片段显示多少 0为不显示检索字段 
		public static final String  MICR_LIGHTNUM = "MICR_LIGHTNUM";
		/**
		 * 配置检索问答
		 */
		//检索问答  每页显示多少条
		public static final String  QUESTION_PAGENUM = "QUESTION_PAGENUM";
		//检索问答————片段重叠时是否合并
		public static final String  QUESTION_SNIPPETMERGE = "QUESTION_SNIPPETMERGE";
		//检索问答————标识高亮显示(上)
		public static final String  QUESTION_HIGHT_START = "QUESTION_HIGHT_START";
		//检索问答————标识高亮显示(下)		
		public static final String  QUESTION_HIGHT_END = "QUESTION_HIGHT_END";
		//检索问答————检索字段显示多少
		public static final String  QUESTION_SNIPPET_SIZE = "QUESTION_SNIPPET_SIZE";
		//检索问答————检索字段从何处显示(如:显示100 配置0.6 则 显示 40-160)		
		public static final String  QUESTION_SNIPPET_STARTEND = "QUESTION_SNIPPET_STARTEND";
		//检索问答————片段显示多少 0为不显示检索字段 
		public static final String  QUESTION_LIGHTNUM = "QUESTION_LIGHTNUM";
		
		
		
		//搜用户每页显示多少
		public static final String MICR_USER_PAGENUM = "MICR_USER_PAGENUM";
		
		/**
		 * 配置检索说明字段  ------ 搜知识库/微知
		 * @return	
		 */
		public static final String SEARCH_EXPLAINBY_TITLE = "SEARCH_EXPLAINBY_TITLE";
		/**
		 * 配置检索说明字段  ------ 搜用户
		 * @return
		 */
		public static final String SEARCH_EXPLAINBY_CONTENT = "SEARCH_EXPLAINBY_CONTENT";
	
}
