/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.trainwatch.web.ldb.cache;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import javax.cache.annotation.CacheDefaults;
import javax.cache.annotation.CacheKey;
import javax.cache.annotation.CacheResult;
import javax.enterprise.context.ApplicationScoped;
import javax.sql.DataSource;
import uk.trainwatch.util.TimeUtils;
import uk.trainwatch.util.sql.SQL;
import uk.trainwatch.web.ldb.LDB;

/**
 *
 * @author peter
 */
@ApplicationScoped
@CacheDefaults(cacheName = "LDBDepartureCache")
public class LDBDepartureCache
{

    private static final String SELECT = "SELECT *,"
                                         + "   s.toc AS toc,"
                                         + "   o.name AS origin,"
                                         + "   d.name AS destination,"
                                         + "   f.ts AS ts"
                                         + " FROM darwin.forecast f"
                                         + " INNER JOIN darwin.schedule s ON f.schedule=s.id"
                                         + " INNER JOIN darwin.location d ON s.dest=d.tpl"
                                         + " INNER JOIN darwin.location o ON s.origin=o.tpl"
                                         + " INNER JOIN darwin.forecast_entry fe ON f.id=fe.fid"
                                         + " INNER JOIN darwin.location l ON fe.tpl=l.tpl"
                                         + " INNER JOIN darwin.crs c ON l.crs=c.id"
                                         // Only for this station
                                         + " WHERE c.crs=?"
                                         // Flagged for display
                                         + " AND fe.ldb=TRUE"
                                         // all non-passes required so we can use Terminates/Starts here etc
                                         + " AND fe.wtp IS NULL";

    @Resource(name = "jdbc/rail")
    private DataSource dataSource;

    /**
     * Timetabled departures
     * <p>
     * @param key
     * @return <p>
     * @throws SQLException
     */
    @CacheResult
    public Collection<LDB> getDepartures( @CacheKey LocationTimeKey key )
            throws SQLException
    {
        // FIXME allow to be shown whilst at the platform. However this might cause issues with a train sitting at the platform but not on the boards?
        LocalTime time = LocalTime.ofSecondOfDay( key.getTime() );
        LocalTime timeAfter = time.minusMinutes( 2 );
        LocalTime timeBefore = time.plusHours( 1 );
        boolean midnight = timeBefore.isBefore( timeAfter );

        // Filter a LocalTime to fit between the required times, accounting for midnight
        Predicate<LocalTime> filter = midnight
                                      ? t -> t.isAfter( timeAfter ) || t.isBefore( timeBefore )
                                      : t -> t.isAfter( timeAfter ) && t.isBefore( timeBefore );

        final String crs = key.getCrs();

        // FIXME this gets all trains at a location not by time from the db
        
        try( Connection con = dataSource.getConnection() ) {
            // must have a working departure
            // order by first of actual departure, estimated then working departure
            try( PreparedStatement ps = SQL.prepare( con, SELECT, crs ) ) {
                return SQL.stream( ps, LDB.fromSQL ).
                        // Filter only public entries
                        filter( LDB::isPublic ).
                        // Filter those that have departed
                        filter( l -> !l.isDeparted() ).
                        // Filter out those out of range, accounting for midnight
                        filter( l -> filter.test( l.getTime() ) ).
                        // Sort to Darwin rules, accounts for midnight
                        sorted( ( a, b ) -> TimeUtils.compareLocalTimeDarwin.compare( a.getTime(), b.getTime() ) ).
                        collect( Collectors.toList() );
            }
        }
    }

}