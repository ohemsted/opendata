/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.trainwatch.nrod.tpnm.tool;

import java.util.function.Function;
import javax.xml.bind.JAXBException;
import org.w3c.dom.Node;
import uk.trainwatch.nrod.tpnm.model.TpsData;
import uk.trainwatch.util.xml.JAXBSupport;
import uk.trainwatch.util.xml.XMLDomWriter;

/**
 *
 * @author peter
 */
public class TPNMUnmarshaller
        implements Function<Node, Object>
{

    private static volatile JAXBSupport jaxb;

    private static JAXBSupport getJaxb()
            throws JAXBException
    {
        if( jaxb == null )
        {
            synchronized( TPNMUnmarshaller.class )
            {
                if( jaxb == null )
                {
                    jaxb = new JAXBSupport( TpsData.class.getPackage().getName() );
                }
            }
        }
        return jaxb;
    }

    @Override
    public Object apply( Node t )
    {
        try
        {
            return t == null ? null : getJaxb().unmarshall( t );
        } catch( JAXBException ex )
        {
            throw new RuntimeException( XMLDomWriter.toXml( t ), ex );
        }
    }

}
