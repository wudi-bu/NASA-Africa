package com.bu.ece.interactiveMap.servlets;
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
	Statement stmt = null;
	ResultSet rs = null;
	ArrayList<CropPropertyBean> CropProperty = new ArrayList();
public ArrayList<CropPropertyBean> getCropProperty(){
	try {
		Connection connections = DBUtils.getConnection();
		stmt =  connections.createStatement();
		String query = "SELECT * FROM CropProperty";
		rs=stmt.executeQuery(query); 
		while(rs.next()){
			CropPropertyBean myCropPropertyBean = new CropPropertyBean();
			myCropPropertyBean.CropName = rs.getString(1);
			myCropPropertyBean.Ca_min = rs.getDouble(2);
			myCropPropertyBean.Ca_max = rs.getDouble(3);
			myCropPropertyBean.P_min = rs.getDouble(4);
			myCropPropertyBean.P_max = rs.getDouble(5);
			myCropPropertyBean.PH_min = rs.getDouble(6);
			myCropPropertyBean.PH_max = rs.getDouble(7);
			myCropPropertyBean.Sand_min = rs.getDouble(8);
			myCropPropertyBean.Sand_max = rs.getDouble(9);
			myCropPropertyBean.SOC_min = rs.getDouble(10);
			myCropPropertyBean.SOC_max = rs.getDouble(11);
			myCropPropertyBean.Al_min = rs.getDouble(12);
			myCropPropertyBean.Al_max = rs.getDouble(13);
			myCropPropertyBean.Mg_min = rs.getDouble(14);
			myCropPropertyBean.Mg_max = rs.getDouble(15);
			CropProperty.add(myCropPropertyBean);				
				}
				 
	} catch (Exception e) {
		e.printStackTrace();
	}
	 return CropProperty;
	}

}
