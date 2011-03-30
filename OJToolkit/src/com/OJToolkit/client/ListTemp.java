package com.OJToolkit.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

public class ListTemp extends Composite {

	public ListTemp() {
		
		VerticalPanel verticalPanel = new VerticalPanel();
		initWidget(verticalPanel);
		
		Label lblUserData = new Label("User Data");
		verticalPanel.add(lblUserData);
		
		Label lblNewLabel = new Label("UserID");
		verticalPanel.add(lblNewLabel);
		
		TextBox textBox = new TextBox();
		verticalPanel.add(textBox);
		
		Label lblUsername = new Label("Username");
		verticalPanel.add(lblUsername);
		
		TextBox textBox_1 = new TextBox();
		verticalPanel.add(textBox_1);
		
		Label lblEmail = new Label("Email");
		verticalPanel.add(lblEmail);
		
		TextBox textBox_2 = new TextBox();
		verticalPanel.add(textBox_2);
		
		Label lblSpojusername = new Label("SPOJUsername");
		verticalPanel.add(lblSpojusername);
		
		TextBox textBox_3 = new TextBox();
		verticalPanel.add(textBox_3);
		
		Label lblSpojpassword = new Label("SPOJPassword");
		verticalPanel.add(lblSpojpassword);
		
		TextBox textBox_4 = new TextBox();
		verticalPanel.add(textBox_4);
	}

}
