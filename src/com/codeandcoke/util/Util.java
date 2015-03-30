package com.codeandcoke.util;

import java.io.File;

import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Image;

public class Util {

	public static Image getImage(String name) {
		
		String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
		FileResource fileResource = new FileResource(new File(basepath + "/WEB-INF/img/" + name));
		Image image = new Image(null, fileResource);
		
		return image;
	}
}
