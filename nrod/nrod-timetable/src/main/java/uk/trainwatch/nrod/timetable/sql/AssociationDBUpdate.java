/*
 * Copyright 2014 Peter T Mount.
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
package uk.trainwatch.nrod.timetable.sql;

import uk.trainwatch.util.sql.CUDConsumer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import uk.trainwatch.nrod.timetable.cif.record.Association;
import uk.trainwatch.util.TimeUtils;

/**
 *
 * @author Peter T Mount
 */
public class AssociationDBUpdate
        extends CUDConsumer<Association>
{

    public AssociationDBUpdate( Connection con )
    {
        super( con,
                "INSERT INTO timetable.association"
                + " (mainuid,assocuid,startdt,enddt,assocdays,assoccat,assocdateind,tiploc,assoctype,baselocsuff,assoclocsuff,stpind)"
                + " VALUES (timetable.trainuid(?),timetable.trainuid(?),?,?,?,?,?,timetable.tiploc(?),?,?,?,?)",
                null,
                "DELETE FROM timetable.association"
                + " WHERE mainuid=timetable.trainuid(?) AND assocuid=timetable.trainuid(?)"
                + " AND startdt=? AND enddt=? AND assocdays=?"
                + " AND assoccat=? AND assocdateind=? AND tiploc=timetable.tiploc(?) AND assoctype=?"
        );
    }

    @Override
    public void accept( Association t )
            throws SQLException
    {
        totaled();

        PreparedStatement s = null;
        switch( t.getTransactionType() )
        {
            case NEW:
                s = getInsert();
                int i = setKey( t, s );
                setVal( t, s, i );
                s.executeUpdate();
                inserted();
                break;

            case DELETE:
                s = getDelete();
                setKey( t, s );
                s.executeUpdate();
                deleted();
                break;

            case REVISE:
                // Association's don't update, just create/delete
                break;
        }
    }

    private int setKey( Association t, PreparedStatement s )
            throws SQLException
    {
        int i = 1;
        s.setString( i++, t.getMainTrainUID() );
        s.setString( i++, t.getAssocTrainUID() );
        TimeUtils.setDate( s, i++, t.getStartDate() );
        TimeUtils.setDate( s, i++, t.getEndDate() );
        s.setInt( i++, t.getAssocDays().getDaysRunning() );
        s.setInt( i++, t.getAssociationCategory().ordinal() );
        s.setInt( i++, t.getAssocDateInd().ordinal() );
        s.setString( i++, t.getAssocLocation().getKey() );
        s.setInt( i++, t.getAssocType().ordinal() );
        return i;
    }

    private int setVal( Association t, PreparedStatement s, int i )
            throws SQLException
    {
        s.setString( i++, t.getBaseLocSuffix() );
        s.setString( i++, t.getAssocLocSuffix() );
        s.setInt( i++, t.getStpIndicator().ordinal() );
        return i;
    }
}
