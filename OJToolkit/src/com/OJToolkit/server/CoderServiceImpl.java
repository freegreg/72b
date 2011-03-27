package com.OJToolkit.server;

import java.util.logging.Logger;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

import com.OJToolkit.client.NotLoggedInException;
import com.OJToolkit.client.coderService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.gwt.user.client.Window;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;


import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;


public class CoderServiceImpl extends RemoteServiceServlet implements coderService {
	
	private static final Logger LOG =  Logger.getLogger(CoderServiceImpl.class.getName());
	private static final PersistenceManagerFactory PMF = JDOHelper.getPersistenceManagerFactory("transactions-optional");
	
	public void addCoder(String username) throws NotLoggedInException {
		checkLoggedIn();
		PersistenceManager pm = getPersistenceManager();
		try{
			
//			Window.alert("Username " + username);
			Coder c = new Coder(username);
			User user = getUser();		
			c.setEmail(user.getEmail());
			pm.makePersistent(c);
			
		} finally{
			pm.close();
		}
		
		// TODO Auto-generated method stub

	}
	
	  private void checkLoggedIn() throws NotLoggedInException {
		    if (getUser() == null) {
		      throw new NotLoggedInException("Not logged in.");
		    }
		  }
	  
	  private User getUser() {
		    UserService userService = UserServiceFactory.getUserService();
		    return userService.getCurrentUser();
		  }

		  private PersistenceManager getPersistenceManager() {
		    return PMF.getPersistenceManager();
		  }
		  


}
