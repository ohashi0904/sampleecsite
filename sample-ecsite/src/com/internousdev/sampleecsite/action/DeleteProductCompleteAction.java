package com.internousdev.sampleecsite.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.sampleecsite.dao.AdminItemDAO;
import com.internousdev.sampleecsite.dto.ProductInfoDTO;
import com.internousdev.sampleecsite.util.SearchConditionLoader;
import com.opensymphony.xwork2.ActionSupport;

public class DeleteProductCompleteAction extends ActionSupport implements SessionAware{
	private int id;
	public Map<String, Object> session;

	public String execute() throws Exception{
		ProductInfoDTO dto = (ProductInfoDTO) session.get("deleteProductDTO");

		if(dto != null){
			AdminItemDAO dao = new AdminItemDAO();
			System.out.println(dto.getId());
			System.out.println(dto.getProductId());
			dao.deleteProduct(dto.getId());
			dao.deleteCart(dto.getProductId());
		}

		SearchConditionLoader loader = new SearchConditionLoader();
		loader.execute(session);

		return SUCCESS;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}


}
