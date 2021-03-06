/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.trainwatch.nrod.timetable.cif.record;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.function.Function;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import uk.trainwatch.nrod.location.Tiploc;
import uk.trainwatch.nrod.timetable.util.Activity;
import uk.trainwatch.util.JsonUtils;
import uk.trainwatch.util.TimeUtils;

/**
 *
 * @author Peter T Mount
 */
public class OriginLocation
        extends Location
        implements Serializable
{

    private static final long serialVersionUID = 1L;

    static final Function<CIFParser, Record> factory = p -> new OriginLocation(
            p.getTiplocSuffix(),
            p.getTime_hhmmH(),
            p.getTime_hhmm(),
            p.getString( 3 ),
            p.getString( 3 ),
            p.getAllowance(),
            p.getAllowance(),
            p.getActivity(),
            p.getAllowance()
    );

    /**
     * Function that can decode a JsonObject to an OriginLocation
     */
    public static final Function<JsonObject, OriginLocation> fromJson = o -> new OriginLocation(
            new Tiploc( o.getString( "tiploc" ) ),
            JsonUtils.getLocalTime( o, "workDeparture" ),
            JsonUtils.getLocalTime( o, "pubDeparture" ),
            JsonUtils.getString( o, "platform" ),
            JsonUtils.getString( o, "line" ),
            JsonUtils.getInt( o, "engAllowance" ),
            JsonUtils.getInt( o, "pathAllowance" ),
            JsonUtils.getEnumArray( Activity.class, o, "activity" ),
            JsonUtils.getInt( o, "perfAllowance" )
    );

    public static final Function<OriginLocation, JsonObjectBuilder> toJsonBuilder = l -> Json.createObjectBuilder().
            add( "type", l.getRecordType().
                 toString() ).
            add( "tiploc", l.getLocation().
                 getKey() ).
            add( "workDeparture", TimeUtils.toJson( l.getWorkDeparture() ) ).
            add( "pubDeparture", TimeUtils.toJson( l.getPublicDeparture() ) ).
            add( "platform", l.getPlatform() ).
            add( "line", l.getLine() ).
            add( "engAllowance", l.getEngAllowance() ).
            add( "pathAllowance", l.getPathAllowance() ).
            add( "perfAllowance", l.getPerfAllowance() ).
            add( "activity", JsonUtils.getArray( l.getActivity() ) );

    public static final Function<OriginLocation, JsonObject> toJson = l -> toJsonBuilder.apply( l ).build();

    private final LocalTime workDeparture;
    private final LocalTime publicDeparture;
    private final String platform;
    private final String line;
    private final int engAllowance;
    private final int pathAllowance;
    private final Activity[] activity;
    private final int perfAllowance;

    public OriginLocation( Tiploc location,
                           LocalTime workDeparture,
                           LocalTime publicDeparture,
                           String platform,
                           String line,
                           int engAllowance,
                           int pathAllowance,
                           Activity[] activity,
                           int perfAllowance )
    {
        super( RecordType.LO, location );
        this.workDeparture = workDeparture;
        this.publicDeparture = publicDeparture;
        this.platform = platform.trim();
        this.line = line.trim();
        this.engAllowance = engAllowance;
        this.pathAllowance = pathAllowance;
        this.activity = activity;
        this.perfAllowance = perfAllowance;
    }

    @Override
    public void accept( RecordVisitor v )
    {
        v.visit( this );
    }

    public LocalTime getWorkDeparture()
    {
        return workDeparture;
    }

    public LocalTime getPublicDeparture()
    {
        return publicDeparture;
    }

    public String getPlatform()
    {
        return platform;
    }

    public String getLine()
    {
        return line;
    }

    public int getEngAllowance()
    {
        return engAllowance;
    }

    public int getPathAllowance()
    {
        return pathAllowance;
    }

    public Activity[] getActivity()
    {
        return activity;
    }

    public int getPerfAllowance()
    {
        return perfAllowance;
    }

    @Override
    public LocalTime getTime()
    {
        return getWorkDeparture();
    }

}
