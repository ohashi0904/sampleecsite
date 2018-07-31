package com.internousdev.sampleecsite.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.sampleecsite.dao.MCategoryDAO;
import com.internousdev.sampleecsite.dto.MCategoryDTO;
import com.internousdev.sampleecsite.util.CommonUtility;
import com.opensymphony.xwork2.ActionSupport;

public class HomeAction extends ActionSupport implements SessionAware{
	private List<MCategoryDTO> mCategoryDTOList = new ArrayList<MCategoryDTO>();
	private String categoryId;
	private Map<String, Object> session;
	public String execute(){

		if(!(session.containsKey("loginId")) && !(session.containsKey("tempUserId"))){
			CommonUtility commonUtility = new CommonUtility();
			session.put("tempUserId", commonUtility.getRamdomValue());
		}

		if(!session.containsKey("logined")){
			session.put("logined", 0);
		}

		if(!session.containsKey("mCategoryList")){
			MCategoryDAO mCategoryDAO = new MCategoryDAO();
			mCategoryDTOList = mCategoryDAO.getMCategoryList();
			session.put("mCategoryDTOList", mCategoryDTOList);
		}
		return SUCCESS;
	}

	public String getCategoryId(){
		return categoryId;
	}

	public void setCategoryId(String categoryId){
		this.categoryId = categoryId;
	}

	public List<MCategoryDTO> getmCategoryDTOList(){
		return mCategoryDTOList;
	}

	public void setmCategoryDTOList(List<MCategoryDTO> mCategoryDTOList){
		this.mCategoryDTOList = mCategoryDTOList;
	}

	public Map<String, Object> getSession(){
		return session;
	}

	public void setSession(Map<String,Object> session){
		this.session = session;
	}
}
