/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.trainwatch.web.train.tags;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import uk.trainwatch.nre.darwin.reference.DarwinReferenceManager;
import uk.trainwatch.util.CDIUtils;

/**
 *
 * @author Peter T Mount
 */
public class ViaTag
        extends BodyTagSupport
{

    @Inject
    private DarwinReferenceManager darwinReferenceManager;

    private Integer value;

    @SuppressWarnings("LeakingThisInConstructor")
    public ViaTag()
    {
        CDIUtils.inject( this );
    }

    @Override
    public void release()
    {
        value = null;
    }

    public void setValue( Integer lateReason )
    {
        this.value = lateReason;
    }

    @Override
    public int doStartTag()
            throws JspException
    {
        if( value != null ) {
            String text = darwinReferenceManager.getViaText( value );
            if( text != null ) {
                try {
                    pageContext.getOut().print( text );
                }
                catch( IOException ex ) {
                    throw new JspException( ex );
                }
            }
        }
        return SKIP_BODY;
    }

}
