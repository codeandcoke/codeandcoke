package com.codeandcoke;

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
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("codeandcoke")
public class CodeandcokeUI extends UI {

	private VerticalLayout layout;
	private HorizontalLayout editorLayout;
	private AceEditor editor;
	private TextArea taOutput;
	private Button btCompile, btExecute;
	private CncMenuBar menuBar;
	private CncCompiler compiler;
	private Label lbMessage;
	
	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = CodeandcokeUI.class, widgetset = "com.codeandcoke.widgetset.CodeandcokeWidgetset")
	public static class Servlet extends VaadinServlet {
	}
	
	public Button getBtCompile() {
		return btCompile;
	}
	
	public Button getBtExecute() {
		return btExecute;
	}

	@Override
	protected void init(VaadinRequest request) {
		
		compiler = new CncCompiler();
		
		initLayout();
		showMenuBar();
		initEditorLayout();
		showEditor();
		showButtonsPanel();
		showOutputArea();
	}
	
	private void initLayout() {
		layout = new VerticalLayout();
		setContent(layout);
		
		layout.addComponent(Util.getImage("cabecera_480.jpg"));
	}
	
	private void showMenuBar() {
		
		menuBar = new CncMenuBar(this);
		layout.addComponent(menuBar.getMenuBar());
	}
	
	private void initEditorLayout() {
		
		layout.setMargin(true);
		
		editorLayout = new HorizontalLayout();
		//editorLayout.setMargin(true);
		editorLayout.setSpacing(true);
		layout.addComponent(editorLayout);
	}
	
	private void showOutputArea() {
		
		lbMessage = new Label();
		layout.addComponent(lbMessage);
		
		taOutput = new TextArea();
		taOutput.setHeight("250px");
		taOutput.setWidth("800px");
		
		layout.addComponent(taOutput);
	}
	
	private void showEditor() {
		editor = new AceEditor();
		editor.setMode(AceMode.java);
		editor.setHighlightActiveLine(true);
		editor.setHeight("250px");
		editor.setWidth("800px");
		
		editorLayout.addComponent(editor);
	}
	
	private void showButtonsPanel() {
		
		VerticalLayout buttonsLayout = new VerticalLayout();
		buttonsLayout.setMargin(true);
		buttonsLayout.setSpacing(true);
		
		editorLayout.addComponent(buttonsLayout);
		
		btCompile = new Button("Compile");
		btCompile.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				lbMessage.setValue("Compiling . . .");
				compiler.setCode(editor.getValue());
				compiler.compile();
			}
		});
		buttonsLayout.addComponent(btCompile);
		
		btExecute = new Button("Execute");
		btExecute.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				lbMessage.setValue("Running . . .");
				compiler.setCode(editor.getValue());
				compiler.execute();
			}
		});
		buttonsLayout.addComponent(btExecute);
	}

}