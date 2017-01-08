package com.gmail.at.irotech.transactions;

import static org.junit.Assert.assertEquals;

import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.gmail.at.irotech.transactions.utils.ClasspathFileImporter;

public class TransactionAnalyserTest {

	private TransactionAnalyser ta = new TransactionAnalyser();
	private ClasspathFileImporter fileImport = new ClasspathFileImporter();

	@Test
	public void givenSampleInput1ReturnExpectedAverageTransactions() throws Exception {
		
		String inputXml = fileImport.importFileAsString("SampleInput1.xml");
		String resultXml = ta.analyse(inputXml);

		checkAvgTransaction("15.93", resultXml);
		checkCustomerAvgTransaction("JON", "15.80", resultXml);
		checkCustomerAvgTransaction("JEREMY", "13.40", resultXml);
		checkCustomerAvgTransaction("JESSE", "18.60", resultXml);
	}

	@Test
	public void givenSampleInput2ReturnExpectedAverageTransactions() throws Exception {
		
		String inputXml = fileImport.importFileAsString("SampleInput2.xml");
		String resultXml = ta.analyse(inputXml);

		checkAvgTransaction("9.50", resultXml);
		checkCustomerAvgTransaction("ABIGAIL", "7.90", resultXml);
		checkCustomerAvgTransaction("ALEXANDER", "7.30", resultXml);
		checkCustomerAvgTransaction("AVA", "13.40", resultXml);
		checkCustomerAvgTransaction("ETHAN", "7.20", resultXml);
		checkCustomerAvgTransaction("ISABELLA", "8.30", resultXml);
		checkCustomerAvgTransaction("JACOB", "10.30", resultXml);
		checkCustomerAvgTransaction("JAYDEN", "11.30", resultXml);
		checkCustomerAvgTransaction("MADISON", "11.30", resultXml);
		checkCustomerAvgTransaction("SOPHIA", "9.00", resultXml);
		checkCustomerAvgTransaction("WILLIAM", "9.00", resultXml);
	}

	private void checkAvgTransaction(String avgTransaction, String xml) throws Exception {

		InputSource inputSource = new InputSource(new StringReader(xml));

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db.parse(inputSource);

		XPathFactory xPathfactory = XPathFactory.newInstance();
		XPath xpath = xPathfactory.newXPath();

		XPathExpression expr = xpath.compile("/Result/AverageTransaction");
		String value = (String) expr.evaluate(document, XPathConstants.STRING);

		assertEquals("Average transaction was not correct", avgTransaction, value);
	}

	private void checkCustomerAvgTransaction(String customerName, String avgTransaction, String xml) throws Exception {

		InputSource inputSource = new InputSource(new StringReader(xml));

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db.parse(inputSource);

		XPathFactory xPathfactory = XPathFactory.newInstance();
		XPath xpath = xPathfactory.newXPath();

		XPathExpression expr = xpath.compile("/Result/Customers/Customer[Username = '" + customerName + "']/AverageTransaction");
		String value = (String) expr.evaluate(document, XPathConstants.STRING);

		assertEquals("Average customer transaction was not correct : " + customerName, avgTransaction, value);
	}

}
