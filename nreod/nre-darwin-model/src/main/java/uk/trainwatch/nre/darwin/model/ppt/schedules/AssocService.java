//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.09 at 07:06:13 PM BST 
//


package uk.trainwatch.nre.darwin.model.ppt.schedules;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AssocService complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AssocService">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attGroup ref="{http://www.thalesgroup.com/rtti/PushPort/CommonTypes/v1}CircularTimes"/>
 *       &lt;attribute name="rid" use="required" type="{http://www.thalesgroup.com/rtti/PushPort/CommonTypes/v1}RIDType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AssocService")
public class AssocService {

    @XmlAttribute(name = "rid", required = true)
    protected String rid;
    @XmlAttribute(name = "wta")
    protected String wta;
    @XmlAttribute(name = "wtd")
    protected String wtd;
    @XmlAttribute(name = "wtp")
    protected String wtp;
    @XmlAttribute(name = "pta")
    protected String pta;
    @XmlAttribute(name = "ptd")
    protected String ptd;

    /**
     * Gets the value of the rid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRid() {
        return rid;
    }

    /**
     * Sets the value of the rid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRid(String value) {
        this.rid = value;
    }

    public boolean isSetRid() {
        return (this.rid!= null);
    }

    /**
     * Gets the value of the wta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWta() {
        return wta;
    }

    /**
     * Sets the value of the wta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWta(String value) {
        this.wta = value;
    }

    public boolean isSetWta() {
        return (this.wta!= null);
    }

    /**
     * Gets the value of the wtd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWtd() {
        return wtd;
    }

    /**
     * Sets the value of the wtd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWtd(String value) {
        this.wtd = value;
    }

    public boolean isSetWtd() {
        return (this.wtd!= null);
    }

    /**
     * Gets the value of the wtp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWtp() {
        return wtp;
    }

    /**
     * Sets the value of the wtp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWtp(String value) {
        this.wtp = value;
    }

    public boolean isSetWtp() {
        return (this.wtp!= null);
    }

    /**
     * Gets the value of the pta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPta() {
        return pta;
    }

    /**
     * Sets the value of the pta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPta(String value) {
        this.pta = value;
    }

    public boolean isSetPta() {
        return (this.pta!= null);
    }

    /**
     * Gets the value of the ptd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPtd() {
        return ptd;
    }

    /**
     * Sets the value of the ptd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPtd(String value) {
        this.ptd = value;
    }

    public boolean isSetPtd() {
        return (this.ptd!= null);
    }

}
