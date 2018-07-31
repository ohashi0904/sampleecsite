package com.internousdev.sampleecsite.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.sampleecsite.dao.MCategoryDAO;
import com.internousdev.sampleecsite.dao.ProductInfoDAO;
import com.internousdev.sampleecsite.dto.MCategoryDTO;
import com.internousdev.sampleecsite.dto.PaginationDTO;
import com.internousdev.sampleecsite.dto.ProductInfoDTO;
import com.internousdev.sampleecsite.util.InputChecker;
import com.internousdev.sampleecsite.util.Pagination;
import com.opensymphony.xwork2.ActionSupport;

public class SearchItemAction extends ActionSupport implements SessionAware {
	private String categoryId;
	private String keywords;
	private String pageNo;
	private List<MCategoryDTO> mCategoryDTOList = new ArrayList<MCategoryDTO>();
	private List<String> keywordsErrorMessageList = new ArrayList<String>();
	private List<ProductInfoDTO> productInfoDTOList = new ArrayList<ProductInfoDTO>();
	private Map<String, Object> session;
	public String execute(){
		String result = ERROR;

		InputChecker inputChecker = new InputChecker();
		if(keywords==null){
			keywords="";
		}
		keywordsErrorMessageList = inputChecker.doCheck("検索ワード", keywords, 0, 16, true, true, true, true, false, true, true, false, false);

		ProductInfoDAO productInfoDAO = new ProductInfoDAO();
		switch(categoryId){
			case "1":
				productInfoDTOList = productInfoDAO.getProductInfoListAll(keywords.replaceAll("　", " ").split(" "));
				result = SUCCESS;
				break;

			default:
				productInfoDTOList = productInfoDAO.getProductInfoListByKeywords(keywords.replaceAll("　", " ").split(" "), categoryId);
				result = SUCCESS;
				break;
		}
		Iterator<ProductInfoDTO> iterator = productInfoDTOList.iterator();
		if(!(iterator.hasNext())){
			productInfoDTOList = null;
		}
		session.put("keywordsErrorMessageList", keywordsErrorMessageList);

	if(!session.containsKey("mCategoryList")){
		MCategoryDAO mCategoryDAO = new MCategoryDAO();
		mCategoryDTOList = mCategoryDAO.getMCategoryList();
		session.put("mCategoryDTOList", mCategoryDTOList);
	}

	if(!(productInfoDTOList==null)){
		Pagination pagination = new Pagination();
		PaginationDTO paginationDTO = new PaginationDTO();
		if(pageNo==null){
			paginationDTO = pagination.initialize(productInfoDTOList, 9);
		}else{
			paginationDTO = pagination.getPage(productInfoDTOList, 9, pageNo);
		}

		session.put("productInfoDTOList", paginationDTO.getCurrentProductInfoPage());
		session.put("totalPageSize", paginationDTO.getTotalPageSize());
		session.put("currentPageNo", paginationDTO.getCurrentPageNo());
		session.put("totalRecordSize", paginationDTO.getTotalRecordSize());
		session.put("startRecordNo", paginationDTO.getStartRecordNo());
		session.put("endRecordNo", paginationDTO.getEndRecordNo());
		session.put("previousPage", paginationDTO.hasPreviousPage());
		session.put("previousPageNo", paginationDTO.getPreviousPageNo());
		session.put("nextPage", paginationDTO.hasNextPage());
		session.put("nextPageNo", paginationDTO.getNextPageNo());
		}else{
		session.put("productInfoDTOList", null);
		}
	return result;
	}

	public String getPageNo(){
		return pageNo;
	}

	public void setPageNo(String pageNo){
		this.pageNo = pageNo;
	}

	public List<MCategoryDTO> getmCategoryDTOList(){
		return mCategoryDTOList;
	}

	public void setmCategoryDTOList(List<MCategoryDTO> mCategoryDTOList){
		this.mCategoryDTOList = mCategoryDTOList;
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

	public List<String> getKeywordsErrorMessageList(){
		return keywordsErrorMessageList;
	}

	public void setKeywordsErrorMessageList(List<String> keywordsErrorMessageList){
		this.keywordsErrorMessageList = keywordsErrorMessageList;
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
