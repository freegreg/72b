package com.OJToolkit_2.client;

import java.util.ArrayList;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

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

	private final LoginServiceAsync loginService = GWT
			.create(LoginService.class);

	private LoginInfo loginInfo;
	private AbsolutePanel core;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		RootPanel rootPanel = RootPanel.get();

		/*
		 * AbsolutePanel absolutePanel = new AbsolutePanel();
		 * rootPanel.add(absolutePanel, 0, 0); absolutePanel.setSize("100%",
		 * "100%");
		 * 
		 * VerticalPanel header = new VerticalPanel(); absolutePanel.add(header,
		 * 0, 0); header.setSize("100%", "20%");
		 * 
		 * AbsolutePanel left = new AbsolutePanel(); absolutePanel.add(left, 0,
		 * 69); left.setSize("20%", "80%");
		 * 
		 * core = new AbsolutePanel(); absolutePanel.add(core, 99, 69);
		 * core.setVisible(true); core.setSize("80%", "80%");
		 */// core.add(new FrmRegistration());

		// core.add(new FrmRegistration());

		// core.clear();

		// core.add(new FrmLogin("fdsf"));
		/*
		 * Label loginLabel = new Label(
		 * "Please sign in to your Google Account to access the OJToolkit application."
		 * );
		 * 
		 * Anchor signInLink = new Anchor("Sign In");
		 */
		DockLayoutPanel dockLayoutPanel = new DockLayoutPanel(Unit.EM);
		dockLayoutPanel.setSize("100%","100%");
		rootPanel.add(dockLayoutPanel, 0, 0);

		TestNorth tn = new TestNorth();
		dockLayoutPanel.addNorth(tn, 10);
		tn.setSize("100%", "100%");
		
		TestWestUi tw = new TestWestUi();
		dockLayoutPanel.addWest(tw, 15);
		tw.setSize("100%", "100%");
		

//		FrmRegistration fReg = new FrmRegistration();
//		dockLayoutPanel.add(fReg);
//		fReg.setSize("100%", "100%");
		
//		FrmLogin flg = new FrmLogin("AmrSamir");
		AbsolutePanel core = new AbsolutePanel();
		dockLayoutPanel.add(core);
//		core.setSize("100%", "100%");
		CoreContainer.initialize(core);
		
		CoreContainer.getInstance().setContent(new FrmLogin("Amr Samir"));

		CoreContainer.getInstance().setContent(new FrmRegistration());
	//	dockLayoutPanel.remove(tw);
		
		
//		Label lblLab = new Label("West Label");
//		dockLayoutPanel.addWest(lblLab, 10.5);
//		lblLab.setSize("100%", "100%");
	
		// initWidget(dockLayoutPanel);
/*
		Label lblLabelNorth = new Label("Label North");
		dockLayoutPanel.addNorth(lblLabelNorth, 7.5);
		lblLabelNorth.setSize("100%", "100%");
		
		Label lblLab = new Label("West");
		dockLayoutPanel.addWest(lblLab, 7.5);
		lblLab.setSize("100%", "100%");

		Label lblEast = new Label("East");
		dockLayoutPanel.addEast(lblEast, -14.2);
		lblEast.setSize("100%", "100%");

		Label lblCenter = new Label("Center");
		dockLayoutPanel.add(lblCenter);
		lblCenter.setSize("100%", "100%");
*/
		/*
		 * loginService.login(GWT.getHostPageBaseURL(), new
		 * AsyncCallback<LoginInfo>() { public void onFailure(Throwable error) {
		 * }
		 * 
		 * 
		 * public void onSuccess(LoginInfo result) { loginInfo = result; if
		 * (result.isLoggedIn()) { checkRegistered();
		 * 
		 * } else { // core.clear(); core.add(new
		 * FrmLogin(result.getLoginUrl())); } } });
		 */

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
				// Window.alert("Success_CoderData");
				// core.clear();
				core.add(new FrmViewUsers(result));
				// signOutLink.setHref(loginInfo.getLogoutUrl());
				// rootPanel.add(signOutLink);
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
