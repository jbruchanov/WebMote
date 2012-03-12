package com.scurab.web.remotecontrol.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class FavoritiesView extends Composite
{

	private static FavoritiesViewUiBinder uiBinder = GWT.create(FavoritiesViewUiBinder.class);
	@UiField ListBox cmbType;
	@UiField TextBox txtName;
	@UiField TextBox txtLink;
	@UiField Button btnAdd;
	@UiField Button btnDelete;
	@UiField VerticalPanel itemContainer;

	interface FavoritiesViewUiBinder extends UiBinder<Widget, FavoritiesView>
	{
	}

	public FavoritiesView()
	{
		initWidget(uiBinder.createAndBindUi(this));
	}

	public ListBox getCmbType()
	{
		return cmbType;
	}

	public TextBox getTxtName()
	{
		return txtName;
	}

	public TextBox getTxtLink()
	{
		return txtLink;
	}

	public Button getBtnAdd()
	{
		return btnAdd;
	}

	public Button getBtnDelete()
	{
		return btnDelete;
	}

	public VerticalPanel getItemContainer()
	{
		return itemContainer;
	}


}
