package com.example.codeandcoke;

import javax.servlet.annotation.WebServlet;

import org.vaadin.aceeditor.AceEditor;
import org.vaadin.aceeditor.AceMode;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("codeandcoke")
public class CodeandcokeUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = CodeandcokeUI.class, 
		widgetset = "com.example.codeandcoke.widgetset.CodeandcokeWidgetset")
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		setContent(layout);
		
		MenuBar menuBar = new MenuBar();
		layout.addComponent(menuBar);
		
		MenuItem menuHome = menuBar.addItem("Home", null, null);
		MenuItem menuProject = menuBar.addItem("Project", null, null);
		menuProject.addItem("Compile", null);
		menuProject.addItem("Execute", null);
		
		AceEditor editor = new AceEditor();
		editor.setValue("public class HelloWorld");
		editor.setMode(AceMode.java);
		editor.setTabIndex(4);
		editor.setWidth("70%");
		editor.setHeight("300px");
		editor.setWordWrap(true);
		layout.addComponent(editor);

		Button button = new Button("Compile");
		button.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				layout.addComponent(new Label("Compiling . . ."));
			}
		});
		layout.addComponent(button);
		
		Button button2 = new Button("Execute");
		button2.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				layout.addComponent(new Label("Running . . ."));
			}
		});
		layout.addComponent(button2);
	}

}