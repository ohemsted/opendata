//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.09 at 07:06:13 PM BST 
//


package uk.trainwatch.nre.darwin.model.ppt.tddata;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Indicate a corrected Tracking ID (headcode) for a service in a TD berth.
 * 
 * <p>Java class for TrackingID complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TrackingID">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="berth" type="{http://www.thalesgroup.com/rtti/PushPort/TDData/v1}FullTDBerthID"/>
 *         &lt;element name="incorrectTrainID" type="{http://www.thalesgroup.com/rtti/PushPort/CommonTypes/v1}TrainIdType"/>
 *         &lt;element name="correctTrainID" type="{http://www.thalesgroup.com/rtti/PushPort/CommonTypes/v1}TrainIdType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TrackingID", propOrder = {
    "berth",
    "incorrectTrainID",
    "correctTrainID"
})
public class TrackingID {

    @XmlElement(required = true)
    protected FullTDBerthID berth;
    @XmlElement(required = true)
    protected String incorrectTrainID;
    @XmlElement(required = true)
    protected String correctTrainID;

    /**
     * Gets the value of the berth property.
     * 
     * @return
     *     possible object is
     *     {@link FullTDBerthID }
     *     
     */
    public FullTDBerthID getBerth() {
        return berth;
    }

    /**
     * Sets the value of the berth property.
     * 
     * @param value
     *     allowed object is
     *     {@link FullTDBerthID }
     *     
     */
    public void setBerth(FullTDBerthID value) {
        this.berth = value;
    }

    public boolean isSetBerth() {
        return (this.berth!= null);
    }

    /**
     * Gets the value of the incorrectTrainID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncorrectTrainID() {
        return incorrectTrainID;
    }

    /**
     * Sets the value of the incorrectTrainID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncorrectTrainID(String value) {
        this.incorrectTrainID = value;
    }

    public boolean isSetIncorrectTrainID() {
        return (this.incorrectTrainID!= null);
    }

    /**
     * Gets the value of the correctTrainID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorrectTrainID() {
        return correctTrainID;
    }

    /**
     * Sets the value of the correctTrainID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorrectTrainID(String value) {
        this.correctTrainID = value;
    }

    public boolean isSetCorrectTrainID() {
        return (this.correctTrainID!= null);
    }

}
