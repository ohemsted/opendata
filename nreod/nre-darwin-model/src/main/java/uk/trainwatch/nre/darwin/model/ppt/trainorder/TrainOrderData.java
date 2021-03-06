//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.09 at 07:06:13 PM BST 
//


package uk.trainwatch.nre.darwin.model.ppt.trainorder;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Defines the sequence of trains making up the train order
 * 
 * <p>Java class for TrainOrderData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TrainOrderData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="first" type="{http://www.thalesgroup.com/rtti/PushPort/TrainOrder/v1}TrainOrderItem"/>
 *         &lt;sequence minOccurs="0">
 *           &lt;element name="second" type="{http://www.thalesgroup.com/rtti/PushPort/TrainOrder/v1}TrainOrderItem"/>
 *           &lt;element name="third" type="{http://www.thalesgroup.com/rtti/PushPort/TrainOrder/v1}TrainOrderItem" minOccurs="0"/>
 *         &lt;/sequence>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TrainOrderData", propOrder = {
    "first",
    "second",
    "third"
})
public class TrainOrderData {

    @XmlElement(required = true)
    protected TrainOrderItem first;
    protected TrainOrderItem second;
    protected TrainOrderItem third;

    /**
     * Gets the value of the first property.
     * 
     * @return
     *     possible object is
     *     {@link TrainOrderItem }
     *     
     */
    public TrainOrderItem getFirst() {
        return first;
    }

    /**
     * Sets the value of the first property.
     * 
     * @param value
     *     allowed object is
     *     {@link TrainOrderItem }
     *     
     */
    public void setFirst(TrainOrderItem value) {
        this.first = value;
    }

    public boolean isSetFirst() {
        return (this.first!= null);
    }

    /**
     * Gets the value of the second property.
     * 
     * @return
     *     possible object is
     *     {@link TrainOrderItem }
     *     
     */
    public TrainOrderItem getSecond() {
        return second;
    }

    /**
     * Sets the value of the second property.
     * 
     * @param value
     *     allowed object is
     *     {@link TrainOrderItem }
     *     
     */
    public void setSecond(TrainOrderItem value) {
        this.second = value;
    }

    public boolean isSetSecond() {
        return (this.second!= null);
    }

    /**
     * Gets the value of the third property.
     * 
     * @return
     *     possible object is
     *     {@link TrainOrderItem }
     *     
     */
    public TrainOrderItem getThird() {
        return third;
    }

    /**
     * Sets the value of the third property.
     * 
     * @param value
     *     allowed object is
     *     {@link TrainOrderItem }
     *     
     */
    public void setThird(TrainOrderItem value) {
        this.third = value;
    }

    public boolean isSetThird() {
        return (this.third!= null);
    }

}
