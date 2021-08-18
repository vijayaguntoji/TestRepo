package com.neudesic.debug;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import com.neudesic.utilities.DBConnection;

public class SampleTest {
	
	public static void main(String[] args) throws IOException {
		
//		ExcelReader reader = new ExcelReader("C://Users//VinodReddy//Desktop//QA//TestData//SubmitAppData.xlsx", "SubmitApp");
		Properties prop = new Properties();
//		System.out.println(reader.getCellData("SubmitApp", "DOB", 5));
//		System.out.println(reader.getColumnCount("SubmitApp"));
//		System.out.println(reader.getRowCount("SubmitApp"));
		prop.load(new FileInputStream("connection.properties"));
		DBConnection connection = new DBConnection(prop.getProperty("Driver"), prop.getProperty("Host"), prop.getProperty("User"), prop.getProperty("Password"));
		
		String query = "SELECT WCUST, WPFNAME, WPLNAME, WPTIN, WPCPHON, WPEMAIL, WPADDR1, WPADDR2, WPCITY, WPSTATE, WPZIP, WPBDATE, WPACHABA, WPACHACCT, WPACHTYPE, WPCCCACCT FROM KWDB108.KW_APPL WHERE WPTIN = '376373467' AND WPCPHON = '9728558151' ORDER BY WCUST DESC LIMIT 1";
		List<String> list = new ArrayList<>();
		list.add("2");
		list.add("3");
		list.add("4");
		System.out.println(DBConnection.getMultipleValuesWithColIndex(query, list));
	}

}
