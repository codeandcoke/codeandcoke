package com.codeandcoke;

import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;

public class CncMenuBar {

	private CodeandcokeUI ui;
	private MenuBar menuBar;
	private MenuBar.Command commandCompile, commandExecute;
	
	public CncMenuBar(CodeandcokeUI ui) {
		
		this.ui = ui;
		menuBar = new MenuBar();
		
		initCommands();
		initBar();
	}
	
	public MenuBar getMenuBar() {
		return menuBar;
	}
	
	private void initBar() {
		
		MenuItem homeMenu = menuBar.addItem("Home", null);
		MenuItem projectMenu = menuBar.addItem("Project", null);
		projectMenu.addItem("Compile", commandCompile);
		projectMenu.addItem("Execute", commandExecute);
	}
	
	private void initCommands() {
		
		commandCompile = new MenuBar.Command() {
			@Override
			public void menuSelected(MenuItem selectedItem) {
				ui.getBtCompile().click();
			}
		};
		
		commandExecute = new MenuBar.Command() {
			@Override
			public void menuSelected(MenuItem selectedItem) {
				ui.getBtExecute().click();
			}
		};
	}
	
}
