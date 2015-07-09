//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.09 at 07:06:13 PM BST 
//


package uk.trainwatch.nre.darwin.model.ctt.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Journey" type="{http://www.thalesgroup.com/rtti/XmlTimetable/v8}Schedule" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Association" type="{http://www.thalesgroup.com/rtti/XmlTimetable/v8}Association" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="timetableID" use="required" type="{http://www.thalesgroup.com/rtti/PushPort/CommonTypes/v1}TimetableIDType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "journey",
    "association"
})
@XmlRootElement(name = "PportTimetable")
public class PportTimetable {

    @XmlElement(name = "Journey")
    protected List<Schedule> journey;
    @XmlElement(name = "Association")
    protected List<Association> association;
    @XmlAttribute(name = "timetableID", required = true)
    protected String timetableID;

    /**
     * Gets the value of the journey property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the journey property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getJourney().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Schedule }
     * 
     * 
     */
    public List<Schedule> getJourney() {
        if (journey == null) {
            journey = new ArrayList<Schedule>();
        }
        return this.journey;
    }

    public boolean isSetJourney() {
        return ((this.journey!= null)&&(!this.journey.isEmpty()));
    }

    public void unsetJourney() {
        this.journey = null;
    }

    /**
     * Gets the value of the association property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the association property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAssociation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Association }
     * 
     * 
     */
    public List<Association> getAssociation() {
        if (association == null) {
            association = new ArrayList<Association>();
        }
        return this.association;
    }

    public boolean isSetAssociation() {
        return ((this.association!= null)&&(!this.association.isEmpty()));
    }

    public void unsetAssociation() {
        this.association = null;
    }

    /**
     * Gets the value of the timetableID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimetableID() {
        return timetableID;
    }

    /**
     * Sets the value of the timetableID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimetableID(String value) {
        this.timetableID = value;
    }

    public boolean isSetTimetableID() {
        return (this.timetableID!= null);
    }

}
