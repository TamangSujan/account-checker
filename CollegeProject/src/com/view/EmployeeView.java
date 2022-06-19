package com.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.Textures.Textures;
import com.controller.Controller;
import com.dialog.Message;
import com.print.PrintFile;
import com.ui.ColorClass;
import com.ui.UI;

public class EmployeeView {

	private Message message;
	private PrintFile pf;
	private UI ui;
	private ColorClass cc;
	private Textures textures;

	private JLabel heading;
	/**Employee Registration*/
	private JLabel name,address,phone,email,dob,subTitle1,accountName,accountNumber;
	private JLabel subTitle2,rate,workingHours;
	private JTextField nameTxt,addressTxt,phoneTxt,emailTxt,dobTxt,accountNameTxt,accountNumberTxt;
	private JTextField rateTxt,workingHoursTxt;
	private JButton register;

	public EmployeeView(UI ui,ColorClass cc,Textures textures) {
		message = new Message();
		pf = new PrintFile();
		this.ui = ui;
		this.cc = cc;
		this.textures = textures;
	}

	public void registerEmployee(JPanel panel,Controller c) {
		panel.setLayout(new GridLayout(26,1));
		/*Label*/
		heading = ui.UILabel("Registration", null, cc.defaultBlack);
		name = ui.UILabel("Full Name", null, cc.defaultBlack);
		address = ui.UILabel("Address", null, cc.defaultBlack);
		phone = ui.UILabel("Phone Number", null, cc.defaultBlack);
		email = ui.UILabel("Email", null, cc.defaultBlack);
		dob = ui.UILabel("Date Of Birth", null, cc.defaultBlack);
		subTitle1 = ui.UILabel("Account Details", null, cc.defaultBlack);
		accountName = ui.UILabel("Account Name", null, cc.defaultBlack);
		accountNumber = ui.UILabel("Account Number", null, cc.defaultBlack);
		subTitle2 = ui.UILabel("Payment", null, cc.defaultBlack);
		rate = ui.UILabel("Working Rate", null, cc.defaultBlack);
		workingHours = ui.UILabel("Working Hours", null, cc.defaultBlack);
		/*Field*/
		nameTxt = ui.UITextField("",Color.white,cc.defaultBlack,cc.defaultBlack,cc.tdBorderB,cc.focusBlack);
		addressTxt = ui.UITextField("",Color.white,cc.defaultBlack,cc.defaultBlack,cc.tdBorderB,cc.focusBlack);
		phoneTxt = ui.UITextField("",Color.white,cc.defaultBlack,cc.defaultBlack,cc.tdBorderB,cc.focusBlack);
		emailTxt = ui.UITextField("",Color.white,cc.defaultBlack,cc.defaultBlack,cc.tdBorderB,cc.focusBlack);
		dobTxt = ui.UITextField("",Color.white,cc.defaultBlack,cc.defaultBlack,cc.tdBorderB,cc.focusBlack);
		accountNameTxt = ui.UITextField("",Color.white,cc.defaultBlack,cc.defaultBlack,cc.tdBorderB,cc.focusBlack);
		accountNumberTxt = ui.UITextField("",Color.white,cc.defaultBlack,cc.defaultBlack,cc.tdBorderB,cc.focusBlack);
		rateTxt = ui.UITextField("",Color.white,cc.defaultBlack,cc.defaultBlack,cc.tdBorderB,cc.focusBlack);
		workingHoursTxt = ui.UITextField("",Color.white,cc.defaultBlack,cc.defaultBlack,cc.tdBorderB,cc.focusBlack);
		/*Button*/
		register = ui.UIButton("Register",textures.addIconW,textures.addIconW, cc.dodgerBlue, Color.white, cc.lightBlack);

		register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				message.globalDialog(EmployeeView.this,null, c, "Add+","Do you want to register new Employee?",1,null);
			}
		});

		/*Adding*/
		heading.setText("Registration");
		panel.add(heading);
		panel.add(new JLabel(""));
		panel.add(name);
		panel.add(nameTxt);
		panel.add(address);
		panel.add(addressTxt);
		panel.add(phone);
		panel.add(phoneTxt);
		panel.add(email);
		panel.add(emailTxt);
		panel.add(dob);
		panel.add(dobTxt);
		panel.add(new JLabel(""));
		panel.add(subTitle1);
		panel.add(accountName);
		panel.add(accountNameTxt);
		panel.add(accountNumber);
		panel.add(accountNumberTxt);
		panel.add(new JLabel(""));
		panel.add(subTitle2);
		panel.add(rate);
		panel.add(rateTxt);
		panel.add(workingHours);
		panel.add(workingHoursTxt);
		panel.add(new JLabel(""));
		panel.add(register);
	}

	private JMenuBar searchBar;
	private JTextField searchTxt;
	private JButton search;
	private JScrollPane scroll;
	public void searchEmployee(JPanel panel, Controller c) {
		panel.setLayout(new BorderLayout());
		
		String[] head = {"ID","Name","Address","Phone Number","Email","DOB","Acc. Name","Acc. Number","Hourly Rate","Working Hours"};
		String[][] row = new String[100][10];
		
		searchBar = ui.UIBar(Color.white);
		searchTxt = ui.UITextField("Enter Employee's Name",null, cc.defaultBlack, cc.defaultBlack, cc.borderB,cc.focusBlack);
		searchTxt.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {
				if(searchTxt.getText().equals("")) {
					searchTxt.setText("Enter Employee's Name");
				}
			}
			public void focusGained(FocusEvent e) {
				if(searchTxt.getText().equals("Enter Employee's Name")) {
					searchTxt.setText("");
				}
			}
		});
		searchTxt.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					message.search(EmployeeView.this,c,row, searchTxt.getText());
					scroll.updateUI();
				}
				super.keyPressed(e);
			}
		});
		search = ui.UIButton("Search",textures.searchIconW,textures.searchIconW, cc.dodgerBlue, Color.white, cc.lightBlack);
		
		JTable table = ui.UITable(head, row, 30, 30, cc.defaultBlack, Color.white, Color.white, cc.defaultBlack);
		scroll = ui.UIScrollPane(table, cc.defaultBlack, Color.white, Color.white, cc.defaultBlack);
		scroll.setBorder(BorderFactory.createLineBorder(Color.white));
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				message.search(EmployeeView.this,c,row, searchTxt.getText());
				scroll.updateUI();
			}
		});
		search.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					message.search(EmployeeView.this,c,row, searchTxt.getText());
					scroll.updateUI();
				}
				super.keyPressed(e);
			}
		});
		searchBar.add(searchTxt);
		searchBar.add(search);
		panel.add(searchBar,BorderLayout.NORTH);
		panel.add(scroll);
	}

	private JMenuBar idBar;
	private JTextField idTxt;
	private JButton idButton;
	private JPanel salaryMainPanel,salarySubPanelOne,salarySubPanelTwo,subTwoCenterPanel;
	public void salary(JPanel panel, Controller c) {
		panel.setLayout(new BorderLayout());
		idBar = ui.UIBar(Color.white);
		idTxt = ui.UITextField("Enter Employee's ID", Color.white, cc.defaultBlack, cc.defaultBlack,cc.tdBorderB,cc.focusBlack);
		idTxt.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {
				if(idTxt.getText().equals("")) {
					idTxt.setText("Enter Employee's ID");
				}
			}
			public void focusGained(FocusEvent e) {
				if(idTxt.getText().equals("Enter Employee's ID")) {
					idTxt.setText("");
				}
			}
		});
		idButton = ui.UIButton("Search",textures.searchIconW,textures.searchIconW, cc.dodgerBlue, Color.white,cc.lightBlack);
		idBar.add(idTxt);
		idBar.add(idButton);
		
		panel.add(idBar,BorderLayout.NORTH);
		salaryMainPanel = ui.UIPanel(Color.white, new GridLayout(1,2));
		salarySubPanelOne = ui.UIPanel(new ImageIcon(this.getClass().getResource("/textures/Logo3.png")), new GridLayout(20,1),salaryMainPanel,Color.white);
		salarySubPanelTwo = ui.UIPanel(Color.white, new BorderLayout());
		subTwoCenterPanel = ui.UIPanel(Color.white, new GridLayout(11,2,10,30));
		
		salaryMainPanel.add(salarySubPanelOne);
		salaryMainPanel.add(salarySubPanelTwo);
		/*Test UI*/
		subOne();
		subTwo();
		//Listener
		idTxt.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					message.updateSearch(EmployeeView.this,c);
				}
				super.keyPressed(e);
			}
		});
		idButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				message.updateSearch(EmployeeView.this,c);
			}
		});
		idButton.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					message.updateSearch(EmployeeView.this,c);
				}
				super.keyPressed(e);
			}
		});
		deductButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				message.globalDialog(EmployeeView.this, null, c, "Update+","Do you want to deduct salary?",4,null);
			}
		});
		deductButton.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					message.globalDialog(EmployeeView.this, null, c, "Update+","Do you want to deduct salary?",4,null);
				}
				super.keyPressed(e);
			}
		});
		deductTxt.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					message.globalDialog(EmployeeView.this, null, c, "Update+","Do you want to deduct salary?",4,null);
				}
				super.keyPressed(e);
			}
		});
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				message.globalDialog(EmployeeView.this, null, c, "Update+","Do you want to update this Employee?",2,null);
			}
		});
		printButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				message.globalDialog(null, pf, c, "Print+","Do you want to print this Employee's data?",3,salarySubPanelOne);
			}
		});
		fetchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeeView.this.setUlNameTxt(EmployeeView.this.getlNameTxt());
				EmployeeView.this.setUlAddressTxt(EmployeeView.this.getlAddressTxt());
				EmployeeView.this.setUlPhoneTxt(EmployeeView.this.getlPhoneTxt());
				EmployeeView.this.setUlEmailTxt(EmployeeView.this.getlEmailTxt());
				EmployeeView.this.setUlDobTxt(EmployeeView.this.getlDobTxt());
				EmployeeView.this.setUlAccNameTxt(EmployeeView.this.getlAccNameTxt());
				EmployeeView.this.setUlAccNumberTxt(EmployeeView.this.getlAccNumberTxt());
				EmployeeView.this.setUlRateTxt(EmployeeView.this.getlRateTxt());
				EmployeeView.this.setUlHoursTxt(EmployeeView.this.getlHoursTxt());
			}
		});
		panel.add(salaryMainPanel);
	}
	
	private JLabel lId, lIdTxt, lName, lNameTxt, lAddress, lAddressTxt, lPhone, lPhoneTxt, lEmail, lEmailTxt;
	private JLabel lDob, lDobTxt, lAccName, lAccNameTxt, lAccNumber, lAccNumberTxt, lRate, lRateTxt, lHours, lHoursTxt;
	private void subOne() {
		lId = ui.UILabel("Employee's ID", null, cc.defaultBlack);
		lIdTxt = ui.UILabel("No Info", null, cc.defaultBlack);
		lName = ui.UILabel("Full Name", null, cc.defaultBlack);
		lNameTxt = ui.UILabel("No Info", null, cc.defaultBlack);
		lAddress = ui.UILabel("Address", null, cc.defaultBlack);
		lAddressTxt = ui.UILabel("No Info", null, cc.defaultBlack);
		lPhone = ui.UILabel("Phone Number", null, cc.defaultBlack);
		lPhoneTxt = ui.UILabel("No Info", null, cc.defaultBlack);
		lEmail = ui.UILabel("Email", null, cc.defaultBlack);
		lEmailTxt = ui.UILabel("No Info", null, cc.defaultBlack);
		lDob = ui.UILabel("Date Of Birth", null, cc.defaultBlack);
		lDobTxt = ui.UILabel("No Info", null, cc.defaultBlack);
		lAccName = ui.UILabel("Account Name", null, cc.defaultBlack);
		lAccNameTxt = ui.UILabel("No Info", null, cc.defaultBlack);
		lAccNumber = ui.UILabel("Account Number", null, cc.defaultBlack);
		lAccNumberTxt = ui.UILabel("No Info", null, cc.defaultBlack);
		lRate = ui.UILabel("Per Hour Pay Rate", null, cc.defaultBlack);
		lRateTxt = ui.UILabel("No Info", null, cc.defaultBlack);
		lHours = ui.UILabel("Standard Working Hours", null, cc.defaultBlack);
		lHoursTxt = ui.UILabel("No Info", null, cc.defaultBlack);
		salarySubPanelOne.add(lId);
		salarySubPanelOne.add(lIdTxt);
		salarySubPanelOne.add(lName);
		salarySubPanelOne.add(lNameTxt);
		salarySubPanelOne.add(lAddress);
		salarySubPanelOne.add(lAddressTxt);
		salarySubPanelOne.add(lPhone);
		salarySubPanelOne.add(lPhoneTxt);
		salarySubPanelOne.add(lEmail);
		salarySubPanelOne.add(lEmailTxt);
		salarySubPanelOne.add(lDob);
		salarySubPanelOne.add(lDobTxt);
		salarySubPanelOne.add(lAccName);
		salarySubPanelOne.add(lAccNameTxt);
		salarySubPanelOne.add(lAccNumber);
		salarySubPanelOne.add(lAccNumberTxt);
		salarySubPanelOne.add(lRate);
		salarySubPanelOne.add(lRateTxt);
		salarySubPanelOne.add(lHours);
		salarySubPanelOne.add(lHoursTxt);
	}
	
	private JMenuBar deductBar;
	private JTextField deductTxt;
	private JButton deductButton, updateButton, printButton;
	private JLabel ulId,ulIdValue,ulName,ulAddress,ulPhone,ulEmail;
	private JLabel ulDob,ulAccName,ulAccNumber,ulRate,ulHours;
	private JButton fetchButton;
	private JTextField ulNameTxt,ulAddressTxt,ulPhoneTxt,ulEmailTxt;
	private JTextField ulDobTxt,ulAccNameTxt,ulAccNumberTxt,ulRateTxt,ulHoursTxt;
	
	private void subTwo() {
		deductBar = ui.UIBar(Color.white);
		deductTxt =  ui.UITextField("", Color.white, cc.defaultBlack, cc.defaultBlack,cc.tdBorderB,cc.focusBlack);
		deductButton = ui.UIButton("Deduct Salary",textures.moneyIconW,textures.moneyIconW, cc.dodgerBlue, Color.white,cc.lightBlack);
		deductBar.add(deductTxt);
		deductBar.add(deductButton);
		ulId = ui.UILabel("ID", null, cc.defaultBlack);
		ulIdValue = ui.UILabel(lIdTxt.getText(), null, cc.defaultBlack);
		ulName = ui.UILabel("Name", null, cc.defaultBlack);
		ulNameTxt = ui.UITextField("", Color.white, cc.defaultBlack, cc.defaultBlack,cc.tdBorderB,cc.focusBlack);
		ulAddress = ui.UILabel("Address", null, cc.defaultBlack);
		ulAddressTxt = ui.UITextField("", Color.white, cc.defaultBlack, cc.defaultBlack,cc.tdBorderB,cc.focusBlack);
		ulPhone = ui.UILabel("Phone Number", null, cc.defaultBlack);
		ulPhoneTxt = ui.UITextField("", Color.white, cc.defaultBlack, cc.defaultBlack,cc.tdBorderB,cc.focusBlack);
		ulEmail = ui.UILabel("Email", null, cc.defaultBlack);
		ulEmailTxt = ui.UITextField("", Color.white, cc.defaultBlack, cc.defaultBlack,cc.tdBorderB,cc.focusBlack);
		ulDob = ui.UILabel("Date Of Birth", null, cc.defaultBlack);
		ulDobTxt = ui.UITextField("", Color.white, cc.defaultBlack, cc.defaultBlack,cc.tdBorderB,cc.focusBlack);
		ulAccName = ui.UILabel("Account Name", null, cc.defaultBlack);
		ulAccNameTxt = ui.UITextField("", Color.white, cc.defaultBlack, cc.defaultBlack,cc.tdBorderB,cc.focusBlack);
		ulAccNumber = ui.UILabel("Account Number", null, cc.defaultBlack);
		ulAccNumberTxt = ui.UITextField("", Color.white, cc.defaultBlack, cc.defaultBlack,cc.tdBorderB,cc.focusBlack);
		ulRate = ui.UILabel("Pay Rate", null, cc.defaultBlack);
		ulRateTxt = ui.UITextField("", Color.white, cc.defaultBlack, cc.defaultBlack,cc.tdBorderB,cc.focusBlack);
		ulHours = ui.UILabel("Working Hours", null, cc.defaultBlack);
		ulHoursTxt = ui.UITextField("", Color.white, cc.defaultBlack, cc.defaultBlack,cc.tdBorderB,cc.focusBlack);
		fetchButton = ui.UIButton("Fetch Data",textures.fetchIconW,textures.fetchIconW, cc.dodgerBlue, Color.white, cc.lightBlack);
		
		updateButton = ui.UIButton("Update Employee's Data",textures.updateIconW,textures.updateIconW, cc.dodgerBlue, Color.white,cc.lightBlack);
		printButton = ui.UIButton("Print Employee's Data",null,null, cc.dodgerBlue, Color.white,cc.lightBlack);
		//Listener
		//Adding
		salarySubPanelTwo.add(deductBar,BorderLayout.NORTH);
		//
		subTwoCenterPanel.add(ulId);
		subTwoCenterPanel.add(ulIdValue);
		subTwoCenterPanel.add(ulName);
		subTwoCenterPanel.add(ulNameTxt);
		subTwoCenterPanel.add(ulAddress);
		subTwoCenterPanel.add(ulAddressTxt);
		subTwoCenterPanel.add(ulPhone);
		subTwoCenterPanel.add(ulPhoneTxt);
		subTwoCenterPanel.add(ulEmail);
		subTwoCenterPanel.add(ulEmailTxt);
		subTwoCenterPanel.add(ulDob);
		subTwoCenterPanel.add(ulDobTxt);
		subTwoCenterPanel.add(ulAccName);
		subTwoCenterPanel.add(ulAccNameTxt);
		subTwoCenterPanel.add(ulAccNumber);
		subTwoCenterPanel.add(ulAccNumberTxt);
		subTwoCenterPanel.add(ulRate);
		subTwoCenterPanel.add(ulRateTxt);
		subTwoCenterPanel.add(ulHours);
		subTwoCenterPanel.add(ulHoursTxt);
		subTwoCenterPanel.add(fetchButton);
		subTwoCenterPanel.add(updateButton);
		salarySubPanelTwo.add(subTwoCenterPanel);
		salarySubPanelTwo.add(printButton,BorderLayout.SOUTH);
	}

	public String getNameTxt() {
		return nameTxt.getText();
	}

	public void setNameTxt(String nameTxt) {
		this.nameTxt.setText(nameTxt); 
	}

	public String getAddressTxt() {
		return addressTxt.getText();
	}

	public void setAddressTxt(String addressTxt) {
		this.addressTxt.setText(addressTxt);
	}

	public String getPhoneTxt() {
		return phoneTxt.getText();
	}

	public void setPhoneTxt(String phoneTxt) {
		this.phoneTxt.setText(phoneTxt);
	}

	public String getEmailTxt() {
		return emailTxt.getText();
	}

	public void setEmailTxt(String emailTxt) {
		this.emailTxt.setText(emailTxt);;
	}

	public String getDobTxt() {
		return dobTxt.getText();
	}

	public void setDobTxt(String dobTxt) {
		this.dobTxt.setText(dobTxt);
	}

	public String getAccountNameTxt() {
		return accountNameTxt.getText();
	}

	public void setAccountNameTxt(String accountNameTxt) {
		this.accountNameTxt.setText(accountNameTxt);
	}

	public String getAccountNumberTxt() {
		return accountNumberTxt.getText();
	}

	public void setAccountNumberTxt(String accountNumberTxt) {
		this.accountNumberTxt.setText(accountNumberTxt);
	}

	public String getRateTxt() {
		return rateTxt.getText();
	}

	public void setRateTxt(String rateTxt) {
		this.rateTxt.setText(rateTxt);
	}

	public String getWorkingHoursTxt() {
		return workingHoursTxt.getText();
	}

	public void setWorkingHoursTxt(String workingHoursTxt) {
		this.workingHoursTxt.setText(workingHoursTxt);
	}
	
	public String getLIdTxt() {
		return lIdTxt.getText();
	}

	public void setlIdTxt(String lIdTxt) {
		this.lIdTxt.setText(lIdTxt);
	}

	public String getlNameTxt() {
		return lNameTxt.getText();
	}

	public void setlNameTxt(String lNameTxt) {
		this.lNameTxt.setText(lNameTxt);
	}

	public String getlAddressTxt() {
		return lAddressTxt.getText();
	}

	public void setlAddressTxt(String lAddressTxt) {
		this.lAddressTxt.setText(lAddressTxt);
	}

	public String getlPhoneTxt() {
		return lPhoneTxt.getText();
	}

	public void setlPhoneTxt(String lPhoneTxt) {
		this.lPhoneTxt.setText(lPhoneTxt);
	}

	public String getlEmailTxt() {
		return lEmailTxt.getText();
	}

	public void setlEmailTxt(String lEmailTxt) {
		this.lEmailTxt.setText(lEmailTxt);
	}

	public String getlDobTxt() {
		return lDobTxt.getText();
	}

	public void setlDobTxt(String lDobTxt) {
		this.lDobTxt.setText(lDobTxt);
	}

	public String getlAccNameTxt() {
		return lAccNameTxt.getText();
	}

	public void setlAccNameTxt(String lAccNameTxt) {
		this.lAccNameTxt.setText(lAccNameTxt);
	}

	public String getlAccNumberTxt() {
		return lAccNumberTxt.getText();
	}

	public void setlAccNumberTxt(String lAccNumberTxt) {
		this.lAccNumberTxt.setText(lAccNumberTxt);
	}

	public String getlRateTxt() {
		return lRateTxt.getText();
	}

	public void setlRateTxt(String lRateTxt) {
		this.lRateTxt.setText(lRateTxt);
	}

	public String getlHoursTxt() {
		return lHoursTxt.getText();
	}

	public void setlHoursTxt(String lHoursTxt) {
		this.lHoursTxt.setText(lHoursTxt);
	}
	
	public String getUlId() {
		return ulId.getText();
	}

	public void setUlId(String ulId) {
		this.ulId.setText(ulId);
	}

	public String getUlIdValue() {
		return ulIdValue.getText();
	}

	public void setUlIdValue(String ulIdValue) {
		this.ulIdValue.setText(ulIdValue);
	}

	public String getUlNameTxt() {
		return ulNameTxt.getText();
	}

	public void setUlNameTxt(String ulNameTxt) {
		this.ulNameTxt.setText(ulNameTxt);
	}

	public String getUlAddressTxt() {
		return ulAddressTxt.getText();
	}

	public void setUlAddressTxt(String ulAddressTxt) {
		this.ulAddressTxt.setText(ulAddressTxt);
	}

	public String getUlPhoneTxt() {
		return ulPhoneTxt.getText();
	}

	public void setUlPhoneTxt(String ulPhoneTxt) {
		this.ulPhoneTxt.setText(ulPhoneTxt);
	}

	public String getUlEmailTxt() {
		return ulEmailTxt.getText();
	}

	public void setUlEmailTxt(String ulEmailTxt) {
		this.ulEmailTxt.setText(ulEmailTxt);
	}

	public String getUlDobTxt() {
		return ulDobTxt.getText();
	}

	public void setUlDobTxt(String ulDobTxt) {
		this.ulDobTxt.setText(ulDobTxt);
	}

	public String getUlAccNameTxt() {
		return ulAccNameTxt.getText();
	}

	public void setUlAccNameTxt(String ulAccNameTxt) {
		this.ulAccNameTxt.setText(ulAccNameTxt);
	}

	public String getUlAccNumberTxt() {
		return ulAccNumberTxt.getText();
	}

	public void setUlAccNumberTxt(String ulAccNumberTxt) {
		this.ulAccNumberTxt.setText(ulAccNumberTxt);
	}

	public String getUlRateTxt() {
		return ulRateTxt.getText();
	}

	public void setUlRateTxt(String ulRateTxt) {
		this.ulRateTxt.setText(ulRateTxt);
	}

	public String getUlHoursTxt() {
		return ulHoursTxt.getText();
	}

	public void setUlHoursTxt(String ulHoursTxt) {
		this.ulHoursTxt.setText(ulHoursTxt);
	}

	public String getIdTxt() {
		return idTxt.getText();
	}

	public void setIdTxt(String idTxt) {
		this.idTxt.setText(idTxt);
	}

	public String getDeductTxt() {
		return deductTxt.getText();
	}

	public void setDeductTxt(String deductTxt) {
		this.deductTxt.setText(deductTxt);
	}
	
}
