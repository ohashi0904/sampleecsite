package com.internousdev.sampleecsite.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.sampleecsite.dao.MCategoryDAO;
import com.internousdev.sampleecsite.dao.ProductInfoDAO;
import com.internousdev.sampleecsite.dto.MCategoryDTO;
import com.internousdev.sampleecsite.dto.PaginationDTO;
import com.internousdev.sampleecsite.dto.ProductInfoDTO;
import com.internousdev.sampleecsite.util.Pagination;
import com.opensymphony.xwork2.ActionSupport;

public class ProductListAction extends ActionSupport implements SessionAware{
	private String productName;
	private String productNameKana;
	private String imageFilePath;
	private String imageFileName;
	private int price;

	private String categoryId;
	private String keywords;
	private List<MCategoryDTO> mCategoryDTOList = new ArrayList<MCategoryDTO>();
	private List<ProductInfoDTO> productInfoDTOList = new ArrayList<ProductInfoDTO>();

	private Map<String, Object> session;
	public String execute(){
		String result = ERROR;

		ProductInfoDAO productInfoDAO = new ProductInfoDAO();
		productInfoDTOList = productInfoDAO.getProductInfoList();
		Pagination pagination = new Pagination();
		PaginationDTO paginationDTO = pagination.initialize(productInfoDTOList, 9);/*ページ内表示数*/
		session.put("totalPageSize",  paginationDTO.getTotalPageSize());
		session.put("currentPageNumber", paginationDTO.getCurrentPageNo());
		session.put("totalRecordSize", paginationDTO.getTotalPageSize());
		session.put("startRecordNo", paginationDTO.getStartRecordNo());
		session.put("endRecordNo", paginationDTO.getEndRecordNo());
		session.put("pageNumberList", paginationDTO.getPageNumberList());
		session.put("productInfoDTOList", paginationDTO.getCurrentProductInfoPage());
		session.put("hasNextPage", paginationDTO.hasNextPage());
		session.put("hasPreviousPage", paginationDTO.hasPreviousPage());
		session.put("nextPageNo", paginationDTO.getNextPageNo());
		session.put("previousPageNo", paginationDTO.getPreviousPageNo());

		if(!session.containsKey("mCategoryList")){
			MCategoryDAO mCategoryDAO = new MCategoryDAO();
			mCategoryDTOList = mCategoryDAO.getMCategoryList();
			session.put("mCategoryDTOList", mCategoryDTOList);
		}

		result = SUCCESS;
		return result;
	}

	public List <MCategoryDTO> getmCategoryDTOList(){
		return mCategoryDTOList;
	}

	public void setmCategoryDTOList(List<MCategoryDTO> mCategoryDTOList){
		this.mCategoryDTOList = mCategoryDTOList;
	}

	public String getProductName(){
		return productName;
	}

	public void setProductName(String productName){
		this.productName = productName;
	}

	public String getProductNameKana(){
		return productNameKana;
	}

	public void setProductNameKana(String productNameKana){
		this.productNameKana = productNameKana;
	}

	public String getImageFilePath(){
		return imageFilePath;
	}

	public void setImageFilePath(String imageFilePath){
		this.imageFilePath = imageFilePath;
	}

	public String getImageFileName(){
		return imageFileName;
	}

	public void setImageFileName(String imageFileName){
		this.imageFileName = imageFileName;
	}

	public int getPrice(){
		return price;
	}

	public void setPrice(int price){
		this.price = price;
	}

	public String getCategoryId(){
		return categoryId;
	}

	public void setCategoryId(String categoryId){
		this.categoryId = categoryId;
	}

	public String getKeywords(){
		return keywords;
	}

	public void setKeywords(String keywords){
		this.keywords = keywords;
	}

	public List<ProductInfoDTO> getProductInfoDTOList(){
		return productInfoDTOList;
	}

	public void setProductInfoDTOList(List<ProductInfoDTO> productInfoDTOList){
		this.productInfoDTOList = productInfoDTOList;
	}

	public Map<String, Object> getSession(){
		return session;
	}

	public void setSession(Map<String, Object> session){
		this.session = session;
	}
}
