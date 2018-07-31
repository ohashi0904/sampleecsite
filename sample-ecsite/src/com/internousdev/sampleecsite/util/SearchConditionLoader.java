package com.internousdev.sampleecsite.util;

import java.util.List;
import java.util.Map;

import com.internousdev.sampleecsite.dao.MCategoryDAO;
import com.internousdev.sampleecsite.dto.MCategoryDTO;

public class SearchConditionLoader {
	private List<MCategoryDTO> mCategoryDTOList;

	public void execute(Map<String, Object> session){
		if(!session.containsKey("mCategoryDTOList")){
			MCategoryDAO mCategoryDAO = new MCategoryDAO();
			mCategoryDTOList = mCategoryDAO.getMCategoryList();
			session.put("mCategoryDTOList", mCategoryDTOList);
		}

		if(!session.containsKey("targetData")){
			session.put("targetDate", "");
		}
	}

	public void executeAdmin(Map<String, Object> session){
		if(!session.containsKey("mAdminCategoryDTOList")){
			MCategoryDAO mCategoryDAO = new MCategoryDAO();
			mCategoryDTOList = mCategoryDAO.getAdminMCategoryList();
			session.put("mAdminCategoryDTOList", mCategoryDTOList);
		}
	}

	public List<MCategoryDTO> getmCategoryDTOList(){
		return mCategoryDTOList;
	}
}
