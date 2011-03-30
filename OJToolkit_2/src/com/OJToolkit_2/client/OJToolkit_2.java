package com.OJToolkit_2.client;

import java.util.ArrayList;

import org.apache.http.params.CoreConnectionPNames;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class OJToolkit_2 implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */
	private final coderServiceAsync coderService = GWT
	.create(coderService.class);
	
	private final LoginServiceAsync loginService = GWT.create(LoginService.class);

	private LoginInfo loginInfo;
	private AbsolutePanel core;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		RootPanel rootPanel = RootPanel.get();

		AbsolutePanel absolutePanel = new AbsolutePanel();
		rootPanel.add(absolutePanel, 0, 0);
		absolutePanel.setSize("100%", "100%");

		VerticalPanel header = new VerticalPanel();
		absolutePanel.add(header, 0, 0);
		header.setSize("100%", "20%");

		AbsolutePanel left = new AbsolutePanel();
		absolutePanel.add(left, 0, 69);
		left.setSize("20%", "80%");

	    core = new AbsolutePanel();
		absolutePanel.add(core, 99, 69);
		core.setSize("80%", "80%");
		//core.add(new FrmRegistration());
		
		core.add(new FrmRegistration());
		
		//core.clear();
		
		//core.add(new FrmLogin("fdsf"));
		
		
		
		
		/*loginService.login(GWT.getHostPageBaseURL(),
				new AsyncCallback<LoginInfo>() {
					public void onFailure(Throwable error) {
					}
					
					
					public void onSuccess(LoginInfo result) {
						loginInfo = result;
						if (result.isLoggedIn()) {
							checkRegistered();

						} else {
							// core.clear();
							core.add(new FrmLogin(result.getLoginUrl()));
						}
					}
				});*/



	}
	public void checkRegistered() {
		coderService.checkRegistered(new AsyncCallback<Boolean>() {

			@Override
			public void onSuccess(Boolean result) {
				// Window.alert("Registered " + result );
				if (result == true) {

					viewCoders();

				} else {
					// core.clear();
					core.add(new FrmRegistration());

				}

			}

			@Override
			public void onFailure(Throwable caught) {
				// core.clear();
				core.add(new FrmLogin(loginInfo.getLoginUrl()));
				// TODO Auto-generated method stub

			}
		});
	}

	private void viewCoders() {
		coderService.viewCoders(new AsyncCallback<ArrayList<CoderData>>() {

			@Override
			public void onSuccess(ArrayList<CoderData> result) {
				//Window.alert("Success_CoderData");
				// core.clear();
				core.add(new FrmViewUsers(result));
//				signOutLink.setHref(loginInfo.getLogoutUrl());
//				rootPanel.add(signOutLink);
				// TODO Auto-generated method stub

			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Failure_CoderData");
				// TODO Auto-generated method stub

			}
		});
	}


}
