package com.mylearning.design.patterns.abstractFactory;



public class WinFactory extends GUIFactory {

	public Button createButton() {
		// TODO Auto-generated method stub
		return new WinButton();
	}

	

}
