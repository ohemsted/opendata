/*
 * Copyright 2014 peter.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.trainwatch.nrod.timetable.tools;

import java.nio.file.Path;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author peter
 */
//@MetaInfServices( Utility.class )
public class TimeTables
//        extends DBUtility
{

    protected static final Logger LOG = Logger.getLogger( TimeTables.class.getName() );
    private static final String SCHEMA = "timetable";
    private List<Path> cifFiles;

    private boolean fullImport;

    private boolean includeAssociations;
    private boolean includeTiploc;
    private boolean includeSchedules;

//    public TimeTables()
//    {
//        super();
//        getOptions().
//                addOption( null, "full", false, "Full rather than Incremental import" ).
//                addOption( "a", "associations", false, "Include train association updates" ).
//                addOption( "t", "tiploc", false, "Include tiploc updates" ).
//                addOption( "s", "schedule", false, "Include schedule updates" );
//    }
//
//    @Override
//    @SuppressWarnings( "ThrowableInstanceNeverThrown" )
//    public boolean parseArgs( CommandLine cmd )
//    {
//        super.parseArgs( cmd );
//
//        fullImport = cmd.hasOption( "full" );
//
//        includeAssociations = cmd.hasOption( "associations" );
//        includeTiploc = cmd.hasOption( "tiploc" );
//        includeSchedules = cmd.hasOption( "schedule" );
//
//        // Default to both
//        if( !includeAssociations && !includeSchedules && !includeTiploc )
//        {
//            includeAssociations = true;
//            includeSchedules = true;
//            includeTiploc = true;
//        }
//
//        // The first one will be the CIF name
//        cifFiles = Utility.getArgFileList( cmd );
//
//        return !cifFiles.isEmpty();
//    }
//
//    @Override
//    public void runUtility()
//            throws Exception
//    {
//        if( !fullImport )
//        {
//            // Remove any old schedules first. No point if doing a full import
//            removeOldSchedules();
//        }
//        importFiles( cifFiles, new Parser() );
//    }
//
//    @Override
//    protected void initDB( Connection con )
//            throws SQLException
//    {
//        if( fullImport )
//        {
//            LOG.log( Level.INFO, "Clearing down Schedule database" );
//
//            SQL.deleteTable( con, SCHEMA, "schedule_loc" );
//
//            // Clear down our existing tables
//            Arrays.asList(
//                    "schedule",
//                    "association",
//                    "tiploc",
//                    "trainuid",
//                    "lastupdate" ).
//                    forEach( t -> SQL.deleteIdTable( con, SCHEMA, t ) );
//
//            // Initialise our enum tables
//            Arrays.asList(
//                    ATOCCode.class,
//                    ATSCode.class,
//                    Activity.class,
//                    AssociationCategory.class,
//                    AssociationDateIndicator.class,
//                    AssociationType.class,
//                    BankHolidayRunning.class,
//                    BusSec.class,
//                    Catering.class,
//                    OperatingCharacteristics.class,
//                    PowerType.class,
//                    Reservations.class,
//                    STPIndicator.class,
//                    ServiceBranding.class,
//                    Sleepers.class,
//                    TimingLoad.class,
//                    TrainCategory.class,
//                    TrainClass.class,
//                    TrainStatus.class ).
//                    forEach( c -> SQL.updateEnumTable( con, SCHEMA, c ) );
//
//            prepareDaysRun( con );
//
//            LOG.log( Level.INFO, "Schedule database is now clean" );
//        } else
//        {
//            // For an update delete any schedules in the past
//            LOG.log( Level.INFO, "Deleting expires schedules" );
//            try( Statement s = con.createStatement() )
//            {
//                int count = s.executeUpdate( "DELETE FROM " + SCHEMA + ".schedule WHERE runsto < now()::date" );
//                LOG.log( Level.INFO, () -> "Expired " + count + " schedules" );
//            }
//        }
//    }
//
//    /**
//     * Prepares the daysrun table which maps days of week to a single id
//     * <p>
//     * @param con <p>
//     * @throws SQLException
//     */
//    private void prepareDaysRun( Connection con )
//            throws SQLException
//    {
//        String table = "daysrun";
//
//        LOG.log( Level.INFO, () -> "Reinitialising " + table );
//
//        SQL.deleteTable( con, SCHEMA, table );
//
//        try( PreparedStatement s = con.prepareStatement(
//                "INSERT INTO timetable." + table
//                + " (id,monday,tuesday,wednesday,thursday,friday,saturday,sunday)"
//                + " VALUES( ?,?,?,?,?,?,?,?)" ) )
//        {
//            for( int i = 0; i < 0x80; i++ )
//            {
//                s.setInt( 1, i );
//                s.setBoolean( 2, (i & 0x01) != 0 );
//                s.setBoolean( 3, (i & 0x02) != 0 );
//                s.setBoolean( 4, (i & 0x04) != 0 );
//                s.setBoolean( 5, (i & 0x08) != 0 );
//                s.setBoolean( 6, (i & 0x10) != 0 );
//                s.setBoolean( 7, (i & 0x20) != 0 );
//                s.setBoolean( 8, (i & 0x40) != 0 );
//                s.executeUpdate();
//            }
//        }
//
//    }
//
//    /**
//     * Removes old schedules which have now finished
//     *
//     * @param con
//     * @throws SQLException
//     */
//    private void removeOldSchedules()
//            throws SQLException
//    {
//        try( Connection con = getConnection() )
//        {
//            con.setAutoCommit( false );
//            LOG.log( Level.INFO, "Removing old schedules" );
//            try( Statement s = con.createStatement() )
//            {
//                int c = s.executeUpdate(
//                        "DELETE FROM " + SCHEMA + ".schedule_loc"
//                        + " WHERE scheduleid IN ("
//                        + "SELECT id FROM timetable.schedule WHERE runsto < now()::DATE"
//                        + ")"
//                );
//                LOG.log( Level.INFO, "Removed {0} schedule locations", c );
//
//                c = s.executeUpdate( "DELETE FROM " + SCHEMA + ".schedule WHERE runsto < now()::DATE" );
//                LOG.log( Level.INFO, "Removed {0} schedules", c );
//                con.commit();
//            } catch( SQLException ex )
//            {
//                LOG.log( Level.SEVERE, "Failed to remove old schedules", ex );
//                con.rollback();
//                throw ex;
//            }
//
//        }
//    }
//
//    private class Parser
//            implements SQLBiConsumer<Connection, Path>
//    {
//
//        @Override
//        public void accept( Connection con, Path cifFile )
//                throws SQLException
//        {
//            Objects.requireNonNull( cifFile, "No CIF file provided" );
//
//            // Strict mode so we fail on an invalid record type
//            final CIFParser parser = new CIFParser( true );
//
//            final Supplier<String> exceptionLogger = () -> "Failed on line " + parser.lineCount()
//                    + " position " + parser.position()
//                    + "\n" + parser.currentLine( null );
//            try
//            {
//                // Do the import in one massive transaction
//                con.setAutoCommit( false );
//
//                // When did we last run an update
//                LocalDateTime lastUpdate = null;
//                try( Statement s = con.createStatement() )
//                {
//                    try( ResultSet rs = s.executeQuery(
//                            "SELECT extracted,imported FROM timetable.lastupdate ORDER BY extracted DESC LIMIT 1" ) )
//                    {
//                        if( rs != null && rs.next() )
//                        {
//                            lastUpdate = rs.getTimestamp( 1 ).
//                                    toLocalDateTime();
//                            LocalDateTime imported = rs.getTimestamp( 2 ).
//                                    toLocalDateTime();
//                            LOG.log( Level.INFO, "Last file extracted {0}, imported {1}", new Object[]
//                            {
//                                lastUpdate, imported
//                            } );
//                        }
//                    }
//                }
//
//                // Record when we did the update
//                Consumer<Header> header = h ->
//                {
//                    try( PreparedStatement ps = con.prepareStatement(
//                            "INSERT INTO timetable.lastupdate (extracted,imported,filename) VALUES (?,?,?)" ) )
//                    {
//                        ps.setTimestamp( 1, Timestamp.valueOf( h.getLocalDateTimeOfExtract() ) );
//                        ps.setTimestamp( 2, Timestamp.valueOf( TimeUtils.getLocalDateTime() ) );
//                        ps.setString( 3, h.getCurrentFileRef() );
//                        ps.executeUpdate();
//                    } catch( SQLException ex )
//                    {
//                        throw new UncheckedSQLException( ex );
//                    }
//                };
//
//                // The persistance consumers
//                Consumer<Schedule> schedules = Consumers.createIf(
//                        includeSchedules,
//                        () -> SQLConsumer.guard(
//                                new ScheduleDBUpdate( con ) ) );
//
//                Consumer<Schedule> scheduleLocations = Consumers.createIf(
//                        includeSchedules,
//                        () -> SQLConsumer.guard( new ScheduleLocUpdate( con ) ) );
//
//                Consumer<Schedule> scheduleConsumer = Consumers.createIf(
//                        includeSchedules,
//                        () -> Consumers.andThen( schedules,
//                                scheduleLocations ) );
//
//                Consumer<Association> associations = Consumers.createIf(
//                        includeAssociations,
//                        () -> SQLConsumer.guard( new AssociationDBUpdate( con ) ) );
//
//                TiplocDBUpdate tiplocDBUpdate = new TiplocDBUpdate( con );
//                Consumer<TIPLOCAction> tiplocs = Consumers.createIf(
//                        includeTiploc,
//                        () -> SQLConsumer.guard( tiplocDBUpdate ) );
//
//                // Now what to do at the end of the import
//                Consumer<TrailerRecord> trailer = t -> LOG.log( Level.INFO,
//                        () -> "Processed " + parser.lineCount() + " records." );
//
//                // Pick the type of builder - if not forming schedules or associations then there's no need to use
//                // the more expensive visitor
//                BasicRecordVisitor builder;
//                if( includeSchedules )
//                {
//                    builder = new ScheduleBuilderVisitor(
//                            header,
//                            tiplocs,
//                            associations,
//                            scheduleConsumer,
//                            trailer,
//                            lastUpdate );
//                } else
//                {
//                    // No schedules so no point in building Schedule objects
//                    builder = new BasicRecordVisitor(
//                            header,
//                            tiplocs,
//                            associations,
//                            trailer,
//                            lastUpdate );
//                }
//
//                // Progress counter for every 50k records
//                Consumer<? super Record> recCount = Consumers.ifThen(
//                        l -> (parser.lineCount() % 50000) == 0,
//                        l -> LOG.log( Level.INFO, () -> "read " + parser.lineCount() + " records." )
//                );
//
//                try( Stream<String> lines = Files.lines( cifFile ) )
//                {
//                    lines.map( parser::parse ).
//                            filter( Objects::nonNull ).
//                            peek( recCount ).
//                            forEach( r -> r.accept( builder ) );
//                }
//                
//                // Now we can delete any tiplocs, if this fails then theres a schedule still stuck
//                tiplocDBUpdate.deletePending();
//                
//            } catch( IOException ex )
//            {
//                LOG.log( Level.SEVERE, exceptionLogger );
//                throw new UncheckedIOException( ex );
//            } catch( SQLException ex )
//            {
//                LOG.log( Level.SEVERE, exceptionLogger );
//                throw new UncheckedSQLException( ex );
//            } catch( Exception ex )
//            {
//                LOG.log( Level.SEVERE, exceptionLogger );
//                throw new RuntimeException( ex );
//            }
//
//        }
//    }

}
