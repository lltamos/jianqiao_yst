package com.alqsoft.utils;

/**
 * 分页信息
 * 
 * @author wangzc
 * 
 */
public class PagingInfo {

	/**
	 * 每页显示记录数
	 */
	private int pageSize = 8;
	
	/**
	 * 当前页码
	 */
	private int currentPage = 1;

	/**
	 * 获取每页显示记录数
	 * 
	 * @return
	 */
	public int getPageSize() {
		return pageSize;
	}
	
	/**
	 * 获取当前页码
	 * 
	 * @return
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * 构造函数
	 * 
	 * @param pageSize
	 *            每页显示记录数
	 * @param currentPage
	 *            当前页码
	 */
	public PagingInfo(int pageSize, int currentPage) {
		this.pageSize = pageSize;
		this.currentPage = currentPage;
	}

	/**
	 * 获取当前起始记录数
	 * 
	 * @return
	 */
	public int getCurrentRow() {
		return pageSize * (currentPage - 1);
	}
	
	/**
	 * 根据总记录数计算按此每页显示数量共能显示多少页
	 * @param totalCount
	 * @return 总页数
	 */
	public int getTotalPage(int totalCount){
		int totalPage = 0;
		
		if(totalCount % pageSize==0){
			totalPage = totalCount/pageSize;
		}
		else{
			totalPage = (totalCount/pageSize + 1);  
		}
		
		return totalPage;
	}

}
