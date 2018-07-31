package com.internousdev.sampleecsite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.internousdev.sampleecsite.util.DBConnector;

public class AddProductDAO {

	public boolean productIdDuplication(int productId){
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();

		String sql = "SELECT * FROM product_info WHERE product_Id=?";
		boolean result = false;

		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, productId);
			ResultSet resultSet = ps.executeQuery();

			if(resultSet.next()){
				result = true;
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

	public boolean productnameDuplication(String productName){
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();

		String sql = "SELECT * FROM product_info WHERE product_name=?";
		boolean result = false;

		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, productName);
			ResultSet resultSet = ps.executeQuery();

			if(resultSet.next()){
				result = true;
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

	public boolean productnamekanaDuplication(String productNameKana){
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();

		String sql = "SELECT * FROM product_info WHERE product_name_kana=?";
		boolean result = false;

		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, productNameKana);
			ResultSet resultSet = ps.executeQuery();

			if(resultSet.next()){
				result = true;
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
}
