//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.09 at 07:06:13 PM BST 
//


package uk.trainwatch.nre.darwin.model.ctt.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * Defines a Passenger Origin Calling Point
 * 
 * <p>Java class for OR complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OR">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attGroup ref="{http://www.thalesgroup.com/rtti/XmlTimetable/v8}CallPtAttributes"/>
 *       &lt;attGroup ref="{http://www.thalesgroup.com/rtti/XmlTimetable/v8}SchedLocAttributes"/>
 *       &lt;attribute name="wta" type="{http://www.thalesgroup.com/rtti/PushPort/CommonTypes/v1}WTimeType" />
 *       &lt;attribute name="wtd" use="required" type="{http://www.thalesgroup.com/rtti/PushPort/CommonTypes/v1}WTimeType" />
 *       &lt;attribute name="fd" type="{http://www.thalesgroup.com/rtti/PushPort/CommonTypes/v1}TiplocType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OR")
public class OR {

    @XmlAttribute(name = "wta")
    protected String wta;
    @XmlAttribute(name = "wtd", required = true)
    protected String wtd;
    @XmlAttribute(name = "fd")
    protected String fd;
    @XmlAttribute(name = "pta")
    protected String pta;
    @XmlAttribute(name = "ptd")
    protected String ptd;
    @XmlAttribute(name = "tpl", required = true)
    protected String tpl;
    @XmlAttribute(name = "act")
    protected String act;
    @XmlAttribute(name = "planAct")
    protected String planAct;
    @XmlAttribute(name = "can")
    protected Boolean can;
    @XmlAttribute(name = "plat")
    protected String plat;

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
     * Gets the value of the fd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFd() {
        return fd;
    }

    /**
     * Sets the value of the fd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFd(String value) {
        this.fd = value;
    }

    public boolean isSetFd() {
        return (this.fd!= null);
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

    /**
     * Gets the value of the tpl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTpl() {
        return tpl;
    }

    /**
     * Sets the value of the tpl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTpl(String value) {
        this.tpl = value;
    }

    public boolean isSetTpl() {
        return (this.tpl!= null);
    }

    /**
     * Gets the value of the act property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAct() {
        if (act == null) {
            return "  ";
        } else {
            return act;
        }
    }

    /**
     * Sets the value of the act property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAct(String value) {
        this.act = value;
    }

    public boolean isSetAct() {
        return (this.act!= null);
    }

    /**
     * Gets the value of the planAct property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlanAct() {
        return planAct;
    }

    /**
     * Sets the value of the planAct property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlanAct(String value) {
        this.planAct = value;
    }

    public boolean isSetPlanAct() {
        return (this.planAct!= null);
    }

    /**
     * Gets the value of the can property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean getCan() {
        if (can == null) {
            return false;
        } else {
            return can;
        }
    }

    /**
     * Sets the value of the can property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCan(boolean value) {
        this.can = value;
    }

    public boolean isSetCan() {
        return (this.can!= null);
    }

    public void unsetCan() {
        this.can = null;
    }

    /**
     * Gets the value of the plat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlat() {
        return plat;
    }

    /**
     * Sets the value of the plat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlat(String value) {
        this.plat = value;
    }

    public boolean isSetPlat() {
        return (this.plat!= null);
    }

}
