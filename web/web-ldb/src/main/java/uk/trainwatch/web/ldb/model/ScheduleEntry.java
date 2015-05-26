/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.trainwatch.web.ldb.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalTime;
import java.util.stream.Collectors;
import uk.trainwatch.util.TimeUtils;
import uk.trainwatch.util.sql.SQL;
import uk.trainwatch.util.sql.SQLBiConsumer;
import uk.trainwatch.util.sql.SQLFunction;

/**
 *
 * @author peter
 */
public class ScheduleEntry
{

    public static final String SELECT = "SELECT s.id, s.schedule, s.type, t.tpl as tpl, s.pta, s.ptd, s.wta, s.wtd, s.wtp, s.act, s.can"
                                        + " FROM darwin.schedule_entry s"
                                        + " INNER JOIN darwin.tiploc t ON s.tpl=t.id"
                                        + " WHERE s.schedule=?";

    public static final SQLFunction<ResultSet, ScheduleEntry> fromSQL = rs -> new ScheduleEntry(
            rs.getLong( "id" ),
            rs.getLong( "schedule" ),
            rs.getString( "type" ),
            rs.getString( "tpl" ),
            TimeUtils.getLocalTime( rs, "pta" ),
            TimeUtils.getLocalTime( rs, "ptd" ),
            TimeUtils.getLocalTime( rs, "wta" ),
            TimeUtils.getLocalTime( rs, "wtd" ),
            TimeUtils.getLocalTime( rs, "wtp" ),
            rs.getString( "act" ),
            rs.getBoolean( "can" )
    );

    public static final SQLBiConsumer<Connection, Train> populate = ( c, t ) -> {
        if( t.isSchedulePresent() ) {
            try( PreparedStatement ps = SQL.prepare( c, SELECT, t.getScheduleId() ) ) {
                t.setScheduleEntries( SQL.stream( ps, fromSQL ).collect( Collectors.toList() ) );
            }
        }
    };

    private final long id;
    private final long schedule;
    private final String type;
    private final String tpl;
    private final LocalTime pta;
    private final LocalTime ptd;
    private final LocalTime wta;
    private final LocalTime wtd;
    private final LocalTime wtp;
    private final String act;
    private final boolean can;

    public ScheduleEntry( long id, long schedule, String type, String tpl, LocalTime pta, LocalTime ptd, LocalTime wta, LocalTime wtd, LocalTime wtp, String act,
                          boolean can )
    {
        this.id = id;
        this.schedule = schedule;
        this.type = type;
        this.tpl = tpl;
        this.pta = pta;
        this.ptd = ptd;
        this.wta = wta;
        this.wtd = wtd;
        this.wtp = wtp;
        this.act = act;
        this.can = can;
    }

    public long getId()
    {
        return id;
    }

    public long getSchedule()
    {
        return schedule;
    }

    public String getType()
    {
        return type;
    }

    public String getTpl()
    {
        return tpl;
    }

    public LocalTime getPta()
    {
        return pta;
    }

    public LocalTime getPtd()
    {
        return ptd;
    }

    public LocalTime getWta()
    {
        return wta;
    }

    public LocalTime getWtd()
    {
        return wtd;
    }

    public LocalTime getWtp()
    {
        return wtp;
    }

    public String getAct()
    {
        return act;
    }

    public boolean isCan()
    {
        return can;
    }

}