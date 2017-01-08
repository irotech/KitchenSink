package com.gmail.at.irotech.transactions.utils;

import static org.junit.Assert.*;

import org.junit.Test;

import com.gmail.at.irotech.transactions.utils.ClasspathFileImporter;

public class ClasspathFileImporterTest {

	ClasspathFileImporter fileImporter = new ClasspathFileImporter();
	
	@Test
	public void readXmlAsString() throws Exception {
		String sampleInput1 = fileImporter.importFileAsString("SampleInput1.xml");
		
		assertNotNull("Input file was null", sampleInput1);
		
		assertTrue(sampleInput1.contains("JON"));
		assertTrue(sampleInput1.contains("JEREMY"));
		assertTrue(sampleInput1.contains("JESSE"));
		
		System.out.println(sampleInput1);
	}

}
