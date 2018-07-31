package com.internousdev.sampleecsite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.internousdev.sampleecsite.dto.ProductInfoDTO;
import com.internousdev.sampleecsite.util.DBConnector;

public class ProductUpdateDAO {
	public ProductInfoDTO selectById(int id){
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();

		String sql = "SELECT * FROM product_info WHERE id=? AND status=0";

		ProductInfoDTO result = null;

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.next()){
				result = new ProductInfoDTO();
				result.setId(resultSet.getInt("id"));
				result.setProductId(resultSet.getInt("product_id"));
				result.setProductName(resultSet.getString("product_name"));
				result.setProductNameKana(resultSet.getString("product_name_kana"));
				result.setProductDescription(resultSet.getString("product_description"));
				result.setCategoryId(resultSet.getInt("category_id"));
				result.setPrice(resultSet.getInt("price"));
				result.setImageFilePath(resultSet.getString("image_file_path"));
				result.setImageFileName(resultSet.getString("image_file_name"));
				result.setReleaseDate(resultSet.getDate("release_date"));
				result.setReleaseCompany(resultSet.getString("release_company"));
				result.setStatus(resultSet.getInt("status"));
				result.setRegistDate(resultSet.getDate("regist_date"));
				result.setUpdateDate(resultSet.getDate("update_date"));
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

	public boolean update(int productId, String productName, String productNameKana, String productDescription,
			int categoryId, int price, String imageFilePath, String imageFileName, Date releaseDate,
			String releaseCompany, int status,  Date updateDate,int id){
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy//MM/dd HH:mm:ss");

		String sql = "UPDATE INTO product_info(product_id,product_name,product_name_kana,product_description,category_id,price,image_file_path,image_file_name,release_date,release_company,status,update_date, WHERE id) VALUES(?,?,?,?,?,?,?,?,?,?,?,noe(),?)";

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
			preparedStatement.setInt(12, id);

			result = preparedStatement.executeUpdate();
			result = preparedStatement.executeUpdate();
			System.out.println(result + "件更新");
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
}
