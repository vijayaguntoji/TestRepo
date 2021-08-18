package com.neudesic.utilities;

import java.io.FileNotFoundException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBConnection {

	private static Connection connection = null;
	private static PreparedStatement preparedStmt = null;
	private static ResultSet resultSet = null;
	private static ResultSetMetaData resultSetMetaData = null;

	/*********************************************************************************************
	 * Constructor - Initiates the DB connection
	 * 
	 * @param Driver
	 * @param Host
	 * @param UserName
	 * @param Password
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws SQLException
	 *********************************************************************************************/

	public DBConnection(String Driver, String Host, String UserName, String Password)
			throws FileNotFoundException, IOException {

		try {
			Class.forName(Driver);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Please check the path of DB2 driver: " + e.getMessage());
		}

		System.out.println("DB2 driver was loaded successfully!");

		try {

			connection = DriverManager.getConnection(Host, UserName, Password);

			if (connection != null) {
				System.out.println("Connected to the database \"" + connection.getSchema() + "\" successfully!");
			} else {
				throw new RuntimeException("Database connection failed. Please try again with correct details.");
			}

		} catch (SQLException e) {
			throw new RuntimeException("Database connection failed. \nReason: " + e.getMessage());
		}
	}

	/*********************************************************************************************
	 * Executes the query that is passed to this method
	 * 
	 * @param query
	 * @throws SQLException
	 *********************************************************************************************/

	private static void runquery(String query) {

		try {
			preparedStmt = connection.prepareStatement(query);
			resultSet = preparedStmt.executeQuery();
			resultSetMetaData = resultSet.getMetaData();
		} catch (Exception e) {
			throw new RuntimeException("A problem occured with the given query. \nReason: " + e.getMessage());
		}
	}

	/***********************************************************************************************
	 * Fetching a sigle DB value based on the given query and column index
	 * 
	 * @param query
	 * @param columnNum
	 * @return It return a String
	 ***********************************************************************************************/

	public static String getValueWithColIndex(String query, int columnNum) {

		runquery(query);
		boolean records = false;
		String value = null;

		try {
			if (resultSet != null) {

				while (resultSet.next()) {

					records = true;
					value = resultSet.getString(columnNum);
				}
			}
			if (records == false) {
				throw new RuntimeException("No records found with the given query - EMPTY RESULTS");
			}
			return value;
		} catch (Exception e) {
			throw new RuntimeException("Unable to get the results. \nReason: " + e.getMessage());
		}

	}

	/***********************************************************************************************
	 * Fetching a sigle DB value based on the given query and column name
	 * 
	 * @param query
	 * @param columnNum
	 * @return It return a String
	 ***********************************************************************************************/

	public static String getValueWithColName(String query, String columnName) {

		runquery(query);
		boolean records = false;
		String value = null;

		try {
			if (resultSet != null) {

				while (resultSet.next()) {

					records = true;
					value = resultSet.getString(columnName);

				}
			}
			if (records == false) {
				throw new RuntimeException("No records found with the given query.");
			}
			return value;
		} catch (Exception e) {
			throw new RuntimeException("Unable to get the results. \nReason: " + e.getMessage());
		}

	}

	/***********************************************************************************************
	 * Fetching multiple DB values based on the given query and column index
	 * 
	 * @param query
	 * @param column
	 * @return It returna a list of DB values
	 ***********************************************************************************************/
	public static List<String> getMultipleValuesWithColIndex(String query, List<String> column) {

		List<String> dbData = new ArrayList<>();

		runquery(query);
		boolean records = false;

		try {

			if (resultSet != null) {

				while (resultSet.next()) {

					records = true;

					for (int i = 0; i < column.size(); i++) {

						dbData.add(resultSet.getString(Integer.parseInt(column.get(i).toString())).trim());

						System.out.println(dbData.get(i).toString());
					}
				}
			}

		} catch (Exception e) {
			throw new RuntimeException("Unable to get the results. \nReason: " + e.getMessage());
		}

		if (records == false) {

			throw new RuntimeException("No records found with the given query.");

		}
		return dbData;
	}

	/***********************************************************************************************
	 * Fetching multiple DB values based on the given query and column name
	 * 
	 * @param query
	 * @param column
	 * @return It returna a list of DB values
	 ***********************************************************************************************/
	public static List<String> getMultipleValuesWithColName(String query, List<String> column) {

		List<String> dbData = new ArrayList<>();

		runquery(query);
		boolean records = false;

		try {

			if (resultSet != null) {

				while (resultSet.next()) {

					records = true;

					for (int i = 0; i < column.size(); i++) {

						String colName = column.get(i).toString();

						dbData.add(resultSet.getString(colName).trim());

						System.out.println(dbData.get(i).toString());
					}
				}
			}

		} catch (Exception e) {
			throw new RuntimeException("Unable to get the results. \nReason: " + e.getMessage());
		}

		if (records == false) {

			throw new RuntimeException("No records found with the given query.");

		}
		return dbData;
	}
	
	/***********************************************************************************************
	 * 
	 * @param query
	 * @param colIndex
	 * @param colName
	 * @param colType
	 ***********************************************************************************************/
	public void verifyTable(String query, List<Integer> colIndex, List<String> colName, List<String> colType) {
		runquery(query);
		for(int i=1; i<=colIndex.size(); i++) {
			
			try {
				System.out.println(resultSetMetaData.getColumnTypeName(i));
				if(resultSetMetaData.getColumnName(i).equals(colName.get(i-1).toString()) && 
						resultSetMetaData.getColumnTypeName(i).equals(colType.get(i-1).toString())) {
					System.out.println("Table verification is done.");			
				} else {
					System.out.println("Verification Failed.");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}