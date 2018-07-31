package com.internousdev.sampleecsite.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.sampleecsite.dao.CartInfoDAO;
import com.internousdev.sampleecsite.dao.DestinationInfoDAO;
import com.internousdev.sampleecsite.dao.MCategoryDAO;
import com.internousdev.sampleecsite.dao.UserInfoDAO;
import com.internousdev.sampleecsite.dto.DestinationInfoDTO;
import com.internousdev.sampleecsite.dto.MCategoryDTO;
import com.internousdev.sampleecsite.dto.UserInfoDTO;
import com.internousdev.sampleecsite.util.InputChecker;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements SessionAware {
	private String categoryId;
	private String loginId;
	private String password;
	private boolean savedLoginId;

	private List<MCategoryDTO> mCategoryDTOList = new ArrayList<MCategoryDTO>();

	private List<String> loginIdErrorMessageList = new ArrayList<String>();
	private List<String> passwordErrorMessageList = new ArrayList<String>();

	private Map<String, Object> session;

	public String execute(){
		String result = ERROR;

		if(savedLoginId==true){
			session.put("savedLoginId", true);
			session.put("loginId", loginId);
		}else{
			session.put("savedLoginId", false);
			session.remove("loginId", loginId);
		}

		InputChecker inputChecker = new InputChecker();
		loginIdErrorMessageList = inputChecker.doCheck("ログインID", loginId, 1, 8, true, false, false, true, false, false, false, false, false);
		passwordErrorMessageList = inputChecker.doCheck("パスワード", password, 1, 16, true, false, false, true, false, false, false, false, false);

		if(loginIdErrorMessageList.size()!=0 || passwordErrorMessageList.size()!=0){
			session.put("loginIdErrorMessageList", loginIdErrorMessageList);
			session.put("passwordErrorMessageList", passwordErrorMessageList);
			session.put("logined", 0);
		}

		if(!session.containsKey("mCategoryList")){
			MCategoryDAO mCategoryDAO = new MCategoryDAO();
			mCategoryDTOList = mCategoryDAO.getMCategoryList();
			session.put("mCategoryDTOList", mCategoryDTOList);
		}

//		UserInfoDAO userInfoDAO = new UserInfoDAO();
//		if(userInfoDAO.isExistsUserInfo(loginId, password)){
//			if(userInfoDAO.login(loginId, password) > 0 ){
//				UserInfoDTO userInfoDTO = userInfoDAO.getUserInfo(loginId, password);
//				session.put("loginId", userInfoDTO.getUserId());
//				int count=0;
//				CartInfoDAO cartInfoDAO = new CartInfoDAO();
//
//				count = cartInfoDAO.linkToLoginId(String.valueOf(session.get("tempUserId")), loginId);
//				if(count > 0 ){
//					DestinationInfoDAO destinationInfoDAO = new DestinationInfoDAO();
//					try{
//						List<DestinationInfoDTO> destinationInfoDTOList = new ArrayList<DestinationInfoDTO>();
//						destinationInfoDTOList = destinationInfoDAO.getDestinationInfo(loginId);
//						Iterator<DestinationInfoDTO> iterator = destinationInfoDTOList.iterator();
//						if(!(iterator.hasNext())){
//							destinationInfoDTOList = null;
//						}
//						session.put("destinationInfoDtoList", destinationInfoDTOList);
//					}catch(SQLException e){
//						e.printStackTrace();
//					}
//					result = "settlement";
//				}else{
//					result = SUCCESS;
//				}
//			}
//			session.put("logined", 1);
//		}
//		return result;
		UserInfoDAO userInfoDao = new UserInfoDAO();
		if (userInfoDao.isExistsUserInfo(loginId, password)) {
			if (userInfoDao.login(loginId, password) > 0) {
				UserInfoDTO userInfoDTO = userInfoDao.getUserInfo(loginId, password);
				session.put("userInfo", userInfoDTO);
				session.put("loginId", loginId);
				int count = 0;
				CartInfoDAO cartInfoDao = new CartInfoDAO();
				count = cartInfoDao.linkToLoginId(loginId, String.valueOf(session.get("tempUserId")));
				if (count > 0) {
					DestinationInfoDAO destinationInfoDao = new DestinationInfoDAO();
					try {
						List<DestinationInfoDTO> destinationInfoDtoList = new ArrayList<DestinationInfoDTO>();
						destinationInfoDtoList = destinationInfoDao.getDestinationInfo(loginId);
						Iterator<DestinationInfoDTO> iterator = destinationInfoDtoList.iterator();
						if (!(iterator.hasNext())) {
							destinationInfoDtoList = null;
						}
						session.put("destinationInfoDtoList", destinationInfoDtoList);
					} catch (SQLException e) {
						e.printStackTrace();
					}
						if(session.containsKey("goSettlement")){
							result = "settlement";
						}else{
							result = SUCCESS;
						}
				} else {
					result = SUCCESS;
				}
			}
			session.put("logined", 1);

			UserInfoDTO userInfoDTO = (UserInfoDTO) session.get("userInfo");
			if (userInfoDTO.getStatus().equals("1")) {
				result = "admin";
			}
		} else {
			session.put("loginFailedMessage", "ログインIDまたはパスワードが異なります。");
		}
		return result;
	}

	public String getCategoryId(){
		return categoryId;
	}

	public void setCategoryId(String categoryId){
		this.categoryId = categoryId;
	}

	public String getLoginId(){
		return loginId;
	}

	public void setLoginId(String loginId){
		this.loginId = loginId;
	}

	public String getPassword(){
		return password;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public boolean isSavedLoginId(){
		return savedLoginId;
	}

	public void setSavedLoginId(boolean savedLoginId){
		this.savedLoginId = savedLoginId;
	}

	public List<String> getLoginIdErrorMessageList(){
		return loginIdErrorMessageList;
	}

	public void setLoginIdErrorMessageList(List<String> loginIdErrorMessageList){
		this.loginIdErrorMessageList = loginIdErrorMessageList;
	}

	public List<String> getPasswordErrorMessageList(){
		return passwordErrorMessageList;
	}

	public void setPasswordErrorMessageList(List<String> passwordErrorMessageList){
		this.passwordErrorMessageList = passwordErrorMessageList;
	}

	public Map<String, Object> getSession(){
		return session;
	}

	public void setSession(Map<String, Object> session){
		this.session = session;
	}
}

