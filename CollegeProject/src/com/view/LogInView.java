package com.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.Textures.Textures;
import com.controller.Controller;
import com.ui.ColorClass;
import com.ui.UI;
import com.values.Values;

public class LogInView {
	
	private Controller c;
	private UI ui;
	private ColorClass cc;
	private Color defaultBackground = Color.getHSBColor(0.10f, 0.25f, 0.15f);
	private Textures textures;
	
	private JFrame frame;
	private JLabel userLabel, passLabel,leftLabel,rightLabel,logo,footer;
	private JPanel panel;
	private JPanel userPanel,passPanel;
	private JTextField username;
	private JPasswordField password;
	private JButton logIn;
	
	private KeyAdapter keyAdapter = new KeyAdapter() {
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_ENTER) {
				c.LogIn(username.getText(),password.getPassword());
			}else if(e.getKeyCode()==KeyEvent.VK_ENTER) {
				c.LogIn(username.getText(),password.getPassword());
			}else if(e.getKeyCode()==KeyEvent.VK_ENTER) {
				c.LogIn(username.getText(),password.getPassword());
			}
			super.keyPressed(e);
		};
	};
	
	public LogInView(UI ui) {
		this.ui = ui;
		cc = new ColorClass();
		textures = new Textures();
		frame = new JFrame("Log In");
		c = new Controller(this);
		frame.setSize(Values.width/3, Values.height/3*2);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setIconImage(textures.titleLogo.getImage());
		Container container = frame.getContentPane();
		container.setLayout(new BorderLayout(60,0));
		relatedView(container);
		frame.setVisible(true);
	}
	
	private void relatedView(Container container) {
		//
		leftLabel = new JLabel("");
		rightLabel = new JLabel("");
		logo = new JLabel(textures.logoFrontTw);
		//
		userPanel  = new JPanel(new BorderLayout(10,0));
		passPanel  = new JPanel(new BorderLayout(10,0));
		userPanel.setBackground(defaultBackground);
		passPanel.setBackground(defaultBackground);
		//
		footer = new JLabel("2020 Opensource Developed by JMC Student  ");
		footer.setHorizontalAlignment(SwingConstants.RIGHT);
		footer.setForeground(Color.white);
		footer.setFont(ui.serif11);
		panel = new JPanel(new GridLayout(6,2,0,10));
		panel.setBackground(Color.getHSBColor(0.10f, 0.25f, 0.15f));
		/*Label*/
		userLabel = ui.UILabel(textures.username);
		passLabel = ui.UILabel(textures.password);
		/*Field*/
		username = ui.UITextField("",cc.defaultBlack,Color.white,Color.white,cc.tdBorderW,cc.defaultBlack);
		password = ui.UIPasswordField();
		logIn = ui.UIButton("Log In",null,null,cc.dodgerBlue,Color.white,cc.overlayBlack);
		/*Adding Listeners*/
		username.addKeyListener(keyAdapter);
		password.addKeyListener(keyAdapter);
		logIn.addKeyListener(keyAdapter);
		logIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.LogIn(username.getText(),password.getPassword());
			}
		});
		/*Adding to Frame*/
		add(container);
		
	}
	
	private void add(Container container) {
		userPanel.add(userLabel,BorderLayout.WEST);
		userPanel.add(username);
		passPanel.add(passLabel,BorderLayout.WEST);
		passPanel.add(password);
		panel.add(userPanel);
		panel.add(passPanel);
		panel.add(logIn);
		container.add(logo,BorderLayout.NORTH);
		container.add(panel);
		container.add(leftLabel,BorderLayout.WEST);
		container.add(rightLabel,BorderLayout.EAST);
		container.add(footer,BorderLayout.SOUTH);
		container.setBackground(defaultBackground);
	}
	
	public void setPassword(String string) {
		password.setText(string);
	}
	
	public void setUserName(String string) {
		username.setText(string);
	}
	
	public JFrame getFrame() {
		return frame;
	}
}
