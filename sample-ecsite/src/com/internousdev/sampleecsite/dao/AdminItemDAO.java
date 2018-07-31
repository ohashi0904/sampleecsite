package com.internousdev.sampleecsite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.internousdev.sampleecsite.dto.ProductInfoDTO;
import com.internousdev.sampleecsite.util.DBConnector;

public class AdminItemDAO {
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

	public int update(int id, String productId, String productName, String productNameKana, String productDescription,
				String categoryId, int price, String imageFilePath, String imageFileName, String releaseDate,
				String releaseCompany, int status, Date updateDate){
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();

		String sql = "UPDATE product_info SET product_name=?, product_name_kana=?, product_description=?,"
				+ "category_id=?, price=?, image_file_path=?, image_file_name=?, release_date=?,"
				+ "release_company=?, status=?, regist_date=?,update_date=now() WHERE id=?";

		int result = 0;

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, productId);
			preparedStatement.setString(2, productName);
			preparedStatement.setString(3, productNameKana);
			preparedStatement.setString(4, productDescription);
			preparedStatement.setString(5, categoryId);
			preparedStatement.setInt(6, price);
			preparedStatement.setString(7, imageFilePath);
			preparedStatement.setString(8, imageFileName);
			preparedStatement.setString(9, releaseDate);
			preparedStatement.setString(10, releaseCompany);
			preparedStatement.setInt(11, status);
			preparedStatement.setInt(12, id);

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
		return result;
	}

	public int deleteProduct(int id){
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();

		String sql = "DELETE FROM product_info WHERE id=?";

		int result = 0;

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);

			System.out.println(preparedStatement.toString());
			result = preparedStatement.executeUpdate();
			System.out.println(result + "削除");
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

	public int invalidProduct(int id) {

		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();

		String sql = "UPDATE product_info SET status = 1 WHERE id = ?";

		int result = 0;

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);

			System.out.println(preparedStatement.toString());
			result = preparedStatement.executeUpdate();
			System.out.println(result + "無効");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public int deleteCart(int productId){
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		int count = 0;
		String sql = "DELETE FROM cart_info WHERE product_id=?";

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, productId);
			count = preparedStatement.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		try{
			connection.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return count;
	}
}
