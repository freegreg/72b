package com.OJToolkit.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface coderServiceAsync {

	void addCoder(String username, AsyncCallback<Void> callback);

}
