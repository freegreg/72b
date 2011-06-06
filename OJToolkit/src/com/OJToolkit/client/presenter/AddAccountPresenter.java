package com.OJToolkit.client.presenter;

import com.OJToolkit.client.AppController;
import com.OJToolkit.client.Services.CoderServiceAsync;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class AddAccountPresenter implements Presenter {

	public interface Display {
		HasClickHandlers getAddSpojAccountButton();
		HasClickHandlers getAddXAccountButton();
		HasClickHandlers getAddAccountButton();
		HasValue<String> getAccountUserName();
		HasValue<String> getAccountPassword();
		void setAccountType(String accountType);
		void setAddedAccounts(String addedAccounts);
		Widget asWidget();
	}

	private final Display display;
	private final HandlerManager eventBus;
	private String accountType;
	private String addedAccounts;
	private final CoderServiceAsync coderService;

	public AddAccountPresenter(CoderServiceAsync coderService,HandlerManager eventBus, final Display display) {
		this.coderService = coderService;
		this.eventBus = eventBus;
		this.display = display;
		bind();

		this.coderService.getAddedAccounts(new AsyncCallback<String>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Failed to add acount to coder");
			}
			@Override
			public void onSuccess(String result) {
				addedAccounts = result;
				Cookies.setCookie("addedAccountsCookie", addedAccounts, AppController.COOKIES_EXPIRYDATE, null, "/", false);
				display.setAddedAccounts(addedAccounts);
			}
		});
	}

	private void bind() {
		display.getAddSpojAccountButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				accountType = "SPOJ";
				display.setAccountType(accountType);
				// eventBus.fireEvent(new AddAccountDetailsEvent("SPOJ"));
			}
		});

		display.getAddXAccountButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				accountType = "X";
				display.setAccountType(accountType);
				// eventBus.fireEvent(new AddAccountDetailsEvent("X"));
			}
		});

		display.getAddAccountButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				// System.out.println(accountType);
				// eventBus.fireEvent(new AddAccountDetailsEvent("SPOJ"));
				coderService.addAccount(accountType, display
				        .getAccountUserName().getValue(), display
				        .getAccountPassword().getValue(),
				        new AsyncCallback<Void>() {
					        @Override
					        public void onSuccess(Void result) {
						        addedAccounts += "-" + accountType;
						        Cookies.setCookie("addedAccountsCookie",
						                addedAccounts,
						                AppController.COOKIES_EXPIRYDATE, null,"/", false);
						        display.setAddedAccounts(addedAccounts);

					        }
					        @Override
					        public void onFailure(Throwable caught) {
					        	Window.alert("Failed to add an account") ;
					        }
				        });
			}
		});

	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
	}

}