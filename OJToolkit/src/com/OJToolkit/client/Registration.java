package com.OJToolkit.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;


public class Registration extends Composite {

	 private RootPanel rootPanel = RootPanel.get();
	 
	public Registration() {
		
		VerticalPanel panel = new VerticalPanel();
		initWidget(panel);
		
		Label lblRegistration = new Label("Registration");
		panel.add(lblRegistration);
		lblRegistration.setWidth("340px");
		
		Label lblNewLabel = new Label("Username:");
		panel.add(lblNewLabel);
		
		TextBox textBox = new TextBox();
		panel.add(textBox);
		textBox.setWidth("181px");
		
		Button btnRegister = new Button("Register");
		panel.add(btnRegister);
		
	//	rootPanel.add(panel);
	}

}
