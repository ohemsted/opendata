/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.trainwatch.tfl.trackernet;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import uk.trainwatch.tfl.trackernet.model.Root;
import uk.trainwatch.util.xml.JAXBSupport;

/**
 *
 * @author peter
 */
public enum TrackerNetJaxbContext
{

    INSTANCE;

    /**
     * Mapping function to parse XML into JAXB instances
     */
    public static final Function<String, Root> fromXML = s ->
    {
        if( s == null || s.isEmpty() )
        {
            return null;
        }
        try
        {
            return INSTANCE.unmarshall( s );
        } catch( ClassCastException |
                 NullPointerException |
                 JAXBException ex )
        {
            return null;
        }
    };

    public static final Function<Root, String> toXML = p ->
    {
        if( p == null )
        {
            return null;
        }
        try
        {
            return INSTANCE.marshall( p );
        } catch( ClassCastException |
                 NullPointerException |
                 JAXBException ex )
        {
            return null;
        }
    };

    private final Logger log = Logger.getLogger( TrackerNetJaxbContext.class.getName() );

    private final JAXBSupport jaxb;

    private TrackerNetJaxbContext()
    {
        try
        {
            log.log( Level.INFO, "Initialising TFL TrackerNet JAXB" );
            jaxb = new JAXBSupport( 50, Root.class );
        } catch( JAXBException ex )
        {
            Logger.getLogger( TrackerNetJaxbContext.class.getName() ).log( Level.SEVERE, null, ex );
            throw new IllegalStateException( "Failed to create JAXBContect for TFL TrackerNet", ex );
        }
    }

    public <T> T unmarshall( String s )
            throws JAXBException
    {
        return jaxb.unmarshall( s );
    }

    public String marshall( Root p )
            throws JAXBException
    {
        return jaxb.marshallToString( p );
    }

    public <T> T unmarshall( File f )
            throws JAXBException
    {
        return jaxb.unmarshall( f );
    }

    public <T> T unmarshall( Reader r )
            throws JAXBException
    {
        return jaxb.unmarshall( r );
    }

    public <T> T unmarshall( InputStream is )
            throws JAXBException
    {
        return jaxb.unmarshall( is );
    }

    public void marshall( Object v, File f )
            throws JAXBException
    {
        jaxb.marshall( v, f );
    }

    public void marshall( Object v, Writer w )
            throws JAXBException
    {
        jaxb.marshall( v, w );
    }

    public void marshall( Object v, OutputStream os )
            throws JAXBException
    {
        jaxb.marshall( v, os );
    }

}
