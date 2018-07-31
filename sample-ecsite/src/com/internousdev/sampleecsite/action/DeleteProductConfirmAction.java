package com.internousdev.sampleecsite.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.sampleecsite.dao.AdminItemDAO;
import com.internousdev.sampleecsite.dto.MCategoryDTO;
import com.internousdev.sampleecsite.dto.ProductInfoDTO;
import com.internousdev.sampleecsite.util.SearchConditionLoader;
import com.opensymphony.xwork2.ActionSupport;

public class DeleteProductConfirmAction extends ActionSupport implements SessionAware{
	private int id;

	public Map<String,Object> session;

	public String execute() throws Exception{

		AdminItemDAO dao = new AdminItemDAO();
		ProductInfoDTO dto = dao.selectById(id);
		session.put("deleteProductDTO", dto);

		SearchConditionLoader loader = new SearchConditionLoader();
		loader.execute(session);
		loader.executeAdmin(session);

		MCategoryDTO category = null;
		@SuppressWarnings("unchecked")
		List<MCategoryDTO> categoryList = (List<MCategoryDTO>) session.get("mCategoryDTOList");
		for(int i = 0; i < categoryList.size(); i++){
			MCategoryDTO m = categoryList.get(i);
			if(dto.getCategoryId() == m.getCategoryId()){
				category= m;
				break;
			}
		}
		session.put("deleteProductDTOCategory", category.getCategoryName());
		return SUCCESS;
	}

	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	@Override
	public void setSession(Map<String, Object> session){
		this.session = session;
	}
}
