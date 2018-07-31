package com.internousdev.sampleecsite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.internousdev.sampleecsite.dto.ProductInfoDTO;
import com.internousdev.sampleecsite.util.DBConnector;

public class ProductInfoDAO {

	public List<ProductInfoDTO> getProductInfoList(){
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		List<ProductInfoDTO> productInfoDTOList = new ArrayList<ProductInfoDTO>();
		String sql = "select * from product_info";

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
//			while(rs.next()){
//				ProductInfoDTO productInfoDTO = new ProductInfoDTO();
//				productInfoDTO.setId(rs.getInt("id"));
//				productInfoDTO.setProductId(rs.getInt("product_id"));
//				productInfoDTO.setProductName(rs.getString("product_name"));
//				productInfoDTO.setProductNameKana(rs.getString("product_name_kana"));
//				productInfoDTO.setProductDescription(rs.getString("product_description"));
//				productInfoDTO.setCategoryId(rs.getInt("category_id"));
//				productInfoDTO.setPrice(rs.getInt("price"));
//				productInfoDTO.setImageFilePath(rs.getString("image_file_path"));
//				productInfoDTO.setImageFileName(rs.getString("image_file_name"));
//				productInfoDTO.setReleaseDate(rs.getDate("release_date"));
//				productInfoDTO.setReleaseCompany(rs.getString("release_company"));
//				productInfoDTO.setStatus(rs.getInt("status"));
//				productInfoDTO.setUpdateDate(rs.getDate("regist_date"));
//				productInfoDTO.setUpdateDate(rs.getDate("update_date"));
//				productInfoDTOList.add(productInfoDTO);
//			}
			while(rs.next()){
				ProductInfoDTO productInfoDTO = toDTO(rs);
				productInfoDTOList.add(productInfoDTO);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		try{
			connection.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return productInfoDTOList;
	}

	public ProductInfoDTO getProductInfo(int productId){
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		ProductInfoDTO productInfoDTO = new ProductInfoDTO();
		String sql = "select * from product_info where product_id=?";
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, productId);
			ResultSet rs = preparedStatement.executeQuery();
//			while(rs.next()){
//				productInfoDTO.setId(rs.getInt("id"));
//				productInfoDTO.setProductId(rs.getInt("product_id"));
//				productInfoDTO.setProductName(rs.getString("product_name"));
//				productInfoDTO.setProductNameKana(rs.getString("product_name_kana"));
//				productInfoDTO.setProductDescription(rs.getString("product_description"));
//				productInfoDTO.setCategoryId(rs.getInt("category_id"));
//				productInfoDTO.setPrice(rs.getInt("price"));
//				productInfoDTO.setImageFilePath(rs.getString("image_file_path"));
//				productInfoDTO.setImageFileName(rs.getString("image_file_name"));
//				productInfoDTO.setReleaseDate(rs.getDate("release_date"));
//				productInfoDTO.setReleaseCompany(rs.getString("release_company"));
//				productInfoDTO.setStatus(rs.getInt("status"));
//				productInfoDTO.setUpdateDate(rs.getDate("regist_date"));
//				productInfoDTO.setUpdateDate(rs.getDate("update_date"));
			while(rs.next()){
				productInfoDTO = toDTO(rs);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		try{
			connection.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return productInfoDTO;
	}

	public List<ProductInfoDTO> getProductInfoListByCategoryId(int categoryId, int productId, int limitOffset,
			int limitRowCount){
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		List<ProductInfoDTO> productInfoDTOList = new ArrayList<ProductInfoDTO>();
		String sql = "select * from product_info where category_id=? and product_id not in(?) order by rand() limit ?,?";
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, categoryId);
			preparedStatement.setInt(2, productId);
			preparedStatement.setInt(3, limitOffset);
			preparedStatement.setInt(4, limitRowCount);
			ResultSet rs = preparedStatement.executeQuery();
//			while(rs.next()){
//				ProductInfoDTO productInfoDTO = new ProductInfoDTO();
//				productInfoDTO.setId(rs.getInt("id"));
//				productInfoDTO.setProductId(rs.getInt("product_id"));
//				productInfoDTO.setProductName(rs.getString("product_name"));
//				productInfoDTO.setProductNameKana(rs.getString("product_name_kana"));
//				productInfoDTO.setProductDescription(rs.getString("product_description"));
//				productInfoDTO.setCategoryId(rs.getInt("category_id"));
//				productInfoDTO.setPrice(rs.getInt("price"));
//				productInfoDTO.setImageFilePath(rs.getString("image_file_path"));
//				productInfoDTO.setImageFileName(rs.getString("image_file_name"));
//				productInfoDTO.setReleaseDate(rs.getDate("release_date"));
//				productInfoDTO.setReleaseCompany(rs.getString("release_company"));
//				productInfoDTO.setStatus(rs.getInt("status"));
//				productInfoDTO.setUpdateDate(rs.getDate("regist_date"));
//				productInfoDTO.setUpdateDate(rs.getDate("update_date"));
//				productInfoDTOList.add(productInfoDTO);
			while(rs.next()){
				ProductInfoDTO productInfoDTO = toDTO(rs);
				productInfoDTOList.add(productInfoDTO);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		try{
			connection.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return productInfoDTOList;
	}

	public List<ProductInfoDTO> getProductInfoListAll(String[] keywordsList) {
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		List<ProductInfoDTO> productInfoDTOList = new ArrayList<ProductInfoDTO>();
		String sql = "select * from product_info where";
		boolean initializeFlag = true;
		for (String keyword : keywordsList) {
			if (initializeFlag) {
				sql += " (product_name like '%" + keyword + "%' or product_name_kana like '%" + keyword + "%')";
				initializeFlag = false;
			} else {
				sql += " and (product_name like '%" + keyword + "%' or product_name_kana like '%" + keyword + "%')";
			}
		}
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
//			while (rs.next()) {
//				ProductInfoDTO productInfoDTO = new ProductInfoDTO();
//				productInfoDTO.setId(rs.getInt("id"));
//				productInfoDTO.setProductId(rs.getInt("product_id"));
//				productInfoDTO.setProductName(rs.getString("product_name"));
//				productInfoDTO.setProductNameKana(rs.getString("product_name_kana"));
//				productInfoDTO.setProductDescription(rs.getString("product_description"));
//				productInfoDTO.setCategoryId(rs.getInt("category_id"));
//				productInfoDTO.setPrice(rs.getInt("price"));
//				productInfoDTO.setImageFilePath(rs.getString("image_file_path"));
//				productInfoDTO.setImageFileName(rs.getString("image_file_name"));
//				productInfoDTO.setReleaseDate(rs.getDate("release_date"));
//				productInfoDTO.setReleaseCompany(rs.getString("release_company"));
//				productInfoDTO.setStatus(rs.getInt("status"));
//				productInfoDTO.setUpdateDate(rs.getDate("regist_date"));
//				productInfoDTO.setUpdateDate(rs.getDate("update_date"));
//				productInfoDtoList.add(productInfoDTO);
			while(rs.next()){
				ProductInfoDTO productInfoDTO = toDTO(rs);
				productInfoDTOList.add(productInfoDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productInfoDTOList;
	}

	public List<ProductInfoDTO> getProductInfoListByKeywords(String[] keywordsList, String categoryId){
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		List<ProductInfoDTO> productInfoDTOList = new ArrayList<ProductInfoDTO>();
		String sql = "select * from product_info where";
		boolean initializeFlag = true;
		for (String keyword : keywordsList){
			if (initializeFlag){
				sql += " category_id=" + categoryId + "and (product_name like '%" + keyword + "%' or product_name_kana like '%" + keyword + "%')";
				initializeFlag = false;
			}else{
				sql += " and (product_name like '%" + keyword + "%' or product_name_kana like '%" + keyword + "%' )";
			}
		}
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
//			while(rs.next()){
//				ProductInfoDTO productInfoDTO = new ProductInfoDTO();
//				productInfoDTO.setId(rs.getInt("id"));
//				productInfoDTO.setProductId(rs.getInt("product_id"));
//				productInfoDTO.setProductName(rs.getString("product_name"));
//				productInfoDTO.setProductNameKana(rs.getString("product_name_kana"));
//				productInfoDTO.setProductDescription(rs.getString("product_description"));
//				productInfoDTO.setCategoryId(rs.getInt("category_id"));
//				productInfoDTO.setPrice(rs.getInt("price"));
//				productInfoDTO.setImageFilePath(rs.getString("image_file_path"));
//				productInfoDTO.setImageFileName(rs.getString("image_file_name"));
//				productInfoDTO.setReleaseDate(rs.getDate("release_date"));
//				productInfoDTO.setReleaseCompany(rs.getString("release_company"));
//				productInfoDTO.setStatus(rs.getInt("status"));
//				productInfoDTO.setUpdateDate(rs.getDate("regist_date"));
//				productInfoDTO.setUpdateDate(rs.getDate("update_date"));
//				productInfoDTOList.add(productInfoDTO);
			while(rs.next()){
				ProductInfoDTO productInfoDTO = toDTO(rs);
				productInfoDTOList.add(productInfoDTO);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		try{
			connection.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return productInfoDTOList;
	}

	private ProductInfoDTO toDTO(ResultSet resultSet) throws SQLException{
		ProductInfoDTO productInfoDTO = new ProductInfoDTO();
		productInfoDTO.setId(resultSet.getInt("id"));
		productInfoDTO.setProductId(resultSet.getInt("product_id"));
		productInfoDTO.setProductName(resultSet.getString("product_name"));
		productInfoDTO.setProductNameKana(resultSet.getString("product_name_kana"));
		productInfoDTO.setProductDescription(resultSet.getString("product_description"));
		productInfoDTO.setCategoryId(resultSet.getInt("category_id"));
		productInfoDTO.setPrice(resultSet.getInt("price"));
		productInfoDTO.setImageFilePath(resultSet.getString("image_file_path"));
		productInfoDTO.setImageFileName(resultSet.getString("image_file_name"));
		productInfoDTO.setReleaseDate(resultSet.getDate("release_date"));
		productInfoDTO.setReleaseCompany(resultSet.getString("release_company"));
		productInfoDTO.setStatus(resultSet.getInt("status"));
		productInfoDTO.setUpdateDate(resultSet.getDate("regist_date"));
		productInfoDTO.setUpdateDate(resultSet.getDate("update_date"));
		return productInfoDTO;
	}

	public ProductInfoDTO selectById(int id){
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();

		String sql="SELECT * FROM product_info WHERE id=?";
		ProductInfoDTO result = null;

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);

			ResultSet rs = preparedStatement.executeQuery();

			if(rs.next()){
				result = toDTO(rs);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		try{
			connection.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}

	public boolean insert(int productId, String productName, String productNameKana, String productDescription,
					int categoryId, int price, String imageFilePath, String imageFileName, Date releaseDate,
					String releaseCompany, int status, Date registDate, Date updateDate){
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

		String sql = "INSERT INTO product_info(product_id,product_name,product_name_kana,product_description,category_id,price,image_file_path,image_file_name,release_date,release_company,status,regist_date,update_date) VALUES(?,?,?,?,?,?,?,?,?,?,?,now(),now())";

		int result = 0;

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, productId);
			preparedStatement.setString(2, productName);
			preparedStatement.setString(3, productNameKana);
			preparedStatement.setString(4, productDescription);
			preparedStatement.setInt(5, categoryId);
			preparedStatement.setInt(6, price);
			preparedStatement.setString(7, imageFilePath);
			preparedStatement.setString(8, imageFileName);
			preparedStatement.setString(9, simpleDateFormat.format(releaseDate));
			preparedStatement.setString(10, releaseCompany);
			preparedStatement.setInt(11, status);

			result = preparedStatement.executeUpdate();
			System.out.println(result + "件追加");
			System.out.println(preparedStatement.toString());
		}catch(SQLException e){
			e.printStackTrace();
		}
		try{
			connection.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result > 0;
	}

	public boolean delete(int id){
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();

		String sql = "DELETE FROM product_info WHERE id=?";

		int result = 0;
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);

			preparedStatement.executeQuery();
		}catch(SQLException e){
			e.printStackTrace();
		}
		try{
			connection.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result > 0;
	}
}
