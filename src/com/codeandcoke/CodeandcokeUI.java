package com.codeandcoke;

import java.awt.Panel;

import javax.servlet.annotation.WebServlet;

import org.vaadin.aceeditor.AceEditor;
import org.vaadin.aceeditor.AceMode;

import com.codeandcoke.util.Util;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("codeandcoke")
public class CodeandcokeUI extends UI {

	private AceEditor editor;
	private VerticalLayout verticalLayout;
	private HorizontalLayout horizontalLayout;
	private Button btCompile, btExecute;
	
	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = CodeandcokeUI.class, widgetset = "com.codeandcoke.widgetset.CodeandcokeWidgetset")
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		
		initVerticalLayout();
		initEditor();
		
		verticalLayout.addComponent(editor);
		verticalLayout.setMargin(true);
		
		initHorizontalLayout();
		initButtonsPanel();
	}
	
	private void initVerticalLayout() {
		verticalLayout = new VerticalLayout();
		setContent(verticalLayout);
		
		verticalLayout.addComponent(Util.getImage("cabecera_480.jpg"));
	}
	
	private void initHorizontalLayout() {
		horizontalLayout = new HorizontalLayout();
		horizontalLayout.setMargin(true);
		horizontalLayout.setSpacing(true);
		verticalLayout.addComponent(horizontalLayout);
	}
	
	private void initEditor() {
		editor = new AceEditor();
		editor.setMode(AceMode.java);
		editor.setHighlightActiveLine(true);
		editor.setHeight("250px");
		editor.setWidth("70%");
		
		verticalLayout.addComponent(editor);
	}
	
	private void initButtonsPanel() {
		btCompile = new Button("Compile");
		btCompile.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				verticalLayout.addComponent(new Label("Compiling . . ."));
			}
		});
		horizontalLayout.addComponent(btCompile);
		
		btExecute = new Button("Execute");
		btExecute.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				verticalLayout.addComponent(new Label("Running . . ."));
			}
		});
		horizontalLayout.addComponent(btExecute);
	}

}