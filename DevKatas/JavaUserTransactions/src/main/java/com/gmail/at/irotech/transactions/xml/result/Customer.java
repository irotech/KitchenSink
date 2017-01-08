package com.gmail.at.irotech.transactions.xml.result;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for Customer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Customer">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}Username"/>
 *         &lt;element ref="{}AverageTransaction"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Customer", propOrder = {
    "username",
    "averageTransaction"
})
public class Customer
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "Username", required = true)
    protected String username;
    @XmlElement(name = "AverageTransaction", required = true, nillable = true)
    protected BigDecimal averageTransaction;

    /**
     * Gets the value of the username property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the value of the username property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsername(String value) {
        this.username = value;
    }

    public boolean isSetUsername() {
        return (this.username!= null);
    }

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

}
