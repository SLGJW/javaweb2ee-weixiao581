package com.ujiuye.bean;

/**
 * 分页工具类
 * @author Administrator
 *
 */
public class PageTool {
	private int currentPage;//当前页
	private int pageSize;//每页显示的条数
	private int totalSize;//总条数
	
	//上面三个参数是基础的参数，不需要计算，下面的四个参数需要上面三个参数计算得到的
	//提供一个只有上面三个参数的构造方法，通过构造方法来初始化下面四个参数
	private int totalPages;//总页数
	private int prePage;//上一页
	private int nextPage;//下一页
	private int startIndex;//开始的索引
	
	//PageTool pt = new PageTool(2,3,20);
	public PageTool(int currentPage, int pageSize, int totalSize) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.totalSize = totalSize;
		
		//分别对总页数、上一页、下一页、开始索引进行初始化
		initTotalPages(totalSize, pageSize);
		initPrePage(currentPage);
		initNextPage(currentPage, totalPages);
		initStartIndex(currentPage, pageSize);
		
	}
	
	/**
	 * 初始化总页数
	 * 4、总页数totalPages：totalSize / pageSize + (totalSize % pageSize == 0 ? 0 : 1)
	 */
	public void initTotalPages(int totalSize, int pageSize) {
		this.totalPages = totalSize / pageSize + (totalSize % pageSize == 0 ? 0 : 1);
	}
	
	/**
	 * 初始化上一页
	 * prePage   currentPage == 1 ? 1 : currentPage - 1
	 */
	public void initPrePage(int currentPage) {
		this.prePage = (currentPage == 1 ? 1 : currentPage - 1);
	}
	
	/**
	 * 初始化下一页
	 * nextPage currentPage == totalPage ? currentPage : currentPage + 1
	 */
	public void initNextPage(int currentPage, int totalPages) {
		this.nextPage = (currentPage == totalPages ? currentPage : currentPage + 1);
	}
	
	/**
	 * 初始化开始索引
	 * startIndex   （currentPage - 1） * pageSize  
	 */
	public void initStartIndex(int currentPage, int pageSize) {
		this.startIndex = (currentPage - 1) * pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getPrePage() {
		return prePage;
	}

	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	
	@Override
	public String toString() {
		return "PageTool [currentPage=" + currentPage + ", pageSize=" + pageSize + ", totalSize=" + totalSize
				+ ", totalPages=" + totalPages + ", prePage=" + prePage + ", nextPage=" + nextPage + ", startIndex="
				+ startIndex + "]";
	}

	public static void main(String[] args) {
		//一共有20条数据  每页显示3条   当前在第1页
		PageTool pt = new PageTool(1, 3, 20);
		System.out.println(pt);
		
	}
	
	
	
	
	
	
	
	
	
}	
