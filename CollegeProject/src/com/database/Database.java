package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.controller.Controller;
import com.dialog.Message;
import com.view.EmployeeView;

public class Database {
	
	private String username;
	private String password;

	private String url = "jdbc:mysql://localhost:3306/CollegeProject";
	private String dUserName = "root";
	private String dPassword = "Surakshya";

	private Connection con;
	private Statement statement;
	private PreparedStatement preparedStatement;
	private ResultSet result;

	public Database() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,dUserName,dPassword);
			statement = con.createStatement();
			result = statement.executeQuery("select * from admin");
			result.next();
			username = result.getString(2);
			password = result.getString(3);
			result.close();
			statement.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void crudEmployee(String type,EmployeeView e, Controller c,String[][] row, String compareName){
		try {
			con = DriverManager.getConnection(url,dUserName,dPassword);
			statement = con.createStatement();
			switch(type) {
			/*
			*Insert
			*/
			case "Insert":
				if(e.getNameTxt().equals("") || e.getAddressTxt().equals("") || e.getPhoneTxt().equals("") || 
					e.getEmailTxt().equals("") || e.getDobTxt().equals("") || e.getAccountNameTxt().equals("") || 
					e.getAccountNumberTxt().equals("") || e.getRateTxt().equals("") || e.getWorkingHoursTxt().equals("") ) {
					Message.globalInfo("Please fill all the information!");
				}else {
						int id = 202000000;
						result = statement.executeQuery("select * from employee");
						while(result.next()) {
							id++;
						}
						int execute = statement.executeUpdate("insert into employee values('"+id+"','"+e.getNameTxt()+"','"+e.getAddressTxt()+
								"','"+e.getPhoneTxt()+"','"+e.getEmailTxt()+"','"+e.getDobTxt()+
								"','"+e.getAccountNameTxt()+"','"+e.getAccountNumberTxt()+"','"+
								e.getRateTxt()+"','"+e.getWorkingHoursTxt()+"')");
						if(execute == 1) {
							Message.globalInfo("Registered Successfully!");
							e.setNameTxt("");
							e.setAddressTxt("");
							e.setPhoneTxt("");
							e.setEmailTxt("");
							e.setDobTxt("");
							e.setAccountNameTxt("");
							e.setAccountNumberTxt("");
							e.setRateTxt("");
							e.setWorkingHoursTxt("");
						}
						else
							Message.globalInfo("Registration failed!");
						result.close();
				}

				System.out.println("On Add");
				break;
			/*
			 * Search
			 */
			case "Search":
				result = statement.executeQuery("select * from employee");
				int i=0;
				/*Puttin ""*/
				for(int j=0;j<100;j++) {
					for(int k=0;k<10;k++) {
						row[j][k] = "";
					}
				}
				/**/
				while(result.next()) {
					if(compareName.equals("") || compareName.equals("Enter Employee's Name")) {
						for(int j=1;j<=10;j++) {
							row[i][j-1] = result.getString(j);
						}
						i++;
					}else {
						if(result.getString(2).equalsIgnoreCase(compareName)) {
							for(int j=1;j<=10;j++) {
								row[i][j-1] = result.getString(j);
							}
							i++;
						}
					}
				}
				result.close();
				System.out.println("On search");
				break;
			/*
			 *Update
			*/
			case "Update":
				preparedStatement = con.prepareStatement("update employee set Name = ?, Address = ?, Phone = ?, Email = ?,"
						+ "DOB = ?, AccountName = ?, AccountNumber = ?, Rate = ?, WorkingHours = ? where EmployeeID = "+Integer.parseInt(e.getLIdTxt()));
				preparedStatement.setString(1, e.getUlNameTxt());
				preparedStatement.setString(2, e.getUlAddressTxt());
				preparedStatement.setString(3, e.getUlPhoneTxt());
				preparedStatement.setString(4, e.getUlEmailTxt());
				preparedStatement.setString(5, e.getUlDobTxt());
				preparedStatement.setString(6, e.getUlAccNameTxt());
				preparedStatement.setString(7, e.getUlAccNumberTxt());
				preparedStatement.setString(8, e.getUlRateTxt());
				preparedStatement.setString(9, e.getUlHoursTxt());
				int execute = preparedStatement.executeUpdate();
				if(execute == 1) {
					Message.globalInfo("Successfully Updated!");
					e.setUlNameTxt("");
					e.setUlAddressTxt("");
					e.setUlPhoneTxt("");
					e.setUlEmailTxt("");
					e.setUlDobTxt("");
					e.setUlAccNameTxt("");
					e.setUlAccNumberTxt("");
					e.setUlRateTxt("");
					e.setUlHoursTxt("");
				}else {
					Message.globalInfo("Failed to Updated!");
				}
				System.out.println("On Update");
				break;
			/*
			 *UpdateSearch
			*/
			case "UpdateSearch":
				result = statement.executeQuery("select * from employee");
				boolean showup = true;
				while(result.next()) {
					if(result.getString(1).equalsIgnoreCase(e.getIdTxt())) {
						e.setUlIdValue(result.getString(1));
						e.setlIdTxt(result.getString(1));
						e.setlNameTxt(result.getString(2));
						e.setlAddressTxt(result.getString(3));
						e.setlPhoneTxt(result.getString(4));
						e.setlEmailTxt(result.getString(5));
						e.setlDobTxt(result.getString(6));
						e.setlAccNameTxt(result.getString(7));
						e.setlAccNumberTxt(result.getString(8));
						e.setlRateTxt(result.getString(9));
						e.setlHoursTxt(result.getString(10));
						showup = false;
					}
				}
				if(showup) {
					Message.globalInfo("Please Check Employee's ID!");
				}
				result.close();

				System.out.println("On Update Search");
				break;
			/*
			 *Deduce
			*/
			case "DeductSalary":
				preparedStatement = con.prepareStatement("update employee set Rate = ? where EmployeeID = "+Integer.parseInt(e.getUlIdValue()));
				preparedStatement.setString(1, e.getDeductTxt());
				int deduct = preparedStatement.executeUpdate();
				if(deduct == 1) 
					Message.globalInfo("Successfully Deducted!");
				else
					Message.globalInfo("Failed to Deducted!");
				preparedStatement.close();

				System.out.println("Deduct");
				break;
			default:
				break;
			}
			statement.close();
			con.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void resetAdmin() {
		username = "********";
		password = "********";
	}
	
	public String getUserName() {
		return username;
	}

	public String getPassword() {
		return password;
	}

}
