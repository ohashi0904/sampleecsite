package com.internousdev.sampleecsite.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.sampleecsite.dao.ProductInfoDAO;
import com.internousdev.sampleecsite.dto.MCategoryDTO;
import com.internousdev.sampleecsite.dto.ProductInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class ProductDetailsAction extends ActionSupport implements SessionAware{
	private int productId;
	private List<MCategoryDTO> mCategoryDTOList = new ArrayList<MCategoryDTO>();
	private List<ProductInfoDTO> productInfoDTOList = new ArrayList<ProductInfoDTO>();
	private String categoryId;
	private Map<String, Object> session;
	public String execute(){
		String result = ERROR;
		ProductInfoDAO productInfoDAO = new ProductInfoDAO();
		ProductInfoDTO productInfoDTO = new ProductInfoDTO();
		productInfoDTO = productInfoDAO.getProductInfo(productId);
		session.put("id", productInfoDTO.getId());
		session.put("productId", productInfoDTO.getProductId());
		session.put("productName", productInfoDTO.getProductName());
		session.put("productNameKana", productInfoDTO.getProductNameKana());
		session.put("imageFilePath", productInfoDTO.getImageFilePath());
		session.put("imageFileName", productInfoDTO.getImageFileName());
		session.put("price", productInfoDTO.getPrice());

		session.put("releaseCompany", productInfoDTO.getReleaseCompany());
		session.put("releaseDate", productInfoDTO.getReleaseDate());
		session.put("productDescription", productInfoDTO.getProductDescription());
		List<Integer> productCountList = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5));
		session.put("productCountList", productCountList);
		productInfoDTOList = productInfoDAO.getProductInfoListByCategoryId(productInfoDTO.getCategoryId(), productInfoDTO.getProductId(), 0, 3);
		Iterator<ProductInfoDTO> iterator = productInfoDTOList.iterator();
		if(!(iterator.hasNext())){
			productCountList = null;
		}
		if(!productInfoDTOList.isEmpty() || productCountList==null){
			session.put("productInfoDTOList", productInfoDTOList);
			result = SUCCESS;
		}
		return result;
	}
	public List<MCategoryDTO> getmCategoryDtoList() {
		return mCategoryDTOList;
	}

	public void setmCategoryDtoList(List<MCategoryDTO> mCategoryDtoList) {
		this.mCategoryDTOList = mCategoryDtoList;
	}

	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public List<ProductInfoDTO> getProductInfoDtoList() {
		return productInfoDTOList;
	}
	public void setProductInfoDtoList(List<ProductInfoDTO> productInfoDtoList) {
		this.productInfoDTOList = productInfoDtoList;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
