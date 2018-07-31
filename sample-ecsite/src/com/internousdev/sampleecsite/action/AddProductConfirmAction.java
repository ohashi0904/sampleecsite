package com.internousdev.sampleecsite.action;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.sampleecsite.dao.AddProductDAO;
import com.internousdev.sampleecsite.dto.ProductInfoDTO;
import com.internousdev.sampleecsite.util.InputChecker;
import com.internousdev.sampleecsite.util.SearchConditionLoader;
import com.opensymphony.xwork2.ActionSupport;

public class AddProductConfirmAction extends ActionSupport implements SessionAware{
	private int id;
	private String productId;
	private String productName;
	private String productNameKana;
	private String productDescription;
	private String categoryId;
	private String price;
	private String imageFilePath;
	private String imageFileName;
	private String releaseDate;
	private String releaseCompany;
	private Map<String, Object> session;

	private File productImage;
	private String productImageContentType;
	private String productImageFileName;

	private List<String> productIdErrorMessageList = new ArrayList<String>();
	private List<String> productNameErrorMessageList = new ArrayList<String>();
	private List<String> productNameKanaErrorMessageList = new ArrayList<String>();
	private List<String> productDescriptionErrorMessageList = new ArrayList<String>();
	private List<String> priceErrorMessageList = new ArrayList<String>();
	private List<String> releaseDateErrorMessageList = new ArrayList<String>();
	private List<String> releaseCompanyErrorMessageList = new ArrayList<String>();
	private List<String> productImageErrorMessageList = new ArrayList<String>();

	public String execute() throws Exception{
		session.remove("productIdErrorMessageList");
		session.remove("productNameErrorMessageList");
		session.remove("productNameKanaErrorMessageList");
		session.remove("productDescriptionErrorMessageList");
		session.remove("priceErrorMessageList");
		session.remove("releaseDateErrorMessageList");
		session.remove("releaseCompanyErrorMessageList");
		session.remove("productImageErrorMessageList");

		String result = ERROR;

		InputChecker inputChecker = new InputChecker();
		productIdErrorMessageList = inputChecker.doCheck("商品ID", productId, 1, 9, true, false, false, true, false, false, false, false, false);
		productNameErrorMessageList = inputChecker.doCheck("商品名", productName, 1, 100, true, true, true, true, false, true, false, false, false);
		productNameKanaErrorMessageList = inputChecker.doCheck("商品名かな", productNameKana, 1, 100, false, false, true, true, false, false, false, false, false);
		productDescriptionErrorMessageList = inputChecker.doCheck("商品詳細", productDescription, 1, 500, true, true, true, true, true, true, true, false, true);
		priceErrorMessageList = inputChecker.doCheck("価格", price, 1, 5, false, false, false, true, false, false, false, false, false);
		releaseDateErrorMessageList = checkDate("発売年月", releaseDate);
		releaseCompanyErrorMessageList = inputChecker.doCheck("発売会社", releaseCompany, 1, 100, true, true, true, false, false, true, false, false, false);

		if(productImage == null){
			productImageErrorMessageList.add("画像ファイルを選択してください");
		}

		ProductInfoDTO dto = new ProductInfoDTO();
		dto.setId(id);
		dto.setProductId(parseInt(productId));
		dto.setProductName(productName);
		dto.setProductNameKana(productNameKana);
		dto.setProductDescription(productDescription);
		dto.setCategoryId(parseInt(categoryId));
		dto.setPrice(parseInt(price));
		dto.setImageFilePath("./images");
		dto.setImageFileName(productImageFileName);
		dto.setReleaseDate(parseDate(releaseDate));
		dto.setReleaseCompany(releaseCompany);

		session.put("addProductDTO", dto);
		session.put("productImage", productImage);
		session.put("productImageContentType", productImageContentType);
		session.put("productImageFileName", productImageFileName);

		if(productIdErrorMessageList.size() == 0 && productNameErrorMessageList.size() == 0
				&& productNameKanaErrorMessageList.size() == 0 && productDescriptionErrorMessageList.size() == 0
				&& priceErrorMessageList.size() == 0 && releaseDateErrorMessageList.size() == 0
				&& releaseCompanyErrorMessageList.size() == 0 && productImageErrorMessageList.size() == 0){

			result = SUCCESS;

			System.out.println("imageFilePath" + imageFilePath);
			System.out.println("imageFileName" + imageFileName);

			if(productImageFileName != null){
				String filePath = ServletActionContext.getServletContext().getRealPath("/").concat("images");
				System.out.println("Image Location:" + filePath);
				File fileToCreate = new File(filePath, productImageFileName);

				try{
					FileUtils.copyFile(productImage, fileToCreate);
					session.put("image_file_name", productImageFileName);
					session.put("image_file_path", "images/");
					session.put("image_flg", productImageFileName);
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}else{
			System.out.println(productIdErrorMessageList.toString());
			session.put("productIdErrorMessageList", productIdErrorMessageList);
			session.put("productNameErrorMessageList", productNameErrorMessageList);
			session.put("productNameKanaErrorMessageList", productNameKanaErrorMessageList);
			session.put("productDescriptionErrorMessageList", productDescriptionErrorMessageList);
			session.put("priceErrorMessageList", priceErrorMessageList);
			session.put("releaseDateErrorMessageList", releaseDateErrorMessageList);
			session.put("releaseCompanyErrorMessageList", releaseCompanyErrorMessageList);
			session.put("productImageErrorMessageList", productImageErrorMessageList);
		}

		AddProductDAO addProductDAO = new AddProductDAO();

		if(productIdErrorMessageList.isEmpty()){
			if(Integer.parseInt(productId) >= 1000000000){
				productIdErrorMessageList.add("IDは9桁以下に設定してください");
				session.put("productIdErrorMessageList", productIdErrorMessageList);
			}

			if(Integer.parseInt(productId) <= 0){
				productIdErrorMessageList.add("IDは1以上に設定してください");
				session.put("productIdErrorMessageList", productIdErrorMessageList);
				result = ERROR;
			}
		}

		if(priceErrorMessageList.isEmpty()){
			if(Integer.parseInt(price) > 50000){
				priceErrorMessageList.add("金額は50000円以下に設定してください");
				session.put("priceErrirMessageList", priceErrorMessageList);
				System.out.println(session.get("priceErrorMessageList"));
			}

			if(Integer.parseInt(price) <= 0){
				priceErrorMessageList.add("金額は1円以上に設定してください");
				session.put("priceErrorMessageList", priceErrorMessageList);
				System.out.println(session.get("priceErrorMessageList"));
				result = ERROR;
			}
		}

		if(productIdErrorMessageList.isEmpty()){
			if(addProductDAO.productIdDuplication(Integer.parseInt(productId))){
				productIdErrorMessageList.add("すでに同IDの商品があります。別のIDをお試しください");
				session.put("productIdErrorMessageList", productIdErrorMessageList);
				result = ERROR;
			}
		}

		SearchConditionLoader loader = new SearchConditionLoader();
		loader.execute(session);

		System.out.println(productImage);
		System.out.println(productImageContentType);
		System.out.println(productImageFileName);

		System.out.println(productDescription);

		System.out.println(result);
		return result;

	}

	private int parseInt(String value){
		int result = 0;
		try{
			result = Integer.parseInt(value);
		}catch(NumberFormatException e){
			e.printStackTrace();
		}
		return result;
	}

	private Date parseDate(String value){
		Date result = null;
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			result = sdf.parse(value);
			return result;
		}catch(ParseException e){
			e.printStackTrace();
		}
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
			result = sdf.parse(value);
			return result;
		}catch(ParseException e){
			e.printStackTrace();
		}
		return result;
	}

	private List<String> checkDate(String propertyName, String value){
		List<String> errorList = new ArrayList<String>();

		if(StringUtils.isEmpty(value)){
			errorList.add(propertyName + "を入力してください。");
		}else{
			try{
				DateUtils.parseDateStrictly(value, new String[] { "yyyy-MM-dd","yyyy年MM月dd日"});
			}catch(ParseException e){
				e.printStackTrace();
				errorList.add("yyyy-MM-dd または yyyy年MM月dd日 で入力してください");
			}
		}
		return errorList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductNameKana() {
		return productNameKana;
	}

	public void setProductNameKana(String productNameKana) {
		this.productNameKana = productNameKana;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getImageFilePath() {
		return imageFilePath;
	}

	public void setImageFilePath(String imageFilePath) {
		this.imageFilePath = imageFilePath;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getReleaseCompany() {
		return releaseCompany;
	}

	public void setReleaseCompany(String releaseCompany) {
		this.releaseCompany = releaseCompany;
	}

	public File getProductImage() {
		return productImage;
	}

	public void setProductImage(File productImage) {
		this.productImage = productImage;
	}

	public String getProductImageContentType() {
		return productImageContentType;
	}

	public void setProductImageContentType(String productImageContentType) {
		this.productImageContentType = productImageContentType;
	}

	public String getProductImageFileName() {
		return productImageFileName;
	}

	public void setProductImageFileName(String productImageFileName) {
		this.productImageFileName = productImageFileName;
	}

	public List<String> getProductIdErrorMessageList() {
		return productIdErrorMessageList;
	}

	public void setProductIdErrorMessageList(List<String> productIdErrorMessageList) {
		this.productIdErrorMessageList = productIdErrorMessageList;
	}

	public List<String> getProductNameErrorMessageList() {
		return productNameErrorMessageList;
	}

	public void setProductNameErrorMessageList(List<String> productNameErrorMessageList) {
		this.productNameErrorMessageList = productNameErrorMessageList;
	}

	public List<String> getProductNameKanaErrorMessageList() {
		return productNameKanaErrorMessageList;
	}

	public void setProductNameKanaErrorMessageList(List<String> productNameKanaErrorMessageList) {
		this.productNameKanaErrorMessageList = productNameKanaErrorMessageList;
	}

	public List<String> getProductDescriptionErrorMessageList() {
		return productDescriptionErrorMessageList;
	}

	public void setProductDescriptionErrorMessageList(List<String> productDescriptionErrorMessageList) {
		this.productDescriptionErrorMessageList = productDescriptionErrorMessageList;
	}

	public List<String> getPriceErrorMessageList() {
		return priceErrorMessageList;
	}

	public void setPriceErrorMessageList(List<String> priceErrorMessageList) {
		this.priceErrorMessageList = priceErrorMessageList;
	}

	public List<String> getReleaseDateErrorMessageList() {
		return releaseDateErrorMessageList;
	}

	public void setReleaseDateErrorMessageList(List<String> releaseDateErrorMessageList) {
		this.releaseDateErrorMessageList = releaseDateErrorMessageList;
	}

	public List<String> getReleaseCompanyErrorMessageList() {
		return releaseCompanyErrorMessageList;
	}

	public void setReleaseCompanyErrorMessageList(List<String> releaseCompanyErrorMessageList) {
		this.releaseCompanyErrorMessageList = releaseCompanyErrorMessageList;
	}

	public List<String> getProductImageErrorMessageList() {
		return productImageErrorMessageList;
	}

	public void setProductImageErrorMessageList(List<String> productImageErrorMessageList) {
		this.productImageErrorMessageList = productImageErrorMessageList;
	}

	@Override
	public void setSession(Map<String, Object> session){
		this.session = session;
	}
}
