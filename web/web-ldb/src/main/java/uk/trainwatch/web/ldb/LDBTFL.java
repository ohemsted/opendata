/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.trainwatch.web.ldb;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import uk.trainwatch.util.TimeUtils;
import uk.trainwatch.util.sql.SQLFunction;

/**
 *
 * @author peter
 */
public class LDBTFL
        implements Serializable,
                   LDB
{

    private static final long serialVersionUID = 1L;

    public static final SQLFunction<ResultSet, LDB> fromSQL = rs -> {
        String altdest = rs.getString( "altdest" );
        return new LDBTFL(
                Type.valueOf( rs.getString( "type" ) ),
                TimeUtils.getLocalTime( rs, "tm" ),
                TimeUtils.getLocalDateTime( rs, "ts" ),
                rs.getTimestamp( "ts" ),
                rs.getString( "toc" ),
                rs.getString( "origin" ),
                altdest == null || altdest.isEmpty() ? rs.getString( "destination" ) : altdest,
                rs.getInt( "via" ),
                rs.getInt( "cancreason" ),
                rs.getInt( "latereason" ),
                // Identity
                rs.getLong( "id" ),
                rs.getString( "rid" ),
                rs.getString( "uid" ),
                rs.getInt( "schedule" ),
                // actual arrive/depart
                TimeUtils.getLocalTime( rs, "arr" ),
                TimeUtils.getLocalTime( rs, "dep" ),
                // estimated arrive/depart
                TimeUtils.getLocalTime( rs, "etarr" ),
                TimeUtils.getLocalTime( rs, "etdep" ),
                // timetabled arrive/depart
                TimeUtils.getLocalTime( rs, "pta" ),
                TimeUtils.getLocalTime( rs, "ptd" ),
                // Platform
                rs.getString( "plat" ),
                // Data suppression
                rs.getBoolean( "supp" ),
                rs.getBoolean( "platsup" ),
                rs.getBoolean( "cisplatsup" ),
                // Delay tbi
                TimeUtils.getDuration( rs, "delay" ),
                // Terminated/terminates here
                rs.getBoolean( "term" ),
                // Is the delay unknown
                rs.getBoolean( "ldbdel" ),
                // Train length, 0 for unknown
                rs.getInt( "length" ),
                rs.getString( "curloc" )
        );
    };

    private final Type type;
    private final LocalTime time;
    private final LocalDateTime tsDT;
    private final Timestamp ts;
    private final boolean terminated;
    private final boolean delayUnknown;
    private final String toc;
    private final String dest;
    private final String origin;
    private final int via;
    private final int cancReason;
    private final int lateReason;
    private boolean canc;
    private final long id;
    private final String rid;
    private final String uid;
    private final int scheduleId;
    private final LocalTime arr;
    private final LocalTime dep;
    private final LocalTime eta;
    private final LocalTime etd;
    private final LocalTime pta;
    private final LocalTime ptd;
    private final String plat;
    private final boolean sup;
    private final boolean platSup;
    private final boolean cisPlatSup;
    private final Duration delay;
    private final int length;
    private Collection<CallingPoint> points;
    private final String curloc;

    public LDBTFL(
            Type type,
            LocalTime time,
            LocalDateTime tsDT, Timestamp ts,
            String toc,
            String origin,
            String dest, int via,
            Integer cancReason, Integer lateReason,
            long id, String rid, String uid,
            int scheduleId,
            LocalTime arr, LocalTime dep,
            LocalTime eta, LocalTime etd,
            LocalTime pta, LocalTime ptd,
            String plat,
            boolean sup, boolean platSup, boolean cisPlatSup,
            Duration delay,
            boolean terminated,
            boolean delayUnknown,
            int length,
            String curloc )
    {
        this.type = type;
        this.time = time;

        this.tsDT = tsDT;
        this.ts = ts;

        this.toc = toc;
        this.origin = origin;
        this.dest = dest;
        this.via = via;

        this.cancReason = cancReason;
        this.lateReason = lateReason;

        this.id = id;
        this.rid = rid;
        this.uid = uid;
        this.scheduleId = scheduleId;
        this.arr = arr;
        this.dep = dep;
        this.eta = eta;
        this.etd = etd;
        this.pta = pta;
        this.ptd = ptd;
        this.sup = sup;
        this.plat = plat;
        this.platSup = platSup;
        this.cisPlatSup = cisPlatSup;
        this.delay = delay;
        this.terminated = terminated;
        this.delayUnknown = delayUnknown;
        this.length = length;

        this.curloc = curloc;
    }

    /**
     * The type of entry
     * <p>
     * @return
     */
    @Override
    public Type getType()
    {
        return type;
    }

    @Override
    public Timestamp getTs()
    {
        return ts;
    }

    @Override
    public LocalDateTime getTsDT()
    {
        return tsDT;
    }

    @Override
    public Collection<CallingPoint> getPoints()
    {
        return points;
    }

    public void setPoints( Collection<CallingPoint> points )
    {
        this.points = points;
    }

    @Override
    public int getCancReason()
    {
        return cancReason;
    }

    @Override
    public int getLateReason()
    {
        return lateReason;
    }

    @Override
    public boolean isCanc()
    {
        return canc;
    }

    @Override
    public void setCanc( boolean canc )
    {
        this.canc = canc;
    }

    @Override
    public int getVia()
    {
        return via;
    }

    /**
     * Terminated.
     *
     * If {@link #isArrived()} is true then the train has terminated. If not then it's due to terminate here.
     *
     * @return
     */
    @Override
    public boolean isTerminated()
    {
        return terminated;
    }

    /**
     * Has this train arrived.
     *
     * Note to see if the train has arrived but not yet departed use {@link #isOnPlatform()}
     *
     * @return
     */
    @Override
    public boolean isArrived()
    {
        return arr != null;
    }

    /**
     * Has the train departed
     *
     * @return
     */
    @Override
    public boolean isDeparted()
    {
        return dep != null;
    }

    /**
     * Is the train on the platform.
     *
     * This is defined as having an arrival but no departure time. However a cancelled, terminated or non-timetabled (working)
     * train will not show regardless of the times.
     *
     * @return
     */
    @Override
    public boolean isOnPlatform()
    {
        // Cancelled, terminated or not timetabled then no
        if( isCanc() || isTerminated() || !isTimetabled() ) {
            return false;
        }

        // True if arrived but not departed
        return isArrived() && !isDeparted();
    }

    /**
     * Is the train on time. This is defined as being within ±1 minute of the timetable,
     *
     * @return
     */
    @Override
    public boolean isOntime()
    {
        return delay.isZero() || Math.abs( delay.getSeconds() ) < 60;
    }

    /**
     * No report. This is defined as having no reported arrival nor departure times
     *
     * @return
     */
    @Override
    public boolean isNoReport()
    {
        return !isArrived() && !isDeparted();
    }

    /**
     * The recorded time for this entry as defined by the database.
     *
     * This is the first value in the following sequence that is present: dep, arr, detet, arret, ptd, pta, wtd, wta or wtp.
     *
     * @return
     */
    @Override
    public LocalTime getTime()
    {
        return time;
    }

    @Override
    public long getId()
    {
        return id;
    }

    @Override
    public String getRid()
    {
        return rid;
    }

    @Override
    public String getUid()
    {
        return uid;
    }

    @Override
    public LocalTime getArr()
    {
        return arr;
    }

    @Override
    public LocalTime getDep()
    {
        return dep;
    }

    @Override
    public LocalTime getEta()
    {
        return eta;
    }

    @Override
    public LocalTime getEtd()
    {
        return etd;
    }

    @Override
    public LocalTime getPta()
    {
        return pta;
    }

    @Override
    public LocalTime getPtd()
    {
        return ptd;
    }

    /**
     * Is this timetabled. A working train will return false here
     *
     * @return
     */
    @Override
    public boolean isTimetabled()
    {
        return pta != null || ptd != null;
    }

    @Override
    public String getPlat()
    {
        return plat;
    }

    /**
     * Is this entry suppressed.
     *
     * Licence restriction means that if this returns true then this entry must not be displayed to the general public.
     *
     * @return
     */
    @Override
    public boolean isSup()
    {
        return sup;
    }

    /**
     * Is this entry public.
     *
     * License restriction means that if this returns false then the entry must not be displayed to the general public.
     *
     * @return
     */
    @Override
    public boolean isPublic()
    {
        return !isSup();
    }

    /**
     * Platform suppressed.
     *
     * Licence restriction means that if this returns true then the platform must not be displayed.
     *
     * @return
     */
    @Override
    public boolean isPlatSup()
    {
        return platSup;
    }

    /**
     * Platform suppressed manually from a CIS terminal.
     *
     * Licence restriction means that if this returns true then the platform must not be displayed.
     *
     * @return
     */
    @Override
    public boolean isCisPlatSup()
    {
        return cisPlatSup;
    }

    /**
     * Can the platform be displayed
     *
     * Licence restriction means that if this returns false (i.e. platSup or cisPlatSup is true) then the platform must not be
     * displayed.
     *
     * @return
     */
    @Override
    public boolean isDisplayPlatform()
    {
        return !(platSup || cisPlatSup);
    }

    @Override
    public Duration getDelay()
    {
        return delay;
    }

    @Override
    public boolean isDelayed()
    {
        return delay != null && !(delay.isNegative() || delay.isZero());
    }

    /**
     * Is the service delayed. This will be true if the delay is unknown and "Delayed" should be shown on any display boards.
     *
     * @return
     */
    @Override
    public boolean isDelayUnknown()
    {
        return delayUnknown;
    }

    @Override
    public int getScheduleId()
    {
        return scheduleId;
    }

    @Override
    public String getOrigin()
    {
        return origin;
    }

    @Override
    public String getDest()
    {
        return dest;
    }

    @Override
    public String getToc()
    {
        return toc;
    }

    /**
     * The train length at this location
     *
     * @return number of carriages, 0 for unknown
     */
    @Override
    public int getLength()
    {
        return length;
    }

    @Override
    public String getCurloc()
    {
        return curloc;
    }

    /**
     * Duration from now until the expected time. If the expected time has passed
     * then this returns 0
     * <p>
     * @return Duration, never negative
     */
    @Override
    public Duration getTimeUntil()
    {
        LocalDateTime now = getTsDT();
        LocalDateTime time = now.toLocalDate().atTime( getTime() );

        if( time == null ) {
            return Duration.ZERO;
        }

        if( time.isBefore( now ) && Math.abs( time.getHour() - now.getHour() ) > 18 ) {
            time = time.plusDays( 1 );
        }

        return Duration.between( now, time );
    }

}
