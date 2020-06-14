package com.ujiuye.bean;

/**
 * ��ҳ������
 * @author Administrator
 *
 */
public class PageTool {
	private int currentPage;//��ǰҳ
	private int pageSize;//ÿҳ��ʾ������
	private int totalSize;//������
	
	//�������������ǻ����Ĳ���������Ҫ���㣬������ĸ�������Ҫ����������������õ���
	//�ṩһ��ֻ���������������Ĺ��췽����ͨ�����췽������ʼ�������ĸ�����
	private int totalPages;//��ҳ��
	private int prePage;//��һҳ
	private int nextPage;//��һҳ
	private int startIndex;//��ʼ������
	
	//PageTool pt = new PageTool(2,3,20);
	public PageTool(int currentPage, int pageSize, int totalSize) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.totalSize = totalSize;
		
		//�ֱ����ҳ������һҳ����һҳ����ʼ�������г�ʼ��
		initTotalPages(totalSize, pageSize);
		initPrePage(currentPage);
		initNextPage(currentPage, totalPages);
		initStartIndex(currentPage, pageSize);
		
	}
	
	/**
	 * ��ʼ����ҳ��
	 * 4����ҳ��totalPages��totalSize / pageSize + (totalSize % pageSize == 0 ? 0 : 1)
	 */
	public void initTotalPages(int totalSize, int pageSize) {
		this.totalPages = totalSize / pageSize + (totalSize % pageSize == 0 ? 0 : 1);
	}
	
	/**
	 * ��ʼ����һҳ
	 * prePage   currentPage == 1 ? 1 : currentPage - 1
	 */
	public void initPrePage(int currentPage) {
		this.prePage = (currentPage == 1 ? 1 : currentPage - 1);
	}
	
	/**
	 * ��ʼ����һҳ
	 * nextPage currentPage == totalPage ? currentPage : currentPage + 1
	 */
	public void initNextPage(int currentPage, int totalPages) {
		this.nextPage = (currentPage == totalPages ? currentPage : currentPage + 1);
	}
	
	/**
	 * ��ʼ����ʼ����
	 * startIndex   ��currentPage - 1�� * pageSize  
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
		//һ����20������  ÿҳ��ʾ3��   ��ǰ�ڵ�1ҳ
		PageTool pt = new PageTool(1, 3, 20);
		System.out.println(pt);
		
	}
	
	
	
	
	
	
	
	
	
}	
