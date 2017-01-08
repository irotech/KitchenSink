package com.gmail.at.irotech.transactions.utils;

import org.apache.commons.io.IOUtils;

public class ClasspathFileImporter {
	
	public String importFileAsString(String filename) throws Exception {
	    return IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream(filename), "UTF-8");
	}

}
