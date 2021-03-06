/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.trainwatch.nrod.smart;

import java.util.Comparator;
import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;
import uk.trainwatch.util.sql.SQLResultSetHandler;

/**
 *
 * @author peter
 */
@XmlRootElement(name = "area")
public class SmartArea
{

    public static final SQLResultSetHandler<SmartArea> fromSQL = rs -> new SmartArea(
            rs.getLong( 1 ),
            rs.getString( 2 ),
            rs.getString( 3 )
    );

    public static final Comparator<SmartArea> COMPARATOR = ( a, b ) -> a.getArea().
            compareTo( b.getArea() );

    private final long id;
    private final String area;
    private final String comment;

    public SmartArea( long id, String area, String comment )
    {
        this.id = id;
        this.area = Objects.requireNonNull( area );
        this.comment = comment;
    }

    public long getId()
    {
        return id;
    }

    public String getArea()
    {
        return area;
    }

    public String getComment()
    {
        return Objects.toString( comment, "" );
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 47 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 47 * hash + Objects.hashCode( this.area );
        return hash;
    }

    @Override
    public boolean equals( Object obj )
    {
        if( obj == null || getClass() != obj.getClass() ) {
            return false;
        }
        final SmartArea other = (SmartArea) obj;
        return this.id == other.id && Objects.equals( this.area, other.area );
    }

    @Override
    public String toString()
    {
        return "SmartArea{" + "id=" + id + ", area=" + area + ", comment=" + comment + '}';
    }

}
