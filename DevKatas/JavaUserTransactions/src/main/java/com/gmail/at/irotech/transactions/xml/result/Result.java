package com.gmail.at.irotech.transactions.xml.result;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for Result complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Result">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}AverageTransaction"/>
 *         &lt;element name="Customers" type="{}Customers" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Result", propOrder = {
    "averageTransaction",
    "customers"
})
@XmlRootElement(name = "Result")
public class Result
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "AverageTransaction", required = true, nillable = true)
    protected BigDecimal averageTransaction;
    @XmlElement(name = "Customers")
    protected Customers customers;

    /**
     * Gets the value of the averageTransaction property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAverageTransaction() {
        return averageTransaction;
    }

    /**
     * Sets the value of the averageTransaction property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAverageTransaction(BigDecimal value) {
        this.averageTransaction = value;
    }

    public boolean isSetAverageTransaction() {
        return (this.averageTransaction!= null);
    }

    /**
     * Gets the value of the customers property.
     * 
     * @return
     *     possible object is
     *     {@link Customers }
     *     
     */
    public Customers getCustomers() {
        return customers;
    }

    /**
     * Sets the value of the customers property.
     * 
     * @param value
     *     allowed object is
     *     {@link Customers }
     *     
     */
    public void setCustomers(Customers value) {
        this.customers = value;
    }

    public boolean isSetCustomers() {
        return (this.customers!= null);
    }

}
