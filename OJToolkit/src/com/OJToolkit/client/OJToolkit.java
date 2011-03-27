package com.OJToolkit.client;

import com.OJToolkit.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
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

 private final coderServiceAsync coderService = GWT.create(coderService.class);

 private LoginInfo loginInfo = null;
 private VerticalPanel loginPanel = new VerticalPanel();
 private Label loginLabel = new Label("Please sign in to your Google Account to access the StockWatcher application.");
 private Anchor signInLink = new Anchor("Sign In");
 private Anchor signOutLink = new Anchor("Sign Out");
 private RootPanel rootPanel = RootPanel.get();
 private Label lblNewLabel = new Label("Username:"); 
 final TextBox textBox = new TextBox();
 final Button btnRegister = new Button("Register");
 
 /**
  * This is the entry point method.
  */
 public void onModuleLoad() {
	 // Check login status using login service.
	    LoginServiceAsync loginService = GWT.create(LoginService.class);
	   
	    loginService.login(GWT.getHostPageBaseURL(), new AsyncCallback<LoginInfo>() {
	      public void onFailure(Throwable error) {
	      }

	      public void onSuccess(LoginInfo result) {
	        loginInfo = result;
	        if(loginInfo.isLoggedIn()) {
	          loadOJ();
	      //    Window.alert("\n gethostpage " + GWT.getHostPageBaseURL() + "\n  Login URL  " + loginInfo.getLoginUrl() + "\n logout url    " +  loginInfo.getLogoutUrl() + "\n    nickname  " + loginInfo.getNickname() + "\n email " + loginInfo.getEmailAddress() );
	         // rootPanel.setVisible(false);
	        //  Registration reg = new Registration();
	          //Hyperlink link2 = new Hyperlink("Register username", "Registration");
	          //rootPanel.add(link2);
	          //reg.setVisible(true);
//	          VerticalPanel panel = new VerticalPanel();
	         
	
	         // rootPanel.add(panel);
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
	  //  RootPanel.get("mainDiv").add(loginPanel);
		
	}

private void loadOJ() {
	
	
	
	Label lblOjtoolkit = new Label("OJ-Toolkit");
	  signOutLink.setHref(loginInfo.getLogoutUrl());
	rootPanel.add(lblOjtoolkit, 174, 0);
	//RootPanel.get("nicknameContainer").add(textBox)	;
	//RootPanel.get("regButtonContainer").add(btnRegister);
//	rootPanel.add(textBox);
//	textBox.setFocus(true);
//	textBox.selectAll();
//	btnRegister.setFocus(true);
//	rootPanel.add(btnRegister);
	

	//VerticalPanel simplePanel = new VerticalPanel();

	//simplePanel.setSize("429px", "215px");
	textBox.setFocus(true);
	textBox.selectAll();
	
	rootPanel.add(lblNewLabel);
	btnRegister.setFocus(true);
	textBox.setWidth("181px");
	rootPanel.add(textBox);
	rootPanel.add(signOutLink);

	btnRegister.addClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			coderService.addCoder(textBox.getText(), new AsyncCallback<Void>() {
				
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
	//rootPanel.add(simplePanel, 0, 60);
	
	// TODO Auto-generated method stub
	/* final Button sendButton = new Button("Send");
	  final TextBox nameField = new TextBox();
	  nameField.setText("GWT User");
	  final Label errorLabel = new Label();
	  

	  // We can add style names to widgets
	  sendButton.addStyleName("sendButton");

	  // Add the nameField and sendButton to the RootPanel
	  // Use RootPanel.get() to get the entire body element
	  RootPanel.get("nameFieldContainer").add(nameField);
	  RootPanel.get("sendButtonContainer").add(sendButton);
	  RootPanel.get("errorLabelContainer").add(errorLabel);

	  // Focus the cursor on the name field when the app loads
	  nameField.setFocus(true);
	  nameField.selectAll();

	  // Create the popup dialog box
	  final DialogBox dialogBox = new DialogBox();
	  dialogBox.setText("Remote Procedure Call");
	  dialogBox.setAnimationEnabled(true);
	  final Button closeButton = new Button("Close");
	  // We can set the id of a widget by accessing its Element
	  closeButton.getElement().setId("closeButton");
	  final Label textToServerLabel = new Label();
	  final HTML serverResponseLabel = new HTML();
	  VerticalPanel dialogVPanel = new VerticalPanel();
	  dialogVPanel.addStyleName("dialogVPanel");
	  dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
	  dialogVPanel.add(textToServerLabel);
	  dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
	  dia\logVPanel.add(serverResponseLabel);
	  dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
	  dialogVPanel.add(closeButton);
	  dialogVPanel.add(signOutLink);
	  
	  
	  dialogBox.setWidget(dialogVPanel);

	  // Add a handler to close the DialogBox
	  closeButton.addClickHandler(new ClickHandler() {
	   public void onClick(ClickEvent event) {
	    dialogBox.hide();
	    sendButton.setEnabled(true);
	    sendButton.setFocus(true);
	   }
	  });

	  // Create a handler for the sendButton and nameField
	  class MyHandler implements ClickHandler, KeyUpHandler {
	   *//**
	    * Fired when the user clicks on the sendButton.
	    *//*
	   public void onClick(ClickEvent event) {
	    sendNameToServer();
	   }

	   *//**
	    * Fired when the user types in the nameField.
	    *//*
	   public void onKeyUp(KeyUpEvent event) {
	    if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
	     sendNameToServer();
	    }
	   }

	   *//**
	    * Send the name from the nameField to the server and wait for a response.
	    *//*
	   private void sendNameToServer() {
	    // First, we validate the input.
	    errorLabel.setText("");
	    String textToServer = nameField.getText();
	    if (!FieldVerifier.isValidName(textToServer)) {
	     errorLabel.setText("Please enter at least four characters");
	     return;
	    }

	    // Then, we send the input to the server.
	    sendButton.setEnabled(false);
	    textToServerLabel.setText(textToServer);
	    serverResponseLabel.setText("");
	    greetingService.greetServer(textToServer,
	      new AsyncCallback<String>() {
	       public void onFailure(Throwable caught) {
	        // Show the RPC error message to the user
	        dialogBox
	          .setText("Remote Procedure Call - Failure");
	        serverResponseLabel
	          .addStyleName("serverResponseLabelError");
	        serverResponseLabel.setHTML(SERVER_ERROR);
	        dialogBox.center();
	        closeButton.setFocus(true);
	       }

	       public void onSuccess(String result) {
	        dialogBox.setText("Remote Procedure Call");
	        serverResponseLabel
	          .removeStyleName("serverResponseLabelError");
	        serverResponseLabel.setHTML(result);
	        dialogBox.center();
	        closeButton.setFocus(true);
	       }
	      });
	   }
	  }

	  // Add a handler to send the name to the server
	  MyHandler handler = new MyHandler();
	  sendButton.addClickHandler(handler);
	  nameField.addKeyUpHandler(handler);
*/	  
}
}