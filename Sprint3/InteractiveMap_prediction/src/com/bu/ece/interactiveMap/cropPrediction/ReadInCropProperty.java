package com.bu.ece.interactiveMap.cropPrediction;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.io.*;

import com.bu.ece.interactiveMap.connections.DBUtils;

public class ReadInCropProperty {
	public ReadInCropProperty() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Statement stmt = null;
	public ResultSet rs = null;
	public ArrayList<CropPropertyBean> CropProperty = new ArrayList();
	final static String query = "SELECT * FROM CropProperty";
public ArrayList<CropPropertyBean> getCropProperty(){
	try {
		Connection connections = DBUtils.getConnection();
		stmt =  connections.createStatement();
		rs=stmt.executeQuery(query); 
		while(rs.next()){
			CropPropertyBean myCropPropertyBean = new CropPropertyBean();
			myCropPropertyBean.setCropName(rs.getString(1));
			myCropPropertyBean.setCa_min(rs.getDouble(2));
			myCropPropertyBean.setCa_max(rs.getDouble(3));
			myCropPropertyBean.setP_min(rs.getDouble(4));
			myCropPropertyBean.setP_max(rs.getDouble(5));
			myCropPropertyBean.setPH_min(rs.getDouble(6));
			myCropPropertyBean.setPH_max(rs.getDouble(7));
			myCropPropertyBean.setSand_min(rs.getDouble(8));
			myCropPropertyBean.setSand_max(rs.getDouble(9));
			myCropPropertyBean.setSOC_min(rs.getDouble(10));
			myCropPropertyBean.setSOC_max(rs.getDouble(11));
			myCropPropertyBean.setAl_min(rs.getDouble(12));
			myCropPropertyBean.setAl_max(rs.getDouble(13));
			myCropPropertyBean.setMg_max(rs.getDouble(14));
			myCropPropertyBean.setMg_max(rs.getDouble(15)); 
			CropProperty.add(myCropPropertyBean);				
				}
				 
	} catch (Exception e) {
		e.printStackTrace();
	}
	 return CropProperty;
	}

}
