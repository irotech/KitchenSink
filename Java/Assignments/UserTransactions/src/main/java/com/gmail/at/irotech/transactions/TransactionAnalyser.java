package com.gmail.at.irotech.transactions;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import javax.xml.bind.JAXBException;

import com.gmail.at.irotech.transactions.utils.JAXBUtils;
import com.gmail.at.irotech.transactions.xml.customers.Customer;
import com.gmail.at.irotech.transactions.xml.customers.Customers;
import com.gmail.at.irotech.transactions.xml.result.Result;

public class TransactionAnalyser {

	private String resultXML;

	public String analyse(String xml) {

		Result result = new Result();
		BigDecimal customerAverage;
		BigDecimal overallAverage = new BigDecimal(0);
		int overallAverageCounter = 0;
		com.gmail.at.irotech.transactions.xml.result.Customers outputCustomers = 
				new com.gmail.at.irotech.transactions.xml.result.Customers();
		com.gmail.at.irotech.transactions.xml.result.Customer outputCustomer;
		result.setCustomers(outputCustomers);

		try {

			Customers customers = (Customers) JAXBUtils.xjcUnmarshal(Customers.class, xml);

			for (Customer inputCustomer : customers.getCustomers()) {

				outputCustomer = new com.gmail.at.irotech.transactions.xml.result.Customer();
				outputCustomer.setUsername(inputCustomer.getUsername());

				Integer inputCustomerAverage = 0;
				
				if (null != inputCustomer.getTransactions()) {

					List<Integer> items = inputCustomer.getTransactions().getItems();

					int counter = 0;
					for (; counter < items.size(); counter++) {
						inputCustomerAverage += items.get(counter);
					}

					// Skip null AverageTransaction or <AverageTransaction xsi:nil="true" /> from XML
					if (counter > 0) {
						// Current Customer Average Calculation
						customerAverage = new BigDecimal(inputCustomerAverage).divide(new BigDecimal(counter), 2, RoundingMode.DOWN);
						outputCustomer.setAverageTransaction(customerAverage);
						// Add current Customer Average To Total
						overallAverage = overallAverage.add(customerAverage);
						overallAverageCounter++;
					}

				}
				outputCustomers.getCustomers().add(outputCustomer);

			}
			// Overall Average Calculation
			overallAverage = overallAverage.divide(new BigDecimal(overallAverageCounter), 2, RoundingMode.DOWN);
			result.setAverageTransaction(overallAverage);

			resultXML = JAXBUtils.xjcMarshal(result);

		} catch (JAXBException e) {
			resultXML = e.getMessage();
		}

		System.out.println(resultXML);

		return resultXML;
	}

}
