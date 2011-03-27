package com.OJToolkit.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("coder")
public interface coderService extends RemoteService {
	public void addCoder(String username) throws NotLoggedInException;
}
