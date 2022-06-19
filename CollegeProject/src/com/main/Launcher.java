package com.main;

import com.dialog.Message;
import com.ui.UI;
import com.view.LogInView;

public class Launcher {
	
	public static void main(String[] args) {
		//Start
		UI ui = new UI();
		new Message();
		new LogInView(ui);
	}
}
