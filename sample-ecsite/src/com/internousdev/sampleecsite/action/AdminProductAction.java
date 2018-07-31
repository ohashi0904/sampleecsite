package com.internousdev.sampleecsite.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.sampleecsite.dao.ProductInfoDAO;
import com.internousdev.sampleecsite.dto.ProductInfoDTO;
import com.internousdev.sampleecsite.util.SearchConditionLoader;
import com.opensymphony.xwork2.ActionSupport;

public class AdminProductAction extends ActionSupport implements SessionAware{
	public Map<String, Object> session;
	private List<ProductInfoDTO> productList = new ArrayList<ProductInfoDTO>();
	public String execute(){
		ProductInfoDAO dao = new ProductInfoDAO();
		productList = dao.getProductInfoList();

		session.remove("addProductDTO");

		SearchConditionLoader loader = new SearchConditionLoader();
		loader.execute(session);

		return SUCCESS;
	}

	public List<ProductInfoDTO> getProductList(){
		return productList;
	}

	@Override
	public void setSession(Map<String, Object> session){
		this.session = session;
	}

}
