package com.gmail.at.irotech.transactions.utils;

import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class JAXBUtils {

	/*
	 * From Java Object -> to String XML
	 */
	public static String xjcMarshal(Object obj) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		StringWriter writer = new StringWriter();
		marshaller.marshal(obj, writer);
		return writer.toString();
	}

	/*
	 * From String XML -> to Java Object
	 */
	public static Object xjcUnmarshal(Class<?> cls, String xml) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(cls);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		StringReader reader = new StringReader(xml);
		return unmarshaller.unmarshal(reader);
	}

}
