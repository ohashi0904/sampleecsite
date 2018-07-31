package com.internousdev.sampleecsite.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.sampleecsite.dao.CartInfoDAO;
import com.internousdev.sampleecsite.dao.PurchaseHistoryInfoDAO;
import com.internousdev.sampleecsite.dto.CartInfoDTO;
import com.internousdev.sampleecsite.dto.DestinationInfoDTO;
import com.internousdev.sampleecsite.dto.PurchaseHistoryInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class SettlementCompleteAction extends ActionSupport implements SessionAware{

	private String id;
	private String categoryId;
	private Map<String, Object> session;

	public String execute(){
		String result = ERROR;

		@SuppressWarnings("unchecked")
		ArrayList<PurchaseHistoryInfoDTO> purchaseHistoryInfoDTOList = (ArrayList<PurchaseHistoryInfoDTO>)session.get("purchaseHistoryInfoDTOList");

		@SuppressWarnings("unchecked")
		ArrayList<DestinationInfoDTO> destinationInfoDTOList = (ArrayList<DestinationInfoDTO>)session.get("destinationInfoDTOList");
		for(int i=0;i<purchaseHistoryInfoDTOList.size();i++){
			purchaseHistoryInfoDTOList.get(i).setDestinationId(destinationInfoDTOList.get(0).getId());
		}

		PurchaseHistoryInfoDAO purchaseHistoryInfoDAO = new PurchaseHistoryInfoDAO();
		int count = 0;
		for(int i=0;i<purchaseHistoryInfoDTOList.size();i++){
			count += purchaseHistoryInfoDAO.regist(
					String.valueOf(session.get("loginId")),
					purchaseHistoryInfoDTOList.get(i).getProductId(),
					purchaseHistoryInfoDTOList.get(i).getProductCount(),
					purchaseHistoryInfoDTOList.get(i).getDestinationId(),
					purchaseHistoryInfoDTOList.get(i).getSubtotal()
					);
		}
		if(count > 0){
			CartInfoDAO cartInfoDAO = new CartInfoDAO();
			count = cartInfoDAO.deleteAll(String.valueOf(session.get("loginId")));
			if(count > 0){
				List<CartInfoDTO> cartInfoDTOList = new ArrayList<CartInfoDTO>();
				cartInfoDTOList = cartInfoDAO.getCartInfoDTOList(String.valueOf(session.get("loginId")));
				Iterator<CartInfoDTO> iterator = cartInfoDTOList.iterator();
				if(!(iterator.hasNext())){
					cartInfoDTOList = null;
				}
				session.put("cartInfoDTOList", cartInfoDTOList);

				int totalPrice = Integer.parseInt(String.valueOf(cartInfoDAO.getTotalPrice(String.valueOf(session.get("loginId")))));
				session.put("totalPrice", totalPrice);
				result = SUCCESS;
			}
		}
		return result;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
