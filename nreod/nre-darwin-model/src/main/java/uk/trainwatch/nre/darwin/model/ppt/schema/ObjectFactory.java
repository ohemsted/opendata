//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.09 at 07:06:13 PM BST 
//


package uk.trainwatch.nre.darwin.model.ppt.schema;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the uk.trainwatch.nre.darwin.model.ppt.schema package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: uk.trainwatch.nre.darwin.model.ppt.schema
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Pport }
     * 
     */
    public Pport createPport() {
        return new Pport();
    }

    /**
     * Create an instance of {@link Pport.TimeTableId }
     * 
     */
    public Pport.TimeTableId createPportTimeTableId() {
        return new Pport.TimeTableId();
    }

    /**
     * Create an instance of {@link Pport.GetSnapshotReq }
     * 
     */
    public Pport.GetSnapshotReq createPportGetSnapshotReq() {
        return new Pport.GetSnapshotReq();
    }

    /**
     * Create an instance of {@link Pport.GetFullSnapshotReq }
     * 
     */
    public Pport.GetFullSnapshotReq createPportGetFullSnapshotReq() {
        return new Pport.GetFullSnapshotReq();
    }

    /**
     * Create an instance of {@link Pport.FailureResp }
     * 
     */
    public Pport.FailureResp createPportFailureResp() {
        return new Pport.FailureResp();
    }

    /**
     * Create an instance of {@link Pport.UR }
     * 
     */
    public Pport.UR createPportUR() {
        return new Pport.UR();
    }

    /**
     * Create an instance of {@link DataResponse }
     * 
     */
    public DataResponse createDataResponse() {
        return new DataResponse();
    }

}
