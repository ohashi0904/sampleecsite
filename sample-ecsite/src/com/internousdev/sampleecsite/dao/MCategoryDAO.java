package com.internousdev.sampleecsite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.internousdev.sampleecsite.dto.MCategoryDTO;
import com.internousdev.sampleecsite.util.DBConnector;

public class MCategoryDAO {
	public List<MCategoryDTO> getMCategoryList(){
		DBConnector dbconnector = new DBConnector();
		Connection connection = dbconnector.getConnection();
		List<MCategoryDTO> mCategoryDTOList = new ArrayList<MCategoryDTO>();
		String sql = "select * from m_category";
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs =preparedStatement.executeQuery();
			while(rs.next()){
				MCategoryDTO mCategoryDTO = new MCategoryDTO();
				mCategoryDTO.setId(rs.getInt("id"));
				mCategoryDTO.setCategoryId(rs.getInt("category_id"));
				mCategoryDTO.setCategoryName(rs.getString("category_name"));
				mCategoryDTO.setCategoryDescription(rs.getString("category_description"));
				mCategoryDTO.setInsertDate(rs.getDate("insert_date"));
				mCategoryDTO.setUpdateDate(rs.getDate("update_date"));
				mCategoryDTOList.add(mCategoryDTO);
			}
			Iterator<MCategoryDTO> iterator = mCategoryDTOList.iterator();
			if(!(iterator.hasNext())){
				mCategoryDTOList = null;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		try{
			connection.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return mCategoryDTOList;
	}

	public List<MCategoryDTO> getAdminMCategoryList(){

		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();

		List<MCategoryDTO> mCategoryDTOList = new ArrayList<MCategoryDTO>();

		String sql = "SELECT * DROM m_category WHERE id NOT IN (1)";

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next()){
				MCategoryDTO mCategoryDTO = new MCategoryDTO();
				mCategoryDTO.setId(rs.getInt("id"));
				mCategoryDTO.setCategoryId(rs.getInt("category_id"));
				mCategoryDTO.setCategoryName(rs.getString("category_name"));
				mCategoryDTO.setCategoryDescription(rs.getString("category_description"));
				mCategoryDTO.setInsertDate(rs.getDate("insert_date"));
				mCategoryDTO.setUpdateDate(rs.getDate("update_date"));
				mCategoryDTOList.add(mCategoryDTO);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		try{
			connection.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		if(mCategoryDTOList.size() == 0){
			mCategoryDTOList = null;
		}
		return mCategoryDTOList;
	}
}
