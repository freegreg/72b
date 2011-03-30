package com.OJToolkit.client;

import java.util.ArrayList;

import com.OJToolkit.server.Coder;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;

import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Anchor;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class OJToolkit implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	private final coderServiceAsync coderService = GWT
			.create(coderService.class);

	private LoginInfo loginInfo = null;
	private VerticalPanel loginPanel = new VerticalPanel();
	private Label loginLabel = new Label(
			"Please sign in to your Google Account to access the StockWatcher application.");
	private Anchor signInLink = new Anchor("Sign In");
	private Anchor signOutLink = new Anchor("Sign Out");
	private RootPanel rootPanel = RootPanel.get();
	final TextBox txtUserName = new TextBox();
	final TextBox txtSpojUserName = new TextBox();
	final PasswordTextBox pwdSpoj = new PasswordTextBox();
	final Button btnRegister = new Button("register");
	VerticalPanel verticalPanel = new VerticalPanel();

	// final Button btnRegister = new Button("Register");
	// private String hashPassword;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		// Check login status using login service.
		LoginServiceAsync loginService = GWT.create(LoginService.class);

		loginService.login(GWT.getHostPageBaseURL(),
				new AsyncCallback<LoginInfo>() {
					public void onFailure(Throwable error) {
					}

					public void onSuccess(LoginInfo result) {
						loginInfo = result;
						if (loginInfo.isLoggedIn()) {
							checkRegistered();

						} else {
							loadLogin();
						}
					}
				});

	}

	private void loadLogin() {
		// Assemble login panel.
		signInLink.setHref(loginInfo.getLoginUrl());

		loginPanel.add(loginLabel);
		loginPanel.add(signInLink);

		rootPanel.add(loginPanel);
		// RootPanel.get("mainDiv").add(loginPanel);

	}

	public void checkRegistered() {
		coderService.checkRegistered(new AsyncCallback<Boolean>() {

			@Override
			public void onSuccess(Boolean result) {
				// Window.alert("Registered " + result );
				if (result == true) {

					viewCoders();

				} else {
					register();

				}

			}

			@Override
			public void onFailure(Throwable caught) {
				loadLogin();
				// TODO Auto-generated method stub

			}
		});
	}

	private void viewCoders() {
		coderService.viewCoders(new AsyncCallback<ArrayList<CoderData>>() {

			@Override
			public void onSuccess(ArrayList<CoderData> result) {
				Window.alert("Success_CoderData");
				rootPanel.clear();

				for (CoderData coder : result) {
					viewCoder(coder);
				}
				signOutLink.setHref(loginInfo.getLogoutUrl());
				rootPanel.add(signOutLink, 181, 154);
				// TODO Auto-generated method stub

			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Failure_CoderData");
				// TODO Auto-generated method stub

			}
		});
	}

	public void viewCoder(CoderData coder) {

		Label lblUserData = new Label("User Data");
		verticalPanel.add(lblUserData);

		Label lblNewLabel = new Label("UserID");
		verticalPanel.add(lblNewLabel);

		TextBox txtUserID = new TextBox();
		txtUserID.setText(coder.getUserID().toString());
		verticalPanel.add(txtUserID);

		Label lblUsername = new Label("Username");
		verticalPanel.add(lblUsername);

		TextBox txtUserName = new TextBox();
		txtUserName.setText(coder.getUsername());
		verticalPanel.add(txtUserName);

		Label lblEmail = new Label("Email");
		verticalPanel.add(lblEmail);

		TextBox txtEmail = new TextBox();
		txtEmail.setText(coder.getEmail());
		verticalPanel.add(txtEmail);

		Label lblSpojusername = new Label("SPOJUsername");
		verticalPanel.add(lblSpojusername);

		TextBox txtSPOJUsername = new TextBox();
		txtSPOJUsername.setText(coder.getSPOJUsername());
		verticalPanel.add(txtSPOJUsername);

		Label lblSpojpassword = new Label("SPOJPassword");
		verticalPanel.add(lblSpojpassword);

		TextBox txtSpojPassword = new TextBox();
		txtSpojPassword.setText(coder.getSPOJPassword());
		verticalPanel.add(txtSpojPassword);

		rootPanel.add(verticalPanel);
	}

	private void register() {

		Label lblOjtoolkit = new Label("OJ-Toolkit");

		rootPanel.add(lblOjtoolkit, 174, 0);

		RootPanel rootPanel = RootPanel.get();

		Label lblUserName = new Label("Username");
		rootPanel.add(lblUserName, 35, 34);

		Label lblSpojUserName = new Label("Spoj Username");
		rootPanel.add(lblSpojUserName, 35, 74);

		Label lblSpojPassword = new Label("Spoj Password");
		rootPanel.add(lblSpojPassword, 35, 114);

		rootPanel.add(txtUserName, 181, 33);

		rootPanel.add(txtSpojUserName, 181, 74);

		rootPanel.add(pwdSpoj, 181, 114);
		rootPanel.add(btnRegister, 150, 154);
		signOutLink.setHref(loginInfo.getLogoutUrl());
		rootPanel.add(signOutLink, 181, 154);

		btnRegister.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				coderService.addCoder(txtUserName.getText(),
						txtSpojUserName.getText(), pwdSpoj.getText(),
						new AsyncCallback<Void>() {

							@Override
							public void onSuccess(Void result) {
								Window.alert("Success");

							}

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub
								Window.alert("Failure");

							}
						});
				// TODO Auto-generated method stub

			}
		});
		rootPanel.add(btnRegister);

	}
}