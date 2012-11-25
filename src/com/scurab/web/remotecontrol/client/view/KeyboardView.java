package com.scurab.web.remotecontrol.client.view;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.scurab.web.remotecontrol.client.controls.KeyButton;
import com.scurab.web.remotecontrol.client.controls.KeyToggleButton;
import com.scurab.web.remotecontrol.client.interfaces.IsCommandableClickHandler;
import com.scurab.web.remotecontrol.client.view.keyboard.DefaultKeyboard;
import com.scurab.web.remotecontrol.client.view.keyboard.FunctionalKeyboard;
import com.scurab.web.remotecontrol.client.view.keyboard.ShiftKeyboard;
import com.scurab.web.remotecontrol.client.view.keyboard.SpecialKeyboard;
import com.scurab.web.remotecontrol.client.view.keyboard.ArrowsKeyboard;
import com.scurab.web.remotecontrol.client.view.keyboard.MediaKeyboard;
import com.scurab.web.remotecontrol.client.view.keyboard.NumericKeyboard;

public class KeyboardView extends AbstractView
{

	private static KeyboardViewUiBinder uiBinder = GWT.create(KeyboardViewUiBinder.class);
	@UiField DefaultKeyboard defaultKeyboard;
	@UiField ShiftKeyboard shiftKeyboard;
	@UiField SpecialKeyboard specialKeyboard;
	@UiField FunctionalKeyboard funcKeyboard;
	
	@UiField Button btnDefault;
	@UiField Button btnSpecial;
	@UiField Button btnArrows;
	@UiField Button btnNumeric;
	@UiField Button btnFunctional;
//	@UiField Button btnMedia;
	@UiField ArrowsKeyboard arrowsKeyboard;
//	@UiField MediaKeyboard mediaKeyboard;
	@UiField NumericKeyboard numericKeyboard;
	
	private HashSet<KeyButton> mAllButtons = null;
	private HashSet<KeyToggleButton> mAllToggleButtons = null;

	interface KeyboardViewUiBinder extends UiBinder<Widget, KeyboardView>
	{
	}

	public KeyboardView()
	{
		initWidget(uiBinder.createAndBindUi(this));
		mAllButtons = new HashSet<KeyButton>();
		mAllToggleButtons = new HashSet<KeyToggleButton>();
		bind();
	}
	
	private void bind()
	{
		initClickHandlers(defaultKeyboard);
		initClickHandlers(shiftKeyboard);
		initClickHandlers(specialKeyboard);
		initClickHandlers(arrowsKeyboard);
		initClickHandlers(funcKeyboard);
//		initClickHandlers(mediaKeyboard);
		initClickHandlers(numericKeyboard);
	}
	
	private void initClickHandlers(HasWidgets hw)
	{
		Iterator<Widget> iter = hw.iterator();
		while(iter.hasNext())
		{
			Widget w = iter.next();
			if(w instanceof KeyButton)
			{
				KeyButton b = (KeyButton)w;
				setClickHandler(b);
			}
			else if(w instanceof KeyToggleButton)
			{
				KeyToggleButton tb = (KeyToggleButton)w;
				setClickHandler(tb);
			}
			else if(w instanceof HasWidgets)
			{
				HasWidgets h = (HasWidgets)w;
				initClickHandlers(h);
			}
		}
	}
	
	private void setClickHandler(final KeyButton b)
	{
		mAllButtons.add(b);
//		b.addClickHandler(new ClickHandler()
//		{
//			@Override
//			public void onClick(ClickEvent event)
//			{
//				Window.alert((b.getKeyCode() == null ? b.getText() : b.getKeyCode()));
//			}
//		});
	}
	
	private void setClickHandler(final KeyToggleButton b)
	{
		mAllToggleButtons.add(b);
//		b.addClickHandler(new ClickHandler()
//		{
//			@Override
//			public void onClick(ClickEvent event)
//			{
//				Window.alert((b.getKeyCode() == null ? b.getText() : b.getKeyCode()) + " " + b.getValue());
//			}
//		});
	}

	@Override
	public List<IsCommandableClickHandler> getClickElements()
	{
		return null;
	}

	public Button getBtnDefault()
	{
		return btnDefault;
	}

	public void setBtnDefault(Button btnDefault)
	{
		this.btnDefault = btnDefault;
	}

	public Button getBtnSpecial()
	{
		return btnSpecial;
	}

	public void setBtnSpecial(Button btnSpecial)
	{
		this.btnSpecial = btnSpecial;
	}

	public Button getBtnArrows()
	{
		return btnArrows;
	}

	public void setBtnArrows(Button btnArrows)
	{
		this.btnArrows = btnArrows;
	}

	public Button getBtnNumeric()
	{
		return btnNumeric;
	}

	public void setBtnNumeric(Button btnNumeric)
	{
		this.btnNumeric = btnNumeric;
	}

	public Button getBtnFunctional()
	{
		return btnFunctional;
	}

	public void setBtnFunctional(Button btnFunctional)
	{
		this.btnFunctional = btnFunctional;
	}

//	public Button getBtnMedia()
//	{
//		return btnMedia;
//	}
//
//	public void setBtnMedia(Button btnMedia)
//	{
//		this.btnMedia = btnMedia;
//	}

	public DefaultKeyboard getDefaultKeyboard()
	{
		return defaultKeyboard;
	}

	public void setDefaultKeyboard(DefaultKeyboard defaultKeyboard)
	{
		this.defaultKeyboard = defaultKeyboard;
	}

	public ShiftKeyboard getShiftKeyboard()
	{
		return shiftKeyboard;
	}

	public void setShiftKeyboard(ShiftKeyboard shiftKeyboard)
	{
		this.shiftKeyboard = shiftKeyboard;
	}

	public SpecialKeyboard getSpecialKeyboard()
	{
		return specialKeyboard;
	}

	public void setSpecialKeyboard(SpecialKeyboard specialKeyboard)
	{
		this.specialKeyboard = specialKeyboard;
	}

	public ArrowsKeyboard getArrowsKeyboard()
	{
		return arrowsKeyboard;
	}

	public FunctionalKeyboard getFuncKeyboard()
	{
		return funcKeyboard;
	}

//	public MediaKeyboard getMediaKeyboard()
//	{
//		return mediaKeyboard;
//	}

	public NumericKeyboard getNumericKeyboard()
	{
		return numericKeyboard;
	}

	public HashSet<KeyButton> getAllButtons()
	{
		return mAllButtons;
	}

	public HashSet<KeyToggleButton> getAllToggleButtons()
	{
		return mAllToggleButtons;
	}
}
