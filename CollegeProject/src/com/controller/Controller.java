package com.controller;

import javax.swing.JPanel;

import com.database.Database;
import com.dialog.Message;
import com.print.PrintFile;
import com.view.EmployeeView;
import com.view.LogInView;
import com.view.MainView;

public class Controller {
	
	private Database d;
	private LogInView l;
	
	public Controller(LogInView l) {
		d = new Database();
		this.l = l;
	}
	
	public void LogIn(String username,char[] password) {
		String temp = "";
		for(int i=0;i<password.length;i++) {
			temp = temp+password[i];
		}
		if(d.getUserName().equals(username) && d.getPassword().equals(temp)) {
			l.getFrame().dispose();
			d.resetAdmin();
			new MainView(this);
		}else {
			Message.globalInfo("Incorrect username or password!");
			l.setPassword("");
			l.setUserName("");
		}
	}
	
	public void registerEmployee(EmployeeView e){
		d.crudEmployee("Insert", e, null, null, null);
	}
	
	public void searchEmployee(EmployeeView e,String[][] row,String compareName) {
		d.crudEmployee("Search", e, null, row, compareName);
	}

	public void updateEmployee(EmployeeView e) {
		d.crudEmployee("Update", e, null, null, null);
	}

	public void updateSearch(EmployeeView e) {
		d.crudEmployee("UpdateSearch", e, this, null, null);
	}

	public void deductSalary(EmployeeView e) {
		d.crudEmployee("DeductSalary", e, this, null, null);
	}

	public void printFile(PrintFile pf,JPanel panel) {
		pf.printRecord(panel);
	}
}
